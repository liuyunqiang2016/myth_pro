package biz.common.net.tcp;
import java.net.*;

/**
 * ���׽������Ӽ������ӿڡ����������µ�����ʱ����newSocketAccepted����
 * 
 * @(#) NewSocketAcceptListener.java	1.0  
 */

public interface SocketListener {
	
	/**
	 * ��ʾ�������µ�����
	 * @param socket
	 */
	public void newSocketAccepted(Socket socket);

	public void socketClosed(Socket socket);

}
