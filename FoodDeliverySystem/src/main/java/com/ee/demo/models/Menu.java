package com.ee.demo.models;

public class Menu {
	private String restaurantId;
	private String foodId; 
	private String name; 
	private String desc; 
	private double price; 
	private double rating; 
	private String vegOrNonVeg; 
	private String image;
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getFoodId() {
		return foodId;
	}
	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getVegOrNonVeg() {
		return vegOrNonVeg;
	}
	public void setVegOrNonVeg(String vegOrNonVeg) {
		this.vegOrNonVeg = vegOrNonVeg;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	} 
	
	
	
}
