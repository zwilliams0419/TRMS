package com.revature.trms.servlet;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;


@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*100,      // 100MB
                 maxRequestSize=1024*1024*100)   // 100MB
public class DropBoxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		
		@SuppressWarnings("deprecation")
		DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, "sX6La58QE8AAAAAAAAAADGtkdusZpjqGbRhw8U7JUy7TJSZNTv378sR8qcvnnN1U");
        
        //list folders
        ListFolderResult result;
		try {
			result = client.files().listFolder("");
		    while (true) {
		        for (Metadata metadata : result.getEntries()) {
		            System.out.println(metadata.getPathLower());
		        }
		
		        if (!result.getHasMore()) {
		            break;
		        }
		
		        result = client.files().listFolderContinue(result.getCursor());
		    }
		} catch (ListFolderErrorException e1) {
			e1.printStackTrace();
		} catch (DbxException e1) {
			e1.printStackTrace();
		}
        
		
        
		
		
        OutputStream out = response.getOutputStream();
        try 
        {
        	String fileName = "23131";
        	System.out.println(request.getParameter("requestId2"));
			FileMetadata metadata = client.files().downloadBuilder("/" + request.getParameter("requestId2")).download(out);
		} 
        catch (DbxException e) 
        {
			e.printStackTrace();
		}
        out.flush();
        
	
	}
	
	
	
	
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		
		@SuppressWarnings("deprecation")
		DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, "sX6La58QE8AAAAAAAAAADGtkdusZpjqGbRhw8U7JUy7TJSZNTv378sR8qcvnnN1U");

        try {
			for (Part part2 : request.getParts()) {
			    String fileName = extractFileName(part2);
			    try
			    {
			    	InputStream in = request.getPart("file").getInputStream();
			        FileMetadata metadata = client.files().uploadBuilder("/" + request.getParameter("requestId") + "/" + fileName).uploadAndFinish(in);
			    }
			    catch(DbxException e)
			    {
			    	//e.printStackTrace();
			    }
			    
			}
		} 
        catch (ServletException e) {
			e.printStackTrace();
		}
        
        
		
	}
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
	
	
}
	
