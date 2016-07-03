<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/Flychat.css" />
<script type="text/javascript" src="js/Flychat.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>supply a nickname here</title>
</head>
<body id="idx">
<%request.getSession().invalidate(); %>
<div id="fform">
       <div id="title">
            飞聊聊天室
       </div>
       <div id="lup">
            输入您的昵称：
       </div>
    <div id="ldn">  
    <form action="Main" method="post" name="theForm" onSubmit="return userNameCheck();">
    <input type="text" name="nickname" id="check"/>
    <br>
    <br>
    <input type="submit" value="提交"/>
    <input type="reset" value="重置"/>
    </form>
    </div>
</div>
</body>
</html>