package oit.is.inudaisuki.springboot_samples.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.inudaisuki.springboot_samples.model.Fruit;
import oit.is.inudaisuki.springboot_samples.model.FruitMapper;
import oit.is.inudaisuki.springboot_samples.service.AsyncShopService5;

/**
 * /sample5へのリクエストを扱うクラス authenticateの設定をしていれば， /sample5へのアクセスはすべて認証が必要になる
 */
@Controller
@RequestMapping("/sample5")
public class Sample51Controller {

  @Autowired
  FruitMapper fMapper;

  @Autowired
  AsyncShopService5 asyncShop5;

  @GetMapping("async")
  public SseEmitter asyncShop() {
    System.out.println("async:::::::::::");
    SseEmitter emitter = new SseEmitter();
    asyncShop5.asyncShowFruitsList(emitter);
    return emitter;

  }

  @GetMapping("step1")
  public String sample51() {
    return "sample51.html";
  }

  @GetMapping("step2")
  public String sample52(ModelMap model) {
    ArrayList<Fruit> fruits2 = fMapper.selectAllFruit();
    model.addAttribute("fruits2", fruits2);
    return "sample51.html";
  }

  @GetMapping("step3")
  @Transactional
  public String sample53(@RequestParam Integer id, ModelMap model) {
    // 削除対象のフルーツを取得
    Fruit fruit3 = fMapper.selectById(id);
    model.addAttribute("fruit3", fruit3);

    // 削除
    fMapper.deleteById(id);

    // 削除後のフルーツリストを取得
    ArrayList<Fruit> fruits2 = fMapper.selectAllFruit();
    model.addAttribute("fruits2", fruits2);
    return "sample51.html";
  }

  @GetMapping("step4")
  @Transactional
  public String sample54(@RequestParam Integer id, ModelMap model) {
    // 編集対象のフルーツを取得
    Fruit fruit4 = fMapper.selectById(id);
    model.addAttribute("fruit4", fruit4);

    // フルーツリストを取得
    ArrayList<Fruit> fruits2 = fMapper.selectAllFruit();
    model.addAttribute("fruits2", fruits2);
    return "sample51.html";
  }

  /**
   * IDをクエリParamで，果物の名前と値段をフォームで受け取り，DBを更新する
   *
   * @param name
   * @param price
   * @param model
   * @return
   */
  @PostMapping("step5")
  public String sample55(@RequestParam Integer id, @RequestParam String name, @RequestParam Integer price,
      ModelMap model) {
    System.out.println("step5");
    System.out.println(id);
    System.out.println(name);
    System.out.println(price);
    Fruit fruit = new Fruit(id, name, price);
    fMapper.updateById(fruit);
    // フルーツリストを取得
    ArrayList<Fruit> fruits2 = fMapper.selectAllFruit();
    model.addAttribute("fruits2", fruits2);

    return "sample51.html";
  }

  @GetMapping("step6")
  public String sample56(ModelMap model) {
    ArrayList<Fruit> fruits6 = fMapper.selectAllFruit();
    model.addAttribute("fruits6", fruits6);
    return "sample56.html";
  }

  @GetMapping("step7")
  @Transactional
  public String sample57(@RequestParam Integer id, ModelMap model) {
    // 削除対象のフルーツを取得
    Fruit fruit7 = fMapper.selectById(id);
    model.addAttribute("fruit7", fruit7);

    // 削除
    fMapper.deleteById(id);

    // 削除後のフルーツリストを取得
    ArrayList<Fruit> fruits6 = fMapper.selectAllFruit();
    model.addAttribute("fruits6", fruits6);
    return "sample56.html";
  }
}
