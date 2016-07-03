import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import webchat.process;

@WebServlet("/Control")
public class Control extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Control() {
        super();
    }
    private void process(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
    	
    	response.setContentType("text/text;charset=UTF-8");
    	
    	String command=request.getParameter("action");
    	if(command.equals("send"))
		{
			sendMessage(request,response);
			request.getRequestDispatcher("Control?action=get").forward(request, response);
		}
		else if(command.equals("get"))
		{
			PrintWriter out=response.getWriter();
			out.println(getMessage(request,response));
		}
		else if(command.equals("quit"))
		{
			HttpSession theSession=request.getSession();
			
			//remove from user list
			process thelist=(webchat.process) getServletContext().getAttribute("public");
			HttpSession theSn=request.getSession();
			thelist.removeUser((String)theSn.getAttribute(theSn.getId()));
			getServletContext().setAttribute("public", thelist);
			
			response.sendRedirect(getServletContext().getContextPath()+"/");
			theSession.invalidate();
		}
		else if(command.equals("getUserList"))
		{
			PrintWriter out=response.getWriter();
			out.println(((webchat.process)getServletContext().getAttribute("public")).returnUser());
		}
		else if(command.equals("checkName"))
		{
			PrintWriter out=response.getWriter();
			out.println(((webchat.process)getServletContext().getAttribute("public")).checkUser(request.getParameter("comingName")));
		}
    }
    
    private void sendMessage(HttpServletRequest request,HttpServletResponse response)
    {
    	HttpSession theSession=request.getSession();
    	String theName=(String)theSession.getAttribute(theSession.getId());
    	String theMessage=request.getParameter("textMessage");
    	
    	//test begin
    	System.out.println(theMessage);
    	//test end
    	
    	process now=(process)getServletContext().getAttribute("public");
    	
    	
    	if(theSession.getAttribute("secret")==null)
    	{
    		theSession.setAttribute("secret", new LinkedList<String>());
    		now.count+=1;
    	}
    	
    	now.put(theName, theMessage);
//    	getServletContext().setAttribute("public",new process(now.get()));
    	getServletContext().setAttribute("public",now);
    }
    
    private String getMessage(HttpServletRequest request,HttpServletResponse response)
    {
    	process now=(process)getServletContext().getAttribute("public");
    	
    	LinkedList<String> getReturn=now.get();
    	HttpSession theSession=request.getSession();
    	String result="";
    	
    	if(theSession.getAttribute("lastMess")==null)
    	{
    		theSession.setAttribute("lastMess", getReturn);
    		
    		//concat the message and return it
    		int index=0;
    		
    		int length=getReturn.size();
    		String theName=(String)theSession.getAttribute(theSession.getId());
    	
    		//test begin
    		//System.out.println("lastMess doesn't exists");
    		
    		//test end
    		
    		for(;index<length;index++)
    		{
    			String inFor=getReturn.get(index);
    			
    			//test begin
    			//System.out.println("test  "+inFor);
    			
    			//test end
    			
    			
    			
    			//temp remove it
    			
    			if(inFor.startsWith(theName))
    			{
    				continue;
    			}
    			//temp remove it
    			result+="<div>"+getReturn.get(index)+"</div>"+"<br/>";
    		}
    	}
    	else{
    		LinkedList<String> before=(LinkedList<String>)theSession.getAttribute("lastMess");
    		
    		//replace the privous message
    		theSession.setAttribute("lastMess", getReturn);
    		
    		//concat the message and return it
    		String last=before.peekLast();
    		System.out.println("last time last message:"+last);
    		int index=getReturn.indexOf(last);
    		index+=1;
    		
//    		if(index==-1)
 //   		{
  //  			index+=1;
 //   		}
    		
    		//test end
    		
    		
    		//System.out.println("last time last message index:"+index);
    		
    		
    		int length=getReturn.size();
    		
    		String theName=(String)theSession.getAttribute(theSession.getId());
    		
    		for(;index<length;index++)
    		{
    			String inFor=getReturn.get(index);
    			
    			//test begin
    		//	System.out.println("text  "+inFor);
    			
    			//test begin
        	//	System.out.println("lastMess exists");
        		
        		//test end
    			
    			//test end
    			
    			
    			if(inFor.startsWith(theName))
    			{
    				continue;
    			}
    			
    			result+="<div>"+getReturn.get(index)+"</div>"+"<br/>";
    		}
    	}
    	System.out.println(result);
    	return result;
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//set response header
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET");
		response.setHeader("Access-Control-Allow-Headers", "Cache-Control, Origin, Authorization, Content-Type, X-Requested-With");
		response.setHeader("Access-Control-Max-Age", "86400");
		
		process(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
