package app;

import java.io.File;

import utils.ImageUtils;

import com.sun.imageio.plugins.common.ImageUtil;

import data.Face;
import exception.FaceLoadException;
import logic.FaceDataLogic;
import logic.FaceRecognitionLogic;

public class Main {
	
	private static FaceRecognitionLogic logic;
	private static FaceDataLogic dataLogic;
	
	public static void main(String [] args){
		
		logic = new FaceRecognitionLogic();
		dataLogic = new FaceDataLogic();
		
		if(args[0].equals("load")){
			load(args[1]);
			System.exit(0);
		}
		
		if(args.length != 1){
			System.out.println("Usage: facerecognition [filename]");
			System.exit(0);
		}
		
		String fileName = args[0];
		
		logic = new FaceRecognitionLogic();
		String faceMatch = logic.getFaceMatch(fileName);
		
		if(faceMatch == null){
			System.out.println("No match found");
		}
		else{
			System.out.println("Match found: " + faceMatch);
		}

	}

	private static void load(String directory){
		File dbDirectory = new File(directory);
		try{
			for(File picture : dbDirectory.listFiles()){
				if(!isAcceptableFormat(picture.getPath())){
					continue;
				}
				
				Face face = new Face(picture.getPath());
				dataLogic.addFace(face);
			}
		} catch(Exception e){
			throw new FaceLoadException(e);
		}
		
	}
	
	public static boolean isAcceptableFormat(String path){
		if(path.contains("gif") || path.contains("png")){
			return true;
		}
		else{
			return false;
		}
	}
}
