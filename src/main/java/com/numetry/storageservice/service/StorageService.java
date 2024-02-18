package com.numetry.storageservice.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.numetry.storageservice.entity.ImageData;
import com.numetry.storageservice.repo.StorageRepo;
import com.numetry.storageservice.util.ImageUtils;

@Service
public class StorageService {
	 @Autowired
	    private StorageRepo repository;

	    public String uploadImage(MultipartFile file) throws IOException {

	        ImageData imageData = repository.save(ImageData.builder()
	                .name(file.getOriginalFilename())
	                .type(file.getContentType())
	                .imageData(ImageUtils.compressImage(file.getBytes())).build());
	        if (imageData != null) {
	            return "file uploaded successfully : " + file.getOriginalFilename();
	        }
	        return null;
	    }

	    public byte[] downloadImage(String fileName){
	        Optional<ImageData> dbImageData = repository.findByName(fileName);
	        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
	        return images;
	    }
    
    public void deleteFile(String fileName) {
        Optional<ImageData> imageData = repository.findByName(fileName);
        imageData.ifPresent(image -> repository.delete(image));
    }
}
