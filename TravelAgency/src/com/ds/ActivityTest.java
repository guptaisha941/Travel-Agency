package com.ds;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ActivityTest {
    @Test
    public void testGetCost() {
        Activity activity = new Activity("Test Activity", "Description", 50, 20);
        assertEquals(50, activity.getCost(), 0.001);
    }

    @Test
    public void testGetCapacity() {
        Activity activity = new Activity("Test Activity", "Description", 50, 20);
        assertEquals(20, activity.getCapacity());
    }
}

