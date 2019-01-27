package com.fibaro.model;

public class ReturnPurchaseOrdersFake {
    private PurchaseOrders purchaseOrders;
    private int updated;

    public ReturnPurchaseOrdersFake() {
    }

    public PurchaseOrders getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(PurchaseOrders purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public int getUpdated() {
        return updated;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }
}
