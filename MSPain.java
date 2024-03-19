
import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser; //allows to pic the color
/**
 * Let user draw line on graphics pane with the mouse
 *
 * @author (cs)
 * @version (14/03)
 */
public class MSPain
{
    // instance variables 
    private double startX, startY; // x and y pos for when the user presses down
    private double finalX, finalY;
    private Color currentColor = Color.black; //setting a default color of black
    private boolean drawCircle = true; 
    private double lineSize = 20;
    private String currentText = ("");
    double rectX, rectY, rectWidth, rectHeight;
    
    //Constructor for objects of class LineDrawer

    public MSPain()
    {
        // initialise instance variables
        UI.setLineWidth(10);
        UI.addButton("Random Colour", this::randomColor); //callback to change colour
        UI.addButton("Choose Color", this::doChooseColor);
        UI.addButton("Change shape", this::ChangeShape);
        UI.addSlider("Line size", 10, 50, 20, this::lineSize); // this::lineSize is the callback. Everytime slider value changes it calls the lineSize method.
        UI.addTextField("Text", this::setCurrentText); //user types text assigns to currentText
        UI.addButton("Add text", this::addTextToCanvas);
        UI.addButton("Quit", UI::quit);
        UI.setMouseListener(this::doMouse);
    }
    
    /*
     * callback to choose colour
     */
    
    public void doChooseColor() {
        //opena  colour pane for user
        //this.  doesnt have to be there. Do it to be explicit
        this.currentColor = JColorChooser.showDialog(null, "Choose Color", this.currentColor);
        UI.setColor(this.currentColor);
        
    }
    
    /**
     * callback random colour changer
     */
    
    public void randomColor(){
        //set random RGB values between 0.0 and 1.0
        Color col = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
        UI.setColor(col);
    }
    
    
    //every time button is clicked 'drawCircle' becomes false and rect can be drawn
    public void ChangeShape(){
        drawCircle = !drawCircle;  
    }
    
    /**
     * Call back method for mouse
     * Draws a line
     */
    public void doMouse(String action, double x, double y){
        //action. because its a string above. "if mouse is pressed"
        if (action.equals("pressed")) {
            // store the pressed button(coordiantes)
            this.startX = x;
            this.startY = y;
            
        } else if (action.equals("released")) {
            
            if (x > this.startX){
                //makes the starting position of the rectangle where user clicked
                rectX = this.startX;
                //takes the final x pos and intial x pos, subtracts to create rectangle with correct dimensions
                rectWidth = x - this.startX;
            }
                
            //for if the user tries to create a rectangle the opposite way
            else {
                //assigns x starting position of rectangle where the user clicked
                rectX = x;
                //takes starting x pos and subtracts final x pos to create rect with correct dimensions
                //startX - x, because the startX will be BIGGER than finalX since users going backwards
                rectWidth = this.startX - x;
            }
            
            //adjusts Y position/dimensions of rectangle
            
            if (y > this.startY){
                //assigns y starting position of rectangle where the user clicked
                rectY = this.startY;
                //gives rectangle correct height dimensions
                rectHeight = y - this.startY;
            }
                
            // if user creates rect backwards
            else {
                //start y position of rect 
                rectY = y;
                //gives rectangle correct height dimensions
                rectHeight = this.startY - y;
            }
             
            //draws rectangle with x,y position and correct height and width
            // rexct x, rect y is the top left position of rectangle
            UI.drawRect(rectX, rectY, rectWidth, rectHeight);
            
            
        }else if(action.equals("clicked")){
            
            if (drawCircle == true) {
                //draw a circle on click
                UI.fillOval(x-50/2, y-50/2, 50, 50);
            }else {
                UI.fillRect(x-50/2, y-50/2, 50, 50);
            }
            
        }
        
    } 
    
    //double 'size' represents the new slider value user has chosen
    public void lineSize(double size){
        //updates 'lineSize' variable with the new size user has selected
        this.lineSize = size;
        //makes sure future drawings will take on new size. From ecs100library.
        UI.setLineWidth(this.lineSize);
        
    }
    
    //string entered by user is stored in variable (text).
    public void setCurrentText(String text){
        //assigns variable 'text' to currentText
        this.currentText = text;
    }
    
    
     public void addTextToCanvas(){
        //takes text entered by user and adds to pane x,y pos
        double x = 100;
        double y = 200;
        UI.drawString(this.currentText, x, y);
        
    }
    
    
    
        
    }
     
    /**
     * public void doMouse(String action, double x, double y){
        if (action.equals("clicked")){
            //checks if user has licked to add text or do something else
            if (this.currentText != null){
                addTextToCanvas(x, y);
            }
     * 
     */
        

    


    
