package com.fun.elevatorDemo.ElevatorDemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fun.elevatorDemo.ElevatorDemo.exception.InvalidLiftRequestException;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class LiftManagerTest {

  @Test
  public void shouldAssignALiftToAUserRequest() throws InvalidLiftRequestException {

    //Arrange
    LiftManager liftManager = new LiftManager(0, 10);
    int endFloorNumber = 10, startFloorNumber = 0;
    LiftRequest liftRequest = new LiftRequest(startFloorNumber, endFloorNumber);

    //Act
    Lift lift = liftManager.accept(liftRequest);

    //Assert
    assertThat(lift).isNotNull();
    assertThat(lift.getId()).isEqualTo(1);
  }

  @Test
  public void shouldNotAssignLiftWhenUsersLiftRequestHasInvalidFloorNumber() {

    int endFloorNumber = 100, startFloorNumber = 200;
    LiftManager liftManager = new LiftManager(0, 10);
    LiftRequest liftRequest = new LiftRequest(startFloorNumber, endFloorNumber);

    //Act
    assertThatThrownBy(() -> liftManager.accept(liftRequest))
        .isInstanceOf(InvalidLiftRequestException.class)
        .hasMessage("Invalid floor number provided");
  }


  @Test
  public void liftShouldEmitStateOnceARequestIsAssigned() throws InterruptedException {

    LiftUser liftUser = new LiftUser();
    FloorRequest floorRequest = liftUser.createRequest(1, 5);

    System.out.println("floorRequest = " + floorRequest);

    Lift lift = new Lift(1);
    UUID userID = UUID.randomUUID();
    Observable<LiftState> liftStateObservable = lift.acceptUserRequest(floorRequest);

    Observable<LiftState> newLiftStateObservable = lift.acceptUserRequest(floorRequest);

    TestObserver<LiftState> test = newLiftStateObservable.test();
    test.await(1, TimeUnit.SECONDS);

    test.assertValueCount(0);


    LiftState liftState = liftStateObservable.blockingLast();

    System.out.println("liftState after lift arrives to user = " + liftState);

    FloorRequest newRequestForLift = liftUser.createRequest(liftState.currentFloor, 10);
    Observable<LiftState> board = lift.board(newRequestForLift);

    board.doOnComplete(() -> {
      System.out.println("User alighted from the lift");
    });

    int currentFloor = board.blockingLast().getCurrentFloor();
    assertThat( currentFloor).isEqualTo(newRequestForLift.getDestnFloor());

  }

}
