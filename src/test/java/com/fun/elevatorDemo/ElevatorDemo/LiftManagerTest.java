package com.fun.elevatorDemo.ElevatorDemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fun.elevatorDemo.ElevatorDemo.exception.InvalidLiftRequestException;
import org.junit.Ignore;
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
  public void shouldAssignALiftToAUserRequestAndLiftShouldTakeUserToDestinationFloor() throws InvalidLiftRequestException {

    //Arrange
    LiftManager liftManager = new LiftManager(0, 10);
    int endFloorNumber = 10, startFloorNumber = 0;
    LiftRequest liftRequest = new LiftRequest(startFloorNumber, endFloorNumber);

    //Act
    Lift lift = liftManager.accept(liftRequest);

    //Assert
    assertThat(lift).isNotNull();
    assertThat(lift.getId()).isEqualTo(1);
    assertThat(lift.getCurrentFloorNumber()).isEqualTo(endFloorNumber);
  }



  @Test
  public void shouldAssignSameLiftTo2usersIfLiftIsMovingInUserRequestedDirection()
      throws InvalidLiftRequestException {

    LiftManager liftManager = new LiftManager(0, 10);
    LiftRequest liftRequest = new LiftRequest(1, 10);
    LiftRequest secondLiftRequest = new LiftRequest(2, 4);
    Lift lift = liftManager.accept(liftRequest);
    Lift lift2 = liftManager.accept(secondLiftRequest);

    assertThat(lift).isEqualTo(lift2);
  }


  @Ignore
  @Test
  public void shouldNotAssignSameLiftTo2usersIfLiftIsNotMovingInUserRequestedDirection()
      throws InvalidLiftRequestException {

    LiftManager liftManager = new LiftManager(0, 10);
    LiftRequest liftRequest = new LiftRequest(1, 10);
    LiftRequest secondLiftRequest = new LiftRequest(10, 1);
    Lift lift = liftManager.accept(liftRequest);
    Lift lift2 = liftManager.accept(secondLiftRequest);

    assertThat(lift).isNotEqualTo(lift2);
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

}
