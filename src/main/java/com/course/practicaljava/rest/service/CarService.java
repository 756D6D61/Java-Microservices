package com.course.practicaljava.rest.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.course.practicaljava.rest.domain.Car;

public interface CarService {

	List<String> BRANDS = Stream.of("Toyota", "Honda", "Ford").collect(Collectors.toList());
	List<String> COLORS = Stream.of("Red", "Black", "White").collect(Collectors.toList());
	List<String> TYPES = Stream.of("Sedan", "SUV", "MPV").collect(Collectors.toList());

	Car generateCar();

}
