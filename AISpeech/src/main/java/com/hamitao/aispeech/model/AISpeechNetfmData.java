package com.hamitao.aispeech.model;

import java.util.List;

public class AISpeechNetfmData {

    private List<DbdataBean> dbdata;

    public List<DbdataBean> getDbdata() {
        return dbdata;
    }

    public void setDbdata(List<DbdataBean> dbdata) {
        this.dbdata = dbdata;
    }

    public static class DbdataBean {
        /**
         * track : 《红海行动》将参选第91届奥斯卡 与《小偷家族》角逐最佳外语片
         * playSize32 : 400164
         * id : 125318421
         * trackIntroduction : 9月24日下午，电影《红海行动》官方微博发文称,《红海行动》将代表中国香港角逐第91届奥斯卡金像奖“最佳外语片”。


         据香港媒体报道，香港电影制片家协会也于昨日宣布，英皇电影《红海行动》全球票房劲破45亿港元，难得商业与艺术价值并重，将会代表香港提名角逐第91届奥斯卡。





         《红海行动》于2018年2月16日在中国内地上映；3月1日在中国香港上映。2018年5月6日，荣获第25届北京大学生电影节最佳影片奖。据猫眼专业版数据，《红海行动》累计票房共36亿，是截至目前的2018年中国电影票房冠军。 在中国电影票房总榜中，《红海行动》排名第二，仅次于《战狼2》。





         据悉，参加第91届奥斯卡最佳外语片的角逐的还有日本是枝裕和执导的《小偷家族》、韩国的《燃烧》、中国台湾的《大佛普拉斯》。另外，由法国选送的《痛苦》等来自不同国家和地区的十余部电影也陆续进入奥斯卡最佳外语片候选行列。中国内地的参选影片还未揭晓。


         对《红海行动》以大多数票当选“申奥片”，正为新片紧张筹备的导演林超贤，坦言自己始料不及：“没想到，以《红海行动》一出商业元素较高的动作电影，能够获选代表香港角逐奥斯卡最佳外语片，对我来...
         * coverUrlSmall : http://fdfs.xmcdn.com/group20/M00/EA/A9/wKgJLFpcacPAGujRAADx8I4Z1Fs859_web_meduim.jpg
         * coverUrlLarge : http://fdfs.xmcdn.com/group20/M00/EA/A9/wKgJLFpcacPAGujRAADx8I4Z1Fs859_mobile_large.jpg
         * coverUrlMiddle : http://fdfs.xmcdn.com/group20/M00/EA/A9/wKgJLFpcacPAGujRAADx8I4Z1Fs859_web_large.jpg
         * playSize64 : 800255
         * playUrl64 : http://fdfs.xmcdn.com/group45/M08/E0/EC/wKgKlFup3eyBvUYXAAw1_3OnCJA197.mp3
         * playUrl32 : http://fdfs.xmcdn.com/group45/M08/E0/EB/wKgKlFup3enATPylAAYbJFwT0jM222.mp3
         * duration : 100
         */

        private String track;
        private int playSize32;
        private int id;
        private String trackIntroduction;
        private String coverUrlSmall;
        private String coverUrlLarge;
        private String coverUrlMiddle;
        private int playSize64;
        private String playUrl64;
        private String playUrl32;
        private int duration;

        public String getTrack() {
            return track;
        }

        public void setTrack(String track) {
            this.track = track;
        }

        public int getPlaySize32() {
            return playSize32;
        }

        public void setPlaySize32(int playSize32) {
            this.playSize32 = playSize32;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTrackIntroduction() {
            return trackIntroduction;
        }

        public void setTrackIntroduction(String trackIntroduction) {
            this.trackIntroduction = trackIntroduction;
        }

        public String getCoverUrlSmall() {
            return coverUrlSmall;
        }

        public void setCoverUrlSmall(String coverUrlSmall) {
            this.coverUrlSmall = coverUrlSmall;
        }

        public String getCoverUrlLarge() {
            return coverUrlLarge;
        }

        public void setCoverUrlLarge(String coverUrlLarge) {
            this.coverUrlLarge = coverUrlLarge;
        }

        public String getCoverUrlMiddle() {
            return coverUrlMiddle;
        }

        public void setCoverUrlMiddle(String coverUrlMiddle) {
            this.coverUrlMiddle = coverUrlMiddle;
        }

        public int getPlaySize64() {
            return playSize64;
        }

        public void setPlaySize64(int playSize64) {
            this.playSize64 = playSize64;
        }

        public String getPlayUrl64() {
            return playUrl64;
        }

        public void setPlayUrl64(String playUrl64) {
            this.playUrl64 = playUrl64;
        }

        public String getPlayUrl32() {
            return playUrl32;
        }

        public void setPlayUrl32(String playUrl32) {
            this.playUrl32 = playUrl32;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }
    }
}
