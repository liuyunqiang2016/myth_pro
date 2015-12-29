import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Smsclient {
	private int port = 37575;
	//private String ip = "192.168.1.116";
	private String ip = "127.0.0.1";

	public static void main(String[] args) {
		Smsclient test = new Smsclient();
		try {
//			String I0002 = "<xml><msgId>I0002</msgId><serId>7</serId><serType>s001</serType><serTitle>≤‚ ‘</serTitle><context>≤‚ ‘ƒ⁄»›</context><creTime>201312201101</creTime><creAuthor>test</creAuthor><creTel>13333333</creTel><equId>558</equId></xml>";
//			System.out.println((I0002.getBytes().length));
//			String strSend = "000223" + I0002;
//			String I0004 = "<xml><msgId>I0004</msgId><equId>6</equId><softid>12</softid><softVis>≤‚ ‘</softVis><equtype>d</equtype></xml>";
//			System.out.println((I0004.getBytes().length));
//			String strSend = "000109" + I0004;
//			String I0005 = "<xml><msgId>I0005</msgId><equId>6</equId><roomID>1</roomID><IpAdd>≤‚ ‘</IpAdd><accessID>≤‚ ‘ƒ⁄»›</accessID><equType>1</equType><equState>0</equState><commID>1</commID><areaID>1</areaID><unitID>1</unitID><buldID>2</buldID></xml>";
			String I0010 = "<xml><msgId>I0010</msgId><equId>010001</equId></xml>";
			System.out.println((I0010.getBytes().length));
			String strSend = "000227" + I0010;
			System.out.println(strSend);
			test.SmsClient(strSend);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * øÕªß∂À∑¢ÀÕ≥Ã–Ú
	 */
	public String SmsClient(String sndbuf) throws UnknownHostException,
			IOException {
		Socket aClient = null;
		String recvbuf = null;
		aClient = new Socket(ip, port);
		try {
			InputStream in = aClient.getInputStream();
			OutputStream out = aClient.getOutputStream();
			int off = 0;
			out.write(sndbuf.getBytes());
			byte[] inlen = new byte[6];
			while (off < 6) {
				off = off + in.read(inlen, off, 6 - off);
			}
			String strlen = new String(inlen);
			int ilen = Integer.parseInt(strlen) + 1;
			System.out.println(ilen);
			byte[] recv = new byte[ilen];
			off = 0;
			while (off < ilen) {
				off = off + in.read(recv, off, ilen - off);
			}

			String derecv = new String(recv);
			System.out.println(derecv);
//			derecv = derecv.substring(0, derecv.length());
//			System.out.println(derecv);
//			CryptionData DecodeStr = new CryptionData();
//			derecv = DecodeStr.DecryptionStringData(derecv);
//			System.out.println(derecv);
//			hYk83tIhQUZR/2BgqA05Ew==
			// String tmp = URLDecoder.decode(derecv,"UTF-8");
			// recvbuf = String.format("%08d", tmp.getBytes().length)+ tmp;
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("==============="+e.getMessage());
		} finally {
			aClient.close();
		}
		return recvbuf;
	}
}
