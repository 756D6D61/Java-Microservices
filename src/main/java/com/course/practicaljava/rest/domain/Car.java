package com.course.practicaljava.rest.domain;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.apache.lucene.document.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@Document(indexName = "practical-java", type = "car")
public class Car {

	private boolean available;
	private String brand;
	private String color;
	private int price;
	private String type;
	private List<Tire> compatibleTires;

	@Id
	private String id;

	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSSZ", timezone = "Europe/London")
	@Field(type = FieldType.Date, format = DateFormat.date_time)
	private Date firstReleaseDate;

	@JsonUnwrapped
	private Engine engine;

	@JsonInclude(value = Include.NON_EMPTY)
	private List<String> additionalFeatures;

	public Car() {

	}

	public Car(String brand, String color, String type) {
		super();
		this.brand = brand;
		this.color = color;
		this.type = type;
	}

	public List<String> getAdditionalFeatures() {
		return additionalFeatures;
	}

	public String getBrand() {
		return brand;
	}

	public String getColor() {
		return color;
	}

	public List<Tire> getCompatibleTires() {
		return compatibleTires;
	}

	public Engine getEngine() {
		return engine;
	}

	public Date getFirstReleaseDate() {
		return firstReleaseDate;
	}

	public String getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAdditionalFeatures(List<String> additionalFeatures) {
		this.additionalFeatures = additionalFeatures;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setCompatibleTires(List<Tire> compatibleTires) {
		this.compatibleTires = compatibleTires;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public void setFirstReleaseDate(Date firstReleaseDate) {
		this.firstReleaseDate = firstReleaseDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", color=" + color + ", type=" + type + ", price=" + price + ", available="
				+ available + ", firstReleaseDate=" + firstReleaseDate + "]";
	}

}
