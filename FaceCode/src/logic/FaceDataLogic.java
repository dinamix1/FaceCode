package logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import data.Face;
import data.HDDDao;
import exception.FaceLoadException;

public class FaceDataLogic {
	private HDDDao dao;
	
	public FaceDataLogic(){
		dao = new HDDDao();
	}
	
	public void addFace(Face face) throws IOException, ClassNotFoundException{
		//Dont go to the HDD every time, fix
		ArrayList<Face> faceList = null;
		if(dao.doesDBFileExist()){
			faceList = dao.readFaces();
		}
		else{
			faceList = new ArrayList<Face>();
		}
		
		faceList.add(face);
		dao.writeFaces(faceList);
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
	
	public boolean doesDbExist(){
		return dao.doesDBFileExist();
		
	}
	
}
