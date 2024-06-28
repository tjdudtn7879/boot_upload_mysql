package com.boot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.BoardAttachDTO;

//실행시 매퍼파일을 읽어 들이도록 지정
@Mapper
public interface BoardAttachDAO {//xml이랑 이름 맞춰줘야함 ex>insertFile
//	파일업로드는 파라미터를 DTO 사용
	public void insertFile(BoardAttachDTO vo);
	public List<BoardAttachDTO> getFileList(int boardNo);
	public void deleteFile(String boardNo);
}















