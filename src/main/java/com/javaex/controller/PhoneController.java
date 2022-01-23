package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {

	@Autowired
	private PhoneDao pDao;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("PhoneController>list()");

		// Dao에서 리스트를 가져온다
		List<PersonVo> pList = pDao.getPersonList();
		System.out.println(pList);

		// 컨트롤러 ----> DS 데이터를 보낸다 (model)
		model.addAttribute("pList", pList);

		return "list";
	}

	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("PhoneController>writeForm()");

		return "writeForm";
	}

	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo pvo) {
		System.out.println("PhoneController>write()");

		// 저장
		pDao.PersonInsert(pvo);

		// 리다이렉트
		return "redirect:/phone/list";
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int id) {
		System.out.println("PhoneController>delete()");

		pDao.PersonDelete(id);

		return "redirect:/phone/list";
	}

	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(Model model, @RequestParam("id") int id) {
		System.out.println("PhoneController>updateForm()");

		PersonVo pvo = pDao.getPerson(id);

		model.addAttribute("pvo", pvo);

		return "updateForm";
	}

	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@ModelAttribute PersonVo pvo, @RequestParam("id") int id) {
		System.out.println("PhoneController>update()");

		pDao.PersonUpdate(id, pvo);

		return "redirect:/phone/list";
	}

}
