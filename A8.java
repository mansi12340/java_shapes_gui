import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
// import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;

// fix slection bugs

/**
 * ???
 *
 * @author YOUR NAME
 */
public class A8 extends Application {

    // TODO: Instance Variables for View Components and Model
    private final static int MAXROWS = 600;
    private final static int MAXCOLS = 1000;
    private Shape shape;
    private Label message;
    private ArrayList<Shape>  shapes;
    private GraphicsContext gc;

    private Label widthLabel; // New instance variable for width label
    private TextField widthTextField; // New instance variable for width
    private Label heightLabel; // New instance variable for height label
    private TextField heightTextField; // New instance variable for height

    private Button ellipseButton;
    private Button circleButton;
    private Button rectangleButton;
    private Button squareButton;
    private Button clearButton;
    private Button undoButton;
    public String selectedShape = "Ellipse"; 

    private Label outlineColorLabel;
    private ColorPicker outlineColorPicker;

    private Label fillColorLabel;
    private ColorPicker fillColorPicker; 

    private Label outlineWidthLabel;
    private TextField outlineWidthField;

    private Button deleteButton;
    private Button toFrontButton;
    private Button toBackButton;
    private Button resetColorButton;
    private Shape rightSelectedShape = null;

    private Label instructionLabel;

    // TODO: Private Event Handlers and Helper Methods
    public void pressHandler(MouseEvent me) {   
        try {
            if (me.getButton() == MouseButton.PRIMARY) {
                if (me.getY() < MAXROWS - 200) {
                    double width = Double.parseDouble(widthTextField.getText());
                    double height = Double.parseDouble(heightTextField.getText());

                    Color outlineColor = outlineColorPicker.getValue();
                    Color fillColor = fillColorPicker.getValue();
                    double outlineWidth = Double.parseDouble(outlineWidthField.getText());
        
                    switch (selectedShape) {
                        case "Ellipse":
                            shape = new Ellipse(width, height, outlineColor, fillColor, outlineWidth, me.getX(), me.getY());
                            break;
                        case "Circle":
                            shape = new Circle(width, outlineColor, fillColor, outlineWidth, me.getX(), me.getY());
                            break;
                        case "Rectangle":
                            shape = new Rectangle(width, height, outlineColor, fillColor, outlineWidth, me.getX(), me.getY());
                            break;
                        case "Square":
                            shape = new Square(width, outlineColor, fillColor, outlineWidth, me.getX(), me.getY());
                            break;
                        default:
                            return;
                    }
                    shapes.add(shape);
                    updateFrame();
                }
            } else if (me.getButton() == MouseButton.SECONDARY) {
                if (me.getY() < MAXROWS - 200) {
                    for (Shape s : shapes) {
                        if (s.contains(me.getX(), me.getY())) {
                            if (rightSelectedShape != null)
                                rightSelectedShape.rightSelectionToggleOff();
                            rightSelectedShape = s;
                            rightSelectedShape.rightSelectionToggleOn();                        
                            updateFrame();
                            break;
                        }
                    }
                }
            }
        } catch (NumberFormatException ex) {
            // Handle the exception by displaying an error message
            new Alert(Alert.AlertType.WARNING, "Invalid Line Width").showAndWait();
        }
        
    }
    
    private void ellipseButtonHandler(ActionEvent event){
        selectedShape = "Ellipse";
    }
    
    private void circleButtonHandler(ActionEvent event) {
        selectedShape = "Circle";
    }
    
    private void rectangleButtonHandler(ActionEvent event) {
        selectedShape = "Rectangle";
    }
    
    private void squareButtonHandler(ActionEvent event) {
        selectedShape = "Square";
    }
    
    
    private void clearButtonHandler(ActionEvent event) {
        shapes.clear();
        updateFrame();
    }
    
    private void undoButtonHandler(ActionEvent event) {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            updateFrame();
        }
    }
    private void deleteButtonHandler(ActionEvent event) {
        if (rightSelectedShape!= null) {
            shapes.remove(rightSelectedShape);
            rightSelectedShape.rightSelectionToggleOff();
            rightSelectedShape = null;
            updateFrame();
        }
    }
    
    private void toFrontButtonHandler(ActionEvent event) {
        if (rightSelectedShape != null) {
            shapes.remove(rightSelectedShape);
            shapes.add(rightSelectedShape);
            rightSelectedShape.rightSelectionToggleOff();
            rightSelectedShape = null;
            updateFrame();
        }
    }
    
    private void toBackButtonHandler(ActionEvent event) {
        if (rightSelectedShape != null) {
            shapes.remove(rightSelectedShape);
            shapes.add(0,rightSelectedShape);
            rightSelectedShape.rightSelectionToggleOff();
            rightSelectedShape = null;
            updateFrame();
        }
    }
    private void resetColorHandler(ActionEvent event){
        if (rightSelectedShape != null) {
            Color outlineColor = outlineColorPicker.getValue();
            Color fillColor = fillColorPicker.getValue();
            rightSelectedShape.setFillColor(fillColor);
            rightSelectedShape.setOutlineColor(outlineColor);
            rightSelectedShape.rightSelectionToggleOff();
            rightSelectedShape = null;
            updateFrame();
        }
    }
    
    
    
    public void updateFrame() {
        if (rightSelectedShape != null) {
            deleteButton.setDisable(false);
            toFrontButton.setDisable(false);
            toBackButton.setDisable(false);
            resetColorButton.setDisable(false);
        } else {
            deleteButton.setDisable(true);
            toFrontButton.setDisable(true);
            toBackButton.setDisable(true);
            resetColorButton.setDisable(true);
        }     

        // add white layer
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,MAXCOLS, MAXROWS-200);

        // add shapes layer
        for( Shape s : shapes)
            s.draw(gc);

        // add grey layer
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0,MAXROWS - 200, MAXCOLS,200);

       message.setText("Number of shapes: "+ shapes.size()) ;
    }
    
    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, MAXCOLS, MAXROWS); // set the size here
        stage.setTitle("A8-Paint"); // set the window title here
        stage.setScene(scene);
        // 1. Create the model
        shapes = new ArrayList<Shape>();

        // 2. Create the GUI components
        Canvas c = new Canvas(MAXCOLS, MAXROWS);
        message = new Label("Number of shapes: 0");

        ellipseButton = new Button("Ellipse");
        circleButton = new Button("Circle");
        rectangleButton = new Button("Rectangle");
        squareButton = new Button("Square");
        clearButton = new Button("Clear");
        undoButton = new Button("Undo");
        
        outlineColorLabel = new Label("Outline Color:");
        outlineColorPicker = new ColorPicker(Color.BLACK); // Initial value for black color

        fillColorLabel = new Label("Fill Color:");
        fillColorPicker = new ColorPicker(Color.WHITE); // Initial value for white color

        outlineWidthLabel = new Label("Outline Width:");
        outlineWidthField = new TextField("2"); // Initial value for outline width

        widthLabel = new Label("Width(Ellipse/Rectangle)\n Radius(circle)\n Square(side):");
        widthTextField = new TextField("60.0"); // Initial value for width
        heightLabel = new Label("Height");
        heightTextField = new TextField("40.0"); // Initial value for height

        deleteButton = new Button("Delete");
        toFrontButton = new Button("To Front");
        toBackButton = new Button("To Back");
        resetColorButton = new Button("Reset Color");

        instructionLabel = new Label("INSTRUCTIONS\n"+"Left-click to draw shapes. Right-click to select.\n"
        + "Use Delete to remove selected shape.\n"
        + "Use To Front and To Back to change layer order.");

        deleteButton.setDisable(true);
        toFrontButton.setDisable(true);
        toBackButton.setDisable(true);
        resetColorButton.setDisable(true);

        // 3. Add components to the root
        root.getChildren().addAll(
            c, 
            message, 
            ellipseButton,
            circleButton, 
            rectangleButton, 
            squareButton, 
            clearButton, 
            undoButton,
            widthLabel,
            widthTextField,
            heightLabel,
            heightTextField, 
            outlineColorLabel, 
            fillColorLabel, 
            outlineColorPicker,
            fillColorPicker,
            outlineWidthLabel,
            outlineWidthField,
            deleteButton,
            toFrontButton,
            toBackButton,
            resetColorButton,
            instructionLabel
        );

        ellipseButton.setOnAction(this::ellipseButtonHandler);
        circleButton.setOnAction(this::circleButtonHandler);
        rectangleButton.setOnAction(this::rectangleButtonHandler);
        squareButton.setOnAction(this::squareButtonHandler);
        
        clearButton.setOnAction(this::clearButtonHandler);
        undoButton.setOnAction(this::undoButtonHandler);

        deleteButton.setOnAction(this::deleteButtonHandler);
        toFrontButton.setOnAction(this::toFrontButtonHandler);
        toBackButton.setOnAction(this::toBackButtonHandler);
        resetColorButton.setOnAction(this::resetColorHandler);

        // 4. Configure the components (colors, fonts, size, location)
        gc = c.getGraphicsContext2D();

        message.relocate(20, 20); // debug line --- take out soon
        ellipseButton.relocate(20, MAXROWS - 180);
        circleButton.relocate(20, MAXROWS - 150);
        rectangleButton.relocate(20, MAXROWS - 120);
        squareButton.relocate(20, MAXROWS - 90);
        clearButton.relocate(20, MAXROWS - 40);
        undoButton.relocate(80, MAXROWS - 40);

        widthLabel.relocate(350, MAXROWS - 180); // Adjust the y-coordinate as needed
        widthTextField.relocate(350, MAXROWS - 120); // Adjust the y-coordinate as needed
        heightLabel.relocate(350, MAXROWS - 90); // Adjust the y-coordinate as needed
        heightTextField.relocate(350, MAXROWS - 60); // Adjust the y-coordinate as needed
        
        outlineColorLabel.relocate(150, MAXROWS - 180);
        outlineColorPicker.relocate(150, MAXROWS - 160);

        outlineWidthLabel.relocate(150, MAXROWS - 120); // Adjust the y-coordinate as needed
        outlineWidthField.relocate(150, MAXROWS - 100); // Adjust the y-coordinate as needed

        
        fillColorLabel.relocate(150, MAXROWS - 60);
        fillColorPicker.relocate(150, MAXROWS - 40);

        deleteButton.relocate(550, MAXROWS - 180);
        toFrontButton.relocate(550, MAXROWS - 140);
        toBackButton.relocate(550, MAXROWS - 100);
        resetColorButton.relocate(550, MAXROWS - 60);

        instructionLabel.relocate(MAXCOLS -350, MAXROWS - 180);

        updateFrame();

        message.relocate(20,20);   //debug line --- take out soon

        // 5. Add Event Handlers and do final setup
        c.addEventHandler(MouseEvent.MOUSE_PRESSED,this::pressHandler);

        // 6. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}