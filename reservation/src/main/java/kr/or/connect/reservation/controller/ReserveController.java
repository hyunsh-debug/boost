package kr.or.connect.reservation.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/reserve")
public class ReserveController {
	@GetMapping
	public String getReservationRandomDate(Model model) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		int random = (int)(Math.random()*5) + 1;
		LocalDateTime localDateTime = LocalDateTime.now().plusDays(random);	
		String reserveDate = localDateTime.format(formatter);
		
		model.addAttribute("reserveDate", reserveDate);
		
		return "reserve";
	}
}
