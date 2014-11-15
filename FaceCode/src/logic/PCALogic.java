package logic;

import java.util.ArrayList;

import data.Face;
import Jama.Matrix;
import Jama.EigenvalueDecomposition;


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
	
	protected int [] subtractVectors(int [] v1, int [] v2){
		int [] difference = new int[v1.length];
		
		for(int i = 0; i < difference.length; i++){
			difference[i] = v1[i] - v2[i];
		}
		
		return difference;
	}
	//Return covariance matrix AT * A
	public Matrix computeCovariance(Matrix aMatrix){ 
		Matrix aTranspose = aMatrix.transpose();
		return aTranspose.times(aMatrix);
	}
	//Return a matrix of eigenvectors (columns)
	public Matrix computeEigenVectors(Matrix covariance){ 
		EigenvalueDecomposition eigenVectors = new EigenvalueDecomposition(covariance);
		return eigenVectors.getV();
	}
	//Return a matrix of eigenfaces (columns)
	public Matrix computeEigenFaces(Matrix aMatrix,Matrix eigenVectorsMatrix){
		Matrix eigenFaces = aMatrix.times(eigenVectorsMatrix);
		return eigenFaces;
	}
	//Return a matrix of weights in columns
	public Matrix weights(Matrix eigenFaces,Matrix aMatrix){ 
		Matrix eigenFacesTranspose = eigenFaces.transpose();
		Matrix weightMatrix = eigenFacesTranspose.times(aMatrix);
	    return weightMatrix;
	}
}
