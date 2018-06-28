package com.block.framework.pay;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PayVo implements Serializable{

	private static final long serialVersionUID = -4118061263751669421L;

    //用户ID
    private long userId;
    //用户类型
    private int userType;
    //支付数值(经过计算后最终需要付款的钱)
    private int payValue;
    //支付渠道
    private String payWay;
    //支付内部订单id
    private String payOrderId;
    //支付目的
    private PurposeType payPurpose;
    //优惠券ID
    private long couponId;
    //已经优惠的金额
    private int discountMoney;
    
    private PayClientVer clientVer;
    //备注信息
    private String remark;
    
    //存取每种支付的额外信息
    private Map<String,String> ext = new HashMap(4);
    
    /**
     * @param userId
     * @param userType
     * @param payValue
     * @param payWay
     * @param payOrderId
     * @param payPurpose
     * @param couponId
     */
    public PayVo(long userId, int userType, int payValue, String payWay, String payOrderId, int payPurpose,
            long couponId, int clientVer, String remark) {
	   super();
	   this.userId = userId;
	   this.userType = userType;
	   this.payValue = payValue;
	   this.payWay = payWay;
	   this.payOrderId = payOrderId;
	   setClientVer(clientVer);
	   setPayPurpose(payPurpose);
	   this.couponId = couponId;
	   this.remark = remark;
    }
    
    public PayVo(long userId, int userType, int payValue, String payWay, String payOrderId, int payPurpose,
                 long couponId, int clientVer, String remark,String insuranceId) {
        super();
        this.userId = userId;
        this.userType = userType;
        this.payValue = payValue;
        this.payWay = payWay;
        this.payOrderId = payOrderId;
        setClientVer(clientVer);
        setPayPurpose(payPurpose);
        this.couponId = couponId;
        this.remark = remark;
        
    }

    /**
     * @return the clientVer
     */
    public PayClientVer getClientVer() {
        return clientVer;
    }

    /**
     * @param clientVer the clientVer to set
     */
    public void setClientVer(int clientVer) {
        this.clientVer = PayClientVer.parse(clientVer);
    }
    
    public int getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(int discountMoney) {
        this.discountMoney = discountMoney;
    }

    /**
     * @return the userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * @return the userType
     */
    public int getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }

    /**
     * @return the payValue
     */
    public int getPayValue() {
        return payValue;
    }

    /**
     * @param payValue the payValue to set
     */
    public void setPayValue(int payValue) {
        this.payValue = payValue;
    }

    /**
     * @return the payWay
     */
    public String getPayWay() {
        return payWay;
    }

    /**
     * @param payWay the payWay to set
     */
    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    /**
     * @return the payOrderId
     */
    public String getPayOrderId() {
        return payOrderId;
    }

    /**
     * @param payOrderId the payOrderId to set
     */
    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }
   

    /**
     * @return the couponId
     */
    public long getCouponId() {
        return couponId;
    }

    /**
     * @param couponId the couponId to set
     */
    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    
    /**
     * @return the mark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param mark the mark to set
     */
    public void setRemark(String mark) {
        this.remark = mark;
    }
    
    

	
	public PurposeType getPayPurpose() {
		return payPurpose;
	}

	public void setPayPurpose(int payPurpose) {
        this.payPurpose = PurposeType.parse(payPurpose);
    }
//	public void setPayPurpose(PurposeType payPurpose) {
//		this.payPurpose = payPurpose;
//	}

	public void setClientVer(PayClientVer clientVer) {
		this.clientVer = clientVer;
	}
	
	

	public Map<String, String> getExt() {
		return ext;
	}

	public void setExt(Map<String, String> ext) {
		this.ext = ext;
	}

	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PayVo [userId=" + userId + ", userType=" + userType + ", payValue=" + payValue + ", payWay=" + payWay
                + ", payOrderId=" + payOrderId + ", payPurpose=" + payPurpose+", clientVer=" + clientVer + ", couponId=" + couponId
                +  ", remark=" + remark + "]";
    }
}
