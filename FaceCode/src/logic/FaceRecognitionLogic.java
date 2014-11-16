package logic;

import java.util.ArrayList;
import data.Face;

public class FaceRecognitionLogic {
	
	private PCALogic pcaLogic;
	private FaceDataLogic dataLogic;
	private static final double DISTANCE_THRESHOLD = 10;
	
	public Face getBestMatch(Face faceToFind, ArrayList<Face> faceList){
		Face bestMatch = null;
		double bestMatchDistance = -1;
		
		for(Face face : faceList){
			double distance = faceToFind.computeDistance(face);
			if(distance > bestMatchDistance){
				continue;
			}
			
			bestMatch = face;
			bestMatchDistance = distance;
		}
		
		if(bestMatchDistance < DISTANCE_THRESHOLD && bestMatchDistance >= 0){
			return bestMatch;
		}
		else{
			return null;
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
		Face bestMatch = getBestMatch(faceToMatch, faceList);
		if(bestMatch != null){
			return bestMatch.getFaceName();
		}
		else{
			return null;
		}
	}
	
}
