package biz.common.commbean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author 翟艳军
 * @since 20100512
 * @version 1.0
 * 该类为工具类
 * 用来实例化spring容器（spring上下文）
 * 采用单例模式
 */
public class SinSprContext {
	
	private static SinSprContext ssc; 
	
	//该路径本应该作为参数在配置文件中配置
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
