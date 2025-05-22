import java.awt.*;

public class background {
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public Rectangle rec;
    public Image pic;
    public int hits;
    public int health;
    public int count;

    // METHOD DEFINITION SECTION

    //This is a constructor that takes 3 parameters.  This allows us to specify the object's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.


    public background(int pXpos, int pYpos, int dxParameter, int dyParameter, Image picParameter) {

        xpos = pXpos;
        ypos = pYpos;
        width = 90;
        height = 70;
        dx = dxParameter;
        dy = dyParameter;
        pic = picParameter;
        isAlive = true;
        hits = 0;
        health = 3;
        rec = new Rectangle(xpos, ypos, width, height);
        isAlive = true;
        count = 0;
    }}
