package Server.ErrorHandling;

import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;


public class ErrorMessage {
	
	/** contains the same HTTP Status code returned by the server */
	int status;
	
	/** application specific error code */
	int code;
	
	/** message describing the error*/
	String message;
		
	/** link point to page where the error message is documented */
	String link;
	
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

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public ErrorMessage(AppException ex){
		try {
			BeanUtils.copyProperties(this, ex);

		} catch (IllegalAccessException | InvocationTargetException e) {
			this.status = 500;
			this.code = 500;
			this.message = "Server Internal Error";
			this.link = "";
			this.developerMessage = e.getMessage();
		}

		this.type = "Error";
	}
	
	public ErrorMessage(NotFoundException ex){
		this.type = "Error";
		this.status = Response.Status.NOT_FOUND.getStatusCode();
		this.message = ex.getMessage();
		this.link = "https://jersey.java.net/apidocs/2.8/jersey/javax/ws/rs/NotFoundException.html";		
	}

	public ErrorMessage() {}
}
