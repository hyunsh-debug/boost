package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

public class FileInfoDto {
	private String fileName;
	private String saveFileName;
	private String contentType;
	private boolean deleteFlag;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	
	public FileInfoDto() {
		this.deleteFlag = false;
		this.createDate = LocalDateTime.now();
		this.modifyDate = LocalDateTime.now();
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void inputFilInfoField(MultipartFile file) {
		this.fileName = file.getOriginalFilename();
		this.saveFileName = "img/" + file.getOriginalFilename(); //업로드시 image파일만 업로드 가능하기 때문에 임의로 설정
		this.contentType = file.getContentType();
	}

	@Override
	public String toString() {
		return "FileInfoDto [fileName=" + fileName + ", saveFileName=" + saveFileName + ", contentType=" + contentType
				+ ", deleteFlag=" + deleteFlag + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
	
}
