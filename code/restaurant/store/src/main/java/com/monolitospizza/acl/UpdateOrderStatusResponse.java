package com.monolitospizza.acl;

/**
 * @author Matt Stine
 */
public class UpdateOrderStatusResponse {

    private Long orderId;
    private String orderStatus;
    private String result;

    public UpdateOrderStatusResponse(Long orderId, String orderStatus, String result) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.result = result;
    }

    public UpdateOrderStatusResponse() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
