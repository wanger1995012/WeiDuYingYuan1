package com.bw.movie.bean;

import java.util.List;

/**
 * @Author：lenovo
 * @E-mail： 1003195060@163.com
 * @Date：2019/5/17 10:45
 * @Description：描述信息
 */
public class FindAllMovieCommentBean {

    /**
     * result : [{"commentContent":"电影好看","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-05-07/20190507122412.jpg","commentId":868,"commentTime":1557203054000,"commentUserId":12643,"commentUserName":"你是谁","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":866,"commentTime":1557202550000,"commentUserId":12772,"commentUserName":"橙熟","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-05-07/20190507093041.jpg","commentId":863,"commentTime":1557199076000,"commentUserId":12733,"commentUserName":"柚稚","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-05-07/20190507093041.jpg","commentId":861,"commentTime":1557198801000,"commentUserId":12733,"commentUserName":"柚稚","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-05-07/20190507122412.jpg","commentId":860,"commentTime":1557198562000,"commentUserId":12643,"commentUserName":"你是谁","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-05-07/20190507093041.jpg","commentId":858,"commentTime":1557198555000,"commentUserId":12733,"commentUserName":"柚稚","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-05-07/20190507111930.jpg","commentId":857,"commentTime":1557198435000,"commentUserId":12767,"commentUserName":"��żż��","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-05-07/20190507111930.jpg","commentId":856,"commentTime":1557198418000,"commentUserId":12767,"commentUserName":"��żż��","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-05-07/20190507111930.jpg","commentId":854,"commentTime":1557198417000,"commentUserId":12767,"commentUserName":"��żż��","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-05-07/20190507111930.jpg","commentId":855,"commentTime":1557198417000,"commentUserId":12767,"commentUserName":"��żż��","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0}]
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
         * commentContent : 电影好看
         * commentHeadPic : http://mobile.bwstudent.com/images/movie/head_pic/2019-05-07/20190507122412.jpg
         * commentId : 868
         * commentTime : 1557203054000
         * commentUserId : 12643
         * commentUserName : 你是谁
         * greatNum : 0
         * hotComment : 0
         * isGreat : 0
         * replyNum : 0
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int hotComment;
        private int isGreat;
        private int replyNum;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getHotComment() {
            return hotComment;
        }

        public void setHotComment(int hotComment) {
            this.hotComment = hotComment;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }
    }
}
