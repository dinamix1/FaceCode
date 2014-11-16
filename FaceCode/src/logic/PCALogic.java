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
}
