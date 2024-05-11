
package springPractice.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
@GetMapping("hello-mvc")

    public String helloMvc(@RequestParam(required=false) String name, Model model) {
    model.addAttribute("name", name);
    return "hello-template";
    }

    @ResponseBody
     @GetMapping("/hello-string")
    public String helloString(@RequestParam String name) {
        return "hello " + name; // 문자가 view 없이 그대로 전송 -plain text
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam String name)
    {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //json 형식으로 client 전달 {변수이름} : {값} 형태
    }

    public class Hello {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}