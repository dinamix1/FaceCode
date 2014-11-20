package utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import logic.PCALogic;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Jama.Matrix;
import data.Face;

public class LinearAlgebraUtilsTest {
	private LinearAlgebraUtils testUtil;
	@Mock private Face mockFace1;
	@Mock private Face mockFace2;
	@Mock private Face mockFace3;
	@Mock private Face mockFace4;
	
	@Before
	public void before(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFaceListToMatrix(){
		
		int [] v1 = {1,2,3,4,5};
		when(mockFace1.getFaceDifference()).thenReturn(v1);
		
		int [] v2 = {2,3,4,5,6};
		when(mockFace2.getFaceDifference()).thenReturn(v2);
		
		int [] v3 = {3,2,1,5,6};
		when(mockFace3.getFaceDifference()).thenReturn(v3);
		
		int [] v4 = {20,10,4,1,10};
		when(mockFace4.getFaceDifference()).thenReturn(v4);
		
		ArrayList<Face> faceList = new ArrayList<Face>();
		faceList.add(mockFace1);
		faceList.add(mockFace2);
		faceList.add(mockFace3);
		faceList.add(mockFace4);
		
		Matrix differenceMatrix = LinearAlgebraUtils.getFaceDifferencesAsMatrix(faceList);
		
		for(int i = 0; i < differenceMatrix.getColumnDimension(); i++){
			int [] differenceVector = faceList.get(i).getFaceDifference();

			for(int j = 0; j < differenceMatrix.getRowDimension(); j++){
				assertEquals(differenceVector[j], (int) differenceMatrix.get(j,i));
			}
		}
	}
}
