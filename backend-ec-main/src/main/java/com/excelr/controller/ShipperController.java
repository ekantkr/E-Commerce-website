package com.excelr.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excelr.model.Shipper;
import com.excelr.service.ShipperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/order-shippers")
public class ShipperController {

	private final ShipperService shipperService;

	@GetMapping("/{id}")
	public ResponseEntity<Shipper> getShipperById(@PathVariable Integer id) {
		Shipper shipper = shipperService.getShipperById(id);
		return ResponseEntity.ok(shipper);
	}

	@GetMapping("Getallshippers")
	public ResponseEntity<List<Shipper>> getAllShippers() {
		List<Shipper> shippers = shipperService.getAllShippers();
		return ResponseEntity.ok(shippers);
	}

	@PostMapping("/add")
	public ResponseEntity<Shipper> saveShipper(@Valid @RequestBody Shipper shipper) {
		Shipper savedShipper = shipperService.saveShipper(shipper);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedShipper);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteShipperById(@PathVariable Integer id) {
		shipperService.deleteShipperById(id);
		return ResponseEntity.ok("Shipper with ID " + id + " successfully deleted.");
	}
}