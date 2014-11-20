package app;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.ImageUtils;

import com.sun.imageio.plugins.common.ImageUtil;

import data.Face;
import exception.FaceLoadException;
import logic.FaceDataLogic;
import logic.FaceRecognitionLogic;

public class Main {
	
	private static final String DEFAULT_DIRECTORY = "/CodeJam/trainingDataset";
	private static FaceRecognitionLogic logic;
	private static FaceDataLogic dataLogic;
	
	public static void main(String [] args){
		long startTime = System.currentTimeMillis();
		
		logic = new FaceRecognitionLogic();
		dataLogic = new FaceDataLogic();

		load(DEFAULT_DIRECTORY);
		
		if(args.length != 1){
			System.out.println("Usage: facerecognition [filename]");
			System.exit(0);
		}
		
		String fileName = args[0];
		
		logic = new FaceRecognitionLogic();
		String faceMatch = logic.getFaceMatch(fileName);
		Pattern pattern = Pattern.compile("[0-9]*\\_[0-9]*\\_");
		Matcher m = pattern.matcher(fileName);
		String token = "";
		if(m.find()){
			token = m.group();
		}
		String [] tokens = token.split("\\_");
		int personNumber = Integer.parseInt(tokens[0]);
		System.out.println(personNumber);
		
		long endTime = System.currentTimeMillis();
		
		long totalTime = endTime - startTime;
		totalTime = totalTime/1000;

	}

	private static void load(String directory){
		//SHort circuit if we've already loaded files
		if(dataLogic.doesDbExist()){
			return;
		}
		
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
