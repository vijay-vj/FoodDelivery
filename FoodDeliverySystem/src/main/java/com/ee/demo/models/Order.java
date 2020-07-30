package com.ee.demo.models;

import java.util.Date;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Orders")
public class Order {

	@Id
	public String id;

	private int orderId;
	private double totalPrice;
	private HashMap<Menu, Integer> itemsOrdered;
	private boolean status;
	private Date orderTime;

	public boolean getOrderStatus() {
		return false;
	}

	public boolean updateStatus() {
		return false;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public HashMap<Menu, Integer> getItemsOrdered() {
		return itemsOrdered;
	}

	public void setItemsOrdered(HashMap<Menu, Integer> itemsOrdered) {
		this.itemsOrdered = itemsOrdered;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

}
