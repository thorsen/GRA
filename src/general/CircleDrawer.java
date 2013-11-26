/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package general;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import org.jfree.ui.Drawable;

/**
 *
 * @author Victor Fernandez
 */
public class CircleDrawer implements Drawable {
    private Paint outlinePaint;
    private Stroke outlineStroke;
    private Paint fillPaint;

    public CircleDrawer(Paint paint, Stroke stroke, Paint paint1) {
        this.outlinePaint = paint;
        this.outlineStroke = stroke;
        this.fillPaint = paint1;
        
    }

    public void draw(Graphics2D graphics2d, Rectangle2D rectangle2d) {
        java.awt.geom.Ellipse2D.Double double1 = new java.awt.geom.Ellipse2D.Double(rectangle2d.getX(), rectangle2d.getY(), rectangle2d.getWidth(), rectangle2d.getHeight());
        if (this.fillPaint != null) {
            graphics2d.setPaint(this.fillPaint);
            graphics2d.fill(double1);
        }
        if (this.outlinePaint != null && this.outlineStroke != null) {
            graphics2d.setPaint(this.outlinePaint);
            graphics2d.setStroke(this.outlineStroke);
            graphics2d.draw(double1);
        }
    }
}