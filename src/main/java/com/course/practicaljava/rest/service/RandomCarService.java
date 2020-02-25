package com.course.practicaljava.rest.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.course.practicaljava.rest.domain.Car;

@Service
public class RandomCarService implements CarService {

	private Random random = new Random();

	@Override
	public Car generateCar() {
		String randomBrand = BRANDS.get(random.nextInt(BRANDS.size()));
		String randomColor = COLORS.get(random.nextInt(COLORS.size()));
		String randomType = TYPES.get(random.nextInt(TYPES.size()));

		Car car = new Car(randomBrand, randomColor, randomType);
		return null;
	}

}
