package com.fun.elevatorDemo.ElevatorDemo;

import com.fun.elevatorDemo.ElevatorDemo.exception.InvalidLiftRequestException;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * Manages lifts
 * */
@Getter

public class LiftManager {
  private int lowestFloorNumber;
  private int highestFloorNumber;
  

  public LiftManager(int lowestFloorNumber, int highestFloorNumber) {
    this.lowestFloorNumber = lowestFloorNumber;
    this.highestFloorNumber = highestFloorNumber;
  }


  public Lift accept(LiftRequest liftRequest)
      throws InvalidLiftRequestException, InterruptedException {
    if( liftRequest.getStartFloorNumber() > highestFloorNumber  ||
        liftRequest.getEndFloorNumber() < lowestFloorNumber )
      throw new InvalidLiftRequestException("Invalid floor number provided");
   
    //fetch from LiftTaskRequestHolder
    Lift lift = new Lift(1);
    new LiftTasksRequestHolder().storeIntoLift(lift,liftRequest);
    return lift;
  }
}
