package com.BlogApp.ServicesImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.BlogApp.Services.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(MultipartFile image, String path) throws Exception {
		  File directory = new File(path);

	        if (!directory.exists()) {
	            directory.mkdirs();
	        }

	        String name = image.getOriginalFilename();
	        String filePath = Paths.get(path, name).toString();
	        Files.copy(image.getInputStream(), Paths.get(filePath));
	        return filePath;
	}

	@Override
	public InputStream ServeFile(String fileName, String Path)throws IOException {
		String fullPath=Path+File.separator+fileName;
		InputStream inputStream=new FileInputStream(fullPath);
		return inputStream;
	}
	

}
