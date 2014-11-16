package logic;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import data.Face;

public class PCALogicTest {
	
	private PCALogic testLogic;
	@Mock private Face mockFace1;
	@Mock private Face mockFace2;
	@Mock private Face mockFace3;
	@Mock private Face mockFace4;
	
	@Before
	public void before(){
		MockitoAnnotations.initMocks(this);
		testLogic = new PCALogic();
	}
	
	@Test
	public void testComputeAverageFace(){
		
		int [] v1 = {1,2,3,4,5};
		when(mockFace1.getOriginalFaceVector()).thenReturn(v1);
		
		int [] v2 = {2,3,4,5,6};
		when(mockFace2.getOriginalFaceVector()).thenReturn(v2);
		
		int [] v3 = {3,2,1,5,6};
		when(mockFace3.getOriginalFaceVector()).thenReturn(v3);
		
		int [] v4 = {20,10,4,1,10};
		when(mockFace4.getOriginalFaceVector()).thenReturn(v4);
		
		ArrayList<Face> faceList = new ArrayList<Face>();
		faceList.add(mockFace1);
		faceList.add(mockFace2);
		faceList.add(mockFace3);
		faceList.add(mockFace4);
		
		int[] averageFace = testLogic.computeAverageFaceVector(faceList);
		
		assertEquals(averageFace[0],(1+2+3+20)/4);
		assertEquals(averageFace[1],(2+3+2+10)/4);
		assertEquals(averageFace[2],(3+4+1+4)/4);
		assertEquals(averageFace[3],(4+5+5+1)/4);
		assertEquals(averageFace[4],(5+6+6+10)/4);
		
		
		
	}
	
}
