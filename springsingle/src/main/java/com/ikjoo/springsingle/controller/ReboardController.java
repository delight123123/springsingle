package com.ikjoo.springsingle.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ikjoo.springsingle.board.model.ReboardService;
import com.ikjoo.springsingle.board.model.ReboardVO;
import com.ikjoo.springsingle.board.model.UpfileListVO;
import com.ikjoo.springsingle.common.FileUploadUtil;

@Controller
public class ReboardController {

	private final static Logger logger=LoggerFactory.getLogger(ReboardController.class);
	
	@Autowired
	private ReboardService reboardService;
	
	@Autowired
	private FileUploadUtil fileuploadUtil;
	
	@RequestMapping(value = "/write")
	public Object write() {
		
		logger.info("글쓰기 화면 보이기");
		
		
		return "reboard/write";

	}
	
	//@ResponseBody
	@RequestMapping("/boardWrite")
	public Object boardWrite(@ModelAttribute ReboardVO vo,HttpSession session,MultipartHttpServletRequest request,
			Model model) {
		int res=0;
		String userid=(String) session.getAttribute("userid");
		vo.setUserid(userid);
		res=reboardService.reboardWrite(vo);
		//session.setAttribute("insertno", res);
		if(res!=0) {
			logger.info("게시글 등록 성공");
			
			logger.info("파일업로드 시작");
			List<UpfileListVO> list=fileuploadUtil.fileupload(request,session);
			int result=vo.getReboardNo();
			
			if(list.size()>0) {
				for(int i=0;i<list.size();i++) {
					list.get(i).setReboardNo(result);
				}
				logger.info("파일 리스트 insert 시작");
				res=reboardService.upfilelistInsert(list);
			}
			
			String msg="", url="";
			msg="게시글 등록 완료";
			url="/main";
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
		}
		
		
		return "common/message";
	}
	
	/*@ResponseBody
	@RequestMapping("/fileuplod")
	public int fileupload(MultipartHttpServletRequest request,HttpSession session) {
		
		logger.info("파일업로드 시작");
		int result=(Integer) session.getAttribute("insertno");
		logger.info("result={}",result);
		
		//List<UpfileListVO> list=fileuploadUtil.fileupload(request, session);
		List<UpfileListVO> list=fileuploadUtil.fileupload(request,session);
		int res=0;
		if(list.size()>0) {
			for(int i=0;i<list.size();i++) {
				list.get(i).setReboardNo(result);
			}
			logger.info("파일 리스트 insert 시작");
			res=reboardService.upfilelistInsert(list);
		}
		logger.info("파일 리스트 insert 결과 res={}",res);
		
		return res;
		
	}*/
	
	@RequestMapping("/readCnt")
	public Object readCnt(@RequestParam("reNo") int reNo) {
		
		logger.info("detail전 조회수 증가 파라미터 reboardNo={}",reNo);

		int res=reboardService.readcountUp(reNo);

		logger.info("조회수 증가 결과 res={}",res);
		
		return "redirect:/detail?reboardNo="+reNo;
	}
	
	@RequestMapping("/detail")
	public Object detail(@RequestParam("reboardNo") int reboardNo, Model model ) {
		logger.info("게시글 상세보기 파라미터 reboardNo={}",reboardNo);
		
		ReboardVO vo=reboardService.reboardSelByNo(reboardNo);
		
		//해당 게시글에 대한 업로드한 파일
		int res=reboardService.reboardFileCnt(reboardNo);
		logger.info("해당 게시글 업로드 파일 수 res={}",res);
		if(res>0) {
			
			List<UpfileListVO> list=reboardService.fileByReboardNo(reboardNo);
			logger.info("게시글 업로드파일들 list={}",list);
			model.addAttribute("list", list);
		}
		
		logger.info("상세 보기 검색 결과 vo={}",vo);

		model.addAttribute("vo", vo);
		
		return "reboard/detail";
	}
	
	@RequestMapping("/edit")
	public Object edit(@RequestParam("no") int reboardNo, Model model) {
		logger.info("게시글 수정 파라미터 reboardNo={}",reboardNo);
		
		ReboardVO vo= reboardService.reboardSelByNo(reboardNo);
		List<UpfileListVO> list=reboardService.fileByReboardNo(reboardNo);
		
		model.addAttribute("vo", vo);
		model.addAttribute("list", list);
		
		return "reboard/edit";
	}
	
	@ResponseBody
	@RequestMapping("/boardUpdate")
	public int reboardEdit(@ModelAttribute ReboardVO reboardVo) {
		int res=0;
		
		logger.info("글 수정 파라미터 reboardVo={}",reboardVo);
		
		res=reboardEdit(reboardVo);
		logger.info("글 수정 결과 res={}",res);
		if(res>0) {
			res=reboardVo.getReboardNo();
		}
		
		return res;
	}
	
	@ResponseBody
	@RequestMapping("/fileuplodupdate")
	public int fileupdate(MultipartHttpServletRequest request,HttpSession session) {
		
		logger.info("파일업데이트 시작");
		int result=(Integer) session.getAttribute("insertno");
		logger.info("result={}",result);

		//새로운 파일 업로드
		List<UpfileListVO> list=fileuploadUtil.fileupload(request,session);
		int res=0;
		if(list.size()>0) {
			for(int i=0;i<list.size();i++) {
				list.get(i).setReboardNo(result);
			}
			logger.info("파일 리스트 insert 시작");
			res=reboardService.upfilelistInsert(list);
			List<UpfileListVO> delList=reboardService.fileByReboardNo(result);
			boolean delRes=fileuploadUtil.fileDel(delList, session);
			logger.info("기존 파일 삭제 결과 delRes={}",delRes);
		}
		logger.info("파일 리스트 insert 결과 res={}",res);
		
		return res;
		
	}
	
	@RequestMapping("/delete")
	public Object reboardDel(@RequestParam("no") int reboardNo,HttpSession session
			,HttpServletRequest request,Model model) {
		logger.info("게시물 삭제 파라미터 no={}",reboardNo);
		
		ReboardVO vo=reboardService.reboardSelByNo(reboardNo);
		List<UpfileListVO> list=reboardService.fileByReboardNo(reboardNo);
		
		Map<String, String> map=new HashMap<String, String>();
		map.put("no", Integer.toString(reboardNo));
		map.put("groupno", Integer.toString(vo.getGroupno()));
		map.put("step", Integer.toString(vo.getStep()));
		int res=0;
		
		res=reboardService.reboardDel(map);
		
		logger.info("게시글 삭제 결과 res={}",res);
		
		String url, msg="";
		if(res==-1) {
			url="/main";
			msg="해당 게시글이 삭제되었습니다.";
		}else {
			url="/detail?reboardNo="+reboardNo;
			msg="게시글 삭제에 실패하였습니다.";
		}
		
		boolean delRes=false;
		
		//게시글 파일 삭제
		delRes=fileuploadUtil.fileDel(list, session);
		
		logger.info("게시글 파일 삭제 결과 delRes={}",delRes);
		
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);
		
		return "common/message";
	}
	
	@RequestMapping("/download")
	public ModelAndView fileDownload(@RequestParam int no, @RequestParam String filename
			,HttpServletRequest request,HttpSession session) {
		
		logger.info("다운로드 처리 파라미터 no={},filename={}",no,filename);
		int res=0;
		//다운 수 올리기
		res=reboardService.downCntUp(no);
		
		logger.info("다운 수 증가 결과 res={}",res);
		
		UpfileListVO vo=reboardService.fileOneSel(no);
		
		logger.info("다운로드할 파일 정보 vo={}",vo);
		
		String path=fileuploadUtil.getFilePath(session);
		
		File file=new File(path, filename);
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("file", file);
		
		ModelAndView mav=new ModelAndView("reBoardDownloadView", map);
		
		return mav;
		
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public Object replayGet(@RequestParam int no,Model model) {
		logger.info("답글 달기 화면 보이기 파라미터 no={}",no);
		
		ReboardVO vo=reboardService.reboardSelByNo(no);
		
		logger.info("답글 달리는 게시물 vo={}",vo);
		
		model.addAttribute("vo", vo);
		
		return "reboard/reply";
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public Object replayPost(@ModelAttribute ReboardVO vo,Model model,HttpSession session) {
		logger.info("답글 달기 파라미터 vo={}",vo);
		String userid=(String) session.getAttribute("userid");
		vo.setUserid(userid);
		logger.info("id 설정후 vo={}",vo);
		int res=reboardService.reply(vo);
		
		logger.info("답글 달기 결과 res={}",res);
		
		if(res>0) {
			return "redirect:/main";
		}else {
			model.addAttribute("url", "/edit?no="+vo.getReboardNo());
			model.addAttribute("msg", "답글 달기 실패");
			return "common/message";
		}//if
		
	}
}
