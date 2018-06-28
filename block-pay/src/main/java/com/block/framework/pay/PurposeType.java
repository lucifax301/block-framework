package com.block.framework.pay;

import java.io.Serializable;

public class PurposeType implements Serializable{
	/**
	 * 业务自定义支付目的类型
	 */
	int type;
	/**
	 * 业务自定义支付详细信息
	 */
    String desc;
    
    /**
     * @return the type
     */
    public int getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type)
    {
        this.type = type;
    }

    /**
     * @return the desc
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * 
     */
    public PurposeType(int type, String desc)
    {
        this.type = type;
        this.desc =desc;
    }
    
    public PurposeType(){
    	
    }
    
    public static PurposeType parse(int type){
    	return new PurposeType(type,"");
    }
}
