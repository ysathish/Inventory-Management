package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.InventoryModel;

public interface InventoryRepoInterface extends JpaRepository<InventoryModel, Long> 
{

}
