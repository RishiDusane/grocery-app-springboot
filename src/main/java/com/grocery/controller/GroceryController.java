package com.grocery.controller;

import java.time.LocalDate; 
import java.util.Map; 

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.dto.GroceryDTO;
import com.grocery.entities.Grocery;
import com.grocery.service.GroceryService;
import jakarta.validation.Valid; 
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GroceryController {
	
	public final GroceryService groceryService;
	
	// 1. Add a Item
	@PostMapping("/grocery")
	public ResponseEntity<?> addItem(@Valid @RequestBody GroceryDTO groceryDto) {
		System.out.println("in addItem " + groceryDto);
		try {
			Grocery created = groceryService.addItem(groceryDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(created);
		} catch (RuntimeException e) {
			System.out.println("err " + e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse());
		}
	}
	// 2. Delete grocery items by name
	@DeleteMapping("/items/{itemName}")
	public ResponseEntity<?> deleteItem(@PathVariable String itemName) {
		System.out.println("in deleteItem " + itemName);
		try {
			groceryService.deleteItem(itemName);
			// On success, return a simple OK response with a message
			return ResponseEntity.ok().body(Map.of("message", "Item '" + itemName + "' deleted successfully"));
		} catch (RuntimeException e) {
			System.out.println("err" + e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse());
		}
	}
	
	// 3. Fetch all GroceryItems by Category
	@GetMapping("/items/category/{categoryName}")
	public ResponseEntity<?> getItemsByCategory(@PathVariable String categoryName) {
		System.out.println("in getGroceryByCategory " + categoryName);
		try {
			return ResponseEntity.ok(groceryService.getGroceryByCategory(categoryName));
		} catch (RuntimeException e) {
			System.out.println("err" + e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse());
		}
	}

	// 4. Get items by purchase date
	@GetMapping("/items/date/{purchaseDate}")
	public ResponseEntity<?> getItemsByPurchaseDate(@PathVariable LocalDate purchaseDate) {
		System.out.println("in getItemsByPurchaseDate " + purchaseDate);
		try {
			return ResponseEntity.ok(groceryService.getItemsByPurchaseDate(purchaseDate));
		} catch (RuntimeException e) {
			System.out.println("err" + e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse());
		}
	}

	// 5. Search Grocery item by supplierName
	@GetMapping("/items/supplier/{supplierName}")
	public ResponseEntity<?> getItemsBySupplier(@PathVariable String supplierName) {
		System.out.println("in getItemsBySupplier " + supplierName);
		try {
			return ResponseEntity.ok(groceryService.getItemsBySupplier(supplierName));
		} catch (RuntimeException e) {
			System.out.println("err" + e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse());
		}
	}
}