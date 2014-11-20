package test;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import utils.ImageUtils;

public class TestCropImage {

	public static void main(String[] args) throws IOException {

		File input = new File("Pictures//training_dataset//1_10_.gif");
		File[] inputfiles = input.listFiles();

		//for (File file : inputfiles) {
			//BufferedImage newimage = ImageUtils.cropImage(input);

			//ImageIO.write(newimage, "png", new File(
			//		"Pictures//training_dataset_out//outofbounds.png"));
		//}
	}
}
