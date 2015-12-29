package biz.common.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommenUtil {
	
	
	public static String dateFormat(String pattern,Date sourceDate){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(sourceDate);
	}

}
