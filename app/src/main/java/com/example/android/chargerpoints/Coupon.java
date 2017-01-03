package com.example.android.chargerpoints;

/**
 * Created by 17svyas on 12/8/2016.
 */

public class Coupon {

    private String companyName;
    private String description;
    private String couponValue;
    private int pts;
    private int picImageResourceId;
    private int qrImageResourceId;

    public Coupon(String couponVal, String coName, String descrip, int points, int picId, int qrId){
        couponValue = couponVal;
        companyName = coName;
        description = descrip;
        pts = points;
        picImageResourceId = picId;
        qrImageResourceId = qrId;
    }

    public String getCompanyName(){ return companyName; }

    public String getDescription(){ return description; }

    public String getCouponValue(){ return couponValue; }

    public int getPts(){ return pts; }

    public int getPicImageResourceId(){ return picImageResourceId; }

    public int getQrImageResourceId(){ return qrImageResourceId; }


}
