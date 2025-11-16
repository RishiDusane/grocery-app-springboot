package com.grocery.entities;

import java.time.LocalDate;
import com.grocery.category.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Grocery")
@Getter
@Setter

public class Grocery {
@Id // ID
@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-Increment 
private Long id;

@NotBlank(message="Item name cannot be Blank !!!!")
@Column(unique = true, nullable = false)
private String itemName;

@Enumerated(EnumType.STRING)
@NotNull(message="Category cannot be Blank!!!!")
@Column(nullable= false)
private Category category;

@NotNull(message="Quantity cannot be Null !!!!")
@Min(value = 1, message="Quantity must be atleast 1")
@Column(nullable = false)
private int quantity;

@NotNull(message="Price cannot be cannot be zero !!!!")
@DecimalMin(value= "0.01", message ="Price  must be greater than zero")
@Column(nullable=false)
private double price;

@NotNull(message="Purchase date cannot be Null !!!!")
@Column(nullable= false)
private LocalDate purchaseDate;

@NotBlank(message="Supplier name cannot be blank")
@Column(nullable= false)
private String supplierName;

}