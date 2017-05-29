package com.calogardev.pizzarella.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductLinePK implements Serializable {

    private static final long serialVersionUID = -8265838884859178313L;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private Long productId;

    public ProductLinePK() {
	super();
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
	return "ProductLinePK [orderId=" + orderId + ", productId=" + productId + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
	result = prime * result + ((productId == null) ? 0 : productId.hashCode());
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ProductLinePK other = (ProductLinePK) obj;
	if (orderId == null) {
	    if (other.orderId != null)
		return false;
	} else if (!orderId.equals(other.orderId))
	    return false;
	if (productId == null) {
	    if (other.productId != null)
		return false;
	} else if (!productId.equals(other.productId))
	    return false;
	return true;
    }

}
