package biz.common.net.tcp;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.context.WebApplicationContext;

import biz.common.BGMLogger;
import biz.common.format.CommFormat;

public class TCPIPServiceServer implements SocketListener, Runnable
{
	private String serviceName;

	private WebApplicationContext wac;

	private Map serverMap;

	// 通信协议处理器，用于处理通信协议
	protected CommProcessor commProcessor;

	protected CommFormat commFormat;

	private Thread theThread;

	private int maxConnection;

	private ServerSocket socket;

	protected int port = 0;

	private boolean isStop = false;

	protected boolean keepAlive = false;

	private boolean poolThread = false;

	private int poolSize;

	private List threadPool;

	private List socketAcceptListeners;

	/**
	 * socket连接处理线程池同步对象
	 */
	private Object socketProcessSync = new Object();

	public TCPIPServiceServer()
	{
		threadPool = new ArrayList();
	}

	public TCPIPServiceServer(int port)
	{
		this.port = port;
		threadPool = new ArrayList();
	}

	public int getPoolSize()
	{
		return poolSize;
	}

	public void setPoolSize(int poolSize)
	{
		this.poolSize = poolSize;
	}

	public boolean isPoolThread()
	{
		return poolThread;
	}

	public void setPoolThread(boolean poolThread)
	{
		this.poolThread = poolThread;
	}

	public boolean isKeepAlive()
	{
		return keepAlive;
	}

	public void setKeepAlive(boolean keepAlive)
	{
		this.keepAlive = keepAlive;
	}

	public void newSocketAccepted(Socket aSocket)
	{
		BGMLogger.LogInfo("New socket accept from "
				+ aSocket.getRemoteSocketAddress());
		fireSocketAcceptEvent(aSocket);

		if (this.poolThread)
		{
			synchronized (socketProcessSync)
			{
				while (true)
				{
					for (int i = 0; i < this.threadPool.size(); i++)
					{
						SocketProcessThread socketThread = (SocketProcessThread) threadPool
								.get(i);
						if (socketThread.isBeFree())
						{
							socketThread.setBeFree(false);
							socketThread.processSocket(aSocket);
							return;
						}
					}

					if (threadPool.size() < this.maxConnection)
					{
						SocketProcessThread socketThread = new SocketProcessThread(
								aSocket, keepAlive);
						socketThread.setCommProcessor(this.commProcessor);
						socketThread.setCommFormat(this.commFormat);
						socketThread.setPooledThread(true);
						socketThread.setBeFree(false);

						socketThread.parent = this;
						socketThread.startUp();
						threadPool.add(socketThread);
						return;
					}
					BGMLogger.LogInfo("TCP/IP Listen on port [" + port
							+ "] touch the max connection:"
							+ this.maxConnection);
					try
					{
						socketProcessSync.wait();
					} catch (Exception e)
					{

					}
				}
			}
		} else
		{
			SocketProcessThread socketThread = new SocketProcessThread(aSocket,
					keepAlive);
			socketThread.setCommProcessor(this.commProcessor);
			socketThread.setCommFormat(this.commFormat);
			socketThread.parent = this;
			socketThread.startUp();
		}
	}

	private void fireSocketAcceptEvent(Socket socket)
	{
		if (socketAcceptListeners == null)
			return;
		for (int i = 0; i < socketAcceptListeners.size(); i++)
		{
			SocketListener listener = (SocketListener) socketAcceptListeners
					.get(i);
			listener.newSocketAccepted(socket);
		}
	}

	/**
	 * 启动监听服务
	 */
	public void startUp()
	{
		theThread = new Thread(this);
		theThread.setName("Socket Listen Thread [" + port + "]");
		theThread.start();
	}

	public CommProcessor getCommProcessor()
	{
		return commProcessor;
	}

	public void setCommProcessor(CommProcessor commProcessor)
	{
		this.commProcessor = commProcessor;
	}

	public void run()
	{

		try
		{
			BGMLogger.LogInfo("Listen thread listen at port: " + port);
			socket = new ServerSocket(port, 0);
		} catch (Exception e)
		{
			BGMLogger.LogError("Failed to listen on: " + port + " "
					+ e.getMessage());
			return;
		}
		while (!isStop)
		{

			try
			{
				Socket aSocket = socket.accept();
				if (aSocket != null)
				{
					newSocketAccepted(aSocket);
				}

			} catch (java.io.IOException e)
			{
				BGMLogger.LogInfo("Socket Accept is Fail:" + e.getMessage());
				break;
			}
		}
	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port)
	{
		this.port = port;
	}

	public int getMaxConnection()
	{
		return maxConnection;
	}

	public void setMaxConnection(int maxConnection)
	{
		this.maxConnection = maxConnection;
	}

	public boolean isStop()
	{
		return isStop;
	}

	public void setStop(boolean isStop)
	{
		this.isStop = isStop;
	}

	public void socketProcessThreadEnd(SocketProcessThread socketThread)
	{
		synchronized (socketProcessSync)
		{
			threadPool.remove(socketThread);
		}
	}

	/**
	 * 停止线程，关闭套接字
	 * 
	 * @Creation date:
	 * @author
	 */
	public void terminate()
	{
		isStop = true;
		try
		{
			socket.close();
			notifyAll();
		} catch (Exception e)
		{
		}

		try
		{
			Object[] sts = threadPool.toArray();
			for (int i = 0; i < sts.length; i++)
			{
				SocketProcessThread thread = (SocketProcessThread) sts[i];
				thread.terminate();
			}

		} catch (Exception e)
		{

		}
	}

	public CommFormat getCommFormat()
	{
		return commFormat;
	}

	public void setCommFormat(CommFormat commFormat)
	{
		this.commFormat = commFormat;
	}

	public void socketClosed(Socket socket)
	{
		// TODO Auto-generated method stub

	}

	public Map getServerMap()
	{
		return serverMap;
	}

	public void setServerMap(Map serverMap)
	{
		this.serverMap = serverMap;
	}

	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	public WebApplicationContext getWac()
	{
		return wac;
	}

	public void setWac(WebApplicationContext wac)
	{
		this.wac = wac;
	}
}
