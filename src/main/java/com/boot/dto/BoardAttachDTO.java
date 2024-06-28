package com.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardAttachDTO {//테이블 새로 생성, 프로퍼티 변경(대소문자) // 관련된 로직 모두 변경(ex>setter 변경)
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean image;
	private int boardNo;
}
