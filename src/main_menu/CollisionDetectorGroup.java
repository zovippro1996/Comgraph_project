/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_menu;

import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnCollisionEntry;
import javax.media.j3d.WakeupOnCollisionExit;
import javax.vecmath.Vector3d;

/**
 *
 * @author levan
 */
public class CollisionDetectorGroup extends Behavior {

    private boolean inCollision = false;
    private final TransformGroup group;

    private WakeupOnCollisionEntry wEnter;
    private WakeupOnCollisionExit wExit;
    private Transform3D t3d;
    private TransformGroup updateto;

    public CollisionDetectorGroup(TransformGroup gp, Transform3D t3d, TransformGroup updateto) { // Corrected: gp
        group = gp; // Corrected: gp
        inCollision = false;
        this.t3d = t3d;
        this.updateto = updateto;

    }

    public void initialize() {
        wEnter = new WakeupOnCollisionEntry(group);
        wExit = new WakeupOnCollisionExit(group);
        wakeupOn(wEnter);
    }

    public void processStimulus(Enumeration criteria) {
        inCollision = !inCollision;

        if (inCollision) {
            System.out.println("hiihihihihih");
            Transform3D step = new Transform3D();
            step.set(new Vector3d(0.5, 0, 0));
            this.t3d.mul(step);
            this.updateto.setTransform(t3d);

            wakeupOn(wExit);
        } else {
            System.out.println("~~~~");
            wakeupOn(wEnter);

        }

    }
}
