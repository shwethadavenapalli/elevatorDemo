package com.fun.elevatorDemo.ElevatorDemo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class LiftRequest {
 private final int startFloorNumber;
 private final int endFloorNumber;


}
