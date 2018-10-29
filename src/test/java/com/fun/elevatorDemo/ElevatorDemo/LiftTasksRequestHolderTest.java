package com.fun.elevatorDemo.ElevatorDemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

public class LiftTasksRequestHolderTest {

  @Test
  public void shouldHoldRequestToLiftIntoLift1IfLift1DoesntHaveAnyActionsPending() {
    LiftTasksRequestHolder liftTasksRequestHolder = new LiftTasksRequestHolder();
    Lift lift1= new Lift(1);
    LiftRequest liftRequest = new LiftRequest(0,4);
    liftTasksRequestHolder.storeIntoLift(lift1,liftRequest);
    assertThat( liftTasksRequestHolder.getLiftRequest(lift1)).hasSize(1);
  }
}