package com.fun.elevatorDemo.ElevatorDemo;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class FloorRequestDirectionEvaluatorTest {

  @Test
  public void shouldReturnDirectionAsUPWhenTwoFloorRequestsAreInTheSameDirection() {

    List<LiftRequest> floorRequests = Arrays.asList(new LiftRequest(1, 5),
        new LiftRequest(2, 4));

    Map<LiftDirection, List<LiftRequest>> liftDirectionListMap = FloorRequestDirectionEvaluator.groupByDirection(floorRequests);

    assertThat( liftDirectionListMap).hasSize(2);
    assertThat( liftDirectionListMap.get(LiftDirection.UP)).hasSize(2);
    assertThat( liftDirectionListMap.get(LiftDirection.DOWN)).hasSize(0);

  }
}