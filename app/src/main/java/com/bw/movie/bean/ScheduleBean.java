package com.bw.movie.bean;

import java.util.List;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/21 14:22
 * @Description：描述信息
 */
public class ScheduleBean {

    /**
     * result : [{"beginTime":"16:20","duration":"118分钟","endTime":"18:18","id":5,"price":0.13,"screeningHall":"9号厅 （天域全感音厅）","seatsTotal":273,"seatsUseCount":148,"status":1},{"beginTime":"17:05","duration":"118分钟","endTime":"19:03","id":6,"price":0.13,"screeningHall":"8号厅 （临境音厅）","seatsTotal":286,"seatsUseCount":18,"status":1},{"beginTime":"17:40","duration":"118分钟","endTime":"19:05","id":36,"price":0.13,"screeningHall":"6号厅","seatsTotal":21,"seatsUseCount":10,"status":1}]
     * message : 查询成功
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
         * beginTime : 16:20
         * duration : 118分钟
         * endTime : 18:18
         * id : 5
         * price : 0.13
         * screeningHall : 9号厅 （天域全感音厅）
         * seatsTotal : 273
         * seatsUseCount : 148
         * status : 1
         *
         */

        private String beginTime;
        private String duration;
        private String endTime;
        private int id;
        private double price;
        private String screeningHall;
        private int seatsTotal;
        private int seatsUseCount;
        private int status;

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
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

        public int getSeatsTotal() {
            return seatsTotal;
        }

        public void setSeatsTotal(int seatsTotal) {
            this.seatsTotal = seatsTotal;
        }

        public int getSeatsUseCount() {
            return seatsUseCount;
        }

        public void setSeatsUseCount(int seatsUseCount) {
            this.seatsUseCount = seatsUseCount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
