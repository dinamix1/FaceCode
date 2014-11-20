package utils;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class TestImageScale {
	public static void main(String[] args) {
		File image = new File("spacey.png");
		File resizeImage = new File("respace.png");
		
		System.out.println(image.getAbsolutePath());
		System.out.println(resizeImage.getAbsolutePath());
		
		try {
			BufferedImage buffimage = ImageIO.read(image);
			System.out.println(buffimage.getHeight());
			BufferedImage buffreimage = Scalr.resize(buffimage,30);
			ImageIO.write(buffreimage,"png",resizeImage);
			System.out.println(buffreimage.getHeight());
		}
		catch (Exception e) {
			System.out.println("In catch black " + e.getMessage());
		}
	}
}
