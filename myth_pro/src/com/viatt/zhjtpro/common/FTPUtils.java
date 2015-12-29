package com.viatt.zhjtpro.common;

import java.io.BufferedOutputStream; 
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.net.SocketException; 
//import org.apache.commons.fileupload.util.Streams; 
import org.apache.commons.io.IOUtils; 
import org.apache.commons.net.ftp.FTP; 
import org.apache.commons.net.ftp.FTPClient; 
import org.apache.commons.net.ftp.FTPClientConfig; 
import org.apache.commons.net.ftp.FTPFile; 
import org.apache.commons.net.ftp.FTPReply; 
import org.apache.log4j.Logger; 

public class FTPUtils {

	private static final Logger LOGGER = Logger.getLogger(FTPUtils.class);

	private FTPClient ftpClient; // FTP客户端

	/**
	 * 连接到服务器并返回工具实例.
	 * 
	 * @throws AppException
	 *             所有异常自己封装
	 */
	public FTPUtils()
	{
		this.getFtpClientInstance();
	}

	private void getFtpClientInstance()
	{
		try {
			this.ftpClient = new FTPClient();
			// 中文系统设置编码为GBK
			this.ftpClient.setControlEncoding("GBK");
			// 统一配置client 设置系统为NT
			//this.ftpClient.configure(this.getFtpConfig());
			// 通过IP连接
			this.ftpClient.connect(PropertiesReader.getProperty("ftpServer"));
			// 登陆
			this.ftpClient.login(PropertiesReader.getProperty("ftpUserName"),
					PropertiesReader.getProperty("ftpPassword"));
			// 设置以二进制形式读取文件
			this.ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// 文件传输模式为流传输
			this.ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			// 设置FTPServer端口号
			this.ftpClient.setDefaultPort(Integer.valueOf(PropertiesReader.getProperty("ftpPort")));
			// 设置被动模式
			this.ftpClient.enterLocalPassiveMode();
			// 设置读写文件的最大时长为10分钟
			// this.ftpClient.setDataTimeout(60000);
			// 获得服务器响应号
			int reply = this.ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				// 服务器拒绝连接
				this.ftpClient.disconnect();
				LOGGER.error( "FTP server拒绝连接.");
				//throw new AppException(AppExcCodeAndMsg.FTP_SERVER_CONNECTION_ERROR);
			}
		} catch (SocketException e) {
			LOGGER.error( "登录ftp服务器失败！", e);
			//throw new AppException(AppExcCodeAndMsg.FTP_SERVER_TIMEOUT_ERROR, e);
		} catch (IOException e) {
			LOGGER.error("登录ftp服务器失败，FTP服务器无法打开！", e);
			//throw new AppException(AppExcCodeAndMsg.FTP_SERVER_IO_CLOSE_ERROR,e);
		}
	}

	private void checkFtpAvailable() throws Exception {
		if (this.ftpClient == null) {
			// 设置新连接
			this.getFtpClientInstance();
		}
//		else if (!this.ftpClient.isAvailable()) {
//			// 关闭原有连接
//			this.closeConnect();
//			// 设置新连接
//			this.getFtpClientInstance();
//		}
	}

	private boolean remotePathNotCorrect(String remotePath) {
		if (null == remotePath) {
			return true;
		}
		remotePath = remotePath.trim();
		if ("".equals(remotePath)) {
			return true;
		}
		String last = remotePath.substring(remotePath.length()
				- 1);
		String temp = "/\\:?\"<>|*";
		return temp.contains(last);
	}

	public boolean getFile(String remoteFileName, String localDir)
			throws Exception {
		boolean flag = false;
		if (remotePathNotCorrect(remoteFileName) || localDir == null) {
			LOGGER.error("FTP下载文件时传入参数有误!");
			return flag;
		}
		// 下载文件
		BufferedOutputStream buffOut = null;
		InputStream rin = null;
		try {
			this.checkFtpAvailable();
			String fileName = FileHelp.getFileNameByPath(remoteFileName);
			rin = this.ftpClient.retrieveFileStream(remoteFileName);
			if (null == rin) {
				LOGGER.error("FTP远程目录下没有要下载的文件!");
				return flag;
			}
			File parent = new File(localDir);
			if (!parent.exists()) {
				// 父目录不存在测建立
				boolean mkdirs = parent.mkdirs();
				if (!mkdirs) {
					// 建立目录失败
					LOGGER.error("FTP下载文件时建立本地文件失败!");
					return flag;
				}
			}
			buffOut = new BufferedOutputStream(new FileOutputStream(new File(
					parent, fileName)));
			//Streams.copy(rin, buffOut, false);
			// 调用retrieveFileStream之后必须调用
			flag = this.ftpClient.completePendingCommand();
		} catch (Exception e) {
			LOGGER.error("下载文件至本地失败!",  e);
			//throw new AppException(AppExcCodeAndMsg.FTP_SERVER_GETFILE_ERROR, e);
		} finally {
			try {
				if (buffOut != null) {
					buffOut.close();
				}
			} catch (Exception e) {
				LOGGER.error("下载文件后关闭buffOut发生异常.", e);
			} finally {
				try {
					if (rin != null) {
						rin.close();
					}
				} catch (Exception e) {
					LOGGER.error( "下载文件后关闭buffOut发生异常.",  e);
				}
			}
		}
		return flag;

	}
	
	/** 
     * 通过IO下载文件 
     * 
     * @param remoteFileName 
     *            --服务器上的文件名 
     * @param outputStream 
     *            --文件输出流，可输出至用户客户端 
     * @return true 下载成功，false 下载失败 
     * @throws AppException 
     *             发生各种异常均包装成AppException抛出 
     */ 
    public boolean getFileToRemote(String remoteFileName, 
            OutputStream outputStream) throws Exception 
    { 
        boolean flag = false; 
        if (remotePathNotCorrect(remoteFileName) || outputStream == null) 
        { 
            LOGGER.error("FTP通过IO远程上传单个文件，并重命名参数有误!"); 
            return flag; 
        } 
        // 下载文件 
        InputStream rin = null; 
        try 
        { 
            this.checkFtpAvailable(); 
            rin = this.ftpClient.retrieveFileStream(remoteFileName); 
            if (null == rin) 
            { 
                LOGGER.error("FTP远程目录下没有要下载的文件!"); 
                return flag; 
            } 
            //Streams.copy(rin, outputStream, false); 
            // 调用retrieveFileStream之后必须调用 
            flag = this.ftpClient.completePendingCommand(); 
        } 
        catch (Exception e) 
        { 
            LOGGER.error("下载文件至客户端浏览器失败!", e); 
           // throw new AppException(  AppExcCodeAndMsg.FTP_SERVER_GETFILE_ERROR, e); 
        } 
        finally 
        { 
            try 
            { 
                if (rin != null) 
                { 
                    rin.close(); 
                } 
            } 
            catch (Exception e) 
            { 
                LOGGER.error("通过IO下载文件后关闭流发生异常.", e); 
            } 
        } 
        return flag; 
    } 

    /** 
     * 删除单个文件 
     * 
     * @param filename 
     *            要删除的文件 
     * @throws AppException 
     *             发生各种异常均包装成AppException抛出 
     * @return true : 成功 false : 失败 
     */ 
    public boolean deleteFile(String filename) throws Exception 
    { 
        boolean flag = false; 
        if (filename == null) 
        { 
            LOGGER.error( "FTP删除单个文件参数有误!"); 
            return flag; 
        } 
        try 
        { 
            this.checkFtpAvailable(); 
            flag = this.ftpClient.deleteFile(filename); 
        } 
        catch (IOException e) 
        { 
            LOGGER.error( "ftp远程删除文件发生异常.",  e); 
            //throw new AppException( AppExcCodeAndMsg.FTP_SERVER_DELETEFILE_ERROR, e); 
        } 
        return flag; 
    }
    
    /** 
     * 删除单个目录下所有文件,不包括子目录及子目录中的文件 
     * 
     * @param dirName 
     *            要删除的目录 
     * @throws AppException 
     *             发生各种异常均包装成AppException抛出 
     * @return true : 成功 false : 失败 
     */ 
    public boolean deleteAllFileInDir(String dirName) throws Exception 
    { 
        boolean flag = false; 
        if (dirName == null) 
        { 
            LOGGER.error("FTP删除单个目录下所有文件参数有误!"); 
            return flag; 
        } 
        try 
        { 
            this.checkFtpAvailable(); 
            String[] listNames = this.ftpClient.listNames(dirName); 
            for (String name : listNames) 
            { 
                deleteFile(name); 
            } 
            flag = true; 
        } 
        catch (IOException e) 
        { 
            LOGGER.error("ftp远程删除单个目录下所有文件发生异常.", e); 
            //throw new AppException( AppExcCodeAndMsg.FTP_SERVER_DELETEFILE_ERROR, e); 
        } 
        return flag; 
    } 

    /** 
     * 根据传入的路径获取远程文件 
     * 
     * @param path 
     *            传入的远程ftp路径 
     * @return 返回路径对应的FTPFILE数组 
     * @throws AppException 
     *             发生各种异常均包装成AppException抛出 
     */ 
    public FTPFile[] getFileByPath(String path) throws Exception 
    { 
        if (path == null) 
        { 
            LOGGER.error( "FTP根据传入的路径获取远程文件参数有误!"); 
            return null; 
        } 
        try 
        { 
            this.checkFtpAvailable(); 
            return this.ftpClient.listFiles(path); 
        } 
        catch (IOException e) 
        { 
            LOGGER.error( "根据传入路径ftp获取远程文件数组发生异常.", e); 
            //throw new AppException( AppExcCodeAndMsg.FTP_SERVER_GETFTPFILES_ERROR, e); 
        }
		return null; 
    }
    
    /** 
     * 关闭连接 
     * 
     * @throws AppException 
     *             发生各种异常均包装成AppException抛出 
     */ 
    public void closeConnect() throws Exception 
    { 
        try 
        { 
            if (null != this.ftpClient && this.ftpClient.isConnected()) 
            { 
                this.ftpClient.logout(); 
                this.ftpClient.disconnect(); 
            } 
        } 
        catch (Exception e) 
        { 
            LOGGER.error("断开与FTP服务器连接发生异常.",e); 
            //throw new AppException(  AppExcCodeAndMsg.FTP_SERVER_CLOSECONNECT_ERROR, e); 
        } 
    }
}