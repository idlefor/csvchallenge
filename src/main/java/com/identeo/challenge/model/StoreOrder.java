package com.identeo.challenge.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

/**
 * This POJO represent each record data that we read from csv file which is
 * going to be store into database.
 * 
 * We are adopting openCSV library feature here mainly for flexibility and also
 * avoid hardcode as much as possible and easy to scale in the future.
 * 
 * We are using nullable and unique feature from JPA @Column interface to check
 * if we are insert correct data into the database
 * 
 * 
 * @author iden.teo
 */
@Entity
@Table(name = "store_order")
public class StoreOrder {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@CsvBindByName(column = "ROW ID")
	private long id;

	@Column(name = "order_id", unique=true, nullable = false)
	@CsvBindByName(column = "Order ID", required = true)
	private String order_id;

	@Column(name = "order_date", nullable = false)
	@CsvBindByName(column = "Order Date", required = true)
	@CsvDate(value = "dd.MM.yy")
	private Date order_date;

	@Column(name = "ship_date", nullable = false)
	@CsvBindByName(column = "Ship Date", required = true)
	@CsvDate(value = "dd.MM.yy")
	private Date ship_date;

	@Column(name = "ship_mode")
	@CsvBindByName(column = "Ship Mode")
	private String ship_mode;

	@Column(name = "quantity", nullable = false)
	@CsvBindByName(column = "Quantity", required = true)
	private long quantity;

	@Column(name = "discount")
	@CsvBindByName(column = "Discount")
	private BigDecimal discount;

	@Column(name = "profit", nullable = false)
	@CsvBindByName(column = "Profit", required = true)
	private BigDecimal profit;

	@Column(name = "product_id", unique=true, nullable = false)
	@CsvBindByName(column = "Product ID", required = true)
	private String product_id;

	@Column(name = "customer_name")
	@CsvBindByName(column = "Customer Name")
	private String customer_name;

	@Column(name = "category", nullable = false)
	@CsvBindByName(column = "Category", required = true)
	private String category;

	@Column(name = "customer_id", unique=true, nullable = false )
	@CsvBindByName(column = "Customer ID")
	private String customer_id;

	@Column(name = "product_name")
	@CsvBindByName(column = "Product Name")
	private String product_name;

	public StoreOrder() {
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getOrderId() {
		return order_id;
	}

	public void setOrderId(final String order_id) {
		this.order_id = order_id;
	}

	public Date getOrderDate() {
		return order_date;
	}

	public void setOrderDate(final Date order_date) {
		this.order_date = order_date;
	}

	public Date getShipDate() {
		return ship_date;
	}

	public void setShipDate(final Date ship_date) {
		this.ship_date = ship_date;
	}

	public String getShipMode() {
		return ship_mode;
	}

	public void setShipMode(final String ship_mode) {
		this.ship_mode = ship_mode;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(final long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(final BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(final BigDecimal profit) {
		this.profit = profit;
	}

	public String getProductId() {
		return product_id;
	}

	public void setProductId(final String product_id) {
		this.product_id = product_id;
	}

	public String getCustomerName() {
		return customer_name;
	}

	public void setCustomerName(final String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	public String getCustomerId() {
		return customer_id;
	}

	public void setCustomerId(final String customer_id) {
		this.customer_id = customer_id;
	}

	public String getProductName() {
		return product_name;
	}

	public void setProductName(final String product_name) {
		this.product_name = product_name;
	}
}