package com.block.framework.pay;

public enum PayClientVer {

	WX_APP(1, "微信APP支付"),
    WX_WEB(2, "微信公众号支付"),
    WX_AAPP(2, "微信小程序支付") ; 

    int type;
    String desc;
    
    /**
     * 
     */
    PayClientVer(int type, String desc)
    {
        this.type = type;
        this.desc = desc;
    }

    /**
     * @return the type
     */
    public int getType()
    {
        return type;
    }

    /**
     * @param type
     *            the type to set
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
     * @param desc
     *            the desc to set
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }
    
    public static PayClientVer parse(int type)
    {
        for (PayClientVer payClientVer : PayClientVer.values())
        {
            if (payClientVer.getType() == type)
            {
                return payClientVer;
            }
        }
        return null;
    }
}
