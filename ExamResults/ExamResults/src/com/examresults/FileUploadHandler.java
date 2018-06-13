package com.examresults;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import org.apache.commons.io.FilenameUtils;
import org.apache.taglibs.standard.lang.jstl.test.beans.Factory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;


/**
 * Servlet implementation class FileUploadHandler
 */
@WebServlet("/FileUploadHandler")
@MultipartConfig(fileSizeThreshold = 1024*1024*2,maxFileSize = 1024*1024*10,maxRequestSize = 1024*1024*50)
public class FileUploadHandler extends HttpServlet {
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
		 
		 String pathToUpload = "uploads";
		 String contentDisposition = null;
		 
		 pathToUpload = getServletContext().getRealPath("/");
		 pathToUpload = pathToUpload+"admin";
		 
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
                    System.out.println(fileName);
                    fileName = FilenameUtils.getName(fileName);
                    System.out.println(fileName);
                }
            }
            
           if(!contentDisposition.equals(null)) {
               pathToUpload = pathToUpload+File.separator+fileName;
               part.write(pathToUpload);
   			request.setAttribute("message","Success");
   			RequestDispatcher rd=request.getRequestDispatcher("/admin/Dashboard.jsp");            
   			rd.include(request, response);
           }
           else {
        		request.setAttribute("message","No file uploaded");
       			RequestDispatcher rd=request.getRequestDispatcher("/admin/Dashboard.jsp");            
       			rd.include(request, response);
           }
        }

		//doGet(request, response);
	}

}
