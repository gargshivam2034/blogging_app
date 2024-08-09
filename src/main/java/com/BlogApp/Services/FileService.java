package com.BlogApp.Services;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	public String uploadImage(MultipartFile image,String path) throws Exception;
	public InputStream ServeFile(String fIleName ,String Path ) throws IOException;

}
