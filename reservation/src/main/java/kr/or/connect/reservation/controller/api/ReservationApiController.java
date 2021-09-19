package kr.or.connect.reservation.controller.api;


import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import kr.or.connect.reservation.dto.RequestReservationInfoDto;
import kr.or.connect.reservation.dto.ReservationInquiryDto;
import kr.or.connect.reservation.dto.ReservationUserCommentDto;
import kr.or.connect.reservation.service.CommentService;
import kr.or.connect.reservation.service.ReservationService;


@RestController
@RequestMapping(path="/api/reservations")
public class ReservationApiController {
	private ReservationService reservationService;
	private CommentService commentService;
	public ReservationApiController(ReservationService reservationService, CommentService commentService) {
		this.reservationService = reservationService;
		this.commentService = commentService;
	}

	@PostMapping
	public RequestReservationInfoDto reservationInfoAdd(@RequestBody RequestReservationInfoDto requestReservationInfoDto, HttpSession session) {
		if(session.getAttribute("resrvEmail") != null) {
			session.removeAttribute("resrvEmail");
		}
		session.setAttribute("resrvEmail", requestReservationInfoDto.getReservationEmail());
		return reservationService.setReservationParam(requestReservationInfoDto);
	}

	@GetMapping
	public ReservationInquiryDto reservataionList(@RequestParam String reservationEmail){
		return  reservationService.getReservationInfoAll(reservationEmail);
	}

	@PatchMapping("/{reservationInfoId}")
	public int cancelReservation(@PathVariable int reservationInfoId){
		return reservationService.updateReservationInfo(reservationInfoId);
	}

	@PostMapping ("/{reservationInfoId}/comments")
	public void addreservationUserComment(@RequestParam(name = "commentImage", required = false) MultipartFile file,	@RequestParam("score") int score, 
			@RequestParam("reservationInfoId") int reservationInfoId,@RequestParam("textArea") String comment, @RequestParam("productId") int productId) {
		//ReservationUserCommentDto userCommentDto = new ReservationUserCommentDto(Integer.parseInt(productId), Integer.parseInt(reservationInfoId), Integer.parseInt(score), comment);
		ReservationUserCommentDto userCommentDto = new ReservationUserCommentDto(productId, reservationInfoId, score,comment);
		System.out.println(userCommentDto);
		commentService.setUserCommentParam(file, userCommentDto);
	}
}
