package biz.common.net.tcpClient;

import java.io.IOException;
import java.io.InputStream;

import biz.common.BGMLogger;
import biz.common.net.tcp.CommProcessor;

public class TCPClientCommProcessor implements CommProcessor
{
	int lengthHeadLen = 6;

	int lengthHeadType = 1; // 0: ascii, 1: bin

	public TCPClientCommProcessor()
	{
		super();
	}

	public byte[] readPackage(InputStream in) throws IOException, Exception
	{
		byte[] lenHeadBuf = new byte[lengthHeadLen];
		int off = 0;
		// 获得包头
		while (off < lengthHeadLen)
		{
			off = off + in.read(lenHeadBuf, off, lengthHeadLen - off);
			if (off < 0)
			{
				throw new Exception("Socket was closed! while reading!");
			}
		}

		BGMLogger.LogInfo("TCPClientCommProcessor read in head:"
				+ lenHeadBuf.toString());

		int contentLength = 0;
		
		contentLength = Integer.parseInt(new String(lenHeadBuf));

		BGMLogger.LogInfo("TCPIP req package length:" + contentLength);

		if (contentLength == 0 )
			throw new Exception("Invalid TCPIP req package protocol!");
		// 获得包体内容开始
		off = 0;
		int idx = contentLength - 2;
		byte[] contentBuf = new byte[idx];
		while (off < idx)
		{
			int len = in.read(contentBuf, off, idx - off);
			if (len <= 0)
			{
				break;
			}
			off = off + len;
		}
		BGMLogger.LogInfo("IppCommProcessor Read in package As:"
				+ new String(contentBuf, "GBK"));
		return contentBuf;
		// return null;
	}

	public byte[] wrapMessagePackage(byte[] msg) throws Exception
	{
		BGMLogger.LogInfo("IppCommProcessor send to package As:"
				+ new String(msg, "GBK"));
		if (msg == null)
			return msg;

		int length = msg.length;
		if (this.lengthHeadType == 0)
		{
			String lenValue = String.valueOf(length);
			for (int i = 0; i < lengthHeadLen; i++)
				msg[i] = '0';
			int idx = 0;
			for (int i = lengthHeadLen - lenValue.length(); i < lengthHeadLen; i++)
			{
				msg[i] = (byte) lenValue.charAt(idx++);
			}
			// return buf;
		} else
		// bin
		{
			int l = length;
			for (int i = lengthHeadLen - 1; i >= 0; i--)
			{
				msg[i] = (byte) (l % 256);
				l = l / 256;
			}
			// return buf;
		}
		BGMLogger.LogInfo("IppCommProcessor send to package As:"
				+ new String(msg, "GBK"));
		return msg;
	}

}
