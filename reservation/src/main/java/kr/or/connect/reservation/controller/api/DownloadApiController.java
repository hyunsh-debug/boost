package kr.or.connect.reservation.controller.api;

import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.service.CommentService;

@RestController
public class DownloadApiController {
	private static final String IMG_SRC = "/tmp/";
	private CommentService commentService;
	
	public DownloadApiController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping("/download/{reservationUserCommentImageId}")
	public void downloadImg(@PathVariable int reservationUserCommentImageId, HttpServletResponse response) {
		commentService.downloadUserCommentImage(reservationUserCommentImageId, response);
	}
	
	@GetMapping("/img/{fileName:.+}")
	public void showImg(@PathVariable String fileName, HttpServletResponse response){
		String saveFileName = IMG_SRC + fileName;
		String contentType = "image/png";
		
		response.setContentType(contentType);
		
		try(
				FileInputStream fis = new FileInputStream(saveFileName);
				OutputStream out = response.getOutputStream();
				){
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while((readCount = fis.read(buffer)) != -1){
				out.write(buffer,0,readCount);
			}
		}catch(Exception ex){
			throw new RuntimeException("file Save Error");
		}
	}
	
	@GetMapping("/img_map/{fileName:.+}")
	public void showImgMap(@PathVariable String fileName, HttpServletResponse response){
		String saveFileName = IMG_SRC + fileName;
		String contentType = "image/png";
		
		response.setContentType(contentType);
		
		try(
				FileInputStream fis = new FileInputStream(saveFileName);
				OutputStream out = response.getOutputStream();
				){
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while((readCount = fis.read(buffer)) != -1){
				out.write(buffer,0,readCount);
			}
		}catch(Exception ex){
			throw new RuntimeException("file Save Error");
		}
	}	
}
