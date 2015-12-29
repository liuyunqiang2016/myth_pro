package biz.common.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

import org.springframework.web.context.WebApplicationContext;

import biz.common.BGMLogger;
import biz.common.IModel;
import biz.common.format.CommFormat;

/**
 * 
 * SocketProcessThread.java<br>
 * 
 * 
 */
public class SocketProcessThread implements Runnable {

	private boolean keepAlive = false;

	protected TCPIPServiceServer parent;

	private CommProcessor commProcessor;

	private CommFormat commFormat;

	private boolean isStop = false;

	private Socket socket = null;

	private InputStream in = null;

	private OutputStream out = null;

	private int id;

	private Thread thread = null;

	/**
	 * 是否为线程池的线程
	 */
	private boolean pooledThread = false;

	private boolean beFree = true;

	private SocketListener socketListener = null;

	public SocketProcessThread() {
		super();
	}

	public SocketProcessThread(Socket socket, boolean keepAlive) {
		super();
		this.socket = socket;
		this.keepAlive = keepAlive;
	}

	public void startUp() {
		Thread aThread = new Thread(this);
		aThread.setName("TCP/IP SocketProcessThread");
		aThread.start();
		thread = aThread;
	}

	public void setSocketListener(SocketListener listener) {
		socketListener = listener;
	}

	public void run() {
		try {
			in = socket.getInputStream();
			out = socket.getOutputStream();
		} catch (java.io.IOException e) {
			BGMLogger.LogError("failed to get in/out from socket!"
					+ e.getMessage());
			return;
		}
		while (!isStop) {

			try {
				byte[] readMsg = commProcessor.readPackage(in);
				if (readMsg != null) {
					if (commFormat != null) {
						Map opMap = (Map) this.parent.getServerMap().get(
								this.parent.getServiceName().trim());
						String strKey = commFormat.getTransCode(readMsg);
						TCPIPServerModel serverModel = (TCPIPServerModel) opMap
								.get(strKey);
						if (serverModel == null) {
							BGMLogger
									.LogError("SocketProcess Thread: transcode["
											+ strKey + "]'s server was null! ");
							break;
						}
						IModel inputModel = commFormat.newPackageReceived(
								readMsg, serverModel.getInputClass(),
								serverModel.getInputList());
						// BGMLogger.LogInfo("execute spring function["
						// + serverModel.getOpName() + "]");
						// BGMLogger
						// .LogInfo("execute spring function input Object ["
						// + inputModel + "] ");
						IModel outModel = (IModel) execFunction(inputModel,
								serverModel, this.parent.getWac());
						// BGMLogger.LogInfo("execute spring function ["
						// + serverModel.getOpName() + "] end");
						// BGMLogger
						// .LogInfo("execute spring function return Object ["
						// + outModel.getClass().getName() + "]");
						byte[] sendMsg = commFormat.getPackageSend(outModel,
								serverModel.getOutPutClass().trim(),
								serverModel.getOutputList());
						this.send(commProcessor.wrapMessagePackage(sendMsg),
								socket);
					}
				} else { // encounter fatal error!! should close the
					// connection
					if (socketListener != null)
						socketListener.socketClosed(socket);
					BGMLogger
							.LogError("SocketProcess Thread: readMsg was null! ");
					break;
				}
			} catch (IOException ie) {
				if (socketListener != null)
					socketListener.socketClosed(socket);
				BGMLogger.LogError("SocketProcess Thread IOException: "
						+ ie.getMessage());
				break;
			} catch (Exception e) {
				if (socketListener != null)
					socketListener.socketClosed(socket);
				BGMLogger.LogError("SocketProcess Thread Exception: " + e);
				break;
			} finally {
				// BGMLogger.LogInfo("");
				if (!keepAlive) { // close the socket with none keep alive
					// connection
					if (socketListener != null)
						socketListener.socketClosed(socket);
					BGMLogger.LogInfo("Not keep alive, socket closed. ");

					if (!pooledThread) {
						this.beFree = true;
						break;
					}

					closeAll();
					if (this.parent != null)
						parent.socketProcessThreadEnd(this);

					this.beFree = true;
					synchronized (this) {
						try {
							wait();
						} catch (Exception e) {

						}
					}
				} else {
					this.beFree = true;
				}
			}

		}

		closeAll();
		parent.socketProcessThreadEnd(this);
	}

	private Object execFunction(IModel obj, TCPIPServerModel op,
			WebApplicationContext swac) throws Exception {
		Object objDao = swac.getBean(op.getBeanName().trim());
		// BGMLogger.LogInfo("spring service is " + objDao);
		Class objClass = objDao.getClass();
		// BGMLogger.LogInfo("spring service class is " + objClass);
		String strMethodName = op.getOpName();
		// BGMLogger.LogInfo("spring service MethodName is " + strMethodName);
		// BGMLogger.LogInfo("spring service obj is " + obj);
		Method metod = objClass.getMethod(strMethodName,
				new Class[] { IModel.class });
		// BGMLogger.LogInfo("spring service metod is " + metod);
		return metod.invoke(objDao, new Object[] { obj });
		// return null;
	}

	public void send(byte[] msg, Socket socket) throws IOException, Exception {
		OutputStream out = socket.getOutputStream();
		// byte[] sendMsg = this.commProcessor.wrapMessagePackage( msg );
		out.write(msg);
	}

	private void closeAll() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (Exception e) {
			BGMLogger.LogError("ReadThread method run() ERROR! "
					+ e.getMessage());
		}
		in = null;
		out = null;
		socket = null;

	}

	/**
	 * 对Socket 请求进行处理
	 * 
	 * @param socket
	 */
	public void processSocket(Socket socket) {

		try {
			this.socket = socket;
			in = socket.getInputStream();
			out = socket.getOutputStream();
			synchronized (this) {
				notify();
			}

		} catch (java.io.IOException e) {
			BGMLogger.LogError("ReadThread method run() ERROR! "
					+ e.getMessage());
			return;
		}

	}

	/**
	 * 设置线程名
	 * 
	 * @Creation date: (2002-4-30 13:37:30)
	 * @author ZhongMingChang
	 * @param name
	 *            java.lang.String
	 */
	public void setThreadName(String name) {
		if (thread != null)
			thread.setName(name);
	}

	/**
	 * 停止线程，关闭套接字
	 * 
	 * @Creation date: (2002-4-30 10:46:01)
	 * @author ZhongMingChang
	 */
	public void terminate() {
		try {
			isStop = true;
			if (socket != null)
				socket.close();
		}

		catch (Exception e) {
			BGMLogger.LogError("ReadThread method stop() ERROR! "
					+ e.getMessage());
		}
		synchronized (this) {
			notify();
		}

	}

	public boolean isBeFree() {
		return beFree;
	}

	public void setBeFree(boolean beFree) {
		this.beFree = beFree;
	}

	public boolean isKeepAlive() {
		return keepAlive;
	}

	public void setKeepAlive(boolean keepAlive) {
		this.keepAlive = keepAlive;
	}

	public boolean isPooledThread() {
		return pooledThread;
	}

	public void setPooledThread(boolean pooledThread) {
		this.pooledThread = pooledThread;
	}

	public CommProcessor getCommProcessor() {
		return commProcessor;
	}

	public void setCommProcessor(CommProcessor commProcessor) {
		this.commProcessor = commProcessor;
	}

	public CommFormat getCommFormat() {
		return commFormat;
	}

	public void setCommFormat(CommFormat commFormat) {
		this.commFormat = commFormat;
	}
}
