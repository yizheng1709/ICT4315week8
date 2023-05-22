package ict4315.parkingsystem;

import java.util.Properties;
import com.google.gson.Gson;

public class ParkingRequest {
    private String commandName;
    private Properties parameters;

    public ParkingRequest(String commandName, Properties parameters) {
        this.commandName = commandName;
        this.parameters = parameters;
    }

    public String getCommandName() {
        return commandName;
    }

    public Properties getParameters() {
        return parameters;
    }
    
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
    public static ParkingRequest fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ParkingRequest.class);
    }

    @Override
    public String toString() {
        return "ParkingRequest{" +
                "commandName='" + commandName + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
