package com.ds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;

    public double getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }
}
