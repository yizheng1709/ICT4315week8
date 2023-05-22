/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict4315.client;

import java.io.Serializable;
import java.util.Map;

import com.google.gson.Gson;

/**
 *
 * @author student
 */
public class RequestData implements Serializable {

    private String commandName;
    private Map<String, String> data;

    public RequestData(String commandName, Map<String, String> data) {
        this.commandName = commandName;
        this.data = data;
    }

    public Map<String, String> getData() {
        return data;
    }

    public String getCommandName() {
        return commandName;
    }
    
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
    public static RequestData fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, RequestData.class);
    }

}
