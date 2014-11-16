package logic;

import java.util.ArrayList;

import utils.LinearAlgebraUtils;
import data.Face;
import Jama.Matrix;
import Jama.EigenvalueDecomposition;


public class PCALogic {

	
	public int [] computeAverageFaceVector(ArrayList<Face> faceList){
		System.out.println("Computing Average face");
		
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
		System.out.println("Computing differences");
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
		System.out.println("Computing covariance");
		Matrix aTranspose = aMatrix.transpose();
		return aTranspose.times(aMatrix);
	}
	//Return a matrix of eigenvectors (columns)
	public Matrix computeEigenVectors(Matrix covariance){ 
		System.out.println("Computing EigenVectors");
		EigenvalueDecomposition eigenVectors = new EigenvalueDecomposition(covariance);
		Matrix eigenVectorsMatrix = eigenVectors.getV();
		double[][] eig = eigenVectorsMatrix.getArray();
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				eig[i][j] = eig[i][j]/eig[3][j]; //normalize the array
			}
		}
		return new Matrix(eig);
	}
	//Return a matrix of eigenfaces (columns)
	public Matrix computeEigenFaces(Matrix aMatrix,Matrix eigenVectorsMatrix){
		System.out.println("Computing Eigenfaces");
		Matrix eigenFaces = aMatrix.times(eigenVectorsMatrix);
		return eigenFaces;
	}
	//Return a matrix of weights in columns
	public Matrix weights(Matrix eigenFaces,Matrix aMatrix){ 
		System.out.println("Computing weights");
		Matrix eigenFacesTranspose = eigenFaces.transpose();
		Matrix weightMatrix = eigenFacesTranspose.times(aMatrix);
	    return weightMatrix;
	}
	
	public void runPCA(ArrayList<Face> faceList, Face faceToMatch){
		System.out.println("Running PCA");
		Matrix diffMatrix = LinearAlgebraUtils.getFaceDifferencesAsMatrix(faceList);
		Matrix covariance = computeCovariance(diffMatrix);
		Matrix eigenvectors = computeEigenVectors(covariance);
		Matrix eigenfaces = computeEigenFaces(diffMatrix,eigenvectors);
		Matrix weightsMatrix = weights(eigenfaces,diffMatrix);
		
		for(int i = 0; i < faceList.size(); i++){
			double [] weightVector = LinearAlgebraUtils.getColumnVector(weightsMatrix, i);
			faceList.get(i).setWeightVector(weightVector);
		}
		
		double [] diffVector = faceToMatch.getFaceDifferenceAsDoubles(); 
		double [][] fakeMatrix = new double[diffVector.length][1];
		
		for(int i = 0; i < diffVector.length; i++){
			fakeMatrix[i][0] = diffVector[i];
		}
		
		Matrix diffNewFace = new Matrix(fakeMatrix);
		Matrix newFaceWeight = weights(eigenfaces,diffNewFace);
		double [] unknownWeights = LinearAlgebraUtils.getColumnVector(newFaceWeight, 0);
		faceToMatch.setWeightVector(unknownWeights);
	}
}
