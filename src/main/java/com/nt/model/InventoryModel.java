package com.nt.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "inventory")

public class InventoryModel 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long product_id;
	private String product_name;
	private float price;
	private int quantity;
	
	public InventoryModel() {
		super();
	}
	public InventoryModel(Long product_id,String product_name, float price, int quantity) {
		super();
		this.product_id=product_id;
		this.product_name = product_name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "InventoryModel [product_id=" + product_id + ", product_name=" + product_name + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
	
	

}
