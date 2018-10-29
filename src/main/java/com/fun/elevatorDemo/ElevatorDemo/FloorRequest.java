package com.fun.elevatorDemo.ElevatorDemo;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class FloorRequest {
  private final UUID userUUID;
  private final int currentFloor;
  private final int destnFloor;
}
