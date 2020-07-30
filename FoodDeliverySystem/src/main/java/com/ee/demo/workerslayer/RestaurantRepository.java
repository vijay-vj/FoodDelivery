package com.ee.demo.workerslayer;

import java.math.BigInteger;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.repository.Repository;

import com.ee.demo.models.Restaurant;
@Document(collection = "Restaurants")

public interface RestaurantRepository extends Repository<Restaurant, BigInteger> {

	Restaurant findByrestaurantId(String restaurantId);
	
	public Iterable<Restaurant> findBypincode(String pincode);
    
	public Iterable<Restaurant> findBylocation(String location, int radius);
    
    
	
}