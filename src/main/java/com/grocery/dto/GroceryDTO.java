package com.grocery.dto;

import java.time.LocalDate;

import com.grocery.category.Category;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroceryDTO {
	private String itemName;
	private Category category;
	private int quantity;
	private Double price;
	private LocalDate purchaseDate;
	private String supplierName;
}