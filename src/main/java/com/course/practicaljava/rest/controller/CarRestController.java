package com.course.practicaljava.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.practicaljava.repository.CarElasticRepository;
import com.course.practicaljava.rest.domain.Car;
import com.course.practicaljava.rest.service.CarService;

@RestController
@RequestMapping("/api/car/v1")
public class CarRestController {

	@Autowired
	private CarService carService;

	private Random random = new Random();

	private Logger log = LoggerFactory.getLogger(CarRestController.class);

	@GetMapping(path = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
	public Car random() {
		return carService.generateCar();
	}

	@PostMapping(path = "/echo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String echo(@RequestBody Car car) {
		log.info("This car is : " + car);
		return car.toString();
	}

	@GetMapping(path = "/random-cars", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Car> randomCars() {
		ArrayList<Car> result = new ArrayList<Car>();

		for (int i = 0; i < random.nextInt(6); i++) {
			result.add(carService.generateCar());
		}

		return result;
	}

	@GetMapping(path = "/cars/count")
	public long countCar() {
		return CarElasticRepository.count();
	}

	@PostMapping(path = "/cars", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Car createCar(@RequestBody Car car) {
		return CarElasticRepository.save(car);
	}

	@GetMapping(path = "/cars/{id}")
	public Car findCarsById(@PathVariable String id) {
		return CarElasticRepository.findById(id).orElse(null);
	}

	@PutMapping(path = "/cars/{id}")
	public Car updateCarById(@PathVariable String id, @RequestBody Car UpdatedCar) {
		UpdatedCar.setId(id);
		return CarElasticRepository.save(UpdatedCar);
	}

	@GetMapping(path = "/cars/{brand}/{color}")
	public List<Car> findCarsByPath(@PathVariable String brand, @PathVariable String colour,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		int pageable = PageRequest.of(page, size, Sortby(Direction.DESC, "price"));
		return CarElasticRepository.findByBrandAndColour(brand, colour, pageable).getContent();
	}

	@GetMapping(path = "/cars")
	public List<Car> findCarsByParam(@PathVariable String brand, @PathVariable String colour,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		int pageable = PageRequest.of(page, size);
		return CarElasticRepository.findByBrandAndColour(brand, colour, pageable).getContent();
	}

	@GetMapping(path = "/cars/date")
	public List<Car> findCarsReleasedAfter(
			@RequestParam(name = "first_realse_date") @DateTimeFormat(pattern = "yyy-MM-dd") Date firstReleaseDate) {
		return CarElasticRepository.findByFirstReleaseDateAfter(firstReleaseDate.getTime());

	}

}
