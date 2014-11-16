package app;

import logic.FaceRecognitionLogic;

public class Main {
	
	private static FaceRecognitionLogic logic;
	
	public static void main(String [] args){
		
		if(args.length != 2){
			System.out.println("Usage: facerecognition [filename]");
			System.exit(0);
		}
		
		String fileName = args[1];
		
		logic = new FaceRecognitionLogic();
		String faceMatch = logic.getFaceMatch(fileName);
		
		if(faceMatch == null){
			System.out.println("No match found");
		}
		else{
			System.out.println("Match found: " + faceMatch);
		}
		
	}
}
