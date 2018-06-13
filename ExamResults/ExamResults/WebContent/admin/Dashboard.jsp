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
    <!--  Check if a valid session exists  -->
    
    <% if(session == null || session.getAttribute("userloggedin") == null){
    	response.sendRedirect("index.jsp");
    } %>
    <div class="formclass">
    
    <form action="/ExamResults/AdminLogout" method="post">
    <input type="submit" name="btnLogout" class="input" value="Logout" style="width: 60px;margin-left: 300px;"/></input><br />
    
         <h2><label for="txtout" class="input">welcome Administrator</label></h2>  
    </form>   
    <form action="/ExamResults/FileUploadHandler" method="post" enctype=multipart/form-data>
        <h2><input type="file" name="file" class="input"/></h2>
        <h2><input type="submit" value="upload" class="input"/></h2><br />
     </form>
     <form action="/ExamResults/VerifyResults" method="post">       
        <input type="submit" name="btnLogin" class="input" value="Verify Results"/></input><br /><br />
        
      
        <h3><label for="txtout" class="input">  <%
String result=(String)request.getAttribute("examresult");  
if(result!=null)
out.println("<font color=white size=4px>"+result+"</font>");
%>

<%
   String uploadresult = (String) request.getAttribute("message");
   if ( uploadresult != null && (uploadresult).equals("Success")) { %>
   <script> alert("Successfully uploaded");
   window.location="admin/Dashboard.jsp"</script>
<%  } 	
   %>

</label></h3>  
   
    </form>
     </div>
</body>
</html>
