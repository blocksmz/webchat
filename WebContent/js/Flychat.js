/**
 * 
 */
function react()
{
	var frm=$('#userMessage');
//	frm.submit(function(ev)
//			{
//		alert('in post ajax');
				$.ajax({
					type:frm.attr('method'),
					url:frm.attr('action')+'?action=send',
					data:frm.serialize(),
					async:true,
					success:function(data,textStatus,jqXHR)
					{
						$("#messagee").append(data);
					}
				});
//				ev.preventDefault();
//			})
				document.getElementById("textarea").value="";
	flushMessage();
//	getServerQueue();
}

function flushMessage()
{
	var newM=document.createElement("div");
	newM.setAttribute("style",'"float:right;"');
	var uu=document.getElementById('hidde').getAttribute("value");
	var newT=document.createTextNode(document.getElementById("textarea").value);
	newM.appendChild(newT);
	document.getElementById("textarea").value="";
	
	document.getElementById('messagee').appendChild(newM);
	scrollIt();
	
}

function getServerQueue()
{
	$.ajax({url:"http://"+location.host+"/webchat/Control?action=get",
		type:"GET",
		async:true,
		success:function(data,textStatus,jqXHR){
			if(data!=null&&data!="")
			{
				$("#messagee").append(data);
				scrollIt();
			}
		}
	})
}

function getUser()
{
	$.ajax({
		url:"http://"+location.host+"/webchat/Control?action=getUserList",
		type:"GET",
		aync:true,
		success:function(data,textStatus,jqXHR)
		{
			document.getElementById("list").innerHTML=data;
			alert('user list updated!');
		}
	})
}

function scrollIt()
{
	var innerL=document.getElementById("messagee");
	innerL.pageYOffset=innerL.scrollHeight;
	innerL.scrollTop=innerL.scrollHeight;
}

function userNameCheck()
{
	var thk=document.getElementsByName('nickname')[0];
	if(thk.value=='')
		{
			alert("昵称不能为空！");
			document.theForm.nickname.focus();
			return false;
		}
	else{
		$.ajax({
			url:"http://"+location.host+"/webchat/Control?action=checkName",
			type:"POST",
			data:"comingName="+thk.value,
			success:function(data,textStatus,jqXHR)
			{
				if(data==-1)
					{
						alert('昵称已被占用，请重新填写！');
						document.theForm.nickname.focus();
						return false;
					}
			}
		})
		return true;
	}
}

function quit()
{
	$.ajax({url:"http://"+location.host+"/webchat/Control?action=quit",
		type:"GET",
		async:true,
		success:function(data,textStatus,jqxhr)
		{
			window.location="http://"+location.host+"/webchat/index.jsp";
		}
	})
}


function re()
{
	document.getElementById("textarea").value="";
}