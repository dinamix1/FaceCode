package logic;

import java.util.ArrayList;
import data.Face;

public class FaceRecognitionLogic {
	
	private PCALogic pcaLogic;
	private FaceDataLogic dataLogic;
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
	
	public String getFaceMatch(String inputFilename){
		ArrayList<Face> faceList = dataLogic.loadFaceDb();
		Face faceToMatch = dataLogic.loadUnknownFace(inputFilename);
		int [] averageFaceVector = pcaLogic.computeAverageFaceVector(faceList);
		int [] originalFaceVector = faceToMatch.getOriginalFaceVector();
		int [] differenceVector = pcaLogic.subtractVectors(originalFaceVector, averageFaceVector);
		faceToMatch.setFaceDifference(differenceVector);
		
		pcaLogic.runPCA(faceList, faceToMatch);
		
		
		return null;
	}
	
}
