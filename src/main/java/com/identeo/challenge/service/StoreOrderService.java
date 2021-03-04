package com.identeo.challenge.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import com.identeo.challenge.model.StoreOrder;
import com.identeo.challenge.repository.StoreOrderRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvRuntimeException;

/**
 * This Service contain the business logic to read the csv file and store them
 * to database.
 * 
 * It consists of 3 part, scan the folder, read the csv file and parse it
 * and store the content into database using Repository Interface see
 * {@link StoreOrderRepository.java} 
 * 
 * Please see {@link StoreOrder.java} for details on how we use OpenCSV
 * CsvBindByName and CsvDate on how we control the parser operation
 * 
 * @author iden.teo
 */
@Service
public class StoreOrderService {

	@Autowired
	private StoreOrderRepository repository;

	/**
	 * This method utilize the jpaRepository findAll method to
	 * return all record found in the StoreOrderRepository
	 * 
	 *
	 */
	public List<StoreOrder> findAll() {
		return repository.findAll();
	}

	/**
	 * This method is the scan the specific folder and attempt to read any csv file
	 * and parse them and store the content into database
	 * 
	 * 	@param resource
	 * 			input folder resource 
	 * 
	 * @throws Exception, FileNotFoundException
	 */
	public void scanFolderToReadCSVFile(final String inputFolderName) throws Exception, FileNotFoundException {
		try {
			PathMatchingResourcePatternResolver scanner = new PathMatchingResourcePatternResolver();
			Resource[] resources = scanner.getResources(inputFolderName+"/*");

			if (resources == null || resources.length == 0) {
				System.out.println("Warning: could not find any resources in this folder: " + inputFolderName);
			} else {
				for (Resource resource : resources) {
					System.out.println("attempting reading csv file : " + resource.getFilename());					
					
					parseCSVContentSaveToDB(resource);
				}
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Failed to find any file to read : " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Failed to read the resources folder: " + e.getMessage(), e);
		}
	}

	/**
	 * This method contain 2 task which is parse the csv file and save the content to database
	 * 
	 * 	@param resource
	 * 			input folder resource 
	 * 
	 * @throws Exception, FileNotFoundException
	 */
	private void parseCSVContentSaveToDB (final Resource resource) throws CsvRuntimeException, IOException {
		saveCSVDataToDB(parseCsvFileContent(resource));
	}
	
	/**
	 * This method use OpenCSV annotation CsvBindByName and CsvDate inside the JPA entity class
	 * see {@link StoreOrder.java}. The library will parse each record row in the csv file
	 * into individual CSVBean.
	 * 
	 * @param resource
	 * 			input folder resource 
	 * 
	 * @return List of csv bean records
	 * 
	 * @throws IOException, CsvRuntimeException
	 */
	private List<StoreOrder> parseCsvFileContent(final Resource resource) throws IOException, CsvRuntimeException {
		// Auto closed BufferedReader connection
		try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));) {

			// create csv bean reader
			CsvToBean<StoreOrder> csvToBean = new CsvToBeanBuilder<StoreOrder>(br)
					.withType(StoreOrder.class).withIgnoreLeadingWhiteSpace(true).build();

			List<StoreOrder> csvRecords = csvToBean.parse();
			System.out.println("file contains " + csvRecords.size() + " records row ");

			return csvRecords;
			
		} catch (CsvRuntimeException e) {
			throw new CsvRuntimeException("Failed to parsed the csv file due to Csv RunTime exception", e);
		} catch (IOException e) {
			throw new IOException("Failed to parsed the csv file encounter IO Exception", e);
		}
	}

	/**
	 * This method save the parsed csv content into database using jpaRepository
	 * saveAll method.
	 * 
	 * @param csvRecords
	 * 			list of csv record store order POJO
	 * 
	 * */
	protected void saveCSVDataToDB(List<StoreOrder> csvRecords) {
		repository.saveAll(csvRecords);
		System.out.println("file content save " + csvRecords.size() + " records into database. ");
	}

}
