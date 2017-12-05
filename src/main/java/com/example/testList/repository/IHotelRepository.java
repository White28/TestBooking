package com.example.testList.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.testList.model.Hotel;

public interface IHotelRepository extends MongoRepository<Hotel, ObjectId> {

}
