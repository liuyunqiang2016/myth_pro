import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Test {

	public static void main(String[] argv){
		
		String op="19700107093309";
		try {
			Date date = new SimpleDateFormat("yyyyMMddHHmmss").parse(op);
			System.out.println(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
