package Server.ErrorHandling;


import javax.ws.rs.core.Response;

public class AppException extends Exception {

    /**
     * contains redundantly the HTTP status of the response sent back to the client in case of error, so that
     * the developer does not have to look into the response headers. If null a default
     */
    Integer status;

    /** application specific error code */
    String code;

    Object data;

    /** detailed error description for developers*/
    String developerMessage;

    public AppException(Response.Status status, String code, Object data, String developerMessage) {
        super(code);
        this.status = status.getStatusCode();
        this.code = code;
        this.data = data;
        this.developerMessage = developerMessage;
    }

    public AppException(int status, String code, Object data, String developerMessage) {
        super(code);
        this.status = status;
        this.code = code;
        this.data = data;
        this.developerMessage = developerMessage;
    }

    public AppException(int status, String code, Object data) {
        super(code);
        this.status = status;
        this.code = code;
        this.data = data;
    }

    public AppException(Response.Status status, String code, Object data) {
        super(code);
        this.status = status.getStatusCode();
        this.code = code;
        this.data = data;
    }

    public AppException() { }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
