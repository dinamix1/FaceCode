package data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import utils.ImageUtils;

public class HDDDao {
	private static final String FILE_NAME = "faces.ser";
	
	public void writeFaces(ArrayList<Face> faceList) throws IOException{
		System.out.println("Computing faces to disk");
		FileOutputStream fos = new FileOutputStream(FILE_NAME);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(faceList);
		oos.close();
	}
	
	public ArrayList<Face> readFaces() throws IOException, ClassNotFoundException{
		System.out.println("Reading faces from disk");
		ArrayList<Face> faceList;
		
		FileInputStream fis = new FileInputStream(FILE_NAME);
		ObjectInputStream ois = new ObjectInputStream(fis);
		faceList = (ArrayList<Face>) ois.readObject();
		ois.close();
		return faceList;
	}
	
	public boolean doesDBFileExist(){
		File file = new File(FILE_NAME);
		return file.exists();
	}
}
