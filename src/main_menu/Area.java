/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_menu;

import javax.media.j3d.Appearance;
import javax.media.j3d.Geometry;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

/**
 *
 * @author levan
 */
public class Area extends Shape3D{

    	Vector3d vec = new Vector3d( );
	Transform3D t3d = new Transform3D( );
        TransformGroup trans ;

    Area(Geometry createGeometry, Appearance app) {
        super(createGeometry,app);


//To change body of generated methods, choose Tools | Templates.
    }
    
}
