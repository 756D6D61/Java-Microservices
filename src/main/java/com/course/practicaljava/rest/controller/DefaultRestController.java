package com.course.practicaljava.rest.controller;

import java.time.LocalTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DefaultRestController {

	@GetMapping("/welcome")
	public String welcome() {
		System.out.println(StringUtils.join("Hello", "This is ", "Spring Boot ", "REST API"));

		return "Welcome to Spring Boot";
	}

	@GetMapping("/time")
	public String time() {
		return LocalTime.now().toString();
	}

	@GetMapping("/header-one")
	public String headerByAnnotation(@RequestHeader(name = "user-agent") String headerUserAgent,
			@RequestHeader(name = "Practical-java", required = false) String headerPracticalJava) {
		StringBuilder sb = new StringBuilder();
		sb.append("User-agent : " + headerUserAgent);
		sb.append(" || ");
		sb.append("Practical-java : " + headerPracticalJava);
		return sb.toString();
	}

	@GetMapping("/header-two")
	public String headerByServlet(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append("User-agent : " + request.getHeader("User-agent"));
		sb.append(" || ");
		sb.append("Practical-java: " + request.getHeader("Practical-java"));
		return sb.toString();
	}

	@GetMapping("/header-three")
	public Map<String, String> headerByAll(@RequestHeader HttpHeaders headers) {
		return headers.toSingleValueMap();
	}

}
