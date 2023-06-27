package example.imagetaskgang.utils;

// import javax.imageio.ImageIO;

import example.imagetaskgang.utils.Image;

/**
 * Encapsulates the Java BufferedImage class via a
 * platform-independent interface.
 */
public class BufferedImage implements Image {
    /**
     * A Java BufferedImage object.
     */
    // @@ Need to fix 
	//public java.awt.image.BufferedImage mBufferedImage;

    /**
     * Constructor that converts an @a imageData of raw bytes into a
     * Java @a BufferedImage.
     */
    public BufferedImage(byte[] imageData) {
//    	try {
//	      	mBufferedImage = ImageIO.read(new ByteArrayInputStream(imageData));
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
    }

    /**
     * Constructor that stores the @a bufferedInmage parameter into
     * the data member.
     */
    public BufferedImage (Object bufferedImage) {
    	// mBufferedImage = (java.awt.image.BufferedImage) bufferedImage;
    }
}
