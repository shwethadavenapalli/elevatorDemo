package com.fun.elevatorDemo.ElevatorDemo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class LiftTasksRequestHolder {

  private static Map<Lift, List<LiftRequest>> liftRequestListMap = new ConcurrentHashMap<Lift, List<LiftRequest>>();

  boolean valueSet = false;

  public synchronized void storeIntoLift(Lift lift1, LiftRequest liftRequest)
      throws InterruptedException {

    if (valueSet) {
      //wait for consumer to consume an item
      System.out.println("Waiting for the consumer to consume!!!");
      wait();
    }

    //update value
    System.out.println("Storing the values");
    if (liftRequestListMap.get(lift1) == null) {
      liftRequestListMap.put(lift1, new CopyOnWriteArrayList<>());
    }
    liftRequestListMap.get(lift1).add(liftRequest);

    valueSet = true;
    notify();
    Thread.sleep(2000);

  }

  public synchronized List<LiftRequest> getLiftRequest(Lift lift1) throws InterruptedException {

    // Wait for producer to produce an item
    if (!valueSet) {
      System.out.println("Waiting for producer to set value");
      wait();
    }

    System.out.println(" Consumer geting item: " );
    List<LiftRequest> liftRequests = liftRequestListMap.remove(lift1);

    valueSet = false;
    notify();

    Thread.sleep(2000);
    return liftRequests;
  }

  public static void main(String[] args) {
    LiftTasksRequestHolder liftTasksRequestHolder = new LiftTasksRequestHolder();
    Producer producer = new Producer(liftTasksRequestHolder);
    Consumer consumer = new Consumer(liftTasksRequestHolder);
    producer.start();
    consumer.start();
  }


}



//Producer thread
class Producer extends Thread {
  private LiftTasksRequestHolder liftTasksRequestHolder;
  int i;

  public Producer(LiftTasksRequestHolder liftTasksRequestHolder) {
    this.liftTasksRequestHolder = liftTasksRequestHolder;
  }

  public void run() {
    int i=0;
    while (true) {
      try {
        Lift lift1 = new Lift(1);
        LiftRequest liftRequestFrom1To4 = new LiftRequest(i, i++);
        System.out.println("Storing request into lift where req is"+liftRequestFrom1To4.toString());
        liftTasksRequestHolder.storeIntoLift(lift1,liftRequestFrom1To4);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

//Consumer Thread
class Consumer extends Thread {
  private LiftTasksRequestHolder liftTasksRequestHolder;

  public Consumer(LiftTasksRequestHolder liftTasksRequestHolder) {
    this.liftTasksRequestHolder = liftTasksRequestHolder;
  }

  public void run() {
    while (true) {
      try {
        Lift lift1 = new Lift(1);
        System.out.print("Consuming request from liftReqList where");
        List<LiftRequest> lift1RequestList = liftTasksRequestHolder.getLiftRequest(lift1);
        for(LiftRequest liftRequest:lift1RequestList){
          System.out.println(liftRequest.toString());
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}


