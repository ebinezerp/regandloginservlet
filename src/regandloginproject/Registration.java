package regandloginproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			// if password and confirmpassword are same then only registration should
			// successfull
			if (request.getParameter("password").equals(request.getParameter("confirmpassword"))) {

				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servletdemo", "root", "root");
				// creatting a prepare statement object, bcz need to insert dynamic values
				PreparedStatement ps = con.prepareStatement("insert into details values(?,?,?,?,?,?)");
				// directly values are setting to preparestatement without storing in string
				// variable as an extra variable
				ps.setString(1, request.getParameter("firstname"));
				ps.setString(2, request.getParameter("lastname"));
				ps.setString(3, request.getParameter("email"));
				ps.setString(4, request.getParameter("mobile"));
				ps.setString(5,request.getParameter("username"));
				// as password and confirm password both are same only password is storing
				ps.setString(6, request.getParameter("password"));
				// after setting values insert statement is executed
				int i=ps.executeUpdate();
				// checking weather record is  inserted or not
				if(i>0)
				{
					// after successful  registration redirected to login page
					RequestDispatcher dispatcher=request.getRequestDispatcher("login.html");
					dispatcher.forward(request, response);
				}else
				{
					// if registration fails redirect to registration page i.e,. index pate
					RequestDispatcher dispatcher=request.getRequestDispatcher("index.html");
					dispatcher.forward(request, response);
				}
				
				

			} else {

				// if password and confirmpassword are not same redirect to index.html
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
				dispatcher.include(request, response);

			}
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println(e);
		}
	}

}
