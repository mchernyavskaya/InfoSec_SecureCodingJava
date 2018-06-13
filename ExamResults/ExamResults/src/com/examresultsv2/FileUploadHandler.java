package com.examresultsv2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;


/**
 * Servlet implementation class FileUploadHandler
 */
@WebServlet("/FileUploadHandler")
@MultipartConfig(fileSizeThreshold = 1024*1024*2,maxFileSize = 1024*1024*10,maxRequestSize = 1024*1024*50)
public class FileUploadHandler extends HttpServlet {
	
	private Pattern pattern;
	private Matcher matcher;
	private static final String UPLOAD_PATTERN = "([^\\s]+(\\.(?i)(xml))$)";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadHandler() {

        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		  if (session == null) {
				request.setAttribute("examresult","Invalid Session");
				RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");            
				rd.include(request, response);
		  }
		  else {
		 
		 String pathToUpload = "/home/infosec/examresults_uploads/";
		 String contentDisposition = null;
		 
		// pathToUpload = getServletContext().getRealPath("/");
		// pathToUpload = pathToUpload+"admin/uploads";
		 
		 System.out.println(pathToUpload);
        
        //creating the save directory if it doesn't exist
        File uploadDirectory = new File(pathToUpload);
        if(!uploadDirectory.exists()){
            uploadDirectory.mkdirs();
        }
                 
        //Iterating the parts received from 'multipart/form-data' request
        for(Part part : request.getParts()){
            //extracting the file name
            String fileName = null;
            contentDisposition = part.getHeader("content-disposition");
            String[] strgs = contentDisposition.split(";");
            for(String strng : strgs) {
                if(strng.trim().startsWith("filename")){
                    fileName = strng.substring(strng.indexOf("=")+2, strng.length()-1);
                   
                    fileName = FilenameUtils.getName(fileName);
                   
                }
            }
        
           if(isValidXML(fileName)) {
           if(!contentDisposition.equals(null)) {
               pathToUpload = pathToUpload+File.separator+fileName;
               part.write(pathToUpload);
   			request.setAttribute("message","Success");
   			RequestDispatcher rd=request.getRequestDispatcher("/admin/Dashboard.jsp");            
   			rd.include(request, response);
           }
           else {
        		request.setAttribute("examresult","No file uploaded");
       			RequestDispatcher rd=request.getRequestDispatcher("/admin/Dashboard.jsp");            
       			rd.include(request, response);
           }

           }
           else {
        	   
        	   //Someone tried to upload an invalid file, create a security log
        	    request.setAttribute("examresult","Invalid file type");
      			RequestDispatcher rd=request.getRequestDispatcher("/admin/Dashboard.jsp");            
      			rd.include(request, response);
           }
        }
		  }
 
		//doGet(request, response);
	}

	private boolean isValidXML(String filename) {
		// TODO Auto-generated method stub

		
		pattern = Pattern.compile(UPLOAD_PATTERN);
		matcher = pattern.matcher(filename);
		return matcher.matches();
	}

}
