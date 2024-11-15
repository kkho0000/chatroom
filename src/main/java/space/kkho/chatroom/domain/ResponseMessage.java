package space.kkho.chatroom.domain;

public class ResponseMessage<T> {
    private Integer code;
    private String message;
    private T data;

    public static<T> ResponseMessage<T> success ( T data ) {
        ResponseMessage<T> response = new ResponseMessage<>();
        response.setCode(200);
        response.setMessage("Success");
        response.setData(data);
        return response;
    }
    
    public static<T> ResponseMessage<T> error ( String message ) {
        ResponseMessage<T> response = new ResponseMessage<>();
        response.setCode(400);
        response.setMessage(message);
        response.setData(null);
        return response;
    }

    // Getter and Setter for message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Getter and Setter for statusCode
    public int getCode() {
        return this.code;
    }

    public void setCode(int statusCode) {
        this.code = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
