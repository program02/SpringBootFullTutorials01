package com.exam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {
	@Autowired
	StudentRepo repo;
//	@RequestMapping("/")
//	public String home() {
//		return "home.jsp";
//	}

	/*
	 * @RequestMapping("/home") public String home(HttpServletRequest req) {
	 * HttpSession session = req.getSession(); String name=
	 * req.getParameter("name"); session.setAttribute("name", name); return
	 * "home.jsp"; }
	 */

//	@RequestMapping("/home")
//	public String home(@RequestParam("name") String name1, HttpSession session) {
//		session.setAttribute("name", name1);
//		return "home.jsp";
//	}

	/*
	 * @RequestMapping("/home") public String home(Student s,HttpSession session) {
	 * session.setAttribute("name", s.id); return "home.jsp"; }
	 */

//	@RequestMapping("/home")
//	public ModelAndView home(ModelAndView mv,@RequestParam("name") String name1) {
//		mv.addObject("name",name1);
//		mv.setViewName("home.jsp");
//		return mv;
//	}

	@RequestMapping("/home")
	public ModelAndView home(ModelAndView mv, Student s) {
		mv.addObject("name", s.name);
		mv.setViewName("home.jsp");
		return mv;
	}

	@RequestMapping("/form1")
	public ModelAndView form1(ModelAndView mv, Student s) {
		mv.addObject("name", s.name);
		mv.setViewName("home.jsp");
		return mv;
	}

	@RequestMapping("/add")
	public ModelAndView addStudent(ModelAndView mv, Student s) {
		mv.addObject("name", s.name);
		repo.save(s);
		mv.setViewName("home.jsp");
		return mv;
	}

	@RequestMapping("/studentbyid")
	public ModelAndView studentbyid(ModelAndView mv, Student s) {
		Student st = repo.findById(s.id).orElse(new Student());
		System.out.println(st);
		mv.addObject("st", st);
		mv.setViewName("showStudent.jsp");
		return mv;
	}

//	@ResponseBody
	@RequestMapping("/student/{id}")
	public @ResponseBody Student studentbyidjson(ModelAndView mv, @PathVariable("id") long id) {
		Student st = repo.findById(id).orElse(new Student());
		return st;
	}

	@RequestMapping("/studentbyidname")
	public ModelAndView studentbyidname(ModelAndView mv, Student s) {
		Student st = repo.findById(s.id).orElse(new Student());
		List<Student> nameList = repo.findByName(s.name);
		System.out.println(nameList.get(0).id + "  " + nameList.get(0).name);
		List<Student> Listidgreaterthan = repo.findByIdGreaterThan(s.id);
		System.out.println(Listidgreaterthan);
		List<Student> sorted = repo.findByNameSorted(s.name);
		System.out.println(sorted);
		List<Student> sorted2 = repo.findByNameSorted(s.name, s.id);
		System.out.println(sorted2);
		mv.addObject("st", st);
		mv.setViewName("showStudent.jsp");
		return mv;
	}

//	@ResponseBody
	@RequestMapping("/students")
	public @ResponseBody List<Student> studentsjson(ModelAndView mv) {
		List<Student> studentList = repo.findAll();
		return studentList;
	}
	
//	@ResponseBody
	@RequestMapping("/studentbyname/{name}")
	public @ResponseBody List<Student> studentbynamejson(ModelAndView mv,@PathVariable("name") String name) {
		List<Student> studentList = repo.findByName(name);
		return studentList;
	}

}
