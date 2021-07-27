package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Animal;

@Service
public class AnimalServiceWithoutRepository {

	private List<Animal> animals;

	public AnimalServiceWithoutRepository() {
		this.animals = new ArrayList<>();
		this.animals.add(new Animal(1011, "Bob", 567, "Bobdino@email.com", "dino"));
	}

	public List<Animal> getAnimals() {
		return this.animals;
	}

	public Animal getAnimalById(int id) {
		for (Animal animal : animals) {
			if (animal.getId() == id) {
				return animal;
			}
		}
		return null;
	}

	public List<Animal> getAnimalsByType(String type) {
		return animals.stream().filter(a -> a.getType().equals(type)).collect(Collectors.toList());
	}

	public Animal updateAnimal(int id, Animal animal) {
		for (Animal a : animals) {
			if (a.getId() == id) {
				a.setAge(animal.getAge());
				a.setEmail(animal.getEmail());
				a.setName(animal.getName());
				a.setType(animal.getType());

				return a;
			}
		}
		return null;
	}

	public Animal createAnimal(Animal animal) {
		this.animals.add(animal);
		return animal;
	}

	public void deleteAnimal(int id) {
		for (int i = 0; i < animals.size(); i++) {
			Animal a = animals.get(i);

			if (a.getId() == id) {
				this.animals.remove(a);
				break;
			}
		}
	}
}
