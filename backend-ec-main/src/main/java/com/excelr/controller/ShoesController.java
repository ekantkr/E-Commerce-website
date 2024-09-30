package com.excelr.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excelr.model.Shoes;
import com.excelr.repo.ShoesRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/shoes")
public class ShoesController {
	
	@Autowired
	private ShoesRepo repo;
	

	@PostMapping("/add")
	public Shoes saveShoes(@RequestBody Shoes shoes) {
		return repo.save(shoes);
	}
	
	@GetMapping("/shoes/{id}")
	public ResponseEntity<Shoes> getShoeById(@PathVariable Long id) {
	    Optional<Shoes> shoe = repo.findById(id); // Use repo here
	    return shoe.map(ResponseEntity::ok)
	               .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	
	@PutMapping("/update/{id}")
	public ResponseEntity<Shoes> updateShoes(@PathVariable("id") Long id, @RequestBody Shoes shoes) {
	    Optional<Shoes> optionalShoes = repo.findById(id);
	    
	    if (optionalShoes.isPresent()) {
	        Shoes existingShoes = optionalShoes.get();
	        existingShoes.setName(shoes.getName());
	        existingShoes.setCost(shoes.getCost());
	        existingShoes.setDiscount(shoes.getDiscount());
	        existingShoes.setImage(shoes.getImage());
	        existingShoes.setCategory(shoes.getCategory());
	        existingShoes.setBrand(shoes.getBrand());
	        Shoes updatedShoes = repo.save(existingShoes);
	        return ResponseEntity.ok(updatedShoes);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@GetMapping("/getAlltheShoes")
	public List<Shoes> getAllshoes(){
		return repo.findAll();
	}
	
	 @GetMapping("/getByCategory/{category}")
	    public List<Shoes> getShoesByCategory(@PathVariable String category) {
	        return repo.findByCategory(category); 
	    }
	 
	  @GetMapping("/mens")
	    public List<Shoes> getMenShoes() {
	        return repo.findByCategory("men");  
	    }

	    @GetMapping("/womens")
	    public List<Shoes> getWomenShoes() {
	        return repo.findByCategory("women"); 
	    }

	    @GetMapping("/kids")
	    public List<Shoes> getKidsShoes() {
	        return repo.findByCategory("kids");  
	    }
	    
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Void> deleteShoes(@PathVariable("id") Long id) {
	        Optional<Shoes> optionalShoes = repo.findById(id);
	        
	        if (optionalShoes.isPresent()) {
	            repo.delete(optionalShoes.get());
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

}
