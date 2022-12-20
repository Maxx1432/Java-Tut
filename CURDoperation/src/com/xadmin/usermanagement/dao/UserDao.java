package com.xadmin.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;
import com.xadmin.usermanagement.bean.UserBean;

public class UserDao {

	   private String jdbcURL = "jdbc:mysql://localhost:3306/userdb?useSSL=false";
	   private String jdbcUSerName ="root";
	   private String jdbcPassword ="root";
	   private String jdbcDriver ="com.mysql.cj.jdbc.Driver";
	   
	   private static final String INSERT_USERS_SQL = "insert into users"+"(name,email,country) values "+"(?,?,?)";
	   
	   private static final String SELECT_USER_By_ID = "select id,name,email,country from users where id=?";
	   private static final String SELECT_ALL_USERS = "select * from users";
	   private static final String DELETE_USERS_SQL = "delete from users where id=?";
	   private static final String UPDATE_USERS_SQL = "update users set name=?,email=?,country=? where id=?";
	public UserDao() {
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver); //loading JDBC
			connection = DriverManager.getConnection(jdbcURL,jdbcUSerName,jdbcPassword);
			//Creating Connection 
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	//insert user
	public void insertUser(UserBean user)
	{
		System.out.println(INSERT_USERS_SQL);
		try {
			Connection connection = getConnection();
			PreparedStatement preparedstatement = connection.prepareStatement(INSERT_USERS_SQL);
			preparedstatement.setString(1, user.getName());
			preparedstatement.setString(2, user.getEmail());
			preparedstatement.setString(3, user.getCountry());
			System.out.println(preparedstatement);
			preparedstatement.executeUpdate();
		}
		catch(SQLException e)
		{
			printSQLException(e);
		}
	}
	
	//select user by id
		public UserBean selectUser(int id) {
			UserBean user = null;
			try {
				Connection connection = getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement(SELECT_USER_By_ID);
				preparedstatement.setInt(1,id);
				System.out.println(preparedstatement);
				ResultSet rs=preparedstatement.executeQuery();
		
				while(rs.next()) {
				String name=rs.getString("name");
				String email=rs.getString("email");
				String country=rs.getString("country");
				user = new UserBean(id,name,email,country);
				}
				
			}
			catch(SQLException e)
			{
				printSQLException(e);
			}
			return user;
		}
	//select all user
	public List<UserBean> selectAllUsers(){
		List<UserBean> users = new ArrayList<>();
		try {
			Connection connection = getConnection();
			PreparedStatement preparedstatement = connection.prepareStatement(SELECT_ALL_USERS);
			System.out.println(preparedstatement);
			ResultSet rs=preparedstatement.executeQuery();
	
			while(rs.next()) {
			int id = rs.getInt("id");
			String name=rs.getString("name");
			String email=rs.getString("email");
			String country=rs.getString("country");
			users.add(new UserBean(id,name,email,country));
			}
			
		}
		catch(SQLException e)
		{
			printSQLException(e);
		}
		return users;
	}
	//update user
	public boolean updateUser(UserBean user) throws SQLException
	{
		boolean rowUpdated;
		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);){
			System.out.println("Updated User: "+statement);
			statement.setString(1,user.getName());
			statement.setString(2,user.getEmail());
			statement.setString(3,user.getCountry());
			statement.setInt(4,user.getId());
			System.out.println("Updated User: "+statement);
			rowUpdated = statement.executeUpdate() > 0;
			}
			return rowUpdated;
		}
		
	
	//delete user
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);)
		{		statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() >0;
		 }
		return rowDeleted;
	}
	
	
	private void printSQLException(SQLException ex) {
		for(Throwable e : ex)
		{	if(e instanceof SQLException )
			{
				e.printStackTrace(System.err);
				System.err.println("SQLState: "+((SQLException) e).getSQLState());
				System.err.println("ERROR Code : "+((SQLException) e).getErrorCode());
				System.err.println("Message : "+ e.getMessage());
				Throwable t= ex.getCause();
				 while(t != null) {
					 System.out.println("Cause "+ t);
					 t=t.getCause();
				 }
			}
		
	}  
	}
}