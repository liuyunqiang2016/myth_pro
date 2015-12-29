package biz.common;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.InformixDialect;
import org.hibernate.dialect.function.StandardSQLFunction;

public class MyInformixServerDialect extends InformixDialect {

	public MyInformixServerDialect() {
		super();
		
		registerFunction("substr", new StandardSQLFunction("substr"));
		registerHibernateType(Types.DECIMAL, Hibernate.BIG_DECIMAL.getName());  
		//registerFunction( "joinstr1", new StandardSQLFunction(Hibernate.STRING) );
		
	}
	
}
 