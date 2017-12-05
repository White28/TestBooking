package com.example.testList.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "place")
public class Place {

	@Id
	private ObjectId id;
	private String typeOfPlace;
	private int freePlaces;

	public Place() {}

	public Place(String typeOfPlace, int freePlaces) {
		this();
		this.typeOfPlace = typeOfPlace;
		this.freePlaces = freePlaces;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTypeOfPlace() {
		return typeOfPlace;
	}

	public void setTypeOfPlace(String typeOfPlace) {
		this.typeOfPlace = typeOfPlace;
	}

	public int getFreePlaces() {
		return freePlaces;
	}

	public void setFreePlaces(int freePlaces) {
		this.freePlaces = freePlaces;
	}
}
