package com.bw.movie.bean;

import java.util.List;

/**
 * author : zjh
 * e-mail : zjh@163.com
 * date   : 2019/5/23 10:54
 * desc   :  购票记录
 * version: 1.0
 */
public class MyFoodedBean {

    /**
     * result : [{"amount":5,"beginTime":"20:30:00","cinemaName":"青春光线电影院","createTime":1558085645000,"endTime":"22:28:00","id":2964,"movieName":"西虹市首富","orderId":"20190517173405135","price":0.13,"screeningHall":"1号厅","status":1,"userId":12580},{"amount":5,"beginTime":"20:30:00","cinemaName":"青春光线电影院","createTime":1558011299000,"endTime":"22:28:00","id":2953,"movieName":"西虹市首富","orderId":"20190516205458921","price":0.13,"screeningHall":"1号厅","status":1,"userId":12580}]
     * message : 请求成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * amount : 5
         * beginTime : 20:30:00
         * cinemaName : 青春光线电影院
         * createTime : 1558085645000
         * endTime : 22:28:00
         * id : 2964
         * movieName : 西虹市首富
         * orderId : 20190517173405135
         * price : 0.13
         * screeningHall : 1号厅
         * status : 1
         * userId : 12580
         */

        private int amount;
        private String beginTime;
        private String cinemaName;
        private long createTime;
        private String endTime;
        private int id;
        private String movieName;
        private String orderId;
        private double price;
        private String screeningHall;
        private int status;
        private int userId;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getCinemaName() {
            return cinemaName;
        }

        public void setCinemaName(String cinemaName) {
            this.cinemaName = cinemaName;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getScreeningHall() {
            return screeningHall;
        }

        public void setScreeningHall(String screeningHall) {
            this.screeningHall = screeningHall;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
