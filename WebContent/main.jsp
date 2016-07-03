<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/Flychat.css" />
<script type="text/javascript" src="js/Flychat.js"></script>
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>

<title>Flychat</title>
</head>

<body>
<% if(request.getSession().getAttribute(request.getSession().getId())==null)
{
	response.sendRedirect(getServletContext().getContextPath()+"/index.jsp");
}
%>
<div id="theTop">
    <div id="HeadWord">
         飞聊
    </div>

    <div class="headder">
      <span>昵称：<%= request.getSession().getAttribute(request.getSession().getId()) %></span>
      <span><a href=<%= getServletContext().getContextPath()+"/Control?action=quit" %>>退出</a></span>
    </div>
</div>
<div id="page">

    <div id="sidebar">
      <div class="des">所有成员：</div>
      <div id="list">
      </div>
    </div>

    <div id="main">
          
          <div id="upper">
          <div class="des">实时消息：</div>
          <div id="messagee"></div>
          </div>
      <div id="messagebox">
            <div id="tool">
             <input type="hidden" id="hidde" value=<%= request.getSession().getAttribute(request.getSession().getId()) %> />
             
              <input style="display:inline;float:right;" type="button" onClick="re();" value="取消"  />
             <input style="display:inline;float:right;margin-right:5px;margin-left:5px;'" type="button" onClick="react();" value="发送"  />
            </div>
             <form action="Control" id="userMessage" method="post">
                <textarea rows=6 cols=73 id="textarea" name="textMessage"> </textarea>
            </form>
      </div>
    </div>

</div>

<div id="footer">
<div class="footstyle"><center>飞聊聊天室</center></div>
<div class="footstyle"><center>由<a href="http://www.leyufront.com">比特小屋</a>提供技术支持</center></div>
</div>

<script type="text/javascript">
setInterval(getServerQueue,2000);
setInterval(getUser,2000);
</script>

</body>
</html>