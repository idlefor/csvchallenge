package com.identeo.challenge.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.identeo.challenge.model.StoreOrder;
import com.identeo.challenge.service.StoreOrderService;


/**
 * Unit test for the Rest Controller StoreOrderRestController
 * 
 * @author iden.teo
 */
@SpringBootTest()
@AutoConfigureMockMvc
public class StoreOrderRestControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StoreOrderService serviceMock;

	@Test
	public void testFindAllStoreOrder_Work_WhenProvideValidUrl() throws Exception {
		final List<StoreOrder> storeList = setUpOrderList();
		when(serviceMock.findAll()).thenReturn(storeList);

		mockMvc.perform(get("/app/v1/order/all")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(jsonPath("$.length()").value(storeList.size()))
				.andExpect(jsonPath("$[0].id").value("1")) 
				.andExpect(jsonPath("$[0].category").value("toy"))
				.andExpect(jsonPath("$[0].customerName").value("DisneyWorld"))
				.andExpect(jsonPath("$[0].customerId").value("23342"))
				.andExpect(jsonPath("$[0].productId").value("00293DSS001"))
				.andExpect(jsonPath("$[0].productName").value("Rifle Toy"))
				.andExpect(jsonPath("$[1].id").value("2")) 
				.andExpect(jsonPath("$[1].category").value("toy"))
				.andExpect(jsonPath("$[1].customerName").value("DisneyWorld"))
				.andExpect(jsonPath("$[1].customerId").value("23342"))
				.andExpect(jsonPath("$[1].productId").value("002032ERS001"))
				.andExpect(jsonPath("$[1].productName").value("Rifle Magazine"));
	}

	@Test
	public void testFindAllStoreOrder_Fail_WhenProvideInValidUrl() throws Exception {
		mockMvc.perform(get("/app/v2/order/allss")).andExpect(status().isNotFound());
	}
	
	@Test
	public void testSetGoodCSVFileIntoDB_Work_WhenProvideValidUrl() throws Exception {
		mockMvc.perform(post("http://localhost:8080/app/v1/order/readGoodCSVFileIntoDB")).andExpect(status().isOk());
	}
	
	@Test
	public void testSetReadCSVDataIntoDB_Fail_WhenProvideInValidUrl() throws Exception {
		mockMvc.perform(post("http://localhost:8080/app/v2/order/readCSVFileIntoDB")).andExpect(status().isNotFound());
	}
	
	private List<StoreOrder> setUpOrderList() {
		List<StoreOrder> orderList = new ArrayList<StoreOrder>();
		final StoreOrder s1 = new StoreOrder();
		s1.setId(1l);
		s1.setCategory("toy");
		s1.setCustomerId("23342");
		s1.setCustomerName("DisneyWorld");
		s1.setOrderId("0000012");
		s1.setProductId("00293DSS001");
		s1.setQuantity(1L);
		s1.setShipMode("Container");
		s1.setProductName("Rifle Toy");
		orderList.add(s1);

		final StoreOrder s2 = new StoreOrder();
		s2.setId(2l);
		s2.setCategory("toy");
		s2.setCustomerId("23342");
		s2.setCustomerName("DisneyWorld");
		s2.setOrderId("0000002");
		s2.setProductId("002032ERS001");
		s2.setQuantity(2L);
		s2.setShipMode("Container");
		s2.setProductName("Rifle Magazine");
		orderList.add(s2);

		return orderList;
	}
}
