import java.awt.*;
import java.awt.geom.*;

public class Flag implements Shape {
    private final int x;
    private final int y;
    private final int height;
    private final int width;
    private final int pivot;

    public Flag(int x, int y, int width, int height, int pivot) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.pivot = pivot;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height + pivot);
    }

    @Override
    public Rectangle2D getBounds2D() {
        return getBounds();
    }

    @Override
    public boolean contains(double x, double y) {
            double t = (x - this.x) / width;
            return x > this.x &&
                    x < (this.x + width) &&
                    y < Math.pow((1 - t), 2) * this.y + 2 * (1 - t) * t * this.y + Math.pow(t, 2) * (this.y + pivot) &&
                    y > Math.pow((1 - t), 2) * (this.y + height) + 2 * (1 - t) * t * (this.y + height) + Math.pow(t, 2) * (this.y + height + pivot);
    }

    @Override
    public boolean contains(Point2D p) {
        return contains(p.getX(), p.getY());
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return contains(x,y) || contains(w, y) || contains(w, h) || contains(w, y);
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return intersects(r.getX(),r.getY(), r.getMaxX(), r.getMaxY());
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return contains(x,y) && contains(w, y) && contains(w, h) && contains(w, y);
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return contains(r.getX(),r.getY(), r.getMaxX(), r.getMaxY());
    }
    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return new PathIterator() {
            @Override
            public int getWindingRule() {
                return 0;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public void next() {

            }

            @Override
            public int currentSegment(float[] coords) {
                return 0;
            }

            @Override
            public int currentSegment(double[] coords) {
                return 0;
            }
        };
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return null;
    }
}
