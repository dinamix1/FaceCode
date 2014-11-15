package test;

import java.awt.color.ColorSpace;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestGrayScale {
	public static void main(String[] args) throws IOException {
		File image = new File("Pictures//spacey.png");
		BufferedImage buffimage = ImageIO.read(image);

		int[] originalFaceVector = new int[buffimage.getWidth() * buffimage.getHeight()];
		
		//Used to keep track of where we are in 1D face vector array
		int originalFaceVectorTracking = 0;
		
		//Converts RGB to grayscale and inputs it into originlaFaceVector
        for (int y = 0; y < buffimage.getHeight(); y++) {
            for (int x = 0; x < buffimage.getWidth(); x++) {
                Color color = new Color(buffimage.getRGB(x, y));
                
                //Averages out RGB to get an average algorithm grayscale value
                int grayscale = (color.getRed() + color.getGreen() + color.getBlue())/3;

                originalFaceVector[originalFaceVectorTracking++] = grayscale;
                
                //force picture into grayscale
                buffimage.setRGB(x,y,new Color(grayscale,grayscale,grayscale).getRGB());
            }
        }
        
        ImageIO.write(buffimage, "png", new File("Pictures//grayspacey.png"));
        
		for(int i = 0; i < originalFaceVector.length; i++) {
			System.out.println(originalFaceVector[i]);
		}
	}
}
