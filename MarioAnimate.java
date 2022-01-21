//*******************************************************************
//
//   File: MarioAnimate.java          
//
//   Class: MarioAnimate
// 
//   We Do: Jump via the equation y = -1/20 * x^2 + 7/2 *x
//          from relative (0,0) to (50,50)
//
//   You Do: Find out if mario makes the last jump!
//           -- If he does, have him stand on the platform
//           -- If not, have him fall in the hole!
//
//*******************************************************************
import java.awt.*;

import javax.imageio.ImageIO;
import java.io.File; 
import java.io.IOException;

public class MarioAnimate
{
    // global variables define new instance of panel and grab its graphics container
    static DrawingPanel panel = new DrawingPanel(575, 400);
    static Graphics2D g = panel.getGraphics();

    // main routine tests helper method printPyramid
    public static void main(String[] args) 
    {
        // import all images
        Image stand = null;
        Image jump = null;
        Image bg = null;
        try {
            stand = ImageIO.read(new File("Mario_Standing.png"));
            jump = ImageIO.read(new File("Mario_Jumping.png"));
            bg = ImageIO.read(new File("background.png"));
        } catch (IOException e) {}

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
            mariox += 50;
            marioy -= jumpModel(50);

            // stand until next hop 
            marioStand(mariox, marioy, stand, bg);
        }        
    }

    // mario standing
    public static void marioStand(int mariox, int marioy, Image stand, Image bg)
    {
        // draw background from bg image file
        g.drawImage(bg, 0, 0, null);

        // draw mario standing
        g.drawImage(stand, mariox, marioy, null);

        // wait 300 ms
        panel.sleep(300);
    }

    // mario jumping
    public static void marioHop(int mariox, int marioy, Image jump, Image bg)
    {
        // WE DO: animate one hop in 10 frames from relative (0,0) to (50,50)
        for (int x = 0; x < 50; x+=5) 
        {
            // WE DO: draw background from bg image file
            g.drawImage(bg, 0, 0, null);

            // WE DO: draw mario jumping from relative coordinates (mariox, marioy)
            g.drawImage(jump, mariox + x, marioy - jumpModel(x), null);

            // WE DO: wait 300 ms
            panel.sleep(300);
        }     
    }

    // quadratic jump model
    public static int jumpModel(int x)
    {
        return -(x * x / 20) + (7 * x / 2);
    }
}
