package data;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Face implements Serializable{
	
	private static final long serialVersionUID = 3594111941132367353L;
	private String faceName;
	private BufferedImage originalFace;
	private int originalImageHeight;
	private int originalImageWidth;
	private int [][] originalFaceVector;
	private int [][] eigenFace;
	
	public Face(String faceName, BufferedImage originalFace){
		this.faceName = faceName;
		this.originalFace = originalFace;
		originalImageHeight = originalFace.getHeight();
		originalImageWidth = originalFace.getWidth();
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
		int [][] faceVector = new int[originalImageHeight][originalImageWidth];
		return null;
	}
	
	public int computeDistance(Face face){
		return -1;
	}
}
