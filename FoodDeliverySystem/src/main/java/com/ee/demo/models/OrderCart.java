package com.ee.demo.models;

import java.util.ArrayList;
 
public class OrderCart {
	private ArrayList<Menu> cartItem;
	private int numOfItems;
	 
	public void setCartItem(ArrayList<Menu> cartItem) {
		this.cartItem = cartItem;
	}
	
	public ArrayList<Menu> getCartItem() {
		return this.cartItem;
	}

	public int getNumOfItems() {
		return numOfItems;
	}
	public void setNumOfItems(int numOfItems) {
		this.numOfItems = numOfItems;
	}
	 
	public Boolean RemoveItem(Menu item) {
		return this.cartItem.remove(item); 
	}
	
	//public Boolean updateItemQuantity(Menu item, int quantity);
 }
