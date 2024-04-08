package tqs.homework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {

   @GetMapping("/home")
   public String home() {
      return "home";
   }

   @PostMapping("/reservation")
   public String reservation(@RequestParam(value = "code", required = true) String code) {
      return "reservation";
   }

   @PostMapping("/cancel")
   public String cancel(@RequestParam(value = "altCode", required = true) String altCode) {
      return "cancel";
   }

   @GetMapping("/trip")
   public String trip(@RequestParam(value = "code", required = true) String code) {
      return "trip";
   }
}
