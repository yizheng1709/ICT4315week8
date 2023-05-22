/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict4315.server;

import com.google.gson.Gson;
import ict4315.client.RequestData;
import ict4315.client.ResponseData;
import ict4315.parkingsystem.Address;
import ict4315.parkingsystem.CarType;
import ict4315.parkingsystem.ParkingEvent;
import ict4315.parkingsystem.ParkingLot;
import ict4315.parkingsystem.ParkingOffice;
import ict4315.parkingsystem.ParkingPermit;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class ParkingService {

    protected final ParkingOffice parkingOffice;
    private Gson gson = new Gson();

    public ParkingService(ParkingOffice parkingOffice) {
        this.parkingOffice = parkingOffice;
    }

    protected ResponseData handleInput(InputStream in) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String json = reader.lines().collect(Collectors.joining());

        RequestData requestData = RequestData.fromJson(json);
        return performCommand(requestData);
    }
    
    protected void handleClient(InputStream in, OutputStream out) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));

        String json = reader.lines().collect(Collectors.joining());

        RequestData requestData = RequestData.fromJson(json);
        ResponseData responseData = performCommand(requestData);

        String jsonResponse = responseData.toJson();

        writer.println(jsonResponse);
        writer.flush();
    }

    private ResponseData performCommand(RequestData requestData) {
        Map<String, String> args = requestData.getData();
        ResponseData responseData = new ResponseData();
        switch (requestData.getCommandName()) {
            case "CUSTOMER":
                Address customerAddress = new Address(args.get("Address 1"), args.get("Address 2"), args.get("City"), args.get("State"), args.get("Zipcode"));
                try {
                    responseData.setResponse(parkingOffice.register(args.get("First Name"), args.get("Last Name"), args.get("Phone number"), customerAddress));
                } catch (Exception ex) {
                    Logger.getLogger(ParkingService.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case "CAR":
                try {
                responseData.setResponse(parkingOffice.register(CarType.valueOf(args.get("COMPACT/SUV")), args.get("License"), args.get("Customer Id")));
            } catch (Exception ex) {
                Logger.getLogger(ParkingService.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;

            case "PARK": {
                try {
                    ParkingLot parkingLot = parkingOffice.getParkingLot(args.get("Parking lot Id"));

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    
                    LocalDateTime dateTimeIn = LocalDateTime.parse(args.get("Time in"), formatter);
                    
                    LocalDateTime dateTimeOut = LocalDateTime.parse(args.get("Time out"), formatter);

                    ParkingPermit permit = parkingOffice.getParkingPermit(args.get("Permit Id"));
                    System.out.println("Nguyen permit " + permit.getId());

                    ParkingEvent event = new ParkingEvent(parkingLot, dateTimeIn, dateTimeOut, permit);
                    
                    
                    responseData.setResponse(parkingOffice.park(event));
                } catch (Exception ex) {
                    Logger.getLogger(ParkingService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case "CHARGES":
            {
                try {
                    responseData.setResponse(parkingOffice.getParkingCharges(parkingOffice.getCustomer(args.get("Customer Id"))));
                } catch (Exception ex) {
                    Logger.getLogger(ParkingService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;

            default:
                responseData.setSuccess(false);
                responseData.setError("Command is not found");
        }
        System.out.println("Response: " + responseData.getResponse());

        return responseData;
    }

}
