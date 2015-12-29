package com.viatt.zhjtpro.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Smsclient {
	/*
	 * IP port 
	 * sendbuf
	 * rcvLen 
	 */
	public static String SmsClient(String IP, String port, String sndbuf, int rcvLen)
			throws UnknownHostException, IOException {
		Socket aClient = null;
		String recvbuf = null;
		// aClient = new Socket(ip, Integer.parseInt(port));
//System.out.println("IP------>" + IP);
		aClient = new Socket(IP, Integer.parseInt(port));
		try {
			InputStream in = aClient.getInputStream();
			OutputStream out = aClient.getOutputStream();
			int off = 0;
			System.out.println("send buf:["+sndbuf+"]");
			out.write(sndbuf.getBytes());
			byte[] inlen = new byte[rcvLen];
			while (off < rcvLen) {
				off = off + in.read(inlen, off, rcvLen - off);
			}
			String strlen = new String(inlen);
			System.out.println("recv buff len:["+strlen+"]");
			int ilen = Integer.parseInt(strlen);
			byte[] recv = new byte[ilen];
			off = 0;
			while (off < ilen) {
				off = off + in.read(recv, off, ilen - off);
			}
			recvbuf = new String(recv);
			System.out.println("recv buff content:["+recvbuf+"]");
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			aClient.close();
		}
		return recvbuf;
	}
}
