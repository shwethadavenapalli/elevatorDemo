package com.fun.elevatorDemo.ElevatorDemo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class LiftState {

  int liftId;
  int currentFloor;
}
