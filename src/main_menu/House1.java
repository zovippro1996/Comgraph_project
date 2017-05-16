/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_menu;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

/**
 *
 * @author levan
 */
public class House1 extends Shape3D{

    	Vector3d vec = new Vector3d( );
	Transform3D t3d = new Transform3D( );
        TransformGroup trans ;

    House1(Appearance app,double x, double y, double z) {
        super(null,app);
        int flags = ObjectFile.RESIZE;
	flags |= ObjectFile.TRIANGULATE;
        flags |= ObjectFile.STRIPIFY;   
        ObjectFile f = new ObjectFile(flags, 
	(float)(1.0 * Math.PI / 180.0));
        
        Scene s = null;
	try {
	  s = f.load(Resources.getResource("resources/geometry/house2.obj"));
	}
	catch (     FileNotFoundException | ParsingErrorException | IncorrectFormatException e) {
	  System.err.println(e);
	  System.exit(1);
	}
        vec.set( x, y, z );
        Vector3d r;
                r = new Vector3d(1f,1f,1.2f);
        t3d.setScale(r);
        t3d.setTranslation( vec );
        trans = new TransformGroup( t3d );
        trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        trans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        BranchGroup sh = s.getSceneGroup();
       
        trans.addChild( sh );
        
//To change body of generated methods, choose Tools | Templates.
    }
    
}
