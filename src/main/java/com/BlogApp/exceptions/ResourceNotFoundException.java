package com.BlogApp.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	String resourceName;
	String filedName;
	long fieldValue;
	public ResourceNotFoundException(String resourceName, String filedName, long fieldValue) {
		super(String.format("%s not found with  %s :%s",resourceName,filedName,fieldValue));
		this.resourceName = resourceName;
		this.filedName = filedName;
		this.fieldValue = fieldValue;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFiledName() {
		return filedName;
	}
	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}
	public long getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(long fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	
	

}
