package test;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;

public class TravelAgencySystemTest {

    @Test
    public void testPrintItinerary() {
        TravelPackage package1 = createTestTravelPackage();
        String expectedOutput = "Travel Package: Package 1\n" +
                "Destination: Los Angeles\n" +
                "Activity: Scuba Diving\n" +
                "Description: Jump in the water to see the fishes and corals\n" +
                "Cost: 100.0\n" +
                "Capacity: 5\n" +
                "\n" +
                "Activity: Banana Ride\n" +
                "Description: See the sea with a set of bumpy rides\n" +
                "Cost: 200.0\n" +
                "Capacity: 3\n" +
                "\n" +
                "Destination: New York\n" +
                "Activity: Rollar Coaster\n" +
                "Description: Scream your lungs aloud\n" +
                "Cost: 150.0\n" +
                "Capacity: 4\n" +
                "\n";
        assertEquals(expectedOutput, getOutput(package1::printItinerary));
    }

    @Test
    public void testPrintPassengerList() {
        TravelPackage package1 = createTestTravelPackage();
        String expectedOutput = "Travel Package: Package 1\n" +
                "Passenger Capacity: 10\n" +
                "Number of Passengers: 3\n" +
                "Passenger Name: Lohith\n" +
                "Passenger Number: 1\n" +
                "\n" +
                "Passenger Name: Aathira Vinod\n" +
                "Passenger Number: 2\n" +
                "\n" +
                "Passenger Name: Raghav Gupta\n" +
                "Passenger Number: 3\n" +
                "\n";
        assertEquals(expectedOutput, getOutput(package1::printPassengerList));
    }

    @Test
    public void testPrintPassengerDetails() {
        TravelPackage package1 = createTestTravelPackage();
        StandardPassenger passenger1 = (StandardPassenger) package1.getPassengers().get(0);
        String expectedOutput = "Passenger Name: Lohith\n" +
                "Passenger Number: 1\n" +
                "Balance: 500.0\n" +
                "Activities:\n" +
                "Activity: Scuba Diving\n" +
                "Destination: Los Angeles\n" +
                "Price Paid: 100.0\n" +
                "\n" +
                "Activity: Rollar Coaster\n" +
                "Destination: New York\n" +
                "Price Paid: 0.0\n" +
                "\n";
        assertEquals(expectedOutput, getOutput(() -> package1.printPassengerDetails(passenger1)));
    }

    @Test
    public void testPrintAvailableActivities() {
        TravelPackage package1 = createTestTravelPackage();
        String expectedOutput = "Available Activities:\n" +
                "Activity: Scuba Diving\n" +
                "Destination: Los Angeles\n" +
                "Remaining Capacity: 4\n" +
                "\n" +
                "Activity: Banana Ride\n" +
                "Destination: Los Angeles\n" +
                "Remaining Capacity: 3\n" +
                "\n" +
                "Activity: Rollar Coaster\n" +
                "Destination: New York\n" +
                "Remaining Capacity: 4\n" +
                "\n";
        assertEquals(expectedOutput, getOutput(package1::printAvailableActivities));
    }

    private TravelPackage createTestTravelPackage() {
        TravelPackage package1 = new TravelPackage("Package 1", 10);

        Destination destination1 = new Destination("Los Angeles");
        Destination destination2 = new Destination("New York");

        Activity activity1 = new Activity("Scuba Diving", "Jump in the water to see the fishes and corals", 100.0, 5);
        Activity activity2 = new Activity("Banana Ride", "See the sea with a set of bumpy rides", 200.0, 3);
        Activity activity3 = new Activity("Rollar Coaster", "Scream your lungs aloud", 150.0, 4);

        destination1.addActivity(activity1);
        destination1.addActivity(activity2);
        destination2.addActivity(activity3);

        package1.addDestination(destination1);
        package1.addDestination(destination2);

        StandardPassenger passenger1 = new StandardPassenger("Lohith", 1, 500.0);
        GoldPassenger passenger2 = new GoldPassenger("Aathira Vinod", 2, 1000.0);
        PremiumPassenger passenger3 = new PremiumPassenger("Raghav Gupta", 3);

        package1.addPassenger(passenger1);
        package1.addPassenger(passenger2);
        package1.addPassenger(passenger3);

        passenger1.addActivity(activity1);
        passenger1.addActivity(activity3);
        passenger2.addActivity(activity2);
        passenger3.addActivity(activity1);

        return package1;
    }

    private String getOutput(Runnable runnable) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        runnable.run();
        System.setOut(originalOut);
        return outputStream.toString();
    }
}



// Used Junit4 
