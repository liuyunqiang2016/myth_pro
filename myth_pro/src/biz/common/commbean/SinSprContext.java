package biz.common.commbean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author ���޾�
 * @since 20100512
 * @version 1.0
 * ����Ϊ������
 * ����ʵ����spring������spring�����ģ�
 * ���õ���ģʽ
 */
public class SinSprContext {
	
	private static SinSprContext ssc; 
	
	//��·����Ӧ����Ϊ�����������ļ�������
	private static ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	public static ApplicationContext getApp(){
		return app;
	}
	
	public static SinSprContext getInstance(){
		if(ssc==null){
			ssc = new SinSprContext();
			return ssc;
		}else{
			return ssc;
		}
		
	}
	
}
