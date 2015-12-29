package biz.common.net.tcpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

import org.springframework.web.context.WebApplicationContext;

import biz.common.BGMLogger;
import biz.common.IModel;
import biz.common.format.CommFormat;
import biz.common.net.tcp.CommProcessor;

public class TCPClient implements Runnable {
	private String hostName;
	private int portNum;
	private String type;
	private boolean keepAlive;
	private CommFormat commFormat;
	private CommProcessor commProcessor;
	private Map services;
	private WebApplicationContext wac;
	private Thread theThread;
	private Socket socket;
	private InputStream input;
	private OutputStream output;

	public WebApplicationContext getWac() {
		return wac;
	}

	public void setWac(WebApplicationContext wac) {
		this.wac = wac;
	}

	public CommFormat getCommFormat() {
		return commFormat;
	}

	public void setCommFormat(CommFormat commFormat) {
		this.commFormat = commFormat;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPortNum() {
		return portNum;
	}

	public void setPortNum(int portNum) {
		this.portNum = portNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isKeepAlive() {
		return keepAlive;
	}

	public void setKeepAlive(boolean keepAlive) {
		this.keepAlive = keepAlive;
	}

	public int init() {
		try {
			BGMLogger.LogInfo("Connection to [" + hostName + "] on port["
					+ portNum + "]");
			socket = new Socket(hostName, portNum);
			input = socket.getInputStream();
			output = socket.getOutputStream();
			if (this.keepAlive == true) {
				socket.setKeepAlive(keepAlive);
			}
			
		} catch (Exception e) {
			BGMLogger.LogError("Connection to [" + hostName + "] on port["
					+ portNum + "]:" + e.getMessage());
		}
		return 0;
	}

	public int send(String strKey, IModel imodel) {
		
		return 0;
	}

	public int send(String strMsg) {
		try
		{
		//byte[] buf = commFormat.getPackageSend(model, strClassName, map);
		//byte[] sendBuf = commProcessor.wrapMessagePackage(buf);
		//output.write(sendBuf);
		//output.flush();
		}catch(Exception ex)
		{
			
		}
		return 0;
	}

	public IModel recv() {
		try {
			byte[] buf = commProcessor.readPackage(input);
//			commFormat.newPackageReceived(msg, strClassName, map);
//			flows
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int close() {
		try
		{
			input.close();
			output.close();
			socket.close();
		}
		catch(Exception ex)
		{
			
		}
		return 0;
	}

	public Map getServices() {
		return services;
	}

	public void setServices(Map services) {
		this.services = services;
	}

	public void run() {
		this.init();
		this.recv();
	}

	public void startUp() {
		theThread = new Thread(this);
		theThread.setName("Socket Listen Thread [" + portNum + "]");
		theThread.start();
	}

	public CommProcessor getCommProcessor() {
		return commProcessor;
	}

	public void setCommProcessor(CommProcessor commProcessor) {
		this.commProcessor = commProcessor;
	}

}
