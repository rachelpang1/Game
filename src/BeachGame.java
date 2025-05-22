import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;


/***
 * Step 0 for keyboard control - Import
 */
import java.awt.event.*;

/***
 * Step 1 for keyboard control - implements KeyListener
 */
public class BeachGame implements Runnable, KeyListener {

    //Variable Definition Section

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 650;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    //Declare the variables needed for images

    public Image StitchPic;
    public Image BeachPic;
    public Image PlaceHolderPic;
    //Declare the character objects
    //public Mouse mouse1;
    public Player stitch;
    public Bear[] bear = new Bear[6];
    public int x;
    public bullet[] red = new bullet[100];
    public Image bulletPic;
    public Image heart3Pic;
    public Image heart2Pic;
    public Image heart1Pic;
    public bullet[] blue = new bullet[100];
    public Image bluePic;
    public Bear[] beary = new Bear[7];
    public Image GameOverPic;
    public background GameOver;
    public boolean gameover;
    public Coin[] coin = new Coin [4];
    public Image coinPic;
    public Image beary1pic;
    public Image beary2pic;
    public Image beary3pic;
    public boolean allBearsDead;
    public int numCoins;




    public static void main(String[] args) {
        BeachGame myApp = new BeachGame();   //creates a new instance of the game
        new Thread(myApp).start();           //creates a threads & starts up the code in the run( ) method
    }


    public BeachGame() {
        setUpGraphics();


        canvas.addKeyListener(this);


        StitchPic = Toolkit.getDefaultToolkit().getImage("stitch.png");
        BeachPic = Toolkit.getDefaultToolkit().getImage("background.jpg");
        bulletPic = Toolkit.getDefaultToolkit().getImage("redLaser.png");
        heart3Pic = Toolkit.getDefaultToolkit().getImage("3hearts.png");
        heart2Pic = Toolkit.getDefaultToolkit().getImage("2hearts.png");
        heart1Pic = Toolkit.getDefaultToolkit().getImage("1heart.png");
        bluePic = Toolkit.getDefaultToolkit().getImage("bluelaser.png");
        GameOverPic = Toolkit.getDefaultToolkit().getImage("GameOver.jpeg");
        beary1pic = Toolkit.getDefaultToolkit().getImage("Heart1.png");
        beary2pic = Toolkit.getDefaultToolkit().getImage("Hearts2.png");
        beary3pic = Toolkit.getDefaultToolkit().getImage("Hearts3.png");
        stitch = new Player(250, 250, 20, 20, StitchPic);
        GameOver = new background (0,0,0,0, GameOverPic);
        gameover = true;
        coinPic = Toolkit.getDefaultToolkit().getImage("Coin.png");

        for (int i = 0; i < bear.length; i ++) {
//            Bear myBear = new Bear(800, (int)(Math.random()*700+50), 0, 2, PlaceHolderPic);
            Bear myBear = new Bear(800, (int)(Math.random()*600), 0, 2, PlaceHolderPic);
            bear[i] = myBear;
            bullet myblue = new bullet(-100, -100, -8, 0, bluePic);
            blue[i] = myblue;
            blue[i].xpos = bear[i].xpos;
            blue[i].ypos = bear[i].ypos;
            blue[i].isAlive = true;
            blue[i].dx = -8;
            System.out.println("bear " + i + "xpos = " + bear[i].xpos);
        }
        for (int x = 0; x < beary.length; x++) {
            Bear myBear = new Bear((int)(800), (int) (Math.random() * 600), -2, 0, PlaceHolderPic);
            beary[x] = myBear;
            beary[x].isAlive = true;
        }

        for (int x = 0; x<coin.length; x++) {
            Coin mycoin  = new Coin((int) (200 + Math.random() * 100), (int) (Math.random() * 700), 0 , 2, coinPic);
            coin[x] = mycoin;
            coin[x].height = 50;
            coin[x].width = 50;
        }

        for (int x = 0; x < red.length; x ++) {
            bullet myred = new bullet(-100, -100, 0, 0, bulletPic);
            red[x] = myred;

        }

        allBearsDead = false;
        numCoins =0;
    }


//*******************************************************************************
//stitch Method Section

    public void move() {

        stitch.move();

        for (int i = 0; i < red.length; i++) {
            red[x].move();
        }
        for (int i = 0; i < bear.length; i++) {
            bear[i].move();
        }
        for (int i = 0; i < beary.length; i++) {
            beary[i].wrap();
        }
        for (int i = 0; i < bear.length; i++) {
            blue[i].move();
            if (blue[i].xpos < 10) {
                blue[i].xpos = bear[i].xpos;
                blue[i].ypos = bear[i].ypos;
                //System.out.println("blue " + i + "xpos = " + blue[x].xpos);}
            }
        }

    }

    public void checkIntersections () {
        for(int k = 0; k < red.length; k++) {
            for (int i = 0; i < bear.length; i++) {
                if (bear[i].rec.intersects(red[k].rec) && bear[i].isAlive ==true) {
                    bear[i].health = bear[i].health - 1;
                    if (bear[i].health ==0){
                        bear[i].isAlive = false;
                    }
                    if (!bear[0].isAlive && !bear[1].isAlive && !bear[2].isAlive&& !bear[3].isAlive && !bear[4].isAlive && !bear[5].isAlive){
                        allBearsDead = true;
                    }

                    //loop through all bears
                    // if all are dead (need boolean to decide this) then set allBearsDead to true
                    // use allBearsDead in if statement in render
                }
            }
        }
        for(int k = 0; k < red.length; k++) {
            for (int i = 0; i < beary.length; i++) {
                if (beary[i].rec.intersects(red[k].rec)) {
                    beary[i].health = beary[i].health - 1;
                }
            }
        }
        for (int i = 0; i < bear.length; i++) {
            //System.out.println (blue[i].isAlive);
            if (blue[i].rec.intersects(stitch.rec) && blue[i].isAlive == true) {
//                stitch.isAlive = false;
//                gameover = false;

            }
        }
        for (int i = 0; i < beary.length; i++) {
            //System.out.println (blue[i].isAlive);
            if (beary[i].rec.intersects(stitch.rec) && beary[i].isAlive == true) {
//                stitch.isAlive = false;
//                gameover = false;

            }
        }


        for (int x = 0; x<coin.length; x++) {
            if (coin[x].rec.intersects(stitch.rec) && coin[x].isAlive==true) {
                coin[x].isAlive = false;
                numCoins = numCoins+1;
            }
        }


    }

    public void run() {


        while (true) {

            checkKeys();
            move();           //move all the game objects
            checkIntersections();   // check character crashes
            // paint the graphics
            render();
            pause(20);
            GameOver.height = 700;
            GameOver.width = 1000;


            // sleep for 20 ms
        }
    }

    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        if (gameover == true) {
            g.drawImage(BeachPic, 0, 0, 1000, 700, null);
            g.drawImage(stitch.pic, stitch.xpos, stitch.ypos, stitch.width, stitch.height, null);
        }
        else{
            g.drawImage(GameOverPic, GameOver.xpos, GameOver.ypos, GameOver.width, GameOver.height, null);
            System.out.println(gameover);
        }


        if (gameover == true){
            for (int i = 0; i < bear.length; i++) {
                bear[i].isAlive = true;

                if (bear[i].health == 3) {

                    g.drawImage(heart3Pic, bear[i].xpos, bear[i].ypos, bear[i].width, bear[i].height, null);
                }
                if (bear[i].health == 2) {
                    g.drawImage(heart2Pic, bear[i].xpos, bear[i].ypos, bear[i].width, bear[i].height, null);
                }
                if (bear[i].health == 1) {
                    g.drawImage(heart1Pic, bear[i].xpos, bear[i].ypos, bear[i].width, bear[i].height, null);
                }
                if (bear[i].health < 1){
                    bear[i].isAlive = false;
                    blue[i].isAlive = false;

                }
                if (bear[i].isAlive == true){
                    g.drawImage(bluePic, blue[i].xpos, blue[i].ypos, blue[i].width, blue[i].height, null);
                }


            }
            if (allBearsDead==true){
                for (int k = 0; k < beary.length; k++) {
                    beary[k].isAlive = true;


                    if (beary[k].health == 3) {

                        g.drawImage(beary3pic, beary[k].xpos, beary[k].ypos, beary[k].width, beary[k].height, null);
                    }
                    if (beary[k].health == 2) {
                        g.drawImage(beary2pic, beary[k].xpos, beary[k].ypos, beary[k].width, beary[k].height, null);
                    }
                    if (beary[k].health == 1) {
                        g.drawImage(beary1pic, beary[k].xpos, beary[k].ypos, beary[k].width, beary[k].height, null);
                    }
                    if (beary[k].health < 1){
                        beary[k].isAlive = false;
                    }
                }
            }

            if (red[x].isAlive == true) {
                g.drawImage(bulletPic, red[x].xpos, red[x].ypos, red[x].width, red[x].height, null);
            }
            for (x = 0; x<coin.length; x++){
                if (coin[x].isAlive== true)
                g.drawImage(coinPic, coin[x].xpos,coin[x].ypos, coin[x].width, coin[x].height, null );
            }

        }

        g.dispose();
        bufferStrategy.show();
    }


    public void checkKeys() {

        if (stitch.left ==true){
            stitch.dx = -2 - numCoins;
        }
        else if (stitch.right ==true){
            stitch.dx= 2 + numCoins;
        }
        else {
            stitch.dx = 0;
        }

        if (stitch.up ==true){
            stitch.dy = -2 - numCoins;
        }
        else if (stitch.down ==true){
            stitch.dy= 2 + numCoins;
        }
        else {
            stitch.dy = 0;
        }


    }


    public void keyPressed(KeyEvent event) {

        char key = event.getKeyChar();     //gets the character of the key pressed
        int keyCode = event.getKeyCode();  //gets the keyCode (an integer) of the key pressed
        System.out.println("Key Pressed: " + key + "  Code: " + keyCode);

        if (keyCode == 68) { // d
            stitch.right = true;
        }
        if (keyCode == 65) { // a
            stitch.left = true;
        }

        if (keyCode == 83) { // s
            stitch.down = true;
        }
        if (keyCode == 87) { // w
            stitch.up = true;
        }
        if (keyCode == 68) { // d
            stitch.right = true;
        }
        if (keyCode == 65) { // a
            stitch.left = true;
        }

        if (keyCode == 83) { // s
            stitch.down = true;
        }
        if (keyCode == 87) { // w
            stitch.up = true;
        }
        if (keyCode == 32) {
            red[x].isAlive = true;
            red[x].xpos = stitch.xpos;
            red[x].ypos = stitch.ypos + 25;
            red[x].dx = 1;
            if (red[x].xpos == 1000) {
                red[x].isAlive = false;
            }

        }

    }//keyPressed()

    public void keyReleased(KeyEvent event) {
        char key = event.getKeyChar();
        int keyCode = event.getKeyCode();

        if (keyCode == 68) { // d
            stitch.right = false;
        }
        if (keyCode == 65) { // a
            stitch.left = false;
        }
        if (keyCode == 83) { // s
            stitch.down = false;
        }
        if (keyCode == 87) { // w
            stitch.up = false;
        }


    }

    public void keyTyped(KeyEvent event) {

    }//keyTyped()



    public void setUpGraphics() {
        frame = new JFrame("Game");

        panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }


    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

}//end
