package kr.or.connect.reservation.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dao.CommentDao;
import kr.or.connect.reservation.dao.CommentImageDao;
import kr.or.connect.reservation.dao.FileInfoDao;
import kr.or.connect.reservation.dto.FileInfoDto;
import kr.or.connect.reservation.dto.ReservationUserCommentDto;
import kr.or.connect.reservation.dto.ReservationUserCommentImageDto;

@Service
public class CommentService {
	private CommentDao userCommentDao;
	private CommentImageDao userCommentImageDao;
	private	FileInfoDao fileInfoDao;
	
	public CommentService(CommentDao userCommentDao, CommentImageDao userCommentImageDao, FileInfoDao fileInfoDao) {
		this.userCommentDao = userCommentDao;
		this.userCommentImageDao = userCommentImageDao;
		this.fileInfoDao = fileInfoDao;
	}
	
	@Transactional
	public void setUserCommentParam(MultipartFile file, ReservationUserCommentDto userCommentDto) {
		int userCommentId = registerUserComment(userCommentDto);

		if(file != null) {
			int fileInfoId = registerFileInfo(file);
			ReservationUserCommentImageDto userCommentImageDto = new ReservationUserCommentImageDto(userCommentDto.getReservationInfoId(), userCommentId, fileInfoId);
			userCommentImageDao.insertUserCommentImage(userCommentImageDto);
		}
		return;
	}

	private int registerUserComment(ReservationUserCommentDto userCommentDto) {
		return userCommentDao.insertUserComment(userCommentDto);
	}
	
	private int registerFileInfo(MultipartFile file) {
		FileInfoDto fileInfo = new FileInfoDto();
		fileInfo.inputFilInfoField(file);
		uploadFile(file);
		
		return fileInfoDao.insertFileInfo(fileInfo);	
	}
	
	private void uploadFile(MultipartFile file) {
		String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));

		try(
                FileOutputStream fos = new FileOutputStream("c:/tmp/" +  formatDate + "_" + file.getOriginalFilename());
                InputStream is = file.getInputStream();
        ){
        	    int readCount = 0;
        	    byte[] buffer = new byte[1024];
            while((readCount = is.read(buffer)) != -1){
                fos.write(buffer,0,readCount);
            }
        }catch(Exception ex){
            throw new RuntimeException("file Save Error");
        }
	}

	public void downloadUserCommentImage(int reservationUserCommentImageId, HttpServletResponse response) {
		FileInfoDto file = fileInfoDao.selectFileInfoByImageId(reservationUserCommentImageId);

		String saveFileName = "c:/tmp/" + file.getFileName(); 
		int fileLength = 116303;
		
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getFileName() + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", file.getContentType());
        response.setHeader("Content-Length", "" + fileLength);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        
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
