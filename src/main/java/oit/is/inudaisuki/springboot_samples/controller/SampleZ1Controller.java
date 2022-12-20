package oit.is.inudaisuki.springboot_samples.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Sample21Controller
 *
 * クラスの前に@Controllerをつけていると，HTTPリクエスト（GET/POSTなど）があったときに，このクラスが呼び出される
 */
@Controller
public class SampleZ1Controller {

  /**
   * sample21というGETリクエストがあったら sample21()を呼び出し，sample21.htmlを返す
   */
  @GetMapping("/sampleZ1")
  public String sampleZ1(ModelMap model) {
    int image = (int) (Math.random() * 3 + 1);
    String imageUrl = image + "";
    String imageUrl2 = ".png";
    model.addAttribute("imageUrl", imageUrl);
    model.addAttribute("imageUrl2", imageUrl2);
    int image3 = (int) (Math.random() * 3 + 1);
    model.addAttribute("imageUrl3", image3 + ".png");

    return "sampleZ1.html";
  }

}
