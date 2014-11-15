package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class HDDDao {
	private static final String FILE_NAME = "faces.db";
	
	public void writeFaces(ArrayList<Face> faceList) throws IOException{
		FileOutputStream fos = new FileOutputStream(FILE_NAME);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(faceList);
		oos.flush();
		oos.close();
	}
	
	public ArrayList<Face> readFaces() throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream(FILE_NAME);
		ObjectInputStream ois = new ObjectInputStream(fis);
		ArrayList<Face> faceList = (ArrayList<Face>) ois.readObject();
		return faceList;
		
	}
}
