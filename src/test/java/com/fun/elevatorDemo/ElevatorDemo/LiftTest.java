package com.fun.elevatorDemo.ElevatorDemo;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class LiftTest {

  @Test
  public void shouldGotoTheRequestedFloorNumber() {
    LiftRequest liftRequest = new LiftRequest(3, 5);
    Lift lift1 = new Lift(1);
    LiftTasksRequestHolder.storeIntoLift(lift1, liftRequest);
    //lift1.processRequestQueue();
    lift1.goToFloor(liftRequest.getStartFloorNumber());
    assertThat(lift1.getCurrentFloorNumber()).isEqualTo(liftRequest.getStartFloorNumber());
    lift1.goToFloor(liftRequest.getEndFloorNumber());
    assertThat(lift1.getCurrentFloorNumber()).isEqualTo(liftRequest.getEndFloorNumber());

  }

  @Test
  public void shouldProcessMultipleRequest() {
    LiftRequest liftRequestFrom2To5 = new LiftRequest(2, 5);
    LiftRequest liftRequestFrom3To4 = new LiftRequest(3, 4);
    Lift lift1 = new Lift(1);
    LiftTasksRequestHolder.storeIntoLift(lift1, liftRequestFrom2To5);
    LiftTasksRequestHolder.storeIntoLift(lift1, liftRequestFrom3To4);
    lift1.processRequestQueue();

  }


}