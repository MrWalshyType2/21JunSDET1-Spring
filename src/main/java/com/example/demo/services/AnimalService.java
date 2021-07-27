package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Animal;
import com.example.demo.repositories.AnimalRepository;

@Service
public class AnimalService {
	
	private AnimalRepository animalRepository;

	@Autowired
	public AnimalService(AnimalRepository animalRepository) {
		this.animalRepository = animalRepository;
	}

	public List<Animal> getAnimals() {
		List<Animal> animalsInDb = animalRepository.findAll();
		
		return animalsInDb;
	}

	public Animal getAnimalById(int id) {
		Optional<Animal> aInOpt = animalRepository.findById(id);
		return aInOpt.orElseThrow(() -> new EntityNotFoundException());
	}

//	public List<Animal> getAnimalsByType(String type) {
//		
//	}

	public Animal updateAnimal(int id, Animal animal) {
		if (!animalRepository.existsById(id)) throw new EntityNotFoundException();
		Animal animalInDb = animalRepository.getById(id);
		
		animalInDb.setAge(animal.getAge());
		animalInDb.setEmail(animal.getEmail());
		animalInDb.setName(animal.getName());
		animalInDb.setType(animal.getType());
		
		Animal updatedAnimal = animalRepository.save(animalInDb);
		return updatedAnimal;
	}

	public Animal createAnimal(Animal animal) {
		Animal savedAnimal = this.animalRepository.save(animal);
		
		return savedAnimal;
	}

	public void deleteAnimal(int id) {
		if (!animalRepository.existsById(id)) throw new EntityNotFoundException();
		animalRepository.deleteById(id);
	}

}
