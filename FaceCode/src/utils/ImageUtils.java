package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr; //imgscalr imported library but not native. built using base java libraries
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

import java.awt.Image;

/*
 * Scalr Library:
 * http://www.thebuzzmedia.com/downloads/software/imgscalr/javadoc/org/imgscalr/Scalr.html
 */

public class ImageUtils {
	public static BufferedImage reSize(BufferedImage image,int width, int height) {
		//return BufferedImage Object with specified height and width
		//given from org.imgscalr.Scalr library
		return Scalr.resize(image,width,height);
	}

	public static BufferedImage getImage(File file) throws IOException{
		return ImageIO.read(file);
	}

	//This will convert .gif to .png
	//then it will crop and output image file
	public static BufferedImage cropImage(File input) throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//System.out.println("\\nRunning testFaceDetector");

		String filename = input.getName().replaceAll("gif", "png"); //general file name for now
		String outputfilepng = "Pictures//training_dataset_png//";//outputs pngs to this folder for now
		File output = new File(outputfilepng + filename);
		ImageIO.write(getImage(input), "png", output); 
		BufferedImage originalimage = getImage(output);

		CascadeClassifier testFaceDetector = new CascadeClassifier(".\\..\\opencv\\sources\\data\\haarcascades\\haarcascade_mcs_eyepair_big.xml");
		Mat image = Highgui.imread(outputfilepng + filename);

		MatOfRect eyeDetections = new MatOfRect();
		testFaceDetector.detectMultiScale(image, eyeDetections);
		
		try {
		if(eyeDetections.toArray().length == 1) {
			System.out.println(String.format("Detected %s faces", eyeDetections.toArray().length));

			for (Rect rect : eyeDetections.toArray()) {
				double xtopleft = rect.x;
				double xbottomright = rect.x + rect.width;
				double xcenter = (rect.x + (rect.x + rect.width))/2;
				double ytopleft = rect.y;
				double ybottomright = rect.y + rect.height;
				double ycenter = (rect.y + (rect.y + rect.height))/2;

				int newwidth = (int)(1.9 * (3.2/2.43) * rect.width);
				int newheight = (int)(1.9 * rect.width);

				int newYcenter = (int)(ycenter + rect.height);
				int newXright = (int)(xbottomright + newwidth*0.25);
				int newXleft = (int)(xtopleft - newwidth*0.25);
				int newYtop = (int)(newYcenter - newheight/2);
				int newYbottom = (int)(newYcenter + newheight/2);

				Point pointleft = new Point(xtopleft, ytopleft);
				Point pointright = new Point(xbottomright, ybottomright);
				Point pointcenter = new Point(xcenter, ycenter);
				//Core.circle(image,pointright,5,new Scalar(0,255,0));
				Core.rectangle(image, pointleft, pointright, new Scalar(0, 255, 0));

				BufferedImage croppedimage = originalimage.getSubimage(newXleft,newYtop,newwidth,newheight);
				return reSize(croppedimage,250,190);
				//ImageIO.write(crop,"png",new File("Pictures//training_dataset_out//" + output.getName()));
			}
		}
		return reSize(originalimage,250,190);
		}
		catch (Exception ex) {
			return reSize(originalimage,250,190);
		}
	}
}
