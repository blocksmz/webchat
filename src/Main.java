import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.TreeMap;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import webchat.process;


@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Main() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession sn=request.getSession(true);
		ServletContext sc=getServletContext();

		if(sn.getAttribute(sn.getId())==null)
		{
			//store nickname
			sn.setAttribute(sn.getId(),request.getParameter("nickname"));
		}
		
		//set up the message queue
		
		if(sc.getAttribute("public")==null)
		{
			sc.setAttribute("public", new webchat.process());
			
			//store user list
			process tepp=(process)sc.getAttribute("public");
			tepp.putUser(request.getParameter("nickname"));
			sc.setAttribute("public", tepp);
		}
		
		//set up user private queue
		if(sn.getAttribute("private")==null)
		{
			sn.setAttribute("private",new LinkedList<String>());
		}
		
		//online user queue
		if(sn.getAttribute("onlineUser")==null)
		{
			sn.setAttribute("onlineUser",new  LinkedList<String>());
		}
		
		//set response header
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Headers", "Cache-Control, Origin, Authorization, Content-Type, X-Requested-With");
		response.setHeader("Access-Control-Max-Age", "86400");
		
		//forward to the main chat page
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
