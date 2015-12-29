
package biz.common.net.tcp;

import java.io.IOException;
import java.io.InputStream;

import biz.common.BGMLogger;


/**
 * 
 * BizCommProcessor.java<br>
 *
 *Biz提供的TCP/IP通信报文处理类
 */
public class BizCommProcessor implements CommProcessor {

	int lengthHeadLen = 4;
	int lengthHeadType = 0;	//0: ascii, 1: bin
	
	public BizCommProcessor() {
		super();
	}
	
	/**
	 * 从输入流中读取数据包，并进行通讯协议处理。
	 * @param in java.io.InputStream tcp/ip输出流
	 * @return byte[]
	 * @throws IOException
	 */
	public byte[] readPackage(InputStream in) throws IOException, Exception
	{
		byte[] lenHeadBuf = new byte[lengthHeadLen];
		int off = 0;
		//获得包头
		while (off < lengthHeadLen) 
		{
			off = off + in.read(lenHeadBuf, off, lengthHeadLen - off);
			if (off < 0) 
			{			
				throw new Exception("Socket was closed! while reading!");
			}
		}
		
		BGMLogger.LogInfo("BizCommprocess read in head:" + new String(lenHeadBuf ));
		
		int contentLength = 0;
		if(lengthHeadType == 0  )
		{
			contentLength = Integer.parseInt( new String( lenHeadBuf ));
		}
		else //bin
		{
			for( int i=lengthHeadLen-1; i>=0; i-- )
			{
				int value = (int)(lenHeadBuf[i] & 0xff);
				contentLength = contentLength * 256 + value;
			}
		}
		
		
		if( contentLength == 0 )
			throw new Exception("Invalid TCPIP req package protocol!");
			
		//获得包体内容开始
		off = 0;
		byte[] contentBuf = new byte[contentLength];
		while (off < contentLength) 
		{

			int len = in.read(contentBuf, off, contentLength - off);
			if (len <= 0) 
			{ 
				break;
			}
			off = off + len;
		}
		BGMLogger.LogInfo("Biz CommProcessor Read in package As:" + new String(contentBuf ));
		return contentBuf; 
	}

	/**
	 * 根据通信协议对数据包进行打包，如加入通信报文头
	 *
	 * @param msg byte[]
	 * @return byte[]
	 */
	public byte[] wrapMessagePackage(byte[] msg) 
	{
		BGMLogger.LogInfo("Biz CommProcessor send to package As:" + new String(msg ));
		if( msg == null )
			return msg;
		
		int length = msg.length;
		byte[] buf = new byte[length + this.lengthHeadLen ];
		
		System.arraycopy(msg, 0, buf, lengthHeadLen, length);
		
		if( this.lengthHeadType == 0 )
		{
			String lenValue = String.valueOf( length );
			for(int i=0; i<lengthHeadLen; i++ )
				buf[i] = '0';
			int idx = 0;
			for( int i=lengthHeadLen - lenValue.length(); i<lengthHeadLen; i++ )
			{
				buf[i] = (byte)lenValue.charAt(idx++);
			}
//			return buf;
		}
		else 	//bin
		{
			int l = length;
			for( int i=0; i<lengthHeadLen; i++ )
			{
				buf[i] = (byte)(l%256);
				l = l / 256;
			}
//			return buf;
		}
		
		BGMLogger.LogInfo("Biz CommProcessor send to package As:" + new String(buf ));
		return buf;
		
	}
}
