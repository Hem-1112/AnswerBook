package com.controller.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.student.StudentBean;
import com.dao.student.StudentDao;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class studentController 
{
	@Autowired
	StudentDao studentDao;
	
	@PostMapping(value="/addstudent")
	public ResponseEntity addStudent(StudentBean studentBean)
	{
		int status = studentDao.addStudent(studentBean);
		if(status > 0)
		{
			return new ResponseEntity(studentBean,HttpStatus.CREATED);
		}
		return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
	}

	@GetMapping(value="/viewstudent")
	public List<StudentBean> viewStudent()
	{
		return studentDao.studentList();
	}
	
	@DeleteMapping("/deletestudent/{id}")
	public String deleteStudent(@PathVariable String id)
	{
		int status = studentDao.deleteStudent(id);
		if(status>0) {
			return "Student Deleted !!";
		}
		return "Student not Deleted";
	}
	
	@PostMapping(value="/updatestudent/{id}")
	public StudentBean updateStudent(StudentBean studentBean,@PathVariable String id)
	{
		int status = studentDao.updateStudent(studentBean, id);
		if(status > 0){
			return studentBean;
		}
		return null;
	}
}
