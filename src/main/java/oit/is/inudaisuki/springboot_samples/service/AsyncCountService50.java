package oit.is.inudaisuki.springboot_samples.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class AsyncCountService50 {
  int count = 0;

  @Async
  public void asyncCount1(SseEmitter emitter) {
    System.out.println("AsyncCountService50.asyncCount1");
    try {
      ++count;
      // TimeUnit.SECONDS.sleep(1);
      System.out.println(count);
      emitter.send(count);
    } catch (Exception e) {
      // TODO: handle exception
      e.getMessage();
    }
    emitter.complete();
    System.out.println("asyncCount complete");

  }

  @Async
  public void asyncDateTime(SseEmitter emitter) {
    System.out.println("asyncDateTime");
    boolean finished = false;
    int counter = 0;
    while (!finished && counter < 5) {
      counter++;
      System.out.println("counter:" + counter);
      try {
        // TimeUnit.SECONDS.sleep(1);
        emitter.send(LocalDateTime.now().toString());
      } catch (IOException e) {
        e.printStackTrace();

      } catch (Exception e) {
        e.getMessage();
        e.printStackTrace();
      }
    }
    emitter.complete();
    finished = true;
    System.out.println("finished!");
  }

}
