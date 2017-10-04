package org.lab.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class AddressDTO {
	
	@JsonProperty("chat") 
	private JsonNode chat;    // If you don't want to create class for this node which contains multiple fields. If you give as String, it will give mapping exception
	
	@JsonProperty("messages") 
	private JsonNode messages[];  // If you don't want to create class for this node which contains multiple fields. If you give as String, it will give mapping exception
	
	private String street;
	private String city;
	private int zipcode;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	@Override
	public String toString(){
		return getStreet() + ", "+getCity()+", "+getZipcode();
	}
}
