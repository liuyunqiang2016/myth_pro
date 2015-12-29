package biz.common.net.tcp;

import java.io.IOException;


/**
 *  ͨѶЭ�鴦��ӿڣ�ͨ�ų����������� readPackage(InputStream)������������
 *  ��ȡ���ݰ��������ͨѶЭ��Ҳ��Ҫ������ͬʱͨѶ������������
 *  byte[] wrapeMessagePackage(byte[]) ���Է������ݰ����д�����������ͨ��Э�鱨ͷ����
 * 
 */
public interface CommProcessor {

	
/**
 * ���������ж���һ����׼���ݰ�������Ҫ����ͨ��Э�顣
 *
 * @Creation date: (2002-4-30 10:00:55) 
 * @author  ZhongMingChang
 * @return byte[]
 * @param in java.io.InputStream
 */
byte[] readPackage(java.io.InputStream in) throws IOException, Exception;


/**
 * ����ͨ��Э������ݰ����д���������ͨ�ű���ͷ
 * 
 * @Creation date: (2002-5-24 9:16:20) 
 * @author  ZhongMingChang
 * @return byte[]
 * @param msg byte[]
 */
byte[] wrapMessagePackage(byte[] msg) throws IOException, Exception;
}
