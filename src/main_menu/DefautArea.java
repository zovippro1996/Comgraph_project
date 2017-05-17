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
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import java.io.FileNotFoundException;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Geometry;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

/**
 *
 * @author levan
 */
public class DefautArea extends Shape3D{

    	Vector3d vec = new Vector3d( );
	Transform3D t3d = new Transform3D( );
        private TransformGroup trans ;
        private BranchGroup bg;

    public TransformGroup getTrans() {
        return trans;
    }

    public void setTrans(TransformGroup trans) {
        this.trans = trans;
    }

    DefautArea(Appearance app,double x, double y, double z) {
        super(null,app);
        this.setGeometry(createGeometry());
        this.bg = new BranchGroup();
        int flags = ObjectFile.RESIZE;
	flags |= ObjectFile.TRIANGULATE;
        flags |= ObjectFile.STRIPIFY;   
        ObjectFile f = new ObjectFile(flags, 
	(float)(1.0 * Math.PI / 180.0));
        
        Scene s = null;
	try {
	  s = f.load(Resources.getResource("resources/geometry/TheCity.obj"));
	}
	catch (     FileNotFoundException | ParsingErrorException | IncorrectFormatException e) {
	  System.err.println(e);
	  System.exit(1);
	}
        
        vec.set( x, y, z );
        t3d.setTranslation( vec );
       
        t3d.setScale(1);
        //t3d.setRotation(new AxisAngle4f(1.0f, 1.0f, 10.0f, 1f));
        trans = new TransformGroup( t3d );               
        trans.addChild( this);
        
        
       
        
        
//To change body of generated methods, choose Tools | Templates.
    }

    public BranchGroup getBg() {
        return bg;
    }

    public void setBg(BranchGroup bg) {
        this.bg = bg;
    }
private Geometry createGeometry() {
 int m = 40;
 int n = 40;
 Point3f[] pts = new Point3f[m*n];
 int idx = 0;
 for (int i = 0; i < m; i++) {
 for (int j = 0; j < n; j++) {
 float x = (i - m/2)*0.1f;
 float z = (j - n/2)*0.1f;
 float y = 0.1f * (float)(Math.cos(x) * Math.sin(z))/
 ((float)Math.exp(0.25*(x*x+z*z)))-1.0f;
 pts[idx++] = new Point3f(x, y, z);
 }
 }

 int[] coords = new int[2*n*(m-1)];
 idx = 0;
 for (int i = 1; i < m; i++) {
 for (int j = 0; j < n; j++) {
 coords[idx++] = i*n + j;
 coords[idx++] = (i-1)*n + j;
 }
 }
 int[] stripCounts = new int[m-1];
 for (int i = 0; i < m-1; i++) stripCounts[i] = 2*n;

 GeometryInfo gi = new GeometryInfo
 (GeometryInfo.TRIANGLE_STRIP_ARRAY);
 gi.setCoordinates(pts);
 gi.setCoordinateIndices(coords);
 gi.setStripCounts(stripCounts);

 NormalGenerator ng = new NormalGenerator();
 ng.generateNormals(gi);

 return gi.getGeometryArray();
 }    
}
