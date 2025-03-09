package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.exception.InventoryNotFoundException;
import com.nt.model.InventoryModel;
import com.nt.repo.InventoryRepoInterface;

@Service
public class InventoryService
{
	@Autowired
	private InventoryRepoInterface repo;

	public List<InventoryModel> getInventory()
	{
		return repo.findAll();
	}
	
	public InventoryModel getByinventoryId(Long product_id) {
		return repo.findById(product_id).orElseThrow(() -> new InventoryNotFoundException("Inventory item with ID " + product_id + " not found!"));
	}

	public InventoryModel postInventory(InventoryModel inventory) {
		return repo.save(inventory);
	}

	public InventoryModel putInventory(Long product_id, InventoryModel inventory) 
	{
		InventoryModel existing = repo.findById(product_id)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory item with ID " + product_id + " not found for update!"));
existing.setProduct_name(inventory.getProduct_name());
existing.setPrice(inventory.getPrice());
existing.setQuantity(inventory.getQuantity());
return repo.save(existing);
	}

	public void deleteInventory(Long product_id) {
		if (!repo.existsById(product_id)) {  // ✅ Fixed method name
			throw new InventoryNotFoundException("Inventory item with ID " + product_id + " not found for deletion!");
		}
		repo.deleteById(product_id);
	}
}
