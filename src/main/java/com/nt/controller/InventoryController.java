package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.InventoryModel;
import com.nt.service.InventoryService;

@RestController
@RequestMapping("/")
public class InventoryController
{ 
	@Autowired
	private InventoryService service;
	@GetMapping("/inventory")
	public List<InventoryModel> getInventory()
	{
		return service.getInventory();
	}
	@GetMapping("/inventory/{product_id}")
	public ResponseEntity<InventoryModel> getById(@PathVariable Long product_id)
	{
		InventoryModel inventory=service.getByinventoryId(product_id);
		return ResponseEntity.ok(inventory);
	}
	//ResponseEntity<T> is a generic class in Spring Boot that represents an HTTP response, including:

//Response body (data sent back)
//HTTP status code (e.g., 200 OK, 400 Bad Request)
//Headers (optional metadata)
//It is commonly used in REST APIs to send customized responses.
	@PostMapping("/inventory")
	public ResponseEntity<String> post(@RequestBody InventoryModel inventory)
	{
		service.postInventory(inventory);
		return ResponseEntity.ok("inventory added successfully !");
	}
	@PutMapping("/inventory/{product_id}")
	//@PathVariable is an annotation in Spring Boot that is used to extract values from the URL 
	//path and map them to method parameters in a controller.
	public ResponseEntity<String> put(@PathVariable Long product_id, @RequestBody InventoryModel inventory )
	{
		service.putInventory(product_id,inventory);
		return ResponseEntity.ok("update sucess!");
	}
	@DeleteMapping("/inventory/{product_id}")
	public ResponseEntity<String> delete(@PathVariable Long product_id)
	{
		service.deleteInventory(product_id);
		return ResponseEntity.ok("inventory with Id  "+product_id+ "  deleted   susessfully !");
	}
 

} 
