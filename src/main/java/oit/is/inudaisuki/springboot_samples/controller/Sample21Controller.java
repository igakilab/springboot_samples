package oit.is.inudaisuki.springboot_samples.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Sample21Controller
 */
@Controller
public class Sample21Controller {

  /**
   * sample21というGETリクエストがあったら sample21()を呼び出し，sample21.htmlを返す
   */
  @GetMapping("/sample21")
  public String sample21() {
    return "sample21.html";
  }

  /**
   * パスパラメータ2つをGETで受け付ける 1つ目の変数をparam1という名前で，2つ目の変数をparam2という名前で受け取る
   * GETで受け取った2つの変数とsample22()の引数の名前が同じなため， 引数の前に @PathVariable と付けるだけで，パスパラメータの値を
   * javaで処理できるようになる ModelMapはthymeleafに渡すためのMapと呼ばれるデータ構造を持つ変数
   * Mapはkeyとvalueの組み合わせで値を保持する
   *
   * @param param1
   * @param param2
   * @param model
   * @return
   */
  @GetMapping("/sample22/{param1}/{param2}")
  public String sample22(@PathVariable String param1, @PathVariable String param2, ModelMap model) {
    int tasu = Integer.parseInt(param1);// param1が文字列なので，parseIntでint型の数値に変換する
    int tasareru = Integer.parseInt(param2);
    int tasuResult = tasu + tasareru;

    // ModelMap型変数のmodelにtasuResult1という名前の変数で，tasuResultの値を登録する．
    // ここで値を登録するとthymeleafが受け取り，htmlで処理することができるようになる
    model.addAttribute("tasuResult1", tasuResult);
    return "sample21.html";

  }

  /**
   * クエリパラメータの引数2つを受け付ける URLでの?のあとのパラメータ名とjavaメソッドの引数名は同じであることが望ましい(別にする方法は一応ある)
   * 引数をStringとして受け取ってparseIntする以外にもInteger(intのラッパークラス)クラスの変数として受け取ってそのまま加算する方法もある
   *
   * @param tasu1
   * @param tasu2
   * @param model
   * @return
   */
  @GetMapping("/sample23")
  public String sample23(@RequestParam Integer tasu1, @RequestParam Integer tasu2, ModelMap model) {
    int tasuResult = tasu1 + tasu2;
    model.addAttribute("tasuResult2", tasuResult);
    // ModelMap型変数のmodelにtasuResult2という名前の変数で，tasuResultの値を登録する．
    // ここで値を登録するとthymeleafが受け取り，htmlで処理することができるようになる
    return "sample21.html";

  }
}
