package regandloginproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servletdemo", "root", "root");
			//getting record from database where username and password matchs
			PreparedStatement ps=con.prepareStatement("select * from details where username=? and password=?");
			ps.setString(1, request.getParameter("username"));
			ps.setString(2, request.getParameter("password"));
			ResultSet rs= ps.executeQuery();
			System.out.println("outside");
			//if record exists 
			if(rs.next())
			{
				// setting all attributes to request to sent to the profile Servlet
				request.setAttribute("firstname",rs.getString("firstname"));
				request.setAttribute("lastname",rs.getString("lastname"));
				request.setAttribute("email",rs.getString("email"));
				request.setAttribute("mobile",rs.getString("mobile"));
				request.setAttribute("username",rs.getString("username"));
				
				RequestDispatcher dispatcher=request.getRequestDispatcher("/Profile");
				dispatcher.forward(request, response);
			}
			else
			{
				RequestDispatcher dispatcher=request.getRequestDispatcher("index.html");
				dispatcher.forward(request, response);	
			}
		}catch (Exception e) {
			// TODO: handle exception
			
			System.out.println(e);
		}
	}

}
