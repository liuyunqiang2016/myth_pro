package com.viatt.zhjtpro.bo;

import java.io.Serializable;
import org.springframework.beans.BeanUtils;

public abstract class BaseBo implements Serializable
{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6326280018013172553L;

	public BaseBo()
	{
	}

	public BaseBo(Object obj)
	{
		BeanUtils.copyProperties(obj, this);
	}

} 
