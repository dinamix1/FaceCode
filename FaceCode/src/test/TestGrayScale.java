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
		File image = new File("Pictures//training_dataset//1_10_.gif");

		BufferedImage buffimage = ImageIO.read(image);

		int[] originalFaceVector = new int[buffimage.getWidth() * buffimage.getHeight()];
		
		//Used to keep track of where we are in 1D face vector array
		int originalFaceVectorTracking = 0;
		
		//Converts RGB to grayscale and inputs it into originlaFaceVector
        for (int x = 0; x < buffimage.getWidth(); x++) {
            for (int y = 0; y < buffimage.getHeight(); y++) {
                Color color = new Color(buffimage.getRGB(x, y));
                
                //Averages out RGB to get an average algorithm grayscale value
                int grayscale = (color.getRed() + color.getGreen() + color.getBlue())/3;

                originalFaceVector[originalFaceVectorTracking++] = grayscale;
            }
        }
        
		for(int i = 0; i < originalFaceVector.length; i++) {
			System.out.println(originalFaceVector[i]);
		}
	}
}
