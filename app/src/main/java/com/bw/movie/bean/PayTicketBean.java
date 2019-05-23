package com.bw.movie.bean;

/**
 * author : zjh
 * e-mail : zjh@163.com
 * date   : 2019/5/17 10:32
 * desc   :
 * version: 1.0
 */
public class PayTicketBean {
    /**
     * appId : wxb3852e6a6b7d9516
     * message : 支付成功
     * nonceStr : ZOMeAQ556lFcnWEU
     * packageValue : Sign=WXPay
     * partnerId : 1510865081
     * prepayId : wx23101935566088cd81ca73a52849721896
     * sign : 2A10E3760961CD71E71FE7ED20FD151F
     * status : 0000
     * timeStamp : 1558577968
     */

    private String appId;
    private String message;
    private String nonceStr;
    private String packageValue;
    private String partnerId;
    private String prepayId;
    private String sign;
    private String status;
    private String timeStamp;

    /**
     * result : alipay_sdk=alipay-sdk-java-3.1.0&app_id=2018080760951276&biz_content=%7B%22out_trade_no%22%3A%2220190523101210965%22%2C%22subject%22%3A%22%E5%85%AB%E7%BB%B4%E7%A7%BB%E5%8A%A8%E9%80%9A%E4%BF%A1%E5%AD%A6%E9%99%A2-%E7%BB%B4%E5%BA%A6%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.13%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fmobile.bwstudent.com%2FpayApiTest%2FaliPay%2FmovieNotification&sign=22kE3nA2oe1lijKAxz%2F5IwIlySMSzDrSAl586T%2FWEfMJcyrarM8Dj8n9RRC69UBEO8Qlfgo6uRc%2BzX0c15tINGG7eICjqOFNXV1vqygSbIbxvWncgR4y5mPQEyeTE3ikfIOWIBvupF7U6qUnknPml0h6Zci2QoT%2Bv8V7gSMTZXd5DyMyB71088g0AIGcnu8yIl2kH2yeZloxyKy5CAHhvO%2FduKHrwbG9pRl9dCVaYvhz1oFotloGpKK%2Bm0Is4M6jpIhgX41VFdP0BtV2tguWIS79fAu6bPVqOE3dsa4BbrpaXE4yFi4Rhs8YnCvscKhogJ%2F0XIt2riWxcxPEWoYd3Q%3D%3D&sign_type=RSA2&timestamp=2019-05-23+10%3A13%3A13&version=1.0
     */
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
