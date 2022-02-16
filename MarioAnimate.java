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
import javax.imageio.ImageIO;
import java.io.File; 
import java.io.IOException;
import java.awt.image.BufferedImage;

public class MarioAnimate
{
    // global variables define new instance of panel and grab its graphics container
    static DrawingPanel panel = new DrawingPanel(575, 400);
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
            // Q. Why do we add to mariox, but subtract from marioy?
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

            // We DO: update mario's x, y coordinates for each time-step
            
            // WE DO: copy off screen image onto DrawingPanel

            // WE DO: sleep for FRAME_T milliseconds

        }     
    }
    
    // displacement formula to find mario's current position
    public static int pos(double p0, double vel, double acc, double t) {
        // graphics only accepts int coordinates
        return (int) (p0 + vel * t + .5 * acc * t * t);
    }
}
