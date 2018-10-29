package com.fun.elevatorDemo.ElevatorDemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class FloorRequestDirectionEvaluator {

  public static Map<LiftDirection, List<LiftRequest>> groupByDirection(
      List<LiftRequest> floorRequests) {

    List<LiftRequest> upDirectionList = getDirectionList(floorRequests, getFloorsGoingUp());

    List<LiftRequest> downDirectionList = getDirectionList(floorRequests, getFloorReqGoingDown());

    HashMap<LiftDirection, List<LiftRequest>> liftDirectionListHashMap = new HashMap<>();
    liftDirectionListHashMap.put(LiftDirection.UP, upDirectionList);
    liftDirectionListHashMap.put(LiftDirection.DOWN, downDirectionList);
    return liftDirectionListHashMap;
  }

  private static List<LiftRequest> getDirectionList(List<LiftRequest> floorRequests,
      Predicate<LiftRequest> floorsGoingUp) {
    return floorRequests.stream()
        .filter(floorsGoingUp)
        .collect(Collectors.toList());
  }

  private static Predicate<LiftRequest> getFloorsGoingUp() {
    return floorRequest -> floorRequest.getEndFloorNumber() - floorRequest.getStartFloorNumber() > 0;
  }

  private static Predicate<LiftRequest> getFloorReqGoingDown() {
    return floorRequest -> floorRequest.getEndFloorNumber()- floorRequest.getStartFloorNumber() < 0;
  }

}
