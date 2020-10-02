package oit.is.inudaisuki.springboot_samples.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * このクラスのすべてのメソッドは/sample26で呼び出される
 *
 */
@Controller
@RequestMapping("/sample26")
public class Sample26Controller {

  /**
   * sample26というGETリクエストがあったら，sample26()を呼び出して，sample26.htmlを返すメソッド
   *
   * @return
   */
  @GetMapping
  public String sample26() {
    return "sample26.html";
  }

  /**
   * メソッド名は異なるが，sample26というPOSTリクエストがあったら，こちらが呼び出されて，sample26.htmlが返る
   *
   * @return
   */
  @PostMapping
  public String sample27(@RequestParam final Integer min, @RequestParam final Integer max, ModelMap model) {
    int sum = 0;
    ArrayList<Integer> sumRange = new ArrayList<>();
    // minからmaxまでの数値を加算し，ArrayListに格納する
    for (int i = min; i <= max; i++) {
      sum = sum + i;
      sumRange.add(i);
    }
    model.addAttribute("sumRange", sumRange);
    model.addAttribute("sum", sum);
    return "sample26.html";
  }

}
