package com.hamitao.requestframe.entity;

import java.util.List;

/**
 * @data on 2018/7/3 9:31
 * @describe:
 */

public class CourseScheduleInfo {

    /**
     * responseDataObj : {"contents":[{"authorList":["三毛"],"categoryList":["今日推荐Fortest","专家推荐Fortest","儿歌类/益智Fortest"],"characteristicList":["悲伤的fortest","欢快的fortest"],"contentid":"contentid","contentlang":"contentlang","contenttype":"contenttype","descriptionI18List":[{"language":"en","value":"description"}],"duration":0,"imgtitleurl":"imgtitleurl","imgtitleurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/imgtitleurl?Expires=1530525564&OSSAccessKeyId=STS.NKQbxxA1Q9sVKMcVJtwcG8pws&Signature=559ZvC4cLcRadmUHqWU6MyGQes8%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4jkKcLMrO5wjrG9SWvSskohe%2Bxr17XckTz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPAZVz2CeF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABHv9f3hslDTmyN%2FNetCQJdkfacL9vbSVCUnzRLqmY7cSh3QPfQSb3WzpsmXyxvK85%2FqvJe63rZbtei%2FAu4L0ilOrt%2FS59%2BdzT0xyzGJ9eSwUkfqWlgr9Y5K%2BMsyd83qphgGExFCvLdcLA1EXHtyHuDDfSq36PYw56SIXysgwyFDQ%3D","keywordList":["关键字AAA","关键字AAA2"],"likeCount":0,"lyric":"lyric","mediaList":[{"duration":0,"headerimgurl":"","headerimgurlhttpURL":"","httpURL":"","lrcformat":"lrcformat","mediacontent":"mediacontent","mediacontentype":"mediacontentype","mediaid":"mediaidxxxx","medialrc":"medialrc","medialrchttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/medialrc?Expires=1530525564&OSSAccessKeyId=STS.NKQbxxA1Q9sVKMcVJtwcG8pws&Signature=2MC6nt1ZGOZu%2FqFnk3BZix7qGvM%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4jkKcLMrO5wjrG9SWvSskohe%2Bxr17XckTz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPAZVz2CeF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABHv9f3hslDTmyN%2FNetCQJdkfacL9vbSVCUnzRLqmY7cSh3QPfQSb3WzpsmXyxvK85%2FqvJe63rZbtei%2FAu4L0ilOrt%2FS59%2BdzT0xyzGJ9eSwUkfqWlgr9Y5K%2BMsyd83qphgGExFCvLdcLA1EXHtyHuDDfSq36PYw56SIXysgwyFDQ%3D","mediasubtype":"mediasubtype","mediatitle":"mediatitle","mediatype":"mediatype","resolution":"1024*768"}],"mylike_id":"","nameI18List":[{"language":"zh","value":"张三"}],"orientUser":{"age_max":2000,"age_min":1000,"sex":"any"},"quality":"quality","scenario":"scenario","sourceorigin":"sourceorigin","status":"status","target":"target","vendor":"vendor"}],"voiceRecordings":[{"description":"我的录音描述","httpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/appstorage/myvoicerecording/11111.mp3?Expires=1530525565&OSSAccessKeyId=STS.NK1pi5W78t1KxcUg9w3D9cAoH&Signature=6S6MfPHw1G1D8%2BcJIBYeQfmCt2U%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4iEO9OBuugZw%2FOgekXkgzkiP8sVjITEqjz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPU%2Bty2CeF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABWLvm5i5DXhCKjElZ%2F5ePPMqBSx8kP8HlNPkuc1AuokuKRqKD%2BVzeQRIZlOYrvWJfRcsS0sVDTuwS8kgelnk2iBY56cHBYYImWTH6j0BY9za3FtducSlzzD6KcvADhOq2rFucePkUvWjSJIJDw9OOr1qXTYfK7cRxcfs6ksbpU%2B0%3D","id":"voicerecord_2dff55236e964e3aa98e67a5a7c57d72","name":"给小朋友的祝福","ownerid":"mycustomerid","ownername":"","selectStatus":"yes","source":"DEVICE","url":"appstorage/myvoicerecording/11111.mp3"}]}
     */

    private ResponseDataObjBean responseDataObj;

    public ResponseDataObjBean getResponseDataObj() {
        return responseDataObj;
    }

    public void setResponseDataObj(ResponseDataObjBean responseDataObj) {
        this.responseDataObj = responseDataObj;
    }

    public static class ResponseDataObjBean {
        private List<ContentsBean> contents;
        private List<VoiceRecordingsBean> voiceRecordings;

        public List<ContentsBean> getContents() {
            return contents;
        }

        public void setContents(List<ContentsBean> contents) {
            this.contents = contents;
        }

        public List<VoiceRecordingsBean> getVoiceRecordings() {
            return voiceRecordings;
        }

        public void setVoiceRecordings(List<VoiceRecordingsBean> voiceRecordings) {
            this.voiceRecordings = voiceRecordings;
        }

        public static class ContentsBean {
            /**
             * authorList : ["三毛"]
             * categoryList : ["今日推荐Fortest","专家推荐Fortest","儿歌类/益智Fortest"]
             * characteristicList : ["悲伤的fortest","欢快的fortest"]
             * contentid : contentid
             * contentlang : contentlang
             * contenttype : contenttype
             * descriptionI18List : [{"language":"en","value":"description"}]
             * duration : 0
             * imgtitleurl : imgtitleurl
             * imgtitleurlhttpURL : http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/imgtitleurl?Expires=1530525564&OSSAccessKeyId=STS.NKQbxxA1Q9sVKMcVJtwcG8pws&Signature=559ZvC4cLcRadmUHqWU6MyGQes8%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4jkKcLMrO5wjrG9SWvSskohe%2Bxr17XckTz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPAZVz2CeF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABHv9f3hslDTmyN%2FNetCQJdkfacL9vbSVCUnzRLqmY7cSh3QPfQSb3WzpsmXyxvK85%2FqvJe63rZbtei%2FAu4L0ilOrt%2FS59%2BdzT0xyzGJ9eSwUkfqWlgr9Y5K%2BMsyd83qphgGExFCvLdcLA1EXHtyHuDDfSq36PYw56SIXysgwyFDQ%3D
             * keywordList : ["关键字AAA","关键字AAA2"]
             * likeCount : 0
             * lyric : lyric
             * mediaList : [{"duration":0,"headerimgurl":"","headerimgurlhttpURL":"","httpURL":"","lrcformat":"lrcformat","mediacontent":"mediacontent","mediacontentype":"mediacontentype","mediaid":"mediaidxxxx","medialrc":"medialrc","medialrchttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/medialrc?Expires=1530525564&OSSAccessKeyId=STS.NKQbxxA1Q9sVKMcVJtwcG8pws&Signature=2MC6nt1ZGOZu%2FqFnk3BZix7qGvM%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4jkKcLMrO5wjrG9SWvSskohe%2Bxr17XckTz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPAZVz2CeF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABHv9f3hslDTmyN%2FNetCQJdkfacL9vbSVCUnzRLqmY7cSh3QPfQSb3WzpsmXyxvK85%2FqvJe63rZbtei%2FAu4L0ilOrt%2FS59%2BdzT0xyzGJ9eSwUkfqWlgr9Y5K%2BMsyd83qphgGExFCvLdcLA1EXHtyHuDDfSq36PYw56SIXysgwyFDQ%3D","mediasubtype":"mediasubtype","mediatitle":"mediatitle","mediatype":"mediatype","resolution":"1024*768"}]
             * mylike_id :
             * nameI18List : [{"language":"zh","value":"张三"}]
             * orientUser : {"age_max":2000,"age_min":1000,"sex":"any"}
             * quality : quality
             * scenario : scenario
             * sourceorigin : sourceorigin
             * status : status
             * target : target
             * vendor : vendor
             */

            private String contentid;
            private String contentlang;
            private String contenttype;
            private int duration;
            private String imgtitleurl;
            private String imgtitleurlhttpURL;
            private int likeCount;
            private String lyric;
            private String mylike_id;
            private OrientUserBean orientUser;
            private String quality;
            private String scenario;
            private String sourceorigin;
            private String status;
            private String target;
            private String vendor;
            private List<String> authorList;
            private List<String> categoryList;
            private List<String> characteristicList;
            private List<DescriptionI18ListBean> descriptionI18List;
            private List<String> keywordList;
            private List<MediaListBean> mediaList;
            private List<NameI18ListBean> nameI18List;

            public String getContentid() {
                return contentid;
            }

            public void setContentid(String contentid) {
                this.contentid = contentid;
            }

            public String getContentlang() {
                return contentlang;
            }

            public void setContentlang(String contentlang) {
                this.contentlang = contentlang;
            }

            public String getContenttype() {
                return contenttype;
            }

            public void setContenttype(String contenttype) {
                this.contenttype = contenttype;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getImgtitleurl() {
                return imgtitleurl;
            }

            public void setImgtitleurl(String imgtitleurl) {
                this.imgtitleurl = imgtitleurl;
            }

            public String getImgtitleurlhttpURL() {
                return imgtitleurlhttpURL;
            }

            public void setImgtitleurlhttpURL(String imgtitleurlhttpURL) {
                this.imgtitleurlhttpURL = imgtitleurlhttpURL;
            }

            public int getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(int likeCount) {
                this.likeCount = likeCount;
            }

            public String getLyric() {
                return lyric;
            }

            public void setLyric(String lyric) {
                this.lyric = lyric;
            }

            public String getMylike_id() {
                return mylike_id;
            }

            public void setMylike_id(String mylike_id) {
                this.mylike_id = mylike_id;
            }

            public OrientUserBean getOrientUser() {
                return orientUser;
            }

            public void setOrientUser(OrientUserBean orientUser) {
                this.orientUser = orientUser;
            }

            public String getQuality() {
                return quality;
            }

            public void setQuality(String quality) {
                this.quality = quality;
            }

            public String getScenario() {
                return scenario;
            }

            public void setScenario(String scenario) {
                this.scenario = scenario;
            }

            public String getSourceorigin() {
                return sourceorigin;
            }

            public void setSourceorigin(String sourceorigin) {
                this.sourceorigin = sourceorigin;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTarget() {
                return target;
            }

            public void setTarget(String target) {
                this.target = target;
            }

            public String getVendor() {
                return vendor;
            }

            public void setVendor(String vendor) {
                this.vendor = vendor;
            }

            public List<String> getAuthorList() {
                return authorList;
            }

            public void setAuthorList(List<String> authorList) {
                this.authorList = authorList;
            }

            public List<String> getCategoryList() {
                return categoryList;
            }

            public void setCategoryList(List<String> categoryList) {
                this.categoryList = categoryList;
            }

            public List<String> getCharacteristicList() {
                return characteristicList;
            }

            public void setCharacteristicList(List<String> characteristicList) {
                this.characteristicList = characteristicList;
            }

            public List<DescriptionI18ListBean> getDescriptionI18List() {
                return descriptionI18List;
            }

            public void setDescriptionI18List(List<DescriptionI18ListBean> descriptionI18List) {
                this.descriptionI18List = descriptionI18List;
            }

            public List<String> getKeywordList() {
                return keywordList;
            }

            public void setKeywordList(List<String> keywordList) {
                this.keywordList = keywordList;
            }

            public List<MediaListBean> getMediaList() {
                return mediaList;
            }

            public void setMediaList(List<MediaListBean> mediaList) {
                this.mediaList = mediaList;
            }

            public List<NameI18ListBean> getNameI18List() {
                return nameI18List;
            }

            public void setNameI18List(List<NameI18ListBean> nameI18List) {
                this.nameI18List = nameI18List;
            }

            public static class OrientUserBean {
                /**
                 * age_max : 2000
                 * age_min : 1000
                 * sex : any
                 */

                private int age_max;
                private int age_min;
                private String sex;

                public int getAge_max() {
                    return age_max;
                }

                public void setAge_max(int age_max) {
                    this.age_max = age_max;
                }

                public int getAge_min() {
                    return age_min;
                }

                public void setAge_min(int age_min) {
                    this.age_min = age_min;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }
            }

            public static class DescriptionI18ListBean {
                /**
                 * language : en
                 * value : description
                 */

                private String language;
                private String value;

                public String getLanguage() {
                    return language;
                }

                public void setLanguage(String language) {
                    this.language = language;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class MediaListBean {
                /**
                 * duration : 0
                 * headerimgurl :
                 * headerimgurlhttpURL :
                 * httpURL :
                 * lrcformat : lrcformat
                 * mediacontent : mediacontent
                 * mediacontentype : mediacontentype
                 * mediaid : mediaidxxxx
                 * medialrc : medialrc
                 * medialrchttpURL : http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/medialrc?Expires=1530525564&OSSAccessKeyId=STS.NKQbxxA1Q9sVKMcVJtwcG8pws&Signature=2MC6nt1ZGOZu%2FqFnk3BZix7qGvM%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4jkKcLMrO5wjrG9SWvSskohe%2Bxr17XckTz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPAZVz2CeF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABHv9f3hslDTmyN%2FNetCQJdkfacL9vbSVCUnzRLqmY7cSh3QPfQSb3WzpsmXyxvK85%2FqvJe63rZbtei%2FAu4L0ilOrt%2FS59%2BdzT0xyzGJ9eSwUkfqWlgr9Y5K%2BMsyd83qphgGExFCvLdcLA1EXHtyHuDDfSq36PYw56SIXysgwyFDQ%3D
                 * mediasubtype : mediasubtype
                 * mediatitle : mediatitle
                 * mediatype : mediatype
                 * resolution : 1024*768
                 */

                private int duration;
                private String headerimgurl;
                private String headerimgurlhttpURL;
                private String httpURL;
                private String lrcformat;
                private String mediacontent;
                private String mediacontentype;
                private String mediaid;
                private String medialrc;
                private String medialrchttpURL;
                private String mediasubtype;
                private String mediatitle;
                private String mediatype;
                private String resolution;

                public int getDuration() {
                    return duration;
                }

                public void setDuration(int duration) {
                    this.duration = duration;
                }

                public String getHeaderimgurl() {
                    return headerimgurl;
                }

                public void setHeaderimgurl(String headerimgurl) {
                    this.headerimgurl = headerimgurl;
                }

                public String getHeaderimgurlhttpURL() {
                    return headerimgurlhttpURL;
                }

                public void setHeaderimgurlhttpURL(String headerimgurlhttpURL) {
                    this.headerimgurlhttpURL = headerimgurlhttpURL;
                }

                public String getHttpURL() {
                    return httpURL;
                }

                public void setHttpURL(String httpURL) {
                    this.httpURL = httpURL;
                }

                public String getLrcformat() {
                    return lrcformat;
                }

                public void setLrcformat(String lrcformat) {
                    this.lrcformat = lrcformat;
                }

                public String getMediacontent() {
                    return mediacontent;
                }

                public void setMediacontent(String mediacontent) {
                    this.mediacontent = mediacontent;
                }

                public String getMediacontentype() {
                    return mediacontentype;
                }

                public void setMediacontentype(String mediacontentype) {
                    this.mediacontentype = mediacontentype;
                }

                public String getMediaid() {
                    return mediaid;
                }

                public void setMediaid(String mediaid) {
                    this.mediaid = mediaid;
                }

                public String getMedialrc() {
                    return medialrc;
                }

                public void setMedialrc(String medialrc) {
                    this.medialrc = medialrc;
                }

                public String getMedialrchttpURL() {
                    return medialrchttpURL;
                }

                public void setMedialrchttpURL(String medialrchttpURL) {
                    this.medialrchttpURL = medialrchttpURL;
                }

                public String getMediasubtype() {
                    return mediasubtype;
                }

                public void setMediasubtype(String mediasubtype) {
                    this.mediasubtype = mediasubtype;
                }

                public String getMediatitle() {
                    return mediatitle;
                }

                public void setMediatitle(String mediatitle) {
                    this.mediatitle = mediatitle;
                }

                public String getMediatype() {
                    return mediatype;
                }

                public void setMediatype(String mediatype) {
                    this.mediatype = mediatype;
                }

                public String getResolution() {
                    return resolution;
                }

                public void setResolution(String resolution) {
                    this.resolution = resolution;
                }
            }

            public static class NameI18ListBean {
                /**
                 * language : zh
                 * value : 张三
                 */

                private String language;
                private String value;

                public String getLanguage() {
                    return language;
                }

                public void setLanguage(String language) {
                    this.language = language;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }

        public static class VoiceRecordingsBean {
            /**
             * description : 我的录音描述
             * httpURL : http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/appstorage/myvoicerecording/11111.mp3?Expires=1530525565&OSSAccessKeyId=STS.NK1pi5W78t1KxcUg9w3D9cAoH&Signature=6S6MfPHw1G1D8%2BcJIBYeQfmCt2U%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4iEO9OBuugZw%2FOgekXkgzkiP8sVjITEqjz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPU%2Bty2CeF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABWLvm5i5DXhCKjElZ%2F5ePPMqBSx8kP8HlNPkuc1AuokuKRqKD%2BVzeQRIZlOYrvWJfRcsS0sVDTuwS8kgelnk2iBY56cHBYYImWTH6j0BY9za3FtducSlzzD6KcvADhOq2rFucePkUvWjSJIJDw9OOr1qXTYfK7cRxcfs6ksbpU%2B0%3D
             * id : voicerecord_2dff55236e964e3aa98e67a5a7c57d72
             * name : 给小朋友的祝福
             * ownerid : mycustomerid
             * ownername :
             * selectStatus : yes
             * source : DEVICE
             * url : appstorage/myvoicerecording/11111.mp3
             */

            private String description;
            private String httpURL;
            private String id;
            private String name;
            private String ownerid;
            private String ownername;
            private String selectStatus;
            private String source;
            private String url;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getHttpURL() {
                return httpURL;
            }

            public void setHttpURL(String httpURL) {
                this.httpURL = httpURL;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOwnerid() {
                return ownerid;
            }

            public void setOwnerid(String ownerid) {
                this.ownerid = ownerid;
            }

            public String getOwnername() {
                return ownername;
            }

            public void setOwnername(String ownername) {
                this.ownername = ownername;
            }

            public String getSelectStatus() {
                return selectStatus;
            }

            public void setSelectStatus(String selectStatus) {
                this.selectStatus = selectStatus;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
