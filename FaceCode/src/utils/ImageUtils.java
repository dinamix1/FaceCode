package utils;

import java.awt.image.BufferedImage;
import org.imgscalr.Scalr; //imgscalr imported library but not native. built using base java libraries

/*
 * Scalr Library:
 * http://www.thebuzzmedia.com/downloads/software/imgscalr/javadoc/org/imgscalr/Scalr.html
 */

public class ImageUtils {
	public static BufferedImage reSize(BufferedImage image,int width,int height) {
		//return BufferedImage Object with specified height and width
		//given from org.imgscalr.Scalr library
		return Scalr.resize(image,width,height);
	}
}
