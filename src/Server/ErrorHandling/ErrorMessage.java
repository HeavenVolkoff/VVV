package Server.ErrorHandling;


import com.fasterxml.jackson.databind.JsonMappingException;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;


public class ErrorMessage {
	
	/** contains the same HTTP Status code returned by the server */
	int status;
	
	/** application specific error code */
	String code;

	/** message describing the error*/
	Object data;
	
	/** extra information that might useful for developers */
	String developerMessage;

	/** Type of the message = Error */
	String type;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public ErrorMessage(AppException ex){
		this.type =  "ERROR";
		this.status= ex.getStatus();
		this.code =  ex.getCode();
		this.data =  ex.getData();
		this.developerMessage = ex.getDeveloperMessage();
	}
	
	public ErrorMessage(NotFoundException ex){
		this.type = "ERROR";
		this.status = Response.Status.NOT_FOUND.getStatusCode();
		this.code = "NOT_FOUND_" + this.status;
		this.developerMessage = ex.getMessage();
		this.data = ex.getStackTrace();
	}

	public ErrorMessage(JsonMappingException ex) {
		this.type = "ERROR";
		this.status = 400;
		this.code = "BAD_FORMATTED_JSON";
		this.developerMessage = ex.getMessage();
		this.data = ex.getStackTrace();
	}

	public ErrorMessage() {
		this.type = "ERROR";
	}
}
