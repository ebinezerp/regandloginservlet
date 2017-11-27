package regandloginproject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Profile extends HttpServlet{
	
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
	{
		try {
			PrintWriter out=response.getWriter();
			out.println("firstname :"+request.getAttribute("firstname"));
			out.println("lastname :"+request.getAttribute("lastname"));
			out.println("email :"+request.getAttribute("email"));
			out.println("mobile :"+request.getAttribute("mobile"));
			out.println("username :"+request.getAttribute("username"));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
