package com.course.practicaljava.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.course.practicaljava.rest.domain.Car;

@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car, String> {
	public List<Car> findByBrandAndColour(String brand, String colour);

	@Query("{ \"range\": {\"first_release_date\":{ \"gt\" :? 0 } } })")
	public List<Car> findByFirstReleaseDateAfter(long date);

}
