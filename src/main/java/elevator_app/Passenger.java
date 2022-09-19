package elevator_app;

public class Passenger {
    final int targetFloor;

    public Passenger(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    boolean targetFloorReached(int currentFloor) {
        return targetFloor == currentFloor;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "targetFloor=" + targetFloor +
                '}';
    }
}
