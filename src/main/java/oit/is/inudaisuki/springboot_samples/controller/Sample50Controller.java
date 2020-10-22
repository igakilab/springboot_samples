package oit.is.inudaisuki.springboot_samples.controller;

import java.io.IOException;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.inudaisuki.springboot_samples.service.AsyncCountService50;

@Controller
@RequestMapping("/sample50")
public class Sample50Controller {

  @Autowired
  AsyncCountService50 acs50;
  private HashSet<SseEmitter> sseEmitters = new HashSet<SseEmitter>();
  private int messageCount = 0;

  @GetMapping("step1")
  public String sample501() {
    return "sample50.html";
  }

  @GetMapping("asyncCount1")
  public SseEmitter asyncCount1() {
    System.out.println("asyncCount1");
    final SseEmitter sseEmitter = new SseEmitter();
    sseEmitter.onTimeout(() -> {
      sseEmitter.complete();
    });
    acs50.asyncCount1(sseEmitter);
    return sseEmitter;
  }

  /**
   * これはscheduledMsgEmitterと連動する
   *
   * @return
   */
  @GetMapping("asyncCount3")
  public SseEmitter asyncCount3() {
    System.out.println("asyncCount3");
    final SseEmitter sseEmitter = new SseEmitter();
    sseEmitter.onCompletion(() -> {
      synchronized (this.sseEmitters) {
        this.sseEmitters.remove(sseEmitter);
      }
    });
    sseEmitter.onTimeout(() -> {
      sseEmitter.complete();
    });
    sseEmitters.add(sseEmitter);
    return sseEmitter;
  }

  /**
   * Springboot起動後に2秒ごとに呼び出されるmethod
   * これを利用する場合はSpringbootSamplesApplicationの@EnableSchedulingのコメントアウトを外すこと
   */
  @Scheduled(fixedDelay = 2 * 1000)
  public void scheduledMsgEmitter() {
    if (!sseEmitters.isEmpty()) {
      ++messageCount;
    } else {
      System.out.println("No active Emitters");
    }
    System.out.println("Send Messages:" + messageCount);
    sseEmitters.forEach(emitter -> {
      if (null != emitter) {
        try {
          System.out.println("Timeout:" + emitter.getTimeout());
          emitter.send("MessageCounter:" + messageCount);
          emitter.complete();

        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  @GetMapping("asyncDate")
  public SseEmitter asyncDate() {
    SseEmitter emitter = new SseEmitter();
    acs50.asyncDateTime(emitter);
    return emitter;
  }

}
