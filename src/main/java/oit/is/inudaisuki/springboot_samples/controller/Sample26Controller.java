package oit.is.inudaisuki.springboot_samples.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @RequestMapping("/sample26")をクラスの前につけると，このクラスのすべてのメソッドは/sample26で呼び出されることを表す
 */
@Controller
@RequestMapping("/sample26")
public class Sample26Controller {

  /**
   * @GetMappingに引数が与えられていないので，クラスで指定されたとおり/sample26へのGETリクエストがあったら，sample26()を呼び出して，sample26.htmlを返す
   *
   * @return
   */
  @GetMapping
  public String sample26() {
    return "sample26.html";
  }
}
