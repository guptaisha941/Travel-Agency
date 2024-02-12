package com.ds;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PassengerTest {
    @Test
    public void testSignUpForActivity() {
        Passenger passenger = new Passenger("Test Passenger", 1, 100, PassengerType.STANDARD);
        Activity activity = new Activity("Test Activity", "Description", 50, 20);
        passenger.signUpForActivity(passenger, activity);
        assertEquals(50, passenger.getBalance(), 0.001);
        assertEquals(1, passenger.getActivities().size());
    }
}
