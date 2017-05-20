/*
 * $RCSfile: PickHighlightBehavior.java,v $
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
 *   the documentation and/or other materials provided with the
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
 * $Revision: 1.2 $
 * $Date: 2007/02/09 17:21:48 $
 * $State: Exp $
 */

package main_menu;

import javax.media.j3d.*;
import com.sun.j3d.utils.picking.PickTool;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.picking.behaviors.PickMouseBehavior;
import java.util.ArrayList;
import javax.vecmath.*;

public class PickHighlightBehavior extends PickMouseBehavior {
  Appearance savedAppearance = null;
  Shape3D oldShape = null;
  Appearance highlightAppearance;
  private objectmenu menu;
  private TransformGroup trans;
  private  BranchGroup root;
    private  TransformGroup parrent;
    private Canvas3D c;    
    private Bounds b;
    private final Transform3D t3d;
    ArrayList<Shape3D> Shapes;
    Area a;

  public PickHighlightBehavior(Canvas3D canvas, BranchGroup root, Transform3D t3d, TransformGroup trans,          
			       Bounds bounds, TransformGroup parrent,ArrayList<Shape3D> Shapes, Area a) {
      super(canvas, root, bounds);
      this.parrent = parrent;
      this.t3d = t3d;
      this.Shapes = Shapes;
      this.a = a;
      this.c = canvas;
      this.b = bounds;
      this.trans = trans;
      this.root = root;
      this.setSchedulingBounds(bounds);
    
      Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
      Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
      Color3f highlightColor = new Color3f(0.0f, 1.0f, 0.0f);
      Material highlightMaterial = new Material(highlightColor, black,
						highlightColor, white,
						80.0f);
      highlightAppearance = new Appearance();
      highlightAppearance.setMaterial(new Material(highlightColor, black,
						   highlightColor, white,
						   80.0f));

      pickCanvas.setMode(PickTool.GEOMETRY);
      
  }

   
    
  @Override
    public void updateScene(int xpos, int ypos) {
	PickResult pickResult = null;
	Shape3D shape = null;

	pickCanvas.setShapeLocation(xpos, ypos);

	pickResult = pickCanvas.pickClosest();
        
        //Shift Adjustment
	if (pickResult != null) {
          //  shape = (Shape3D) pickResult.getNode(PickResult.SHAPE3D);
            menu = new objectmenu(null, true);
            menu.setLocation(100,100);
            if (!mevent.isShiftDown())
                menu.setVisible(true);
        if (menu.getAction().endsWith("remove")){
            this.parrent.removeChild(root);
            this.Shapes.remove(a);
        }
        if (menu.getAction().equals("Rotate")){
        Transform3D step = new Transform3D();
        step.set(new Vector3d(0,0,0));
        step.rotY(Math.PI/4);
        this.t3d.mul(step);
        this.trans.setTransform(t3d);
        this.a.direc= ++this.a.direc%4;
        }
        if (menu.getAction().equals("Left")){
        Transform3D step = new Transform3D();
        step.set(new Vector3d(0.3,0,0));        
        this.t3d.mul(step);
        this.trans.setTransform(t3d);
        }
        if (menu.getAction().equals("Right")){
        Transform3D step = new Transform3D();
        step.set(new Vector3d(-0.3,0,0));        
        this.t3d.mul(step);
        this.trans.setTransform(t3d);
        }
        if (menu.getAction().equals("Up")){
        Transform3D step = new Transform3D();
        step.set(new Vector3d(0,0,0.3));        
        this.t3d.mul(step);
        this.trans.setTransform(t3d);
        }
        if (menu.getAction().equals("Down")){
        Transform3D step = new Transform3D();
        step.set(new Vector3d(0,0,-0.3));        
        this.t3d.mul(step);
        this.trans.setTransform(t3d);
        }
        if (menu.getAction().equals("Replace")){
      
        this.parrent.removeChild(root);
        this.Shapes.remove(a);
        Area area = new Area (null, this.t3d, menu.getNewh(),this.c, (BoundingSphere) this.b, this.parrent,this.Shapes ); 
        this.parrent.addChild(area.getBg());
        this.Shapes.add(area);
        
        }
          //  this.parrent.removeChild(root);
            
//            Area area = new Area (null, this.t3d, menu.getAction());        
//        PickHighlightBehavior pickBeh = new 
//            PickHighlightBehavior(this.iWorld.getCanvas(), area.getBg(), area.t3d,
//                    area.getTrans(), this.iWorld.getBoundsj3d(), this.iWorld.getObjTrans());
//            
       
	}

	if (oldShape != null){
	    oldShape.setAppearance(savedAppearance);
	}
	if (shape != null) {
	    savedAppearance = shape.getAppearance();
	    oldShape = shape;
           
	    
	}
    }
}
