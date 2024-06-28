package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.BoardAttachDTO;
import com.boot.dto.BoardDTO;
import com.boot.dto.CommentDTO;
import com.boot.service.BoardService;
import com.boot.service.CommentService;
import com.boot.service.UploadService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
	@Autowired
	private BoardService service;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UploadService uploadService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		log.info("@# list");
		
		ArrayList<BoardDTO> list = service.list();
		model.addAttribute("list", list);
		
		return "list";
	}
	
	@RequestMapping("/write")
//	public String write(@RequestParam HashMap<String, String> param) {
	public String write(BoardDTO boardDTO) {
		log.info("@# write");
		log.info("@# boardDTO=>"+boardDTO);
		
		if (boardDTO.getAttachList() != null) {//보드서비스임플 가기전에 어테치 로그 확인용
			boardDTO.getAttachList().forEach(attach -> log.info("@# attach"+attach));
		}
		
//		service.write(param);
		service.write(boardDTO);
		
		return "redirect:list";
	}
	
	@RequestMapping("/write_view")
	public String write_view() {
		log.info("@# write_view");
		
		return "write_view";
	}
	
	@RequestMapping("/content_view")
	public String content_view(@RequestParam HashMap<String, String> param, Model model) {
		log.info("@# content_view");
		
		BoardDTO dto = service.contentView(param);
		model.addAttribute("content_view", dto);
		
		// 해당 게시글에 작성된 댓글 리스트를 가져옴
		ArrayList<CommentDTO> commentList = commentService.findAll(param);
		model.addAttribute("commentList", commentList);
		
		return "content_view";
	}
	
	@RequestMapping("/modify")
	public String modify(@RequestParam HashMap<String, String> param) {
		log.info("@# modify");
		
		service.modify(param);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam HashMap<String, String> param) {
		log.info("@# delete");
		log.info("@# param=>"+param);
		log.info("@# boardNo=>"+param.get("boardNo"));
		
		List<BoardAttachDTO> fileList = uploadService.getFileList(Integer.parseInt(param.get("boardNo")));
		log.info("@# fileList=>"+fileList);
		
		service.delete(param);
		uploadService.deleteFiles(fileList);
		
		return "redirect:list";
	}
	
}
















