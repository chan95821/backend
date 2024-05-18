package springPractice.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
@RestController
public class firstController {
    
  @GetMapping("/first")
  public Map<String, Object> firstController() {
    return null;
    }
}
