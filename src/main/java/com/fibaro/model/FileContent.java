package com.fibaro.model;

import java.util.Date;

public class FileContent {
    private String SUPPLIER;
    private Long CODE;
    private String PO;
    private Integer LINE;
    private String SKU;
    private Date CONFIRMED_DELIVERY_DATE;
    private Integer REMAINING_QUANTITY;

    public FileContent(String SUPPLIER, Long CODE, String PO, Integer LINE, String SKU, Date CONFIRMED_DELIVERY_DATE, Integer REMAINING_QUANTITY) {
        this.SUPPLIER = SUPPLIER;
        this.CODE = CODE;
        this.PO = PO;
        this.LINE = LINE;
        this.SKU = SKU;
        this.CONFIRMED_DELIVERY_DATE = CONFIRMED_DELIVERY_DATE;
        this.REMAINING_QUANTITY = REMAINING_QUANTITY;
    }

    public FileContent() {
    }

    public String getSUPPLIER() {
        return SUPPLIER;
    }

    public void setSUPPLIER(String SUPPLIER) {
        this.SUPPLIER = SUPPLIER;
    }

    public Long getCODE() {
        return CODE;
    }

    public void setCODE(Long CODE) {
        this.CODE = CODE;
    }

    public String getPO() {
        return PO;
    }

    public void setPO(String PO) {
        this.PO = PO;
    }

    public Integer getLINE() {
        return LINE;
    }

    public void setLINE(Integer LINE) {
        this.LINE = LINE;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public Date getCONFIRMED_DELIVERY_DATE() {
        return CONFIRMED_DELIVERY_DATE;
    }

    public void setCONFIRMED_DELIVERY_DATE(Date CONFIRMED_DELIVERY_DATE) {
        this.CONFIRMED_DELIVERY_DATE = CONFIRMED_DELIVERY_DATE;
    }

    public Number getREMAINING_QUANTITY() {
        return REMAINING_QUANTITY;
    }

    public void setREMAINING_QUANTITY(Integer REMAINING_QUANTITY) {
        this.REMAINING_QUANTITY = REMAINING_QUANTITY;
    }

    @Override
    public String toString() {
        return "FileContent{" +
                "SUPPLIER='" + SUPPLIER + '\'' +
                ", CODE=" + CODE +
                ", PO='" + PO + '\'' +
                ", LINE=" + LINE +
                ", SKU='" + SKU + '\'' +
                ", CONFIRMED_DELIVERY_DATE=" + CONFIRMED_DELIVERY_DATE +
                ", REMAINING_QUANTITY=" + REMAINING_QUANTITY +
                '}';
    }
}
