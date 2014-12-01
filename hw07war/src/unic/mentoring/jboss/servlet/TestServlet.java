package unic.mentoring.jboss.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import unic.mentoring.jboss.beans.TestBean;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public TestServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ServletOutputStream os = response.getOutputStream();
		String name = request.getParameter("name");
		
		if (name == null || name.length() == 0)
			name = "Anonymous";
		
		os.println("Hello, " + name + "!");

//		Configuration cfg = new Configuration();
//		cfg.configure("hibernate.cfg.xml");
//		SessionFactory factory = cfg.buildSessionFactory();
//		Session session = factory.openSession();
//		Object o = session.load(User.class, new Integer(1));
//		User s = (User)o;
//		os.println("q=" + s.getName());
//		session.close();
//		factory.close();
	}
}