package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.model.InventoryModel;
import com.nt.repo.InventoryRepo;

@Service
public class InventoryService
{
	@Autowired
	private InventoryRepo repo;

	public InventoryRepo getRepo() {
		return repo;
	}

	public void setRepo(InventoryRepo repo) {
		this.repo = repo;
	}
	public List<InventoryModel> getInventory()
	{
		return repo.findAll();
	}

	public void postInventory(InventoryModel inventory) {
		
		 repo.save(inventory);
	}

	

	public void putInventory(Long product_id, InventoryModel inventory) {
		repo.update(product_id,inventory);
		
	}

	public void deleteInventory(Long product_id) {
		repo.deleteInventory(product_id);
		
	}

	public InventoryModel getByinventoryId(Long product_id) {
		return repo.findById(product_id);
	}
	
	
	

}
