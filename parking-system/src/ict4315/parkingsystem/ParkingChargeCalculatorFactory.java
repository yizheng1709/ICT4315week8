/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict4315.parkingsystem;

import ict4315.parkingsystem.VehicleTypeDiscount;
import ict4315.parkingsystem.WeekdayDiscount;
import java.io.Serializable;

/**
 *
 * @author student
 */
public class ParkingChargeCalculatorFactory implements Serializable {

    public ParkingChargeCalculator getParkingChargeCalculator(String strategy) {
        
        if ("WeekdayDiscount".equalsIgnoreCase(strategy)) {

            System.out.println("WeekdayDiscountCaculator created");
            return new WeekdayDiscount(new BasePakingChargeCalculator());

        } else if ("CarTypeDiscount".equalsIgnoreCase(strategy)) {

            System.out.println("CarTypeDiscount created");
            return new VehicleTypeDiscount(new BasePakingChargeCalculator());

        } else if ("WeekdayCarTypeDiscount".equalsIgnoreCase(strategy)) {

            System.out.println("WeekdayDiscount and CarTypeDiscount created");
            return new WeekdayDiscount(new VehicleTypeDiscount(new BasePakingChargeCalculator()));
        } else if (strategy == null) {
            System.out.println("Base calculator created");
            return new BasePakingChargeCalculator();
        }
        return null;

    }
}
