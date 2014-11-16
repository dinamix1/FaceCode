package data;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Face implements Serializable{
	
	private static final long serialVersionUID = 3594111941132367353L;
	private String faceName;
	private BufferedImage originalFace;
	private int originalImageHeight;
	private int originalImageWidth;
	private int [] originalFaceVector;
	private int [][] eigenFace;
	private int [] faceDifference;
	
	public Face(String faceName, BufferedImage originalFace){
		this.faceName = faceName;
		this.originalFace = originalFace;
		originalImageHeight = originalFace.getHeight();
		originalImageWidth = originalFace.getWidth();
	}
	
	public void setFaceDifference(int [] faceDifference){
		this.faceDifference = faceDifference;
	}
	
	public int [] getFaceDifference(){
		return faceDifference;
	}
	
	public BufferedImage getOriginalFace(){
		return originalFace;
	}
	
	public int [] getOriginalFaceVector(){
		int [][] faceVector = new int[originalImageHeight][originalImageWidth];
		return null;
	}
	
	public int computeDistance(Face face){
		return -1;
	}
}
