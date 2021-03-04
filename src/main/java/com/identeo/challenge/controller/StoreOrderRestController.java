package com.identeo.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.identeo.challenge.model.StoreOrder;
import com.identeo.challenge.service.StoreOrderService;

/**
 * This Rest controller will interact with the service call to fetch all records
 * store in database or attempt to read any store csv file reside in the
 * /src/main/resources/input folder and store into database.
 * 
 * @author iden.teo
 */
@RestController
@RequestMapping("app/v1/order")
public class StoreOrderRestController {

	private static final String WORKING_CSV_FOLDER = "goodinput";
	private static final String NON_WORKING_CSV_FOLDER = "badinput";
	
	@Autowired
	private StoreOrderService service;

	/**
	 * Used to fetch all the store sale orders from DB
	 * 
	 * @return list of {@link StoreOrder}
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<List<StoreOrder>> findAllStoreOrder() {
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Finding all Records");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(service.findAll());
	}

	/**
	 * Trigger the program to read a good csv file and store their content into
	 * database. 
	 * 
	 * This is positive scenario where the file integrity is correct
	 * ( no null, no duplicate value in key column)
	 * The file's content sale.csv was tampered to provide a working
	 * example this program can work
	 * 
	 * @throws Exception
	 */
	@PostMapping(path = "/readGoodCSVFileIntoDB")
	public ResponseEntity<Void> setGoodCSVFileIntoDB() throws Exception {
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Insert Good CSV Record Into Database");
		
		service.scanFolderToReadCSVFile(WORKING_CSV_FOLDER);
		return ResponseEntity.status(HttpStatus.OK).headers(header).build();
	}
	
	/**
	 * Trigger the program to read a bad csv file and store their content into
	 * database. 
	 * 
	 * This is negative scenario where the file integrity is incorrect
	 * ( data have null entry, multiple duplicate value in key column)
	 * The file's content sale.csv is original document we per provided.
	 * 
	 * @throws Exception
	 */
	@PostMapping(path = "/readBadCSVFileIntoDB")
	public ResponseEntity<Void> setBadCSVFileIntoDB() throws Exception {
		HttpHeaders header = new HttpHeaders();
		header.add("Description", "Insert Bad CSV Record Into Database");
		
		service.scanFolderToReadCSVFile(NON_WORKING_CSV_FOLDER);
		return ResponseEntity.status(HttpStatus.OK).headers(header).build();
	}

}
