import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class Ellipse extends Shape {
    private double radiusx;
    private double radiusy;

    public Ellipse(double iwidth, double iheight, Color ioutlineColor, Color ifillColor, double ioutlineWidth, double iposx, double iposy) {
        this.radiusx = iwidth;
        this.radiusy = iheight;
        this.outlineColor = ioutlineColor;
        this.fillColor = ifillColor;
        this.outlineWidth = ioutlineWidth;
        this.posx = iposx;
        this.posy = iposy;
    }

    public void setRadius(double iradiusx, double iradiusy) {
        this.radiusx = iradiusx;
        this.radiusy = iradiusy;
    }

    public double getRadiusX() {
        return radiusx;
    }

    public double getRadiusY() {
        return radiusy;
    }

    public double area() {
        return Math.PI * radiusx * radiusy;
    }

    public double perimeter() {
        // An approximation of ellipse perimeter
        return Math.PI * (3 * (radiusx + radiusy) - Math.sqrt((3 * radiusx + radiusy) * (radiusx + 3 * radiusy)));
    }

    public boolean equals(Ellipse e) {
        return this.radiusx == e.radiusx && this.radiusy == e.radiusy;
    }

    @Override
    public boolean contains(double x, double y) {
        // Check if the point (x, y) is within the bounds of the ellipse
        double centerX = posx + radiusx;
        double centerY = posy + radiusy;
        double normalizedX = (x - centerX) / radiusx;
        double normalizedY = (y - centerY) / radiusy;
        return (normalizedX * normalizedX + normalizedY * normalizedY) <= 1;
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (rightselected) {
            gc.setStroke(Color.RED);
            gc.setLineWidth(outlineWidth + 2);
        } else {
            gc.setStroke(outlineColor);
            gc.setLineWidth(outlineWidth);
        }
        gc.setFill(fillColor);

        // Draw the filled ellipse
        gc.fillOval(posx, posy, radiusx * 2, radiusy * 2);

        // Draw the outline
        gc.strokeOval(posx, posy, radiusx * 2, radiusy * 2);
    }
}
