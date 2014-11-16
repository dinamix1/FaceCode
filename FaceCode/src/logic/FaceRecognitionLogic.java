package logic;

import java.util.ArrayList;

import data.Face;

public class FaceRecognitionLogic {
	
	private PCALogic pcaLogic;
	private FaceDataLogic dataLogic;
	private static final double DISTANCE_THRESHOLD = 10;
	
	public FaceRecognitionLogic(){
		pcaLogic = new PCALogic();
		dataLogic = new FaceDataLogic();
	}
	
	public Face getBestMatch(Face faceToFind, ArrayList<Face> faceList){
		System.out.println("Finding match distnces for: " + faceToFind.getFaceName());
		Face bestMatch = null;
		double bestMatchDistance = -1;
		
		for(Face face : faceList){
			double distance = faceToFind.computeDistance(face);
			System.out.println("DIstance from " + face.getFaceName() + ": " + distance);
			if(distance > bestMatchDistance){
				continue;
			}
			
			bestMatch = face;
			bestMatchDistance = distance;
		}
		
		if(bestMatchDistance < DISTANCE_THRESHOLD && bestMatchDistance >= 0){
			System.out.println("Best match with distance: " + bestMatchDistance);
			return bestMatch;
		}
		else{
			return null;
		}
	}
	
	public String getFaceMatch(String inputFilename){
		System.out.println("Computing best match");
		ArrayList<Face> faceList = dataLogic.loadFaceDb();
		Face faceToMatch = new Face(inputFilename);
		int [] averageFaceVector = pcaLogic.computeAverageFaceVector(faceList);
		for(Face face : faceList){
			int [] originalFaceVector = face.getOriginalFaceVector();
			int [] differenceVector = pcaLogic.subtractVectors(originalFaceVector, averageFaceVector);
			face.setFaceDifference(differenceVector);
		}
		
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
