/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict4315.parkingsystem;

import ict4315.parkingsystem.ParkingEvent;

/**
 *
 * @author student
 */
public interface ParkingChargeCalculator{
     public Double getDiscount(ParkingEvent parkingEvent);
}
