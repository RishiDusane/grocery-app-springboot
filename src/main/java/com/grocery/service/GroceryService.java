package com.grocery.service;

import java.time.LocalDate; // <-- FIX: Import LocalDate
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grocery.category.Category;
import com.grocery.dto.GroceryDTO;
import com.grocery.entities.Grocery;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.GroceryRepository;

@Service
@Transactional
public class GroceryService {

	@Autowired
	private GroceryRepository groceryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//1. Add New Grocery Item
	public Grocery addItem(GroceryDTO groceryDto) {
		Grocery grocery= modelMapper.map(groceryDto, Grocery.class);
		return groceryRepository.save(grocery);
	}
	//2. Delete grocery items by name
	public void deleteItem(String itemName) {
		Grocery grocery = groceryRepository.findByItemName(itemName)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found: " + itemName));
		
		groceryRepository.delete(grocery);
	}
	//3. Fetch all grocery items by a given category
	public List<Grocery> getGroceryByCategory(String categoryName){
		Category category;
		try {
			category= Category.valueOf(categoryName.toUpperCase());
		}catch(Exception e) {
			throw new IllegalArgumentException("Invalid Category name: "+ categoryName);
		}
		return groceryRepository.findByCategory(category);
	}
	//4. For a given purchase date, get the list of grocery items
	public List<Grocery> getItemsByPurchaseDate(LocalDate purchaseDate) {
		return groceryRepository.findByPurchaseDate(purchaseDate);
	}
	//5. Search Grocery item by Supplier name.
	public List<Grocery> getItemsBySupplier(String supplierName) {
		return groceryRepository.findBySupplierName(supplierName);
	}
}