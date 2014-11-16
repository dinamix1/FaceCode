package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import data.Face;

public class FaceRecognitionLogic {
	
	private PCALogic pcaLogic;
	private FaceDataLogic dataLogic;
	private static final double THRESHOLD = 3;
	
	public FaceRecognitionLogic(){
		pcaLogic = new PCALogic();
		dataLogic = new FaceDataLogic();
	}
	
	public Face getBestMatch(Face faceToFind, ArrayList<Face> faceList){
		System.out.println("Finding match distnces for: " + faceToFind.getFaceName());
		Map<String,Double> distanceMap = new HashMap<String,Double>();
		
		double bestDistance = -1;
		Face bestMatch = null;
		
		for(Face face : faceList){
			double distance = faceToFind.computeDistance(face);
			if(distance < bestDistance || bestMatch == null){
				bestMatch = face;
				bestDistance = distance;
			}
			System.out.println("Distance from " + face.getFaceName() + ": " + distance);
			distanceMap.put(face.getFaceName(),distance);
		}
		
//		Map<Integer,Double> map = groupAndAverageDistances(distanceMap);
//		Object [] doubleArray = map.values().toArray();
//		double [] weightedAndAveragedDistances = new double[map.size()];
//
//		for(int i = 0; i < doubleArray.length; i++){
//			weightedAndAveragedDistances[i] = (Double) doubleArray[i];
//		}
//		
//		DescriptiveStatistics stats = new DescriptiveStatistics(weightedAndAveragedDistances);
//		double mean = stats.getMean();
//		double std = stats.getStandardDeviation();
//		
//		Map<Integer,Boolean> matchesMap = new HashMap<Integer,Boolean>();
//		for(Integer person : map.keySet()){
//			double invertedAverage = map.get(person);
//			double xScore = (Math.abs(invertedAverage - mean))/std;
//			System.out.println("xSCore for person: " + person + " is: " + xScore);
//			if(xScore > THRESHOLD){
//				matchesMap.put(person, true);
//			}
//			else{
//				matchesMap.put(person, false);
//			}
//		}
//		
//		for(Integer person : matchesMap.keySet()){
//			if(matchesMap.get(person)){
//				System.out.println("Matched person: " + person);
//				return new Face(""+person);
//			}
//		}
		
		return null;
		
	}
	
	public Map<Integer,Double> groupAndAverageDistances(Map<String,Double> distanceMap){
		Map<Integer,Double> groupedDistances = new HashMap<Integer,Double>();
		Pattern pattern = Pattern.compile("[0-9]*\\_[0-9]*\\_");
		int [] counts = new int[distanceMap.size()];
		for(String fileName : distanceMap.keySet()){
			Matcher m = pattern.matcher(fileName);
			String token = "";
			if(m.find()){
				token = m.group();
			}
			String [] tokens = token.split("\\_");
			int personNumber = Integer.parseInt(tokens[0]);
			
			double value = distanceMap.get(fileName);
			counts[personNumber]  = counts[personNumber] + 1;
			Double aggregateValue = groupedDistances.get(personNumber);
			if(aggregateValue != null){
				groupedDistances.put(personNumber, aggregateValue + value);
			}
			else{
				groupedDistances.put(personNumber, value);
			}
			
		}
		
		for(Integer person : groupedDistances.keySet()){
			double value = groupedDistances.get(person);
			value = value/(counts[person]);
			value = 1/value;
			groupedDistances.put(person, value);
		}
		
		return groupedDistances;
	}
	
	public String getFaceMatch(String inputFilename){
		System.out.println("Computing best match");
		ArrayList<Face> faceList = dataLogic.loadFaceDb();
		Face faceToMatch = new Face(inputFilename);
		int [] averageFaceVector = pcaLogic.computeAverageFaceVector(faceList);
		for(Face face : faceList){
			int [] originalFaceVector = face.getOriginalFaceVector();
			int [] differenceVector = pcaLogic.subtractVectors(originalFaceVector, averageFaceVector);
			face.setFaceDifference(differenceVector);
		}
		
		int [] originalFaceVector = faceToMatch.getOriginalFaceVector();
		int [] differenceVector = pcaLogic.subtractVectors(originalFaceVector, averageFaceVector);
		faceToMatch.setFaceDifference(differenceVector);
		
		pcaLogic.runPCA(faceList, faceToMatch);
		Face bestMatch = getBestMatch(faceToMatch, faceList);
		if(bestMatch != null){
			return bestMatch.getFaceName();
		}
		else{
			return null;
		}
	}
	
}
