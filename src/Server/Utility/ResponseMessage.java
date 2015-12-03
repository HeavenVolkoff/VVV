package Server.Utility;


public class ResponseMessage {
    private String type;
    private Integer status;
    private String code;
    private Object data;

    public ResponseMessage(String code, Object data) {
        this.type = "MESSAGE";
        this.status = 200;
        this.code = code;
        this.data = data;
    }

    public ResponseMessage(int status, String code, Object data) {
        this.type = "MESSAGE";
        this.status = status;
        this.code = code;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public Integer getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
