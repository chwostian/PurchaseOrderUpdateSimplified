package com.fibaro.model;

import java.util.List;

public class PurchaseOrdersDTO {
    private List<PurchaseOrders> purchaseOrdersList;

    public List<PurchaseOrders> getPurchaseOrdersList() {
        return purchaseOrdersList;
    }

    public void setPurchaseOrdersList(List<PurchaseOrders> purchaseOrdersList) {
        this.purchaseOrdersList = purchaseOrdersList;
    }
}
