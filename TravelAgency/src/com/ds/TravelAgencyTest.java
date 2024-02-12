package com.ds;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TravelAgencyTest {
    @Test
    public void testAddPackage() {
        TravelPackage travelPackage = new TravelPackage("Test Package", 3);
        TravelAgency travelAgency = new TravelAgency();
        travelAgency.addPackage(travelPackage);
        assertEquals(1, travelAgency.getPackages().size());
    }
}

