package com.example.demo.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Animal;
import com.example.demo.services.AnimalService;

@RestController
@RequestMapping("/animal") // localhost:8080/animal
public class AnimalController {

	private AnimalService animalService;

	@Autowired // triggering dependency injection
	public AnimalController(AnimalService animalService) {
		this.animalService = animalService;
	}

	@GetMapping
	public ResponseEntity<List<Animal>> getAnimals() {
		List<Animal> data = this.animalService.getAnimals();

		return new ResponseEntity<List<Animal>>(data, HttpStatus.OK);
	}

	// localhost:8080/animal/32
	@GetMapping("/{id}")
	public ResponseEntity<Animal> getAnimalById(@PathVariable("id") int id) {
		Animal data = this.animalService.getAnimalById(id);

		return new ResponseEntity<Animal>(data, HttpStatus.OK);
	}

	// localhost:8080/animal?type=dino
	// - '?' indicates the start of a query parameter
	// - they are in 'key=value' pairs
	// - can have multiple 'key=value' pairs by separating them with the amphersand
	// '&'
	// i.e. localhost:8080/animal?type=dino&name=fred
//	@GetMapping("/type")
//	public ResponseEntity<List<Animal>> getAnimalsByType(@RequestParam("type") String type) {
//		List<Animal> data = this.animalService.getAnimalsByType(type);
//
//		// exception handlers are much preferred to this
//		if (data != null) {
//			return new ResponseEntity<List<Animal>>(data, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<List<Animal>>(data, HttpStatus.NOT_FOUND);
//		}
//	}

	@PutMapping("/{id}")
	public ResponseEntity<Animal> updateAnimal(@PathVariable("id") int id, @Valid @RequestBody Animal animal) {
		Animal data = this.animalService.updateAnimal(id, animal);

		return new ResponseEntity<Animal>(data, HttpStatus.OK);
	}

	@PostMapping
	// @Valid runs the validation annotations on the entity (validates the data)
	// @RequestBody says grab the data from the requests body, and try to put it
	// in the specified type
	public ResponseEntity<Animal> createAnimal(@Valid @RequestBody Animal animal) {
		Animal data = this.animalService.createAnimal(animal);
		
		HttpHeaders headers = new HttpHeaders();
		//headers.add("Location", "localhost:8080/" + data.getId()); // manually add a header
		headers.setLocation(URI.create("localhost:8080/" + data.getId())); // setter for Location header already exists
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<Animal>(data, headers, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAnimal(@PathVariable("id") int id) {
		this.animalService.deleteAnimal(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// @PostMapping
	// @PutMapping
	// @DeleteMapping
	// @PatchMapping
	// etc...
}
