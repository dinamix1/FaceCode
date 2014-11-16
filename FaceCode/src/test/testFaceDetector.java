package test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class testFaceDetector {
	
	public static void main(String[] args) throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.out.println("\\nRunning testFaceDetector");
		
		//File input = new File("Pictures//training_dataset//1_10_.gif");
		//File output = new File("Pictures//training_dataset_png//1_10_.png");
		//ImageIO.write( ImageIO.read( input ), "png", output);
		
		CascadeClassifier testFaceDetector = new CascadeClassifier("C:\\Users\\Tristano\\Documents\\Code_Jam\\opencv\\sources\\data\\haarcascades\\haarcascade_mcs_eyepair_big.xml");
		Mat image = Highgui.imread("Pictures//womenclosed.jpg");
		MatOfRect eyeDetections = new MatOfRect();
		testFaceDetector.detectMultiScale(image, eyeDetections);
		
		System.out.println(String.format("Detected %s faces", eyeDetections.toArray().length));
		
		for (Rect rect : eyeDetections.toArray()) {
			double xtopleft = rect.x;
			double xbottomright = rect.x + rect.width;
			double xcenter = (rect.x + (rect.x + rect.width))/2;
			double ytopleft = rect.y;
			double ybottomright = rect.y + rect.height;
			double ycenter = (rect.y + (rect.y + rect.height))/2;
			
			double newwidth = 1.5 * rect.width;
			double newheight = 1.7 * rect.width;
			
			double newYcenter = ycenter + rect.height;
			double newXright = xbottomright + newwidth*0.25;
			double newXleft = xtopleft - newwidth*0.25;
			double newYtop = newYcenter + newheight/2;
			double newYbottom = newYcenter - newheight/2;
			
			Point pointleft = new Point(xtopleft, ytopleft);
			Point pointright = new Point(xbottomright, ybottomright);
			Point pointcenter = new Point(xcenter, ycenter);
			//Core.circle(image,pointright,5,new Scalar(0,255,0));
			Core.rectangle(image, pointleft, pointright, new Scalar(0, 255, 0));
		}
		
		String filename = "womeneyes.png";
		System.out.println(String.format("Writing %s", filename));
		Highgui.imwrite(filename, image);
	}
}