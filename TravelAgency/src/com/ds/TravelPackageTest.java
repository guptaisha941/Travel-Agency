package com.ds;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TravelPackageTest {
    @Test
    public void testAddDestination() {
        Destination destination = new Destination("Test Destination");
        TravelPackage travelPackage = new TravelPackage("Test Package", 3);
        travelPackage.addDestination(destination);
        assertEquals(1, travelPackage.getItinerary().size());
    }

    @Test
    public void testAddPassenger() {
        Passenger passenger = new Passenger("Test Passenger", 1, 100, PassengerType.STANDARD);
        TravelPackage travelPackage = new TravelPackage("Test Package", 3);
        travelPackage.addPassenger(passenger);
        assertEquals(1, travelPackage.getPassengers().size());
    }
}
