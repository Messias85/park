package com.giovani.park.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import com.fasterxml.jackson.annotation.JsonInclude;



import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

public class ErrorMessage {
	
	
	private String path;
    public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	private String method;
    private int status;
    private String statusText;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    
    private Map<String, String> errors;


    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message) {
        this.path = request.getRequestURI();
        this.method = request.getMethod(); // Adicionado para preencher o atributo 'method'
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
    
        // Getters e Setters
    }
    
    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message,BindingResult result) {
        this.path = request.getRequestURI();
        this.method = request.getMethod(); // Adicionado para preencher o atributo 'method'
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
    
        addErrors(result);
        // Getters e Setters
    }

	

	private void addErrors(BindingResult result) {
		// TODO Auto-generated method stub
		
		
		this.errors = new HashMap<>();
		for(FieldError fielderror : result.getFieldErrors()) {
			this.errors.put(fielderror.getField(),fielderror.getDefaultMessage());
		}
		
	}
	


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
    
    
    
}
