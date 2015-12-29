package biz.common.net.tcp;
import java.net.*;

/**
 * 新套接字连接监听器接口。当监听到新的连接时调用newSocketAccepted方法
 * 
 * @(#) NewSocketAcceptListener.java	1.0  
 */

public interface SocketListener {
	
	/**
	 * 标示监听到新的连接
	 * @param socket
	 */
	public void newSocketAccepted(Socket socket);

	public void socketClosed(Socket socket);

}
