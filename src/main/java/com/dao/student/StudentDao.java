package com.dao.student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import com.bean.student.StudentBean;

@Repository
public class StudentDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int addStudent(StudentBean studentBean)
	{
		return jdbcTemplate.update("insert into student(sid,fname,lname,scontact,smail,uname,upassword,squalification) "
				+ "values(?,?,?,?,?,?,?,?)",studentBean.getsId(),studentBean.getfName(),studentBean.getlName(),
				studentBean.getsContact(),studentBean.getsMail(),studentBean.getuName(),studentBean.getuPassword(),
				studentBean.getsQualification());
	}
	
	private final static class StudentMapper implements RowMapper<StudentBean>
	{
		@Override
		public StudentBean mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			StudentBean studentBean = new StudentBean();
			studentBean.setsId(rs.getString("sid"));
			studentBean.setfName(rs.getString("fname"));
			studentBean.setlName(rs.getString("lname"));
			studentBean.setsContact(rs.getLong("scontact"));
			studentBean.setsMail(rs.getString("smail"));
			studentBean.setuName(rs.getString("uname"));
			studentBean.setuPassword(rs.getString("upassword"));
			studentBean.setsQualification(rs.getString("squalification"));
			return studentBean;
		}
	}
	
	public List<StudentBean> studentList()
	{
		return jdbcTemplate.query("select * from student",new StudentMapper());
	}
	
	public int deleteStudent(String id)
	{
		return jdbcTemplate.update("delete from student where sid=?",id);
	}
	
	public int updateStudent(StudentBean studentBean,String id)
	{
		return jdbcTemplate.update("update student set fname=?, lname=?, scontact=?, smail=?, uname=?, upassword=?, squalification=? "
				+ "where sid=?",studentBean.getfName(),studentBean.getlName(),studentBean.getsContact(),studentBean.getsMail(),
				studentBean.getuName(),studentBean.getuPassword(),studentBean.getsQualification(),id);
	}
}
