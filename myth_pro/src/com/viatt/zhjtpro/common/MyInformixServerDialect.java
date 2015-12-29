package com.viatt.zhjtpro.common;

import org.hibernate.dialect.InformixDialect;
import org.hibernate.dialect.function.StandardSQLFunction;

public class MyInformixServerDialect extends InformixDialect {

	public MyInformixServerDialect() {
		super();
		
		registerFunction("substr", new StandardSQLFunction("substr"));
		//registerFunction( "joinstr1", new StandardSQLFunction(Hibernate.STRING) );
		
	}
	
}
 