package logic;

import java.io.IOException;
import java.util.ArrayList;

import data.Face;
import data.HDDDao;
import exception.FaceLoadException;

public class FaceDataLogic {
	private HDDDao dao;
	
	public void addFace(Face face){
		//Use dao to get current faces
	}
	
	public ArrayList<Face> loadFaceDb() throws FaceLoadException{
		try {
			return dao.readFaces();
		} catch (ClassNotFoundException e) {
			throw new FaceLoadException(e);
		} catch (IOException e) {
			throw new FaceLoadException(e);
		}
	}
	
	public Face loadUnknownFace(String fileName){
		return dao.loadUnknownFace(fileName);
	}
}
