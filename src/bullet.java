import java.awt.*;

public class bullet {

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
    public boolean isIntersecting;


    public bullet(int pXpos, int pYpos,int dx, int dy, Image picParameter) {

        xpos = pXpos;
        ypos = pYpos;
        width = 40;
        height = 40;
        dx = 0;
        dy = 0;
        pic = picParameter;
        isAlive = false;
        isIntersecting = false;
        hits = 0;
        rec = new Rectangle(xpos, ypos, width, height);


    }
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        //if (xpos > 1000 - width || xpos < 0) {
        //dx = -dx;
        //}

        if (ypos < 0 || ypos + height > 700) {
            dy = -dy;
        }

        rec = new Rectangle(xpos, ypos, width, height);

    }
}
