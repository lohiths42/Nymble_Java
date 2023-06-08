/**
* The TravelAgencySystem program implements an application that
* gives the itenarary and details related to a trip to the standard output.
*
* @author  Lohith S
* @version 1.0
* @since   2023-06-08 
*/

package test;

import java.util.ArrayList;
import java.util.List;

class Destination {
    private String name;
    private List<Activity> activities;

    public Destination(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }
}

class Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;

    public Activity(String name, String description, double cost, int capacity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }
}

class Passenger {
    private String name;
    private int passengerNumber;
    private double balance;
    private List<Activity> activities;

    public Passenger(String name, int passengerNumber, double balance) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.balance = balance;
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }
}

class TravelPackage {
    private String name;
    private int passengerCapacity;
    private List<Destination> destinations;
    private List<Passenger> passengers;

    public TravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.destinations = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void addDestination(Destination destination) {
        destinations.add(destination);
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public void printItinerary() {
        System.out.println("Travel Package: " + name);
        for (Destination destination : destinations) {
            System.out.println("Destination: " + destination.getName());
            for (Activity activity : destination.getActivities()) {
                System.out.println("Activity: " + activity.getName());
                System.out.println("Description: " + activity.getDescription());
                System.out.println("Cost: " + activity.getCost());
                System.out.println("Capacity: " + activity.getCapacity());
                System.out.println();
            }
        }
    }

    public void printPassengerList() {
        System.out.println("Travel Package: " + name);
        System.out.println("Passenger Capacity: " + passengerCapacity);
        System.out.println("Number of Passengers: " + passengers.size());
        for (Passenger passenger : passengers) {
            System.out.println("Passenger Name: " + passenger.getName());
            System.out.println("Passenger Number: " + passenger.getPassengerNumber());
            System.out.println();
        }
    }

    public void printPassengerDetails(Passenger passenger) {
        System.out.println("Passenger Name: " + passenger.getName());
        System.out.println("Passenger Number: " + passenger.getPassengerNumber());
        System.out.println("Balance: " + passenger.getBalance());
        System.out.println("Activities:");
        for (Activity activity : passenger.getActivities()) {
            System.out.println("Activity: " + activity.getName());
            System.out.println("Destination: " + getDestinationName(activity));
            System.out.println("Price Paid: " + getPricePaid(activity, passenger));
            System.out.println();
        }
    }

    public void printAvailableActivities() {
        System.out.println("Available Activities:");
        for (Destination destination : destinations) {
            for (Activity activity : destination.getActivities()) {
                int remainingCapacity = activity.getCapacity() - getNumberOfPassengersSignedUp(activity);
                if (remainingCapacity > 0) {
                    System.out.println("Activity: " + activity.getName());
                    System.out.println("Destination: " + destination.getName());
                    System.out.println("Remaining Capacity: " + remainingCapacity);
                    System.out.println();
                }
            }
        }
    }

    private String getDestinationName(Activity activity) {
        for (Destination destination : destinations) {
            if (destination.getActivities().contains(activity)) {
                return destination.getName();
            }
        }
        return "";
    }

    private double getPricePaid(Activity activity, Passenger passenger) {
        if (passenger instanceof StandardPassenger) {
            return activity.getCost();
        } else if (passenger instanceof GoldPassenger) {
            return activity.getCost() * 0.9;
        } else {
            return 0.0;
        }
    }

    private int getNumberOfPassengersSignedUp(Activity activity) {
        int count = 0;
        for (Passenger passenger : passengers) {
            if (passenger.getActivities().contains(activity)) {
                count++;
            }
        }
        return count;
    }
}

class StandardPassenger extends Passenger {
    public StandardPassenger(String name, int passengerNumber, double balance) {
        super(name, passengerNumber, balance);
    }
}

class GoldPassenger extends Passenger {
    public GoldPassenger(String name, int passengerNumber, double balance) {
        super(name, passengerNumber, balance);
    }
}

class PremiumPassenger extends Passenger {
    public PremiumPassenger(String name, int passengerNumber) {
        super(name, passengerNumber, 0.0);
    }
}

public class TravelAgencySystem {
    public static void main(String[] args) {
        // Creating travel package
        TravelPackage package1 = new TravelPackage("America", 10);

        // Creating destinations
        Destination destination1 = new Destination("Los Angeles");
        Destination destination2 = new Destination("New York");

        // Creating activities
        Activity activity1 = new Activity("Scuba Diving", "Jump in the water to see the fishes and corals", 100.0, 5);
        Activity activity2 = new Activity("Banana Ride", "See the sea with a set of bumpy rides", 200.0, 3);
        Activity activity3 = new Activity("Rollar Coaster", "Scream your lungs aloud", 150.0, 4);

        // Adding activities to destinations
        destination1.addActivity(activity1);
        destination2.addActivity(activity2);
        destination1.addActivity(activity3);

        // Adding destinations to travel package
        package1.addDestination(destination1);
        package1.addDestination(destination2);

        // Creating passengers
        StandardPassenger passenger1 = new StandardPassenger("Lohith", 1, 500.0);
        GoldPassenger passenger2 = new GoldPassenger("Aathira Vinod", 2, 1000.0);
        PremiumPassenger passenger3 = new PremiumPassenger("Raghav Gupta", 3);

        // Adding passengers to travel package
        package1.addPassenger(passenger1);
        package1.addPassenger(passenger2);
        package1.addPassenger(passenger3);

        // Signing up passengers for activities
        passenger1.addActivity(activity1);
        passenger1.addActivity(activity3);
        passenger2.addActivity(activity2);
        passenger3.addActivity(activity1);

        // Printing itinerary
        package1.printItinerary();

        // Printing passenger list
        package1.printPassengerList();

        // Printing passenger details
        package1.printPassengerDetails(passenger1);

        // Printing available activities
        package1.printAvailableActivities();
    }
}
