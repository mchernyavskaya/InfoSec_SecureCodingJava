<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exam Results</title>


    <style>

        .formclass
        {
           
            padding:80px;
            margin:200px auto;
            background: #6d2121;
            width:300px;
     
        }

         input
        {
            padding:10px;
            margin-right:2px;
            width:100%;

        }
         h2
         {
             text-align: center;
             color:white;
         }
            h3
         {
            
             text-align: center;
             color:white;
         }
    </style>
    
</head>
<body>
    <form id="form1" action="Login" method="post">
    <div class="formclass">
        <h2>USER LOGIN</h2>
        <input type="text" name="txtuser" class="input" placeholder="username"style="width: 275px"></input><br /><br />
        <input type="password" name="txtpwd" class="input" placeholder="password" style="width: 275px"></input><br /><br />
        <input type="submit" name="btnLogin" class="input" value="Login"/></input><br /><br />
        <h3><label for="txtout" class="input"> <%
String login_msg=(String)request.getAttribute("error");  
if(login_msg!=null)
out.println("<font color=white size=4px>"+login_msg+"</font>");
%></label></h3>  
       
    </div>
    </form>
</body>
</html>
</body>
</html>