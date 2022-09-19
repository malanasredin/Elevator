package elevator_app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Building {
    final int floorCount = ThreadLocalRandom.current().nextInt(5, 20 + 1);
    final int maxPassengersOnFloor = ThreadLocalRandom.current().nextInt(0, 10 + 1);
    final Elevator elevator = new Elevator();
    final Map<Integer, ArrayList<Passenger>> floors = new HashMap<>();

    public Building() {
        initPassengers();

        startElevator();
    }

    private void initPassengers() {
        for (int i = 0; i < floorCount; i++) {
            final int currentFloor = i;
            final ArrayList<Passenger> passengersOnFloor = new ArrayList<>();
            for (int j = 0; j < maxPassengersOnFloor; j++) {
                int targetFloor = currentFloor;
                do {
                    targetFloor = ThreadLocalRandom.current().nextInt(0, floorCount);
                } while (targetFloor == currentFloor);
                passengersOnFloor.add(new Passenger(targetFloor));
            }
            floors.put(currentFloor, passengersOnFloor);
        }
    }

    void testPrint() {
        for (Map.Entry<Integer, ArrayList<Passenger>> entry : floors.entrySet()) {
            int floor = entry.getKey();
            ArrayList<Passenger> passengers = entry.getValue();
            System.out.println("Floor # " + floor);
            System.out.println(passengers.toString());
            // do what you have to do here
            // In your case, another loop.
        }
    }

    int peopleWaitingForElevator() {
        int count = 0;

        for (Map.Entry<Integer, ArrayList<Passenger>> entry : floors.entrySet()) {
            count += entry.getValue().size();
        }

        return count;
    }

    void startElevator() {
        while (peopleWaitingForElevator() > 0) {
            do {
                elevator.letPeopleOut();
                if (elevator.isFull) {
                    elevator.move();
                    continue;
                }

                final int currentFloor = elevator.currentFloor;
                final ArrayList<Passenger> passengersOnFloor = floors.get(currentFloor);
                final Iterator<Passenger> iter = passengersOnFloor.iterator();
                while (!elevator.isFull && iter.hasNext()) {
                    final Passenger passenger = iter.next();
                    elevator.passengers.add(passenger);
                    iter.remove();
                    elevator.checkIsFull();
                }
                elevator.move();
                
            } while (!elevator.passengers.isEmpty());

        }
    }


}

