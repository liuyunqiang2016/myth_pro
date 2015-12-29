package biz.common;

import java.io.Serializable;
import org.springframework.beans.BeanUtils;

public abstract class BaseBo implements Serializable
{

	public BaseBo()
	{
	}

	public BaseBo(Object obj)
	{
		BeanUtils.copyProperties(obj, this);
	}

} 
