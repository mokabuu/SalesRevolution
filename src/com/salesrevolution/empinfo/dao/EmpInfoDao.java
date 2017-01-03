package com.salesrevolution.empinfo.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import org.apache.commons.dbutils.DbUtils;

import com.salesrevolution.util.DBConnection;

@RequestScoped
@Named
@ManagedBean(name = "EmpInfoDao")
public class EmpInfoDao {
	public int id;
	public String emp_id;
	public String lastname;
	public String firstname;
	public String middlename;
	public int sex;
	public String uni_code;
	public String email;
	public String line;
	public String twitter;
	public String facebook;
	public String emp_com;
	public String emp_sec;
	public String emp_class;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getUni_code() {
		return uni_code;
	}

	public void setUni_code(String uni_code) {
		this.uni_code = uni_code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getEmp_com() {
		return emp_com;
	}

	public void setEmp_com(String emp_com) {
		this.emp_com = emp_com;
	}

	public String getEmp_sec() {
		return emp_sec;
	}

	public void setEmp_sec(String emp_sec) {
		this.emp_sec = emp_sec;
	}

	public String getEmp_class() {
		return emp_class;
	}

	public void setEmp_class(String emp_class) {
		this.emp_class = emp_class;
	}

	public void insertEmpInfo(){
		Connection con = null;
		java.sql.PreparedStatement ps = null;
		java.sql.ResultSet rs = null;
		try{
			con = DBConnection.mysqlConnect();
			ps = con.prepareStatement("insert into emp_info (id, emp_id, lastname, firstname, middlename, sex, uni_code, email, line, twitter, facebook, emp_com, emp_sec, emp_class) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			int i = 1;
			
			ps.setInt(i++, id);
			ps.setString(i++, emp_id);
			ps.setString(i++, lastname);
			ps.setString(i++, firstname);
			ps.setInt(i++, sex);
			ps.setString(i++, uni_code);
			ps.setString(i++, email);
			ps.setString(i++, line);
			ps.setString(i++, twitter);
			ps.setString(i++, facebook);
			ps.setString(i++, emp_com);
			ps.setString(i++, emp_sec);
			ps.setString(i++, emp_class);
			ps.executeUpdate();
		}catch(SQLException e){
			System.out.println(e);
			e.printStackTrace();
		}finally{
			DbUtils.closeQuietly(con, ps, rs);
		}
	}

}
