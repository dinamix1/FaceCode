package data;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Face implements Serializable{
	
	private static final long serialVersionUID = 3594111941132367353L;
	private String faceName;
	private BufferedImage originalFace;
	private int [][] eigenFace;
	private int [] faceDifference;
	
	public Face(String faceName, BufferedImage originalFace){
		this.faceName = faceName;
		this.originalFace = originalFace;
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
		int[] originalFaceVector = new int[originalFace.getWidth() * originalFace.getHeight()];
		
		//Used to keep track of where we are in 1D face vector array
		int originalFaceVectorTracking = 0;
		
		//Converts RGB to grayscale and inputs it into originlaFaceVector
        for (int x = 0; x < originalFace.getWidth(); x++) {
            for (int y = 0; y < originalFace.getHeight(); y++) {
                Color color = new Color(originalFace.getRGB(x, y));
                
                //Averages out RGB to get an average algorithm grayscale value
                int grayscale = (color.getRed() + color.getGreen() + color.getBlue())/3;

                originalFaceVector[originalFaceVectorTracking++] = grayscale;
            }
        }
		return originalFaceVector;
	}
	
	public int computeDistance(Face face){
		return -1;
	}
}
