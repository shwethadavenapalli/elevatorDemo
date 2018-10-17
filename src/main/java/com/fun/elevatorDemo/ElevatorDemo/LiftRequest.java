package com.fun.elevatorDemo.ElevatorDemo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LiftRequest {

 private final int endFloorNumber;
 private final int startFloorNumber;

}
