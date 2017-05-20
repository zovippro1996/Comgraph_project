package main_menu;

import javax.media.j3d.*;
import com.sun.j3d.utils.picking.PickTool;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.picking.behaviors.PickMouseBehavior;
import java.util.ArrayList;
import javax.vecmath.*;

public class PickHighlightBehavior extends PickMouseBehavior {

    //Global Attribute for 1 3D Object
    Appearance savedAppearance = null;
    Shape3D oldShape = null;
    Appearance highlightAppearance;
    private objectmenu menu;
    private TransformGroup trans;
    private BranchGroup root;
    private TransformGroup parrent;
    private Canvas3D c;
    private Bounds b;
    private final Transform3D t3d;
    ArrayList<Shape3D> Shapes;
    Area a;

    //Constructor
    public PickHighlightBehavior(Canvas3D canvas, BranchGroup root, Transform3D t3d, TransformGroup trans,
            Bounds bounds, TransformGroup parrent, ArrayList<Shape3D> Shapes, Area a) {
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
            menu.setLocation(100, 100);
            if (!mevent.isControlDown()) {
                menu.setVisible(true);
            }
            if (menu.getAction().endsWith("remove")) {
                this.parrent.removeChild(root);
                this.Shapes.remove(a);
            }
            if (menu.getAction().equals("Rotate")) {
                Transform3D step = new Transform3D();
                step.set(new Vector3d(0, 0, 0));
                step.rotY(Math.PI / 4);
                this.t3d.mul(step);
                this.trans.setTransform(t3d);
                this.a.direc = ++this.a.direc % 4;
            }
            if (menu.getAction().equals("Left")) {
                Transform3D step = new Transform3D();
                step.set(new Vector3d(0.3, 0, 0));
                this.t3d.mul(step);
                this.trans.setTransform(t3d);
            }
            if (menu.getAction().equals("Right")) {
                Transform3D step = new Transform3D();
                step.set(new Vector3d(-0.3, 0, 0));
                this.t3d.mul(step);
                this.trans.setTransform(t3d);
            }
            if (menu.getAction().equals("Up")) {
                Transform3D step = new Transform3D();
                step.set(new Vector3d(0, 0, 0.3));
                this.t3d.mul(step);
                this.trans.setTransform(t3d);
            }
            if (menu.getAction().equals("Down")) {
                Transform3D step = new Transform3D();
                step.set(new Vector3d(0, 0, -0.3));
                this.t3d.mul(step);
                this.trans.setTransform(t3d);
            }
            if (menu.getAction().equals("Replace")) {

                this.parrent.removeChild(root);
                this.Shapes.remove(a);
                Area area = new Area(null, this.t3d, menu.getNewh(), this.c, (BoundingSphere) this.b, this.parrent, this.Shapes);
                this.parrent.addChild(area.getBg());
                this.Shapes.add(area);

            }

        }

        if (oldShape != null) {
            oldShape.setAppearance(savedAppearance);
        }
        if (shape != null) {
            savedAppearance = shape.getAppearance();
            oldShape = shape;

        }
    }
}
