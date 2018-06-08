package com.block.framework.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer cuid;

	private Integer muid;

	private Date ctime;

	private Date mtime;
	//实体附加信息，可在 http rpc 携带一些上下文信息
	private Object extend;

	
	
	public Object getExtend() {
		return extend;
	}

	public void setExtend(Object extend) {
		this.extend = extend;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public Integer getMuid() {
		return muid;
	}

	public void setMuid(Integer muid) {
		this.muid = muid;
	}

	public Integer getCuid() {
		return cuid;
	}

	public void setCuid(Integer cuid) {
		this.cuid = cuid;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.JSON_STYLE);
	}

	@SuppressWarnings("rawtypes")
	public Map toMap() {
		return new BeanMap(this);
	}

	// /**
	// * 金额，Integer类型转Float类型
	// * @param Integer
	// * @return
	// */
	// protected Float int2Float(Integer integer) {
	// if (integer != null) {
	// return (float)integer / 100;
	// } else {
	// return null;
	// }
	// }
	//
	// /**
	// * 金额，Float类型转Integer类型
	// * @param Float
	// * @return
	// */
	// protected Integer float2int(Float f) {
	// if (f != null) {
	// return (int) (f * 100);
	// } else {
	// return null;
	// }
	// }
}
