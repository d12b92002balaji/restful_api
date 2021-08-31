package com.project.myApp;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class alienRepo {

	Connection con=null;
	
	public alienRepo()
	{
		String url="jdbc:mysql://localhost:3306/restdb";
		String username="root";
		String password ="#dheepan@200212";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection(url,username,password);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	
	public List<alien> getAliens()
	{
		List<alien> aliens =new ArrayList<>();
		String sql ="select * from alien";
		try {
		Statement st =con.createStatement();
		ResultSet rs =st.executeQuery(sql);
		while(rs.next())
		{
			alien a=new alien();
			a.setId(rs.getInt(1));
			a.setName(rs.getString(2));
			a.setPoints(rs.getInt(3));
			aliens.add(a);
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return aliens;
	}
	public alien getAlien(int id)
	{
		String sql ="select * from alien where id="+id;
		alien a=new alien();
		try {
		Statement st =con.createStatement();
		ResultSet rs =st.executeQuery(sql);
		if(rs.next())
		{
			
			a.setId(rs.getInt(1));
			a.setName(rs.getString(2));
			a.setPoints(rs.getInt(3));
			
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return a;
	}


	public void create(alien a1) {
		String sql="insert into alien values (?,?,?)";
		try {
			PreparedStatement st =con.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setString(2,a1.getName());
			st.setInt(3,a1.getPoints());
			st.executeUpdate();
			
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		
	}
	public void update(alien a1) {
		String sql="update alien set name=?,points=? where id=?";
		try {
			PreparedStatement st =con.prepareStatement(sql);
	
			st.setString(1,a1.getName());
			st.setInt(2,a1.getPoints());
			st.setInt(3, a1.getId());
			st.executeUpdate();
			
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		
	}


	public void delete(int id) {
		String sql="delete from alien where id=?";
		try {
			PreparedStatement st =con.prepareStatement(sql);
	
			st.setInt(1, id);
			st.executeUpdate();
			
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		
	}
	
	
}
