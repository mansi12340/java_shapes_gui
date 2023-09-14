import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

/**
 * Write a description of class Shape here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Shape{
   public Color fillColor;
   public Color outlineColor;
   public double outlineWidth;
   public boolean rightselected = false;

   public double posx;
   public double posy;

   public void rightSelectionToggleOn(){
      rightselected = true;
   }
   public void rightSelectionToggleOff(){
      rightselected = false;
   }

   public double getX() {return posx;}

   public void setX(double iposx) {this.posx = iposx;}

   public double getY() { return posy;}

   public void setY(double iposy) { this.posy = iposy; }

   public Color getOutlineColor() { return outlineColor; }
  
   public void setOutlineColor(Color outlineColor) { this.outlineColor = outlineColor;}
  
  public Color getFillColor() { return fillColor; }
  
  public void setFillColor(Color fillColor) {
      this.fillColor = fillColor;
  }
  public abstract boolean contains(double x, double y);
   public abstract void draw(GraphicsContext gc);
}
