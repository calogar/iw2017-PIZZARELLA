package com.calogardev.pizzarella.dto;

import java.io.Serializable;

public class ProductLinePKDto implements Serializable {

    private static final long serialVersionUID = 6585072320806774988L;

    private Long orderId;

    private Long productId;

    public ProductLinePKDto() {
	super();
    }

    public ProductLinePKDto(Long orderId, Long productId) {
	super();
	this.orderId = orderId;
	this.productId = productId;
    }

    /**
     * @return the orderId
     */
    public Long getOrderId() {
	return orderId;
    }

    /**
     * @param orderId
     *            the orderId to set
     */
    public void setOrderId(Long orderId) {
	this.orderId = orderId;
    }

    /**
     * @return the productId
     */
    public Long getProductId() {
	return productId;
    }

    /**
     * @param productId
     *            the productId to set
     */
    public void setProductId(Long productId) {
	this.productId = productId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ProductLinePKDto [orderId=" + orderId + ", productId=" + productId + "]";
    }

}
