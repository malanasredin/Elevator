package elevator_app;

import java.util.ArrayList;

public class Elevator {
    int currentFloor;
    final int maxPassengers = 5;
    Direction direction;
    int directionStartFloor;
    int directionFinishFloor;
    boolean isFull;
    ArrayList<Passenger> passengers = new ArrayList<>();

    public void move() {
        if (direction == null) {
            direction = Direction.UP;
        }

        int maxUp = currentFloor;
        int maxDown = currentFloor;
        for (int i = 0; i < passengers.size(); i++) {
            final Passenger passenger = passengers.get(i);
            final int floorCount =  passenger.targetFloor - currentFloor;
            if(floorCount > 0 && floorCount > maxUp)  {
                maxUp = floorCount;
            } else if(floorCount < 0 && floorCount < maxDown) {
                maxDown = floorCount;
            }
        }
        // пересчитать на самый дальний этаж по направлению и переместить этаж на 1
    }

    public void checkIsFull() {
        isFull = passengers.size() == 5;
    }

    public void letPeopleOut() {
        if (!passengers.isEmpty()) {
            passengers.removeIf(passenger -> passenger.targetFloorReached(currentFloor));
        }
    }
}
