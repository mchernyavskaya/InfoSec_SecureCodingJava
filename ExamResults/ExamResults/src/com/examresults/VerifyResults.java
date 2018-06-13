package com.examresults;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class VerifyResults
 */
@WebServlet("/VerifyResults")
public class VerifyResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyResults() {
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
		String examresult = "RESULTS: ";
		
		try{
			 File myClass = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
			 
			 int packageSubFolder = getClass().getPackage().getName().replaceAll("[^.]", "").length() + 2;
			 
			 for(int i=0; i<packageSubFolder; i++){
		            myClass = myClass.getParentFile();
		        }
			 String webInfPath = getServletContext().getRealPath("/");
			// String webInfPath = myClass.getAbsolutePath().replaceAll("%20", " ") + File.separator;
			 System.out.println(webInfPath);
		 
			 File file = new File(webInfPath+"/admin/Results.xml");
			 System.out.println(file);
             DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder dBuilder = dBFactory.newDocumentBuilder();
             Document xmldoc = dBuilder.parse(file);
           
           
           
        
           XPath xPath = XPathFactory.newInstance().newXPath();
           
           String expression = "/RESULTS/STUDENT";
            
			System.out.println(expression);
		
			 Object result = xPath.compile(expression).evaluate(xmldoc, XPathConstants.NODESET);
			 NodeList nodeList = (NodeList) result;
			 
			 System.out.println(nodeList.getLength());
			 if(nodeList.getLength() > 0) {
					
			 if (nodeList != null) {
			  for (int i = 0; i < nodeList.getLength(); i++) {
						
				Element el = (Element) nodeList.item(i);
				String name = el.getElementsByTagName("NAME").item(0).getTextContent();
				String physics = el.getElementsByTagName("PHYSICS").item(0).getTextContent();
				String chemistry = el.getElementsByTagName("CHEMISTRY").item(0).getTextContent();
				String mathematics = el.getElementsByTagName("MATHEMATICS").item(0).getTextContent();
				String english = el.getElementsByTagName("ENGLISH").item(0).getTextContent();
				String computers = el.getElementsByTagName("COMPUTERS").item(0).getTextContent();
				
				examresult += "NAME: "+name+"\n"+"PHYSICS: "+physics+"\n"+"CHEMISTRY: "+chemistry+"\n"+"MATHEMATICS: "+mathematics+"\n"+"ENGLISH: "+english+"\n"+"COMPUTERS: "+computers+"\n\n";
						
			
			  }	 
				System.out.println(examresult);
				
				request.setAttribute("examresult",examresult);
				RequestDispatcher rd=request.getRequestDispatcher("admin/Dashboard.jsp");            
				rd.include(request, response);
			 }	
			 }
			 else {
					request.setAttribute("examresult","No Results found");
					RequestDispatcher rd=request.getRequestDispatcher("admin/Dashboard.jsp");            
					rd.include(request, response);
			 }
		   }
           catch(Exception e){
               // System.out.println(e);
                //response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                //response.sendError(e.printStackTrace());
                //e.printStackTrace();
                
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                e.printStackTrace(printWriter);
                response.setContentType("text/plain");
                response.getOutputStream().print(stringWriter.toString());
            }
		//doGet(request, response);
	}
	
}
