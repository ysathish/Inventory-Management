package com.nt.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcptionHandler
{
	//handle Inventory not found exception
	@ExceptionHandler
	public ResponseEntity<Map<String,Object>> handleInventoryNotFoundException(InventoryNotFoundException exc)
	{
		Map<String,Object> response=new HashMap<>();
		response.put("message", exc.getMessage());
		response.put("status", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	

}
