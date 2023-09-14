import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
/**
 * Write a description of class Circle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rectangle extends Shape{
   private double height;
   private double width;

   public Rectangle ( double iwidth, double iheight, Color ioutlineColor ,Color ifillColor,double ioutlineWidth, double iposx, double iposy ){
       this.width = iwidth;
       this.height = iheight;
       this.outlineColor = ioutlineColor;
       this.fillColor = ifillColor;
       this.outlineWidth = ioutlineWidth;
       this.posx = iposx;
       this.posy = iposy;
   }  
   
   
   public void setWidth ( double amount ){ this.width = amount; }
   
   public void setheight ( double amount ) { this.height = amount; }
   
   public double getWidth ( ) { return width; }
    
   public double getheight ( ) { return height; }
   
   public double area( ) {
       return height * width;
   }

   public String toString()
   {
       return "Rectangle width = " + width + " height = " + height + "  at { " + posx + " , " + posy + " )";
   }
   @Override
   public boolean contains(double x, double y) {
       return x >= posx && x <= posx + width &&
              y >= posy && y <= posy + height;
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
       
       gc.fillRect(posx, posy, width, height); // Draw the filled rectangle
       gc.strokeRect(posx, posy, width, height); // Draw the outline
       
   }   
}
