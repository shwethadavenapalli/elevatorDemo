package com.fun.elevatorDemo.ElevatorDemo;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lift {
  private int id;
  private MotionState motionState = MotionState.STATIONARY;

  private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

  public Lift(int id) {
    this.id = id;
  }


  public Observable<LiftState> acceptUserRequest(FloorRequest floorRequest) {

    if ( motionState.equals(MotionState.MOVING))
      return Observable.empty();

    ObservableOnSubscribe<LiftState> handler = emitter -> {

      Future<Object> future = executor.schedule(() -> {

        IntStream.range(floorRequest.getCurrentFloor(), floorRequest.getDestnFloor() + 1 )
            .forEach( floorNumber -> {
              System.out.println("Lift is in floorNumber = " + floorNumber);
              emitter.onNext(new LiftState(id, floorNumber));
              setMotionState(MotionState.MOVING);

            });

        emitter.onComplete();

        setMotionState(MotionState.STATIONARY);
        return null;
      }, 2, TimeUnit.SECONDS);

      emitter.setCancellable(() -> future.cancel(false));
    };

    return Observable.create(handler );
  }

  public Observable<LiftState> board(FloorRequest request) {

    return acceptUserRequest(request);
  }
}
