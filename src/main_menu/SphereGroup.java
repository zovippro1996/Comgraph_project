package main_menu;

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;

public class SphereGroup
        extends Group {

    Shape3D[] shapes;
    int numShapes = 0;
    int shapeIsSelected = 0;

    //  Constructors
    public SphereGroup() {
        //    x,y spacing   x,y count  appearance
        this(2.85f, 2.85f, 10, 10, null, false);
    }

    public SphereGroup(Appearance app) {
        //    radius   x,y spacing   x,y count  appearance
        this(2.85f, 2.85f, 10, 10, app, false);
    }

    public SphereGroup(float xSpacing, float ySpacing,
            int xCount, int yCount, boolean overrideflag) {
        this(xSpacing, ySpacing, xCount, yCount, null, overrideflag);
    }

    public SphereGroup(float xSpacing, float ySpacing,
            int xCount, int yCount, Appearance app, boolean overrideflag) {
        if (app == null) {
            app = new Appearance();
            Material material = new Material();
            material.setDiffuseColor(new Color3f(0.0f, 0.2f, 0.1f));
            material.setSpecularColor(new Color3f(0.0f, 0.2f, 0.1f));
            material.setShininess(0.3f);
            app.setMaterial(material);
        }

        TransformGroup spinTg = new TransformGroup();
        spinTg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        spinTg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        double xStart = 0;
        double yStart = 0;
        double x, z = yStart, y = 0;
        shapes = new Shape3D[xCount * yCount];
        for (int i = 0; i < yCount; i++) {

            x = xStart;
            for (int j = 0; j < xCount; j++) {
                DefautArea area = new DefautArea(app, x, y, z);
                area.setName("Area " + Integer.toString(i) + ", " + Integer.toString(j));
                x += xSpacing;

                spinTg.addChild(area.getTrans());

            }
            z += ySpacing;
        }
        addChild(spinTg);

    }

    public Shape3D getSelected() {
        return getShape(shapeIsSelected);
    }

    private Shape3D getShape(int index) {
        return shapes[index];
    }

    Shape3D[] getShapes() {
        return shapes;
    }

    private Geometry createGeometry() {
        int m = 40;
        int n = 40;
        Point3f[] pts = new Point3f[m * n];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                float x = (i - m / 2) * 0.2f;
                float z = (j - n / 2) * 0.2f;
                float y = 0.4f * (float) (Math.cos(x) * Math.sin(z))
                        / ((float) Math.exp(0.5 * (x * x + z * z))) - 1.0f;
                pts[idx++] = new Point3f(x, y, z);
            }
        }

        int[] coords = new int[2 * n * (m - 1)];
        idx = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                coords[idx++] = i * n + j;
                coords[idx++] = (i - 1) * n + j;
            }
        }
        int[] stripCounts = new int[m - 1];
        for (int i = 0; i < m - 1; i++) {
            stripCounts[i] = 2 * n;
        }

        GeometryInfo gi = new GeometryInfo(GeometryInfo.TRIANGLE_STRIP_ARRAY);
        gi.setCoordinates(pts);
        gi.setCoordinateIndices(coords);
        gi.setStripCounts(stripCounts);

        NormalGenerator ng = new NormalGenerator();
        ng.generateNormals(gi);

        return gi.getGeometryArray();
    }
}
