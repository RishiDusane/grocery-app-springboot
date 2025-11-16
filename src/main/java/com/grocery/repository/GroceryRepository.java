package com.grocery.repository;

import java.time.LocalDate; // <-- FIX: Import LocalDate
import java.util.List;
import java.util.Optional; // <-- FIX: Import Optional

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.category.Category;
import com.grocery.entities.Grocery;

public interface GroceryRepository extends JpaRepository<Grocery, Long>{
	
	List<Grocery> findByCategory(Category category);
	
	// Required for "Delete grocery items by item name"
	Optional<Grocery> findByItemName(String itemName);
	
	// Required for "For a given purchase date, get the list"
	List<Grocery> findByPurchaseDate(LocalDate purchaseDate);
	
	// Required for "Search Grocery item by supplierName"
	List<Grocery> findBySupplierName(String supplierName);

}