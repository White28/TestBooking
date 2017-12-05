package com.example.testList.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.testList.model.Hotel;
import com.example.testList.model.Place;
import com.example.testList.repository.IHotelRepository;

@RestController
@RequestMapping(value = "/hotel")
public class HotelController {

	@Autowired
	private IHotelRepository hotelRepository;

	@RequestMapping(method = RequestMethod.POST)
	public void create(@RequestBody Hotel hotel) {
		hotelRepository.insert(hotel);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Hotel> getAll() {
		return hotelRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Hotel getById(@PathVariable(value = "id") ObjectId id) {
		return hotelRepository.findOne(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void delete(@PathVariable(value = "id") ObjectId id) {
		hotelRepository.delete(id);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody Hotel hotel) {
		hotelRepository.save(hotel);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/booking",
			params = {"amountOfReserves", "typeOfPlace"})
	public void booking(@PathVariable(value = "id") ObjectId id,
			@RequestParam(value = "amountOfReserves") int amountOfReserves,
			@RequestParam(value = "typeOfPlace") String typeOfPlace) {

		Hotel hotel = hotelRepository.findOne(id);
		List<Place> places = hotel.getPlaces();

		Place place = getPlaceByType(places, typeOfPlace);
		place.setFreePlaces(place.getFreePlaces() - amountOfReserves);
		hotelRepository.save(hotel);
	}


	// @RequestMapping(method = RequestMethod.GET, value = "/{id}/place")
	// public void reservePlaces(Hotel hotel, int amountOfReserve, String typeOfPlace) {
	//
	// List<Place> places = hotel.getPlaces();
	//
	// Place place = getPlaceByType(places, typeOfPlace);
	// place.setFreePlaces(place.getFreePlaces() - amountOfReserve);
	//
	// /// update(hotel);
	// }

	private Place getPlaceByType(List<Place> places, String typeOfPlace) {
		return places.stream().filter(type -> type.getTypeOfPlace().equals(typeOfPlace)).findFirst()
				.get();
	}
}
