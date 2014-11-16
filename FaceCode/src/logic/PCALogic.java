package logic;

import java.util.ArrayList;

import data.Face;

public class PCALogic {

	
	public int [] computeAverageFaceVector(ArrayList<Face> faceList){
		
		if(faceList.isEmpty()){
			return null;
		}
		
		int vectorSize = faceList.get(0).getOriginalFaceVector().length;
		int noFaces = faceList.size();
		int [] averageFaceVector = new int[vectorSize];
		
		for(int i = 0; i < vectorSize; i++){
			
			int sumAtI = 0;
			for(Face face : faceList){
				int [] faceVector = face.getOriginalFaceVector();
				sumAtI  = sumAtI + faceVector[i];
			}
			
			averageFaceVector[i] = sumAtI / noFaces;
		}
		
		
		return averageFaceVector;
	}
	
	public void computeAverageFaceDifferences(int [] averageFace, ArrayList<Face> faceList){
		for (Face face : faceList){
			int [] originalFaceVector = face.getOriginalFaceVector();
			int [] faceDifference = subtractVectors(originalFaceVector, averageFace);
			face.setFaceDifference(faceDifference);
		}
	}
	
	private int [] subtractVectors(int [] v1, int [] v2){
		int [] difference = new int[v1.length];
		
		for(int i = 0; i < difference.length; i++){
			difference[i] = v1[i] - v2[i];
		}
		
		return difference;
	}
	
	
}
