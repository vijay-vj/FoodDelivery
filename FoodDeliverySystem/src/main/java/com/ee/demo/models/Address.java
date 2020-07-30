package com.ee.demo.models;

public class Address {
	private String streetAddress; 
	private String city; 
	private int zip;
	private String country;
	private String googleLocation;
		
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getGoogleLocation() {
		return googleLocation;
	}
	public void setGoogleLocation(String googleLocation) {
		this.googleLocation = googleLocation;
	} 
	
	
	
}
