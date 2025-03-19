package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	@GetMapping("/jen")
	public String Wrokingfine()
	{
		return "Jenkin Working fine";
	}
	
	@GetMapping("/inventory")
	public List<InventoryModel> getAll()
	{
		return service.getInventory();
	}
	
	@GetMapping("/inventory/{product_id}")
	public ResponseEntity<InventoryModel> getById(@PathVariable Long product_id)
	{
		InventoryModel inventory = service.getByinventoryId(product_id);
		return ResponseEntity.ok(inventory);
	}
	
	@PostMapping("/inventory")
	public ResponseEntity<String> post(@RequestBody InventoryModel inventory)
	{
		service.postInventory(inventory);
		return ResponseEntity.ok("Inventory added successfully!");
	}
	
	@PutMapping("/inventory/{product_id}")
	public ResponseEntity<String> put(@PathVariable Long product_id, @RequestBody InventoryModel inventory )
	{
		service.putInventory(product_id, inventory);
        return ResponseEntity.ok("Inventory updated successfully!");
	}
	
	@DeleteMapping("/inventory/{product_id}")
	public ResponseEntity<String> delete(@PathVariable Long product_id)
	{
		service.deleteInventory(product_id);
		return ResponseEntity.ok("Inventory with ID " + product_id + " deleted successfully!");
	}
}
