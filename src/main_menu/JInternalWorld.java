/*
 * $RCSfile: JInternalWorld.java,v $
 *
 * Copyright (c) 2007 Sun Microsystems, Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistribution of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the  documentation and/or other materials provided with the
 *   distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN") AND ITS LICENSORS SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR
 * ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL,
 * CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND
 * REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF OR
 * INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or
 * intended for use in the design, construction, operation or
 * maintenance of any nuclear facility.
 *
 * $Revision: 1.6 $
 * $Date: 2007/02/09 17:21:42 $
 * $State: Exp $
 */

package main_menu;

import com.sun.j3d.utils.picking.behaviors.PickRotateBehavior;
import com.sun.j3d.utils.picking.behaviors.PickTranslateBehavior;
import com.sun.j3d.utils.picking.behaviors.PickZoomBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

import javax.swing.JInternalFrame;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;

import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;



public class JInternalWorld extends JInternalFrame {
    /** DOCUMENT ME! */
    Shape3D shapeIsSelected;
    Shape3D []shapeList;
    private final Component comp;
    private SimpleUniverse universe;
    private BranchGroup scene;
    private Canvas3D canvas ;
    private BoundingSphere bounds;

    /**
     *
     * @return
     */
    public BoundingSphere getBoundsj3d() {
        return bounds;
    }

    public void setBounds(BoundingSphere bounds) {
        this.bounds = bounds;
    }

    public Canvas3D getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas3D canvas) {
        this.canvas = canvas;
    }
    private Appearance a ;
    private TransformGroup objTrans ;

    public TransformGroup getObjTrans() {
        return objTrans;
    }

    public void setObjTrans(TransformGroup objTrans) {
        this.objTrans = objTrans;
    }

    public Appearance getA() {
        return a;
    }

    public void setA(Appearance a) {
        this.a = a;
    }

    public SimpleUniverse getUniverse() {
        return universe;
    }

    public void setUniverse(SimpleUniverse universe) {
        this.universe = universe;
    }

    public BranchGroup getScene() {
        return scene;
    }

    public void setScene(BranchGroup scene) {
        this.scene = scene;
    }
    /**
     * Creates a new JInternalWorld object.
     *
     * @param isInteractive tells the world to be constructed as interactive
     * @param isDelayed tells the rotator to start at a random alpha.
     * @param isRandom
     */
    public JInternalWorld(boolean isInteractive, boolean isDelayed, boolean isRandom) {
        super();
        setSize(600, 600);
        setClosable(true);

        canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        
        comp = canvas;

        Dimension dim = new Dimension(512, 512);
        comp.setPreferredSize(dim);
        comp.setSize(dim);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(comp, BorderLayout.CENTER);
        pack();

        // Create a simple scene and attach it to the virtual universe
        this.universe = new SimpleUniverse(canvas); 
//TODO: this is awful and must not be done like that in final version
        this.scene = createSceneGraph(isInteractive, isRandom,canvas);

        // This will move the ViewPlatform back a bit so the
        // objects in the scene can be viewed.
        this.universe.getViewingPlatform().setNominalViewingTransform();
        this.universe.getViewer().getView().setMinimumFrameCycleTime(30);
        this.universe.addBranchGraph(scene);
    }

    /**
     * Creates the world. Only exists to cleanup the source a bit
     *
     * @param isInteractive tells the world to be constructed as interactive
     * @param isDelayed tells the rotator to start at a random alpha.
     *
     * @return a global branchgroup containing the world, as desired.
     */
    private BranchGroup createSceneGraph(boolean isInteractive, boolean isRandom){
       return createSceneGraph(isInteractive,isRandom,null);
    }
    private BranchGroup createSceneGraph(boolean isInteractive, boolean isRandom, Canvas3D c) {
        BranchGroup objRoot = new BranchGroup(); 
        objRoot.setCapability(BranchGroup.ALLOW_DETACH);
        objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        objTrans = new TransformGroup();
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objTrans.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
        objTrans.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        objTrans.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        objTrans.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);

        
        Transform3D t3dTrans = new Transform3D();
        t3dTrans.setScale(0.5);
        t3dTrans.setTranslation(new Vector3d(1, 0, -5));
        t3dTrans.setRotation(new AxisAngle4f(10.0f, 1.0f, 1.0f, 1f));
        objTrans.setTransform(t3dTrans);
        objRoot.addChild(objTrans);
        

        // Create a simple Shape3D node; add it to the scene graph.
        // issue 383: changed the cube to a text, so that any graphical problem related to Yup can be seen.
        
        Color3f eColor    = new Color3f(0.0f, 0.0f, 0.0f);
    Color3f sColor    = new Color3f(1.0f, 1.0f, 1.0f);
    Color3f objColor  = new Color3f(0.6f, 0.6f, 0.6f);
    
        Material m = new Material(objColor, eColor, objColor, sColor, 100.0f);
    a = new Appearance();
    a.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
    m.setLightingEnable(true);
    a.setMaterial(m);
            
        ObjectsArray oa = new ObjectsArray(5,5, a);
                             
        bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
                100.0);
        Color3f bgColor = new Color3f(1.0f, 1.0f, 1.0f);
        Background bgNode = new Background(bgColor);
        bgNode.setApplicationBounds(bounds);
        oa.Apply(objTrans,c,bounds);
        
        
        objRoot.addChild(bgNode);
	// Set up the ambient light
	Color3f ambientColor = new Color3f(0.3f, 0.3f, 0.3f);
	AmbientLight ambientLightNode = new AmbientLight(ambientColor);
	ambientLightNode.setInfluencingBounds(bounds);
	objRoot.addChild(ambientLightNode);
	
	// Set up the directional lights
	Color3f light1Color = new Color3f(1.0f, 1.0f, 0.9f);
	Vector3f light1Direction  = new Vector3f(0.0f, 1.0f, 0.6f);
	Color3f light2Color = new Color3f(1.0f, 1.0f, 0.9f);
	Vector3f light2Direction  = new Vector3f(-1.0f, -1.0f, -1.0f);
	
	DirectionalLight light1
	    = new DirectionalLight(light1Color, light2Direction);
	light1.setInfluencingBounds(bounds);
	objRoot.addChild(light1);
	
//	DirectionalLight light2
//	    = new DirectionalLight(light2Color, light2Direction);
//	light2.setInfluencingBounds(bounds);
//	objRoot.addChild(light2);


        
        
//        PointLight ptlight = new PointLight(new Color3f(Color.gray),
//        new Point3f(0.7f,0.8f,0.5f), new Point3f(0.2f,0.3f,0.5f));
//        ptlight.setInfluencingBounds(bounds);
//        objRoot.addChild(ptlight);
        
        
        
      //  oa.Apply(objRoot, c, bounds);

      
//      MouseRotate mr = new MouseRotate(comp, objTrans);
//            mr.setSchedulingBounds(bounds);
//            mr.setSchedulingInterval(1);
//            objRoot.addChild(mr);
    objTrans.addChild(new SphereGroup());
    PickRotateBehavior rotate = new PickRotateBehavior(objRoot,c, bounds);
    PickZoomBehavior zoom = new PickZoomBehavior(objRoot,c, bounds);
    PickTranslateBehavior translte = new PickTranslateBehavior(objRoot,c, bounds);
    
    objRoot.addChild(translte);
    objRoot.addChild(zoom);  
    objRoot.addChild(rotate);
            
            
        return objRoot;
    }


}
