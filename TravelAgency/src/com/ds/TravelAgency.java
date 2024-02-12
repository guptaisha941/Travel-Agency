package com.ds;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
class TravelAgency {
    private List<TravelPackage> packages;

    public TravelAgency() {
        this.packages = new ArrayList<TravelPackage>();
    }

    public void addPackage(TravelPackage travelPackage) {
        packages.add(travelPackage);
    }

    public void printItinerary(TravelPackage travelPackage) {
        // Print itinerary of the travel package
        System.out.println("Travel Package: " + travelPackage.getName());
        System.out.println("Itinerary");
        for (Destination destination : travelPackage.getItinerary()) {
            System.out.println("Destination: " + destination.getName());
            System.out.println("Activities:");
            for (Activity activity : destination.getActivities()) {
                System.out.println("- " + activity.getName() + ": " + activity.getDescription() + ", Cost: $" + activity.getCost() + ", Capacity: " + activity.getCapacity());
            }
        }
    }

    public void printPassengerList(TravelPackage travelPackage) {
        // Print the passenger list of the travel package
        System.out.println("Passenger List for Travel Package: " + travelPackage.getName());
        System.out.println("Passenger Capacity: " + travelPackage.getPassengerCapacity());
        System.out.println("Number of Passengers: " + travelPackage.getPassengers().size());
        System.out.println("Passengers:");
        for (Passenger passenger : travelPackage.getPassengers()) {
            System.out.println("- Name: " + passenger.getName() + ", Passenger Number: " + passenger.getPassengerNumber());
        }
    }

    public void printPassengerDetails(Passenger passenger) {
        // Print the details of an individual passenger
        System.out.println("Passenger Details:");
        System.out.println("- Name: " + passenger.getName());
        System.out.println("- Passenger Number: " + passenger.getPassengerNumber());
        if (passenger.getType() != PassengerType.PREMIUM) {
            System.out.println("- Balance: $" + passenger.getBalance());
        }
        System.out.println("Activities Signed Up For:");
        for (Activity activity : passenger.getActivities()) {
            System.out.println("- Activity: " + activity.getName() + ", Destination: " + getDestinationName(activity) + ", Price Paid: $" + getActivityPrice(passenger, activity));
        }
    }

    public void printAvailableActivities(TravelPackage travelPackage) {
        // Print the details of all the activities that still have spaces available
        System.out.println("Available Activities for Travel Package: " + travelPackage.getName());
        for (Destination destination : travelPackage.getItinerary()) {
            System.out.println("Destination: " + destination.getName());
            System.out.println("Available Activities:");
            for (Activity activity : destination.getActivities()) {
                if (activity.getCapacity() > 0) {
                    System.out.println("- " + activity.getName() + ": " + activity.getDescription() + ", Cost: $" + activity.getCost() + ", Capacity: " + activity.getCapacity());
                }
            }
        }
    }

    private String getDestinationName(Activity activity) {
        for (TravelPackage travelPackage : packages) {
            for (Destination destination : travelPackage.getItinerary()) {
                for (Activity destActivity : destination.getActivities()) {
                    if (destActivity.equals(activity)) {
                        return destination.getName();
                    }
                }
            }
        }
        return "Unknown";
    }

    private double getActivityPrice(Passenger passenger, Activity activity) {
        if (passenger.getType() == PassengerType.STANDARD) {
            return activity.getCost();
        } else if (passenger.getType() == PassengerType.GOLD) {
            return activity.getCost() * 0.9; // 10% discount for gold passengers
        } else {
            return 0; // Premium passengers sign up for free
        }
    }
}