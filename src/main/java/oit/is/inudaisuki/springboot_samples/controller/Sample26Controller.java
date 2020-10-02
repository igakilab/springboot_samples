package oit.is.inudaisuki.springboot_samples.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.inudaisuki.springboot_samples.model.Score;

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
   * メソッド名は異なるが，/sample26というPOSTリクエストがあったら，こちらが呼び出されて，sample26.htmlが返る
   *
   * @return
   */
  @PostMapping
  public String sample27(@RequestParam Integer min, @RequestParam Integer max, ModelMap model) {
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

  /**
   * /sample26/ave にPOSTでアクセスされるとこのメソッドが呼び出される
   *
   * @return
   */
  @PostMapping("ave")
  public String sample28(@RequestParam Double num1, @RequestParam Double num2, @RequestParam Double num3,
      ModelMap model) {
    ArrayList<Double> numList = new ArrayList<>();
    numList.add(num1);
    numList.add(num2);
    numList.add(num3);
    Score score = new Score(numList);
    model.addAttribute("score", score);
    return "sample26.html";
  }

}
