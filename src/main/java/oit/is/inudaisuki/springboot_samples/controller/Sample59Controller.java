package oit.is.inudaisuki.springboot_samples.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.inudaisuki.springboot_samples.model.Fruit;
import oit.is.inudaisuki.springboot_samples.model.FruitMapper;
import oit.is.inudaisuki.springboot_samples.service.AsyncShopService59;

/**
 * /sample5へのリクエストを扱うクラス authenticateの設定をしていれば， /sample5へのアクセスはすべて認証が必要になる
 * 他のクラスと同じRequestMappingも書ける．ただし，特定のメソッドへのGETリクエストのURLは一意じゃないとだめ．
 */
@Controller
@RequestMapping("/sample5")
public class Sample59Controller {

  @Autowired
  FruitMapper fMapper;

  @Autowired
  AsyncShopService59 shop59;

  /**
   * これまでと同様，フルーツのリストをDBから取得してthymeleafで返す処理
   *
   * @param model
   * @return
   */
  @GetMapping("step9")
  public String sample59(ModelMap model) {
    final ArrayList<Fruit> fruits9 = shop59.syncShowFruitsList();
    model.addAttribute("fruits9", fruits9);
    return "sample59.html";
  }

  /**
   * JavaScriptからEventSourceとして呼び出されるGETリクエスト SseEmitterを返すことで，PUSH型の通信を実現する
   *
   * @return
   */
  @GetMapping("step10")
  public SseEmitter sample510() {
    final SseEmitter sseEmitter = new SseEmitter();

    sseEmitter.onTimeout(() -> {
      sseEmitter.complete();
    });
    this.shop59.asyncShowFruitsList(sseEmitter);
    return sseEmitter;
  }

  /**
   * 購入処理． 購入リンクをクリックするとDBから削除し，表示を更新する 同時にブラウザで同じページを表示しているユーザのページの表示もPUSHで更新する
   *
   * @param id
   * @param model
   * @return
   */
  @GetMapping("step11")
  public String sample511(@RequestParam Integer id, ModelMap model) {
    final Fruit fruit11 = this.shop59.syncBuyFruits(id);
    model.addAttribute("fruit11", fruit11);

    final ArrayList<Fruit> fruits9 = shop59.syncShowFruitsList();
    model.addAttribute("fruits9", fruits9);

    return "sample59.html";
  }
}
