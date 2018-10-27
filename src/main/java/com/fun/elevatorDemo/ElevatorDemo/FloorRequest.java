package com.fun.elevatorDemo.ElevatorDemo;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class FloorRequest {
  private final UUID randomUUID;
  private final int currentFloor;
  private final int destnFloor;
}
