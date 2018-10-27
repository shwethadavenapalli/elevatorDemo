package com.fun.elevatorDemo.ElevatorDemo;

import java.util.Random;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class LiftUser {

  UUID uuid = UUID.randomUUID();

  public FloorRequest createRequest() {

    Random random = new Random();
    int currentFloor = random.nextInt(10);
    return new FloorRequest(uuid, currentFloor, 10-currentFloor );
  }

  public FloorRequest createRequest(int currentFloor) {

    Random random = new Random();
    int destnFloor = random.nextInt(10);

    return new FloorRequest(uuid, currentFloor, destnFloor);
  }

  public FloorRequest createRequest(int currentFloor, int destnFloor) {
    return new FloorRequest(uuid, currentFloor, destnFloor);
  }

}
