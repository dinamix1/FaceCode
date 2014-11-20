package test;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

import utils.ImageUtils;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class testFaceDetector {

	public static void main(String[] args) throws IOException {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.out.println("\\nRunning testFaceDetector");

		File input = new File("Pictures//training_dataset//1_10_.gif");

		String filename = input.getName().replaceAll("gif", "png");
		File output = new File("Pictures//training_dataset_png//" + filename);

		System.out.println(output.getName());
		ImageIO.write(ImageIO.read(input), "png", output);
		BufferedImage originalimage = ImageIO.read(output);
		// BufferedImage res = ImageUtils.reSize(originalimage, 150,170);
		// ImageIO.write(res,"png",new
		// File("Pictures//training_dataset_out//test3.png"));

		CascadeClassifier testFaceDetector = new CascadeClassifier(
				".\\..\\opencv\\sources\\data\\haarcascades\\haarcascade_mcs_eyepair_big.xml");
		Mat image = Highgui.imread("Pictures//training_dataset_png//"
				+ filename);

		MatOfRect eyeDetections = new MatOfRect();
		testFaceDetector.detectMultiScale(image, eyeDetections);

		System.out.println(String.format("Detected %s faces",
				eyeDetections.toArray().length));

		try {
			if (eyeDetections.toArray().length == 1) {
				for (Rect rect : eyeDetections.toArray()) {
					double xtopleft = rect.x;
					double xbottomright = rect.x + rect.width;
					double xcenter = (rect.x + (rect.x + rect.width)) / 2;
					double ytopleft = rect.y;
					double ybottomright = rect.y + rect.height;
					double ycenter = (rect.y + (rect.y + rect.height)) / 2;

					int newwidth = (int) Math.ceil(1.9 * (3.2 / 2.43)
							* rect.width);
					int newheight = (int) Math.ceil(1.9 * rect.width);

					int newYcenter = (int) (ycenter + rect.height);
					int newXright = (int) (xbottomright + newwidth * 0.25);
					int newXleft = (int) (xtopleft - newwidth * 0.25);
					int newYtop = (int) (newYcenter - newheight / 2);
					int newYbottom = (int) (newYcenter + newheight / 2);

					Point pointleft = new Point(xtopleft, ytopleft);
					Point pointright = new Point(xbottomright, ybottomright);
					Point pointcenter = new Point(xcenter, ycenter);
					// Core.circle(image,pointright,5,new Scalar(0,255,0));
					Core.rectangle(image, pointleft, pointright, new Scalar(0,
							255, 0));

					BufferedImage crop = ImageUtils.reSize(
							originalimage.getSubimage(newXleft, newYtop,
									newwidth, newheight), 250, 190);
					ImageIO.write(crop, "png", new File(
							"Pictures//training_dataset_out//" + filename));
				}
			} else {
				BufferedImage resi = ImageUtils.reSize(originalimage, 250, 190);
				ImageIO.write(resi, "png", new File(
						"Pictures//training_dataset_out//" + filename));
			}
		} catch (Exception ex) {
			BufferedImage resi = ImageUtils.reSize(originalimage, 250, 190);
			ImageIO.write(resi, "png", new File(
					"Pictures//training_dataset_out//" + filename));
		}

		String filename2 = "womeneyes.png";
		System.out.println(String.format("Writing %s", filename));
		Highgui.imwrite(filename, image);
	}
}
