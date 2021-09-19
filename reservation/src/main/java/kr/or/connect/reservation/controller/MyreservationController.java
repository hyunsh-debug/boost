package kr.or.connect.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/myreservaion")
public class MyreservationController {
	@GetMapping
	public String showMyreservationPage() {
		return "myreservation";
	}
}
