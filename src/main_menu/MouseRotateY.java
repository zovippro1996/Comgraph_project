package main_menu;

import java.awt.*;
import java.util.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.picking.behaviors.PickRotateBehavior;

/**
 * MouseRotateY is a Java3D behavior object that lets users control the 
 * rotation of an object via a mouse.
 * <p>
 * To use this utility, first create a transform group that this 
 * rotate behavior will operate on. Then,
 *<blockquote><pre>
 * 
 *   MouseRotateY behavior = new MouseRotateY();
 *   behavior.setTransformGroup(objTrans);
 *   objTrans.addChild(behavior);
 *   behavior.setSchedulingBounds(bounds);
 *
 *</pre></blockquote>
 */

public class MouseRotateY extends PickRotateBehavior {
  double  y_angle;
  double  y_factor;

  /**
   * Creates a rotate behavior given the transform group.
     * @param Group
     * @param c
     * @param b
   */
  public MouseRotateY(BranchGroup Group, Canvas3D c, BoundingSphere b) {
    super(Group,c,b);
  }


 
  @Override
  public void initialize() {
    super.initialize();
    y_angle = 0;
    y_factor = .03;
    
  }

  public double getYFactor() {
    return y_factor;
  }
  
  public void setFactor( double factor) {
    y_factor = factor;
    
  }
  

  @Override
  public void processStimulus (Enumeration criteria) {
      WakeupCriterion wakeup;
      AWTEvent[] event;
      int id;
      int  dx;

  }
}
