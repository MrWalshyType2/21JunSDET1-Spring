package com.example.demo.controllers;

import java.time.LocalTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Animal;

// localhost:8080/basic
@RestController // indicates to Spring this is a bean
@RequestMapping("/basic")
public class BasicController {
	
//	@RequestMapping(method = RequestMethod.GET) // equivalent to @GetMapping
	@GetMapping("/something") // localhost:8080/basic/something
	public String basicHTMLResponse() {
		// spring will automatically add a header with the assumed
		// media type
		// - text/html
		return "<h1>Hello World</h1>";
	}
	
	@GetMapping("/time")
	public LocalTime getTime() {
		return LocalTime.now();
	}
	
}
