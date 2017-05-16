/*
 * $RCSfile: MouseRotateY.java,v $
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
 * $Date: 2007/02/09 17:21:46 $
 * $State: Exp $
 */

package main_menu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.mouse.*;
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
 * The above code will add the rotate behavior to the transform
 * group. The user can rotate any object attached to the objTrans.
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
