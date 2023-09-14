import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
/**
 * Write a description of class Circle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Square extends Shape{
   private double length;

   public Square ( double ilength, Color ioutlineColor ,Color ifillColor,double ioutlineWidth, double iposx, double iposy ){
       this.length = ilength;
       this.outlineColor = ioutlineColor;
       this.fillColor = ifillColor;
       this.outlineWidth = ioutlineWidth;
       this.posx = iposx;
       this.posy = iposy;
  
   }  
   
   public void setLength ( double amount ){ this.length = amount; }
   
   public double getLength ( ) { return length;}
   
   public double area( ) { return length * length;}
   
   public String toString(){ return "Square length = " + length + "  at { " + posx + " , " + posy + " )";}
   @Override
    public boolean contains(double x, double y) {
        return x >= posx && x <= posx + length &&
               y >= posy && y <= posy + length;
    }
   @Override
   public void draw(GraphicsContext gc) {
    if (rightselected == true){
        gc.setStroke(Color.RED);
        gc.setLineWidth(outlineWidth + 2);
    } else {
        gc.setStroke(outlineColor);
        gc.setLineWidth(outlineWidth);
    }
       gc.setFill(fillColor);       
       gc.fillRect(posx, posy, length, length); // Draw the filled square
       gc.strokeRect(posx, posy, length, length); // Draw the outline
       
   }
}
