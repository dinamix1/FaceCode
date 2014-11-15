package logic;

import java.util.ArrayList;
import data.Face;

public class FaceRecognitionLogic {
	
	private static final int DISTANCE_THRESHOLD = 10;
	
	public boolean isFaceInList(Face faceToFind, ArrayList<Face> faceList){
		Face bestMatch = null;
		int bestMatchDistance = -1;
		
		for(Face face : faceList){
			int distance = faceToFind.computeDistance(face);
			if(distance > bestMatchDistance){
				continue;
			}
			
			bestMatch = face;
			bestMatchDistance = distance;
		}
		
		if(bestMatchDistance < DISTANCE_THRESHOLD && bestMatchDistance >= 0){
			return true;
		}
		else{
			return false;
		}
	}
	
}
