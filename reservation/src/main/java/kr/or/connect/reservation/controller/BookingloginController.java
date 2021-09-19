package kr.or.connect.reservation.controller;



import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.connect.reservation.service.ReservationService;

@Controller
@RequestMapping(path="/bookinglogin")
public class BookingloginController {
	private ReservationService reservationService;
	public BookingloginController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	@GetMapping
	public String showBookingLoginPage() {
		return "bookinglogin";
	}
	
	@PostMapping
	public String checkEmail(@RequestParam(value = "resrv_email", required = false) String resrvEmail, HttpSession session, 
			RedirectAttributes redirectAttr) {
		
		boolean emailChk = reservationService.isEmailChk(resrvEmail);

		if(!emailChk) {		
			redirectAttr.addFlashAttribute("errorMessage", "이메일이 틀렸습니다.");
			return "redirect:/bookinglogin";
		}
		else {
			session.setAttribute("resrvEmail", resrvEmail);
			return "myreservation";
		}
	}
}
