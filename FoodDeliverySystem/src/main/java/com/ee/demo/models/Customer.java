package com.ee.demo.models;

public class Customer extends Person{
 	private OrderCart cart;
	private Order order;
	private OrderLog log;
	//public boolean placeOrder(Menu menu);
	
	public OrderCart getCart() {
		return cart;
	}
	public void setCart(OrderCart cart) {
		this.cart = cart;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public OrderLog getLog() {
		return log;
	}
	public void setLog(OrderLog log) {
		this.log = log;
	}
	
	
}