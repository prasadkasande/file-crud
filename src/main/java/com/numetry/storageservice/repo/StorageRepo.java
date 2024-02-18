package com.numetry.storageservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.numetry.storageservice.entity.ImageData;


public interface StorageRepo extends JpaRepository<ImageData, Long> {
	
	Optional<ImageData> findByName(String filename);
	

	

}
