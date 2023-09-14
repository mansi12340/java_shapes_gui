import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
/**
 * Write a description of class Circle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Circle extends Shape{
   private double radius;
   
   
   private final double PI = 3.14159;

   public Circle ( double iradius,Color ioutlineColor ,Color ifillColor,double ioutlineWidth, double iposx, double iposy ){
       this.radius = iradius;
       this.outlineColor = ioutlineColor;
       this.fillColor = ifillColor;
       this.outlineWidth = ioutlineWidth;
       this.posx = iposx;
       this.posy = iposy;
   }

    public void setRadius (double amount ) { this.radius = amount;}
   
   public double getRadius ( ) { return radius; }
   
   public double area( ) { return PI * radius * radius; }
   
   public double circumference( ) { return 2 * PI * radius;}
   
   public boolean equals (Circle c) {
       if (this.radius == c.radius)
            return true;
       return false;
   }
   public String toString(){ return "Circle radius = " + radius + "  at { " + posx + " , " + posy + " )"; }
   @Override
    public boolean contains(double x, double y) {
        // Check if the point (x, y) is within the bounds of the circle
        double centerX = posx + radius;
        double centerY = posy + radius;
        double distanceSquared = (x - centerX) * (x - centerX) + (y - centerY) * (y - centerY);
        return distanceSquared <= radius * radius;
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
        
        // Draw the filled circle
        gc.fillOval(posx, posy, radius * 2, radius * 2);

        // Draw the outline
        gc.strokeOval(posx, posy, radius * 2, radius * 2);   
    }
}
