//*******************************************************************
//
//   File: MarioAnimate.java          
//
//   Class: MarioAnimate
// 
//   We Do: Create a 1 second jump animation from relative (0,0) to (50,50)
//
//   You Do: Find out if mario makes the last jump!
//           -- If he does, have him stand on the platform
//           -- If not, have him fall in the hole!
//
//*******************************************************************
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.File; 
import java.io.IOException;
import java.awt.image.BufferedImage;


public class MarioAnimate
{
    // global variables define new instance of panel and grab its graphics container
    static DrawingPanel panel = new DrawingPanel(500, 500);
    static Graphics2D g = panel.getGraphics();

    // set up off screen scene and off screen graphics object to enable double buffering
    static BufferedImage offscreen = new BufferedImage(575, 400, BufferedImage.TYPE_INT_RGB);
    static Graphics2D osg = offscreen.createGraphics();

    public static void main(String[] args) throws IOException
    {
        // import all images
        Image stand = null;
        Image jump = null;
        Image bg = null;
        
        stand = ImageIO.read(new File("Mario_Standing.png"));
        jump = ImageIO.read(new File("Mario_Jumping.png"));
        bg = ImageIO.read(new File("background.png"));

        // initial (x, y) coordinates for mario
        int mariox = 25;
        int marioy = 295;

        // stand mario in initial frame
        marioStand(mariox, marioy, stand, bg);

        // animate all hops up the pyramid
        for (int count = 0; count < 5; count++)
        {
            // animate one hop
            marioHop(mariox, marioy, jump, bg);

            // update (mariox, marioy) coordinates
            // Q. Why do we subtract jumpModel from marioy?
            
            // can just subtract 50 instead od jumpModel(50)
            mariox += 50;
            marioy -= 50;

            // stand until next hop 
            marioStand(mariox, marioy, stand, bg);
        }        
    }

    // mario standing
    public static void marioStand(int mariox, int marioy, Image stand, Image bg)
    {
        // draw background from bg image file onto off screen image
        osg.drawImage(bg, 0, 0, null);

        // draw mario standing
        osg.drawImage(stand, mariox, marioy, null);

        // copy off screen image onto DrawingPanel
        g.drawImage(offscreen, 0, 0, null);
        
        // wait 1000 ms (1 second)
        panel.sleep(1000);
    }

    // mario jumping
    public static void marioHop(int mariox, int marioy, Image jump, Image bg)
    {
        // a frame time of 17 ms corresponds to about 60 frames per second
        final int FRAME_T = 17;

        // WE DO: animate one hop in one second from relative (0,0) to (50,50)
        for (double t = 0; t < 1; t += FRAME_T/1000.0) 
        {
            // WE DO: draw background from bg image file onto off screen image
            osg.drawImage(bg, 0, 0, null);

            // WE DO: draw mario jumping from relative coordinates (mariox,
            // marioy)
            // find relationship between a and v knowing that it takes 1 second
            // and the displacement is -50
            // solution:
            // -50 = v + .5 * a
            // a = -2v-100
            // v=-300, a=500 is one option that looks reasonable.
            // you can pick any two values that satisfy the relationship above
            osg.drawImage(jump, (int)(mariox + 50*t), (int)(position(marioy, -300, 500, t)), null);
            
            // copy off screen image onto DrawingPanel
            g.drawImage(offscreen, 0, 0, null);

            // WE DO: sleep for FRAME_T milliseconds
            panel.sleep(FRAME_T);
        }     
    }

    // displacement formula to find mario's current position
    public static double position(double initPos, 
                                  double v, 
                                  double a, 
                                  double t) {
        return initPos + v * t + .5 * a * t * t;
    }

    // We discussed re-using code for drawing pyramids vs drawing images
    // pros: building something up over time giving a sense that previous
    //       activities had value going forward
    // cons: more source code given out, potentially slowing things down or
    //       leading to more questions that aren't directly related to the main task
    // Either is fine with me. If re-using code, fill in below.  Otherwise, remove
    

    // helper method to draw a box with 3d effects
    // whose top-left corner is x, y
    public static void drawBox(int x, int y, Graphics2D g) 
    {      
        // define (r, g, b) Colors
        Color brown = new Color(150, 75, 0);
        Color chocolate = new Color(210,105,30);

        // box size
        int sz = 25;

        //-------------------------------------------------------------//
        // WE DO: 3D box effects

        // 3d box effects - buffer size
        int buffer = 5;

        // 3D box effects - outer box

        // 3D box effects - cross lines

        // 3D box effects - inner box over cross lines

        // 3D box effects - outer outline

        //-------------------------------------------------------------//
    }

    public static void drawRow(int x, int y, int length, Graphics2D g)
    {
        // WE DO: row for LHS pyramid
        
    }

    // method to draw LHS and RHS pyramids
    public static void drawPyramid(int size, Graphics2D g) 
    {
        // starting points for x, y
        int x = 50;
        int y = 300;

        // WE DO: draw the pyramids, row by row
      
    }
}

