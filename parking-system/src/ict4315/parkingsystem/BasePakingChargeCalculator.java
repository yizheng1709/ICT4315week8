/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict4315.parkingsystem;

import ict4315.parkingsystem.ParkingChargeCalculator;
import ict4315.parkingsystem.ParkingEvent;
import java.io.Serializable;

/**
 *
 * @author student
 */
public class BasePakingChargeCalculator implements ParkingChargeCalculator, Serializable {

    @Override
    public Double getDiscount(ParkingEvent parkingEvent) {
        return 0.0;
    }
    
}
