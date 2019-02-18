package com.hamitao.aispeech.model;

import java.io.Serializable;
import java.util.List;

/**
 * @data on 2018/6/21 16:31
 * @describe:
 */

public class AISpeechParserDbData {

    private List<DbdataBean> dbdata;

    public List<DbdataBean> getDbdata() {
        return dbdata;
    }

    public void setDbdata(List<DbdataBean> dbdata) {
        this.dbdata = dbdata;
    }

    public static class DbdataBean implements Serializable {
        /**
         * imgSize :
         * artistPic : http://47.98.36.22/c/7713.jpg
         * id : 86453
         * img : http://47.98.36.22/c/7713.jpg
         * artist : 樊竹青
         * album : 经典动画歌曲大赏
         * lrcUrl :
         * title : 白龙马
         * albumPic : http://47.98.36.22/a/23408.jpg
         * size :
         * url : http://47.98.36.22/233296.mp3
         * duration : 0
         * lrcSize : 0
         */

        private String imgSize;
        private String artistPic;
        private String id;
        private String img;
        private String artist;
        private String album;
        private String lrcUrl;
        private String title;
        private String albumPic;
        private String size;
        private String url;
        private int duration;
        private int lrcSize;

        public String getImgSize() {
            return imgSize;
        }

        public void setImgSize(String imgSize) {
            this.imgSize = imgSize;
        }

        public String getArtistPic() {
            return artistPic;
        }

        public void setArtistPic(String artistPic) {
            this.artistPic = artistPic;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String album) {
            this.album = album;
        }

        public String getLrcUrl() {
            return lrcUrl;
        }

        public void setLrcUrl(String lrcUrl) {
            this.lrcUrl = lrcUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAlbumPic() {
            return albumPic;
        }

        public void setAlbumPic(String albumPic) {
            this.albumPic = albumPic;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getLrcSize() {
            return lrcSize;
        }

        public void setLrcSize(int lrcSize) {
            this.lrcSize = lrcSize;
        }
    }
}
