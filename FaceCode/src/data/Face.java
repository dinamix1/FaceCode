package data;

import java.io.Serializable;

public class Face implements Serializable{
	
	private static final long serialVersionUID = 3594111941132367353L;
	private String faceName;
	private int [][] originalFace;
	private int [][] eigenFace;
	
	public Face(String faceName, int [][] originalFace){
		this.faceName = faceName;
		this.originalFace = originalFace;
	}
	
	public void setEigenFace(int [][] eigenFace){
		this.eigenFace = eigenFace;
	}
	
	public int [][] getEigenFace(){
		return eigenFace;
	}
	
	public int [][] getOriginalFace(){
		return eigenFace;
	}
	
	public int [] getOriginalFaceVector(){
		return null;
	}
	
	public int computeDistance(Face face){
		return -1;
	}
}
