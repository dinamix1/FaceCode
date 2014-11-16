package utils;

import java.util.ArrayList;

import Jama.Matrix;
import data.Face;

public class LinearAlgebraUtils {
	
	public static Matrix getFaceDifferencesAsMatrix(ArrayList<Face> faceList){
		
		int faceDifferenceVectorSize = faceList.get(0).getFaceDifference().length; 
		
		//Each face difference vector is a column vector in the matrix
		Matrix matrix = new Matrix(faceList.size(), faceDifferenceVectorSize);
		
		for(int i = 0;i < faceList.size(); i++){
			
			Face face = faceList.get(i);
			int [] faceDifferenceVector = face.getFaceDifference();
			
			for(int j = 0; j < faceDifferenceVectorSize; j++){
				matrix.set(j, i, faceDifferenceVector[j]);
			}
		}
		
		return matrix;
	}
}
