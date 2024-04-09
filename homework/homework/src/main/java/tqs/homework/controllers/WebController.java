package tqs.homework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tqs.homework.services.ExchangeRateService;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class WebController {

   private final ExchangeRateService exchangeRateService;

   public WebController(ExchangeRateService exchangeRateService) {
      this.exchangeRateService = exchangeRateService;
   }

   @GetMapping("/home")
   public String home(Model model) throws Exception {
      Map<String, Double> exchangeRates = exchangeRateService.getExchangeRates();
      model.addAttribute("exchangeRates", exchangeRates);
      return "home";
   }

   @PostMapping("/reservation")
   public String reservation(@RequestParam(value = "resCode", required = true) String resCode) {
      return "reservation";
   }

   @PostMapping("/cancel")
   public String cancel(@RequestParam(value = "altCode", required = true) String altCode) {
      return "cancel";
   }

   @GetMapping("/trip")
   public String trip(@RequestParam(value = "tripCode", required = true) String tripCode) {
      return "trip";
   }

   @GetMapping("/payment")
   public String payment() {
      return "payment";
   }

   @PostMapping("/buy")
   public String buy() {
      return "buy";
   }
}
