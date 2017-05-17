/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_menu;

import com.microcrowd.loader.java3d.max3ds.Loader3DS;
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
import javax.media.j3d.*;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

/**
 *
 * @author levan
 */
public class Area extends Shape3D{

    	Vector3d vec = new Vector3d();
	Transform3D t3d = new Transform3D( );
        private TransformGroup trans ;
        private BranchGroup bg;
        private CollisionDetector be ;

    public CollisionDetector getBe() {
        return be;
    }

    public void setBe(CollisionDetector be) {
        this.be = be;
    }

    public TransformGroup getTrans() {
        return trans;
    }

    public void setTrans(TransformGroup trans) {
        this.trans = trans;
    }

    Area(Appearance app,double x, double y, double z, String housename) {
        super(null,app);
        this.setGeometry(createGeometry());
        this.bg = new BranchGroup();
        int flags = ObjectFile.RESIZE;
	flags |= ObjectFile.TRIANGULATE;
        flags |= ObjectFile.STRIPIFY;   
        ObjectFile f = new ObjectFile(flags, 
	(float)(1.0 * Math.PI / 180.0));
        Loader3DS loader = new Loader3DS();
        Scene s = null;
	try {
	  s = f.load(Resources.getResource("resources/houses/"+housename+"/"+housename+".obj"));
	}
	catch (     FileNotFoundException | ParsingErrorException | IncorrectFormatException e) {
	  System.err.println(e);
	  System.exit(1);
	}
        
        vec.set( x, y, z );
        t3d.setTranslation( vec );
       
        t3d.setScale(1.5);
        //t3d.setRotation(new AxisAngle4f(1.0f, 1.0f, 10.0f, 1f));
        trans = new TransformGroup( t3d );
        trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        trans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        trans.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
        
        trans.addChild( s.getSceneGroup());
        
        this.trans.setCollidable(true);
        //Box cube = new Box(1.0,1.0,1.0);
        
        //Appearance ap = cube.getAppearance();
	//ColoringAttributes ca = new ColoringAttributes();
	//ca.setColor(0.6f, 0.3f, 0.0f);
	//ap.setCapability(ap.ALLOW_COLORING_ATTRIBUTES_WRITE);
	//ap.setColoringAttributes(ca);
        //be = new CollisionDetector(cube);

        //this.trans.addChild(cube);
        this.bg.setCollidable(true);
      //  this.bg.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
    //    this.bg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        this.bg.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        this.bg.setCapability(BranchGroup.ALLOW_DETACH);
        this.bg.setName("hello tu");
        this.bg.addChild(trans);
        
       
        
        
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
 float x = (i - m/2)*0.02f;
 float z = (j - n/2)*0.02f;
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
