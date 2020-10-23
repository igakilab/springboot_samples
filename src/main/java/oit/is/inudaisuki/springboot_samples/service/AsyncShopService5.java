package oit.is.inudaisuki.springboot_samples.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class AsyncShopService5 {

  @Async
  public void asyncShowFruitsList(SseEmitter emitter) {
    try {
      while (true) {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("emitter.send:asyncShowFruitsList");
        emitter.send("/sample5/step6");
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
      emitter.complete();
    }
    System.out.println("asyncShowFruitsList complete");
  }

}
