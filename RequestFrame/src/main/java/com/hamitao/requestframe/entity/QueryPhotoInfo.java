package com.hamitao.requestframe.entity;

import java.util.List;

/**
 * @data on 2018/6/11 11:45
 * @describe:
 */

public class QueryPhotoInfo {

    /**
     * responseDataObj : {"photos":[{"comment":"comment","ownerid":"sz10002_kidsrobot_52ffa9fd2c424967b697b98b42c0df3e","photoid":"photo_4753f61ad7514642afe422103aff64a9","photoname":"photoname","photourl":"photourl","updatetime":"2018-06-11 11:07:13"}]}
     * result : success
     */

    private ResponseDataObjBean responseDataObj;
    private String result;

    public ResponseDataObjBean getResponseDataObj() {
        return responseDataObj;
    }

    public void setResponseDataObj(ResponseDataObjBean responseDataObj) {
        this.responseDataObj = responseDataObj;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static class ResponseDataObjBean {
        private List<PhotosBean> photos;

        public List<PhotosBean> getPhotos() {
            return photos;
        }

        public void setPhotos(List<PhotosBean> photos) {
            this.photos = photos;
        }

        public static class PhotosBean {
            /**
             * comment : comment
             * ownerid : sz10002_kidsrobot_52ffa9fd2c424967b697b98b42c0df3e
             * photoid : photo_4753f61ad7514642afe422103aff64a9
             * photoname : photoname
             * photourl : photourl
             * updatetime : 2018-06-11 11:07:13
             */

            private String comment;
            private String ownerid;
            private String photoid;
            private String photoname;
            private String photourl;
            private String updatetime;

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getOwnerid() {
                return ownerid;
            }

            public void setOwnerid(String ownerid) {
                this.ownerid = ownerid;
            }

            public String getPhotoid() {
                return photoid;
            }

            public void setPhotoid(String photoid) {
                this.photoid = photoid;
            }

            public String getPhotoname() {
                return photoname;
            }

            public void setPhotoname(String photoname) {
                this.photoname = photoname;
            }

            public String getPhotourl() {
                return photourl;
            }

            public void setPhotourl(String photourl) {
                this.photourl = photourl;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }
        }
    }
}
