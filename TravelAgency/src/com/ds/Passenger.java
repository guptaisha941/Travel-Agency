package com.ds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Passenger {
    private String name;
    private int passengerNumber;
    private PassengerType type;
    private double balance;
    private List<Activity> activities;

    public Passenger(String name, int passengerNumber,double balance, PassengerType type) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.type = type;
        this.balance = balance;
        this.activities = new ArrayList<Activity>();
    }

    public void signUpForActivity(Passenger passenger,  Activity activity) {
        if (activities.contains(activity)) {
            System.out.println(passenger.getName() + " already signed up for this activity.");
            return;
        }
        if (type == PassengerType.STANDARD) {
            if (balance < activity.getCost()) {
                System.out.println("Insufficient balance to sign up for this activity.");
                return;
            }
            balance -= activity.getCost();
        } else if (type == PassengerType.GOLD) {
            double discountedCost = activity.getCost() * 0.9; // 10% discount for gold passengers
            if (balance < discountedCost) {
                System.out.println("Insufficient balance to sign up for this activity.");
                return;
            }
            balance -= discountedCost;
        }
        activities.add(activity);
        activity.setCapacity(activity.getCapacity() - 1);
        System.out.println(passenger.getName() + " signed up for activity: " + activity.getName());
    }

}
