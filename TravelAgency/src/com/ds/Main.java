package com.ds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

    public class Main {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            TravelAgency agency = new TravelAgency();

            // Create activities
            Activity activity1 = new Activity("Sightseeing Tour", "Guided tour of the city landmarks", 50, 20);
            Activity activity2 = new Activity("Beach Volleyball", "Fun beach sport activity", 20, 3);
            Activity activity3 = new Activity("Scuba Diving", "Underwater diving adventure", 100, 1);
            Activity activity4 = new Activity("Hiking", "Explore natural trails", 30, 1);
            Activity activity5 = new Activity("Wine Tasting", "Taste local wines", 40, 5);

            // Create destinations and add activities
            Destination destination1 = new Destination("Paris");
            destination1.addActivity(activity1);
            destination1.addActivity(activity4); // Adding a new activity to Paris
            Destination destination2 = new Destination("Maldives");
            destination2.addActivity(activity2);
            destination2.addActivity(activity3);

            // Create a travel package and add destinations
            TravelPackage package1 = new TravelPackage("Summer Escape", 3);
            package1.addDestination(destination1);
            package1.addDestination(destination2);

            // Add package to the agency
            agency.addPackage(package1);

            while (true) {
                System.out.println("\nWelcome to the Travel Agency Menu:");
                System.out.println("1. Sign Up for an Activity");
                System.out.println("2. View Itinerary");
                System.out.println("3. View Passenger List");
                System.out.println("4. View Passenger Details");
                System.out.println("5. View Available Activities");
                System.out.println("6. View Package Details");
                System.out.println("7. Exit");
                System.out.print("Please enter your choice: ");
                int choice;
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number corresponding to your choice.");
                    scanner.nextLine(); // Consume invalid input
                    continue;
                }

                switch (choice) {
                    case 1:
                        signUpForActivity(scanner, agency);
                        break;
                    case 2:
                        viewItinerary(agency);
                        break;
                    case 3:
                        viewPassengerList(agency);
                        break;
                    case 4:
                        viewPassengerDetails(scanner, agency);
                        break;
                    case 5:
                        viewAvailableActivities(agency);
                        break;
                    case 6:
                        viewPackageDetails(scanner, agency);
                        break;
                    case 7:
                        System.out.println("Exiting... Goodbye!");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        private static void signUpForActivity(Scanner scanner, TravelAgency agency) {
            System.out.println("Enter passenger name:");
            String name = scanner.next();
            System.out.println("Enter passenger number:");
            int passengerNumber = scanner.nextInt();
            System.out.println("Enter passenger balance:");
            double balance = scanner.nextDouble();
            System.out.println("Enter passenger type (STANDARD, GOLD, PREMIUM):");
            PassengerType type = PassengerType.valueOf(scanner.next().toUpperCase());
            System.out.println("Enter activity name to sign up for:");
            String activityName = scanner.next();

            Passenger passenger = new Passenger(name, passengerNumber, balance, type);
            Activity activity = findActivityByName(agency, activityName);

            if (activity != null) {
                passenger.signUpForActivity(passenger, activity);
            } else {
                System.out.println("Activity not found.");
            }
        }

        private static void viewItinerary(TravelAgency agency) {
            System.out.println("\nItinerary:");
            for (TravelPackage travelPackage : agency.getPackages()) {
                agency.printItinerary(travelPackage);
            }
        }

        private static void viewPassengerList(TravelAgency agency) {
            System.out.println("\nPassenger Lists:");
            for (TravelPackage travelPackage : agency.getPackages()) {
                agency.printPassengerList(travelPackage);
            }
        }

        private static void viewPassengerDetails(Scanner scanner, TravelAgency agency) {
            System.out.println("Enter passenger name:");
            String name = scanner.next();
            boolean found = false;
            for (TravelPackage travelPackage : agency.getPackages()) {
                for (Passenger passenger : travelPackage.getPassengers()) {
                    if (passenger.getName().equalsIgnoreCase(name)) {
                        agency.printPassengerDetails(passenger);
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                System.out.println("Passenger not found.");
            }
        }

        private static void viewAvailableActivities(TravelAgency agency) {
            System.out.println("\nAvailable Activities:");
            for (TravelPackage travelPackage : agency.getPackages()) {
                agency.printAvailableActivities(travelPackage);
            }
        }

        private static Activity findActivityByName(TravelAgency agency, String activityName) {
            for (TravelPackage travelPackage : agency.getPackages()) {
                for (Destination destination : travelPackage.getItinerary()) {
                    for (Activity activity : destination.getActivities()) {
                        if (activity.getName().equalsIgnoreCase(activityName)) {
                            return activity;
                        }
                    }
                }
            }
            return null;
        }

        private static void viewPackageDetails(Scanner scanner, TravelAgency agency) {
            System.out.println("\nAvailable Packages:");
            for (TravelPackage travelPackage : agency.getPackages()) {
                System.out.println("- " + travelPackage.getName());
            }
            System.out.println("\nEnter the package name to view details:");
            String packageName = scanner.next();
            TravelPackage selectedPackage = findPackageByName(agency, packageName);
            if (selectedPackage != null) {
                System.out.println("\nPackage Details:");
                System.out.println("- Name: " + selectedPackage.getName());
                System.out.println("- Passenger Capacity: " + selectedPackage.getPassengerCapacity());
                System.out.println("- Itinerary:");
                for (Destination destination : selectedPackage.getItinerary()) {
                    System.out.println("  - Destination: " + destination.getName());
                    System.out.println("    Activities:");
                    for (Activity activity : destination.getActivities()) {
                        System.out.println("      - " + activity.getName() + ": " + activity.getDescription() +
                                ", Cost: $" + activity.getCost() + ", Capacity: " + activity.getCapacity());
                    }
                }
            } else {
                System.out.println("Package not found.");
            }
        }

        private static TravelPackage findPackageByName(TravelAgency agency, String packageName) {
            for (TravelPackage travelPackage : agency.getPackages()) {
                if (travelPackage.getName().equalsIgnoreCase(packageName)) {
                    return travelPackage;
                }
            }
            return null;
        }

    }
