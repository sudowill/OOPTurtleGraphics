import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import uk.ac.leedsbeckett.oop.LBUGraphics;

public class MainClass extends LBUGraphics {
        JFrame mainFrame = new JFrame("Turtle OOP Project - William Holmes"); // create a frame to display the turtle panel on
        mainFrame.setLayout(new FlowLayout()); // not strictly necessary
        mainFrame.add(this); // "this" is this object that extends turtle graphics so we are adding a turtle graphics panel to the frame
        mainFrame.pack(); // set the frame to a size we can see
        mainFrame.setVisible(true); // now display it
        about();
        displayMessage("Use 'cmdlist' to view commands");
        clear();
    }
    
    public static void main(String[] args) {
        new MainClass();
    }
}


    @Override
    public void about() // Overrides command to display my name whilst still making the turtle "dance"
    {
        super.about();
        displayMessage("Will");
    }

    public void RGB(int redValue, int greenValue, int blueValue) {
        Color rgbColour = new Color(redValue, greenValue, blueValue);
        setPenColour(rgbColour);
    }

    public void triangle(int length) {
        getGraphics2DContext().drawPolygon(new int[] { getxPos() - (length / 2), getxPos(), getxPos() + (length / 2) },
                new int[] { getyPos(), (int) (getyPos() - (length * (Math.sqrt(3) / 2))), getyPos() }, 3);
    }

    public void rgb(int Red, int Green, int Blue) {
        Color color = new Color(Red, Green, Blue);
        setPenColour(color);

    }

    private boolean writeCommand = true;
    private boolean saved = false;
    private boolean saveWarn = false;

    public void processCommand(String command) // this method must be provided because LBUGraphics will call it when it's JTextField is used
    {

        String[] commandEntered = command.toLowerCase().split(" ");
        int parameter = 0;
        int parameter2 = 0;
        int parameter3 = 0;
        String cmd = commandEntered[0];
        if (commandEntered.length == 2) {
            try {
                parameter = Integer.parseInt(commandEntered[1]);
            } catch (NumberFormatException e) {
                return;
            }
        } else if (commandEntered.length == 4) {
            try {
                parameter = Integer.parseInt(commandEntered[1]);
                parameter2 = Integer.parseInt(commandEntered[2]);
                parameter3 = Integer.parseInt(commandEntered[3]);
            } catch (NumberFormatException e) {
                return;
            }
        } else {
            return;
        }

        // PEN COMMANDS
        switch (commandEntered[0]) {

        case "down":
        case "pendown":
        case "pen down":
            penDown();
            setPenColour(Color.green);
            displayMessage("PEN IS DOWN");

		                
					case "up":
					case "penup":
					case "pen up":
		                penUp();
		                displayMessage("PEN IS UP");
		                break;
		                
		                // TURTLE MOVEMENT

					case "forward":
		                forward(parameter);
		                displayMessage("Turtle moved forward");
		                break;
		                	
		                
					case "backward":
		                forward(-parameter);
		                displayMessage("Turtle moved backward");
		                break;
		                
					case "left":
		                turnLeft(parameter);
		                displayMessage("Turtle turned left");
		                break;
		                
					case "right":
		                turnRight(parameter);
		                displayMessage("Turtle turned right");
		                break;
		            

		                
		                // PEN SETTINGS
					case "black":
		                setPenColour(Color.black);
		                displayMessage("Colour = BLACK");
		                break;
		                
					case "green":
		                setPenColour(Color.green);
		                displayMessage("Colour = GREEN");
		                break;
		                
					case "red":
		                setPenColour(Color.red);
		                displayMessage("Colour = RED");
		                break;
		                
					case "white":
		                setPenColour(Color.white);
		                displayMessage("Colour = WHITE");
		                break;

					case "rgb":
						rgb(parameter, parameter2, parameter3);
						break;
		                
					case "width":
						setStroke(parameter);
						break;
						
						
						
					// TURTLE SPEED
					case "speed":
					case "turtlespeed":
						setTurtleSpeed(parameter);
						break;
						
					// PEN SIZE
					case "pensize":
						
						
						
		            // CLEAR THE CANVAS
					case "clear":
					case "clearcanvas":
						clear();
						displayMessage("CANVAS CLEARED");
						
					// CHANGE CANVAS COLOUR
					case "background":
						// setBackground_Col(Color.parameter);
						
						
						
					// POINT THE TURTLE
					
					case "point":
		    			pointTurtle(parameter);
		    			break;
		                
					// RESET THE TURTLE
					case "reset":
					case "resetturtle":
						penUp();
		                reset();
		                displayMessage("Resetted the turtle");
		                penDown();
		                break;
		                
		                
		                // SHAPES
					case "circle":
						circle(parameter);
						break;
						
					case "square":
						for (int count = 1; count <= 4; count++) {
				            forward(parameter);
				            turnRight();
						}
						break;
						
					
						
						
					case "triangle":
						int n = (parameter);
						penDown();
						forward(n);
						turnRight(120);
						forward(n);
						turnRight(120);
						forward(n);
						penUp();
						forward(50);
						
					// IMAGE SAVE

					case "save":
		    			if (commandEntered.length == 1) {
		    				if (!saved) {
		    					saveImage("turtle_canvas.png");
		    					saved = true;
		    					displayMessage("Image saved as turtle_canvas.png");
		    				} else {
		    					if (!saveWarn) {
		    						displayMessage("Image already saved. Use 'save again' to overwrite.");
		    						saveWarn = true;
		    					} else {
		    						saveImage("turtle_canvas.png");
		    						displayMessage("Image overwritten.");
		    					}
		    				}
		    			} else if (commandEntered.length == 2 && commandEntered[1].equals("again")) {
		    				saveImage("turtle_canvas.png");
		    				displayMessage("Image overwritten.");
		    			} else {
		    				displayMessage("Invalid save command");
		    			}
		    			break;
	                    
					
					case "load":
						if(saved)
	                    {
	                        try
	                        {
	                            File inputfile = new File(commandEntered[1] + ".png");
	                            BufferedImage bufferedImage = ImageIO.read(inputfile);
	                            setBufferedImage(bufferedImage);
	                            displayMessage("Your canvas has been loaded");
	                        }

	                        catch (IOException e)
	                        {
	                            displayMessage("The Load command has failed, or the file doesn't exist");
	                        }
	                    }

	                    else
	                    {
	                        displayMessage("Warning: Current Canvas/commands never been saved!");
	                        saveWarn = true;
	                    }
	                    break;
	                    
					// RANDOM COMMANDS
					case "about":
						about();
						break;
		                // COMMANDS LIST
					case "cmdlist":
					case "commandlist":
					case "help":
		                JFrame frame = new JFrame("COMMAND LIST FOR TURTLE");
		                frame.setMinimumSize(new Dimension(500, 300));
		                frame.setVisible(true);
		                JLabel Title = new JLabel("<html>"
		                		+ "<br/>==========TURTLE MOVEMENT================"
		                		+ "<p>forward = turtle goes forward"
		                		+ "<p>backward = turtle goes backward"
		                		+ "<p>right = turtle turns right"
		                		+ "<p>left = turtle turns left"
		                		+ "<p>reset = turtle resets to central position"
		                		+ "<p>speed (put integar here, remove brackets) = sets the speed of turtle, smaller int faster it goes"
		                		+ "<p><br>============TRAIL COLOURS=============="
		                		+ "<p>red = makes a red trail"
		                		+ "<p>white = makes a white trail"
		                		+ "<p>green = makes a green trail"
		                		+ "<p>white = makes a white trail"
		                		+ "<p>black = makes a black trail"
		                		+ "<p><br>============MAKE SHAPES================"
		                		+ "<p>circle (put integar here, remove brackets) = makes a circle with given radius"
		                		+ "<p><br>============CANVAS COMMANDS============"
		                		+ "<p>clear = clears the canvas			"
		                		+ "<br><br>\"</html>", SwingConstants.CENTER);

		                frame.add(Title);
		                frame.pack();
		                
					
		                
		            
	                    
	                // IF THE USER DOES NOT PUT A COMMAND OR INVALID COMMAND, JUST NEED TO EXTEND IT TO NUMERIC VALUES NOW
					default:
						displayMessage("Invalid command, please use 'cmdlist' to view the commands");
	        }
	                
	  
	



	                
	                //lands here if return was pressed or "ok" JButton clicked
	                //TO DOO   
    }}
	

