package com.fun.elevatorDemo.ElevatorDemo;

import com.fun.elevatorDemo.ElevatorDemo.exception.InvalidLiftRequestException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * Manages lifts
 * */
@AllArgsConstructor
@Getter
public class LiftManager {
  private int lowestFloorNumber;
  private int highestFloorNumber;

  public Lift accept(LiftRequest liftRequest) throws InvalidLiftRequestException {
    if( liftRequest.getStartFloorNumber() > highestFloorNumber  ||
        liftRequest.getEndFloorNumber() < lowestFloorNumber )
      throw new InvalidLiftRequestException("Invalid floor number provided");

    return new Lift(1);
  }
}
