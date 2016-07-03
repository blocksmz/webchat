package webchat;
import java.util.*;
import javax.servlet.ServletContext;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

public class process{
	
	public process()
	{
		
	}
	
	public process(LinkedList<String> para)
	{
		for(String tmp:para)
		{
			queue.add(tmp);
		}
	}
	
	public static long count=0;
	
	LinkedList<String> userInfo=new LinkedList<String>();
	
	int messageSum=0;
	
	LinkedList<String> queue=new LinkedList<String>();
	
	public void put(String para1, String Message)
	{
		
		if(messageSum>=100)
		{
			queue.pollFirst();
			queue.offerLast(para1+":"+Message);
		}
		else{
			queue.offerLast(para1+":"+Message);
			messageSum+=1;
		}
	}
	
	public LinkedList<String> get()
	{
		return queue;
	}
	
	public void putUser(String nickname)
	{
		userInfo.add(nickname);
		
	}
	public void removeUser(String nickname)
	{
		userInfo.remove(nickname);
		
	}
	
	public int checkUser(String nickname)
	{
		return userInfo.indexOf(nickname);
	}
	
	public String returnUser()
	{
		String userString="";
		
		for(String tpp:userInfo)
		{
			userString+="<div>"+tpp+"</div>";
		}
		return userString;
	}
	

}
