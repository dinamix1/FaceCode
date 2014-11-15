package test;
import logic.PCALogic;
import Jama.Matrix;

public class testJama {

	public static void main(String[] args) {
		
		double [][] array = {{1,2,3},{4,5,6},{7,8,9}};
		Matrix A = new Matrix(array);
		
		
		PCALogic test = new PCALogic();
		
		Matrix covariance = test.computeCovariance(A);
		Matrix eigenvectors = test.computeEigenVectors(covariance);
		double[][] cov = covariance.getArray();
		double[][] eig = eigenvectors.getArray();
		
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				System.out.print(cov[i][j] + " ");
			}
			System.out.println("");
		}
		
		System.out.println("");
		
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				System.out.print(eig[i][j] + " ");
			}
			System.out.println("");
		}
		
		
		
		
		
		

	}

}
