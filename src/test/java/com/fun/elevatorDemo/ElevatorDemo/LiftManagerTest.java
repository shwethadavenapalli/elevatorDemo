package com.fun.elevatorDemo.ElevatorDemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fun.elevatorDemo.ElevatorDemo.exception.InvalidLiftRequestException;
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

}
