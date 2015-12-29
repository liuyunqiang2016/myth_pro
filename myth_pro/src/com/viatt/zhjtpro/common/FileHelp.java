package com.viatt.zhjtpro.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts.upload.FormFile;

public class FileHelp {

	public static void downFile(String fileName, String fileSize, String colName,
			HttpServletResponse response) {
		String upDir = PropertiesReader.getProperty("upDir");
		if (upDir == null || upDir.equals("")) {
			return;
		}
		String appDir = PropertiesReader.getProperty("appDir");
		if (appDir == null || appDir.equals("")) {
			return;
		}
		String colDir = "";
		if (!colName.equals("")) {
			colDir = appDir + upDir + "/" + colName + "/";
		} else {
			colDir = appDir + upDir + "/";
		}
		try {
			InputStream fis = new BufferedInputStream(new FileInputStream(
					colDir + fileName));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes(),"ISO-8859-1"));
			response.addHeader("Content-Length", fileSize);
			OutputStream toClient = new BufferedOutputStream(response
					.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String upLoadFile(FormFile file, String colName,String type, String newFileName) {
		String fileName = file.getFileName();
		if (fileName.equals("")) {
			return "";
		}
		 String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
        if(type.toUpperCase().equals("PIC")){
        	boolean flag = ext.equals("JPG");
        	if(!flag){
        		return "�ϴ��ļ���չ����Ϊjpg";
        	}
        }
		String upDir = PropertiesReader.getProperty("upDir");
		if (upDir == null || upDir.equals("")) {
			return "�ϴ��ļ�·��δ����";
		}
		String appDir = PropertiesReader.getProperty("appDir");
		if (appDir == null || appDir.equals("")) {
			return "�ϴ��ļ�·��δ����";
		}
		File fupDir = new File(upDir);
		if (!fupDir.exists()) {
			fupDir.mkdirs();
		}
		String colDir = "";
		if (!colName.equals("")) {
			colDir = appDir + upDir + "/" + colName + "/";
			File fcolDir = new File(colDir);
			if (!fcolDir.exists()) {
				fcolDir.mkdirs();
			}
		} else {
			colDir = appDir + upDir + "/";
		}
		try {
			FileOutputStream out = new FileOutputStream(colDir + newFileName.replace("-", ""));
			InputStream in = file.getInputStream();
			byte[] bye = new byte[file.getFileSize()];
			in.read(bye);
			out.write(bye);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "";
	}
	
	public static String upLoadFile1(FormFile file, String colName,String type,String fileName) {
		if(file.getFileName()==""){
			return "";
		}
		if (fileName.equals("")) {
			return "";
		}
        String ext = file.getFileName().substring(file.getFileName().lastIndexOf(".") + 1).toUpperCase();
        if(type.toUpperCase().equals("PIC")){
        	boolean flag = ext.equals("JPG");
        	if(!flag){
        		return "�ϴ��ļ���չ����Ϊjpg";
        	}
        }
		String upDir = PropertiesReader.getProperty("upDir");
		if (upDir == null || upDir.equals("")) {
			return "�ϴ��ļ�·��δ����";
		}
		String appDir = PropertiesReader.getProperty("appDir");
		if (appDir == null || appDir.equals("")) {
			return "�ϴ��ļ�·��δ����";
		}
		File fupDir = new File(upDir);
		if (!fupDir.exists()) {
			fupDir.mkdirs();
		}
		String colDir = "";
		if (!colName.equals("")) {
			colDir = appDir + upDir + "/" + colName + "/";
			File fcolDir = new File(colDir);
			if (!fcolDir.exists()) {
				fcolDir.mkdirs();
			}
		} else {
			colDir = appDir + upDir + "/";
		}
		try {
			FileOutputStream out = new FileOutputStream(colDir + fileName.replace("-", ""));
			InputStream in = file.getInputStream();
			byte[] bye = new byte[file.getFileSize()];
			in.read(bye);
			out.write(bye);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "";
	}
	
	/** 
     * 根据传入的文件路径获取文件名 本质上截取一段路径 
     * 
     * @param path 将要获取文件名的路径 
     * @return 获取到文件名 
     */ 
    public static String getFileNameByPath(String path) 
    { 
        if (path == null) 
        { 
            return null; 
        } 
        String retValue = ""; 
        String replace = path.replace("/", ",").replace("\\", ","); 
        String paths[] = replace.split(","); 
        for (int i = paths.length - 1; i >= 0; i--) 
        { 
            if (!paths[i].isEmpty()) 
            { 
                retValue = paths[i]; 
                break; 
            } 
        } 
        return retValue; 
    } 

/**  
     * 删除目录（文件夹）以及目录下的文件  
     * @param   dir 被删除目录的文件路径  
     * @return  目录删除成功返回true,否则返回false  
     */  
    public static boolean deleteDirectory(String dir) 
    { 
        //如果dir不以文件分隔符结尾，自动添加文件分隔符   
        if (!dir.endsWith(File.separator)) 
        { 
            dir = dir + File.separator;   
        } 
        File dirFile = new File(dir); 

        //如果dir对应的文件不存在，或者不是一个目录，则退出   
        if (!dirFile.exists() || !dirFile.isDirectory()) 
        {   
            return false;   
        } 

        //删除文件夹下的所有文件(包括子目录) 
        File[] files = dirFile.listFiles(); 
        for (int i = 0; i < files.length; i++) 
        { 
            //删除子文件   
            if (files[i].isFile()) 
            { 
                //deleteFile(files[i].getAbsolutePath()); 
            } 
            //删除子目录   
            else 
            { 
                deleteDirectory(files[i].getAbsolutePath()); 
            } 
        } 

        //删除当前目录   
        return dirFile.delete(); 
    }
}
