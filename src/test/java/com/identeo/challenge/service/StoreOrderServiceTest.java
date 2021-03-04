package com.identeo.challenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import com.identeo.challenge.model.StoreOrder;
import com.identeo.challenge.repository.StoreOrderRepository;

/**
 * Unit test for the Rest Controller StoreOrderRestController
 * Using JUnit 5 her to perform assertion assessment.
 * 
 * @author iden.teo
 */
@SpringBootTest()
public class StoreOrderServiceTest {
	
	@InjectMocks
	private StoreOrderService service;
	
	@MockBean
	private Resource mockResource;
	@MockBean
	private PathMatchingResourcePatternResolver mockPathMatchingResourcePatternResolver;
	@MockBean
	private StoreOrderRepository mockReposity;

	@Test
	public void testScanFolderToReadCSVFile_ThrowFileNotFoundException_IfProvideInvalidFilePathResource() {		
		Assertions.assertThrows(FileNotFoundException.class, () -> {
			service.scanFolderToReadCSVFile("invalidFolderPath");
		});
	}	
	
	@Test
	public void testSaveParsedDataToDB_Work_WhenProvideListOfCsvBeans() throws Exception {
		List<StoreOrder> csvRecordlist = setUpStoreOrderList();		
		mockReposity.saveAll(csvRecordlist);
		
		assertNotNull(mockReposity.findById(1L));
		assertNotNull(mockReposity.findById(2L));
	}

	@Test
	public void testSaveParsedDataToDB_Work_WhenProvideNothing() throws Exception {	
		mockReposity.saveAll(new ArrayList<StoreOrder>());
		
		assertEquals(mockReposity.count() , 0);
	}
	
	private List<StoreOrder> setUpStoreOrderList() {
		List<StoreOrder> csvRecordlist = new ArrayList<StoreOrder>();
		StoreOrder s1 = new StoreOrder();
		s1.setId(1L);
		s1.setCustomerId("223131");
		s1.setCustomerName("Decathlon");
		s1.setProductId("0000231");
		s1.setCategory("toy");
		s1.setOrderId("s000923");
		s1.setQuantity(1);
		s1.setShipMode("land");
		csvRecordlist.add(s1);
		StoreOrder s2 = new StoreOrder();
		s2.setId(2L);
		s2.setCustomerId("109234");
		s2.setCustomerName("Decathlon");
		s2.setProductId("0000201");
		s2.setCategory("toy");
		s2.setOrderId("s000223");
		s2.setQuantity(2);
		s2.setShipMode("land");
		csvRecordlist.add(s2);
		
		return csvRecordlist;
	}
	
}
