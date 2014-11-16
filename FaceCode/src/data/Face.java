package data;

import java.io.Serializable;

public class Face implements Serializable{
	
	private static final long serialVersionUID = 3594111941132367353L;
	private String faceName;
	private int [][] originalFace;
	private int [] faceDifference;
	
	public Face(String faceName, int [][] originalFace){
		this.faceName = faceName;
		this.originalFace = originalFace;
	}
	
	public void setFaceDifference(int [] faceDifference){
		this.faceDifference = faceDifference;
	}
	
	public int [] getFaceDifference(){
		return faceDifference;
	}
	
	public int [][] getOriginalFace(){
		return originalFace;
	}
	
	public int [] getOriginalFaceVector(){
		return null;
	}
	
	public int computeDistance(Face face){
		return -1;
	}
}
