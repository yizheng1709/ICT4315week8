package ict4315.parkingsystem;

import com.google.gson.Gson;

public class ParkingResponse {
    private int statusCode;
    private String message;

    // Constructor
    public ParkingResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
    public static ParkingResponse fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ParkingResponse.class);
    }

    @Override
    public String toString() {
        return "ParkingResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }
}