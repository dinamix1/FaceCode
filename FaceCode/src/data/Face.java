package data;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import exception.FaceLoadException;

public class Face implements Serializable{
	
	private static final long serialVersionUID = 3594111941132367353L;
	private String faceName;
	transient private BufferedImage originalFace = null;
	private int [] faceDifference;
	private double [] weightVector;
	transient private int [] originalFaceVector = null;
	
	public Face(String faceName){
		this.faceName = faceName;
	}
	
	public void setWeightVector(double [] weights){
		weightVector = weights;
	}
	
	public String getFaceName(){
		return faceName;
	}
	
	public double [] getWeightVector(){
		return weightVector;
	}
	
	public void setFaceDifference(int [] faceDifference){
		this.faceDifference = faceDifference;
	}
	
	public int [] getFaceDifference(){
		return faceDifference;
	}
	
	public double [] getFaceDifferenceAsDoubles(){
		double [] differences = new double[faceDifference.length];
		for(int i = 0; i < faceDifference.length; i++){
			differences[i] = (double) faceDifference[i];
		}
		
		return differences;
	}
	
	public BufferedImage getOriginalFace(){
		//Lazily load the file
		if(originalFace != null){
			return originalFace;
		}
		else{
			try {
				System.out.println("Loading image: " + faceName);
				originalFace = ImageIO.read(new File(faceName));
				if(originalFace == null){
					System.out.println("File: " + faceName + " returned null image");
				}
			} catch (IOException e) {
				throw new FaceLoadException(e);
			}
			
			return originalFace;
		}
		
		
	}
	
	public int [] getOriginalFaceVector(){
		if(originalFaceVector != null){
			return originalFaceVector;
		}
		
		int[] originalFaceVector = new int[getOriginalFace().getWidth() * getOriginalFace().getHeight()];
		
		//Used to keep track of where we are in 1D face vector array
		int index = 0;
		
		//Converts RGB to grayscale and inputs it into originlaFaceVector
        for (int y = 0; y < originalFace.getHeight(); y++) {
            for (int x = 0; x < originalFace.getWidth(); x++) {
                Color color = new Color(originalFace.getRGB(x, y));
                
                //Averages out RGB to get an average algorithm grayscale value
                int grayscale = (color.getRed() + color.getGreen() + color.getBlue())/3;

                originalFaceVector[index] = grayscale;
                index++;
            }
        }
        
        this.originalFaceVector = originalFaceVector;
		return originalFaceVector;
	}
	
	public double computeDistance(Face face){
		if(this.weightVector.length != face.weightVector.length){
			return -1;
		}
		
		double [] newFaceWeights = face.getWeightVector();
		double [] thisFaceWeights = this.getWeightVector();
		
		double total = 0;
		
		for(int i = 0; i < newFaceWeights.length; i++){
			double diff = newFaceWeights[i] - thisFaceWeights[i];
			total = total + Math.pow(diff, 2);
		}
		
		double result = Math.sqrt(total);
		return result;
	}
}
