package com.fun.elevatorDemo.ElevatorDemo;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Lift implements Runnable {

  private int id;
  private MotionState motionState = MotionState.STATIONARY;
  private int currentFloorNumber;


  public Lift(int id) {
    this.id = id;
  }

  public void run() {
    for (; ; ) {
      System.out.println("Lift.run");
      try {
        processRequestQueue();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

  public void processRequestQueue() throws InterruptedException {
    List<LiftRequest> liftRequestList = new LiftTasksRequestHolder().getLiftRequest(this);
    LiftRequest liftRequest = liftRequestList.get(0);
    goToFloor(liftRequest.getStartFloorNumber());
    goToFloor(liftRequest.getEndFloorNumber());
    liftRequestList.remove(0);
  }


  public void goToFloor(int startFloorNumber) {
    System.out.println("Lift.goToFloor");

    if (currentFloorNumber > startFloorNumber) {
      for (int i = currentFloorNumber; i > startFloorNumber; i--) {
        try {
          Thread.sleep(2000);
          currentFloorNumber--;

        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }

      }
    } else {
      for (int i = currentFloorNumber; i < startFloorNumber; i++) {
        try {
          Thread.sleep(2000);
          currentFloorNumber++;
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }

      }
    }

  }
}
