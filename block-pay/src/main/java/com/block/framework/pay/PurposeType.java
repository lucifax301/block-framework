package com.block.framework.pay;

public enum PurposeType {
	DEFAULT(1, "支付费用");
	int type;
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
    private PurposeType(int type, String desc)
    {
        this.type = type;
        this.desc =desc;
    }
    
    
}
