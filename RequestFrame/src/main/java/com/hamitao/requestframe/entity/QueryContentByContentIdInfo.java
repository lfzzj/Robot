package com.hamitao.requestframe.entity;

import java.util.List;

/**
 * @data on 2018/5/19 17:06
 * @describe:
 */

public class QueryContentByContentIdInfo {


    /**
     * responseDataObj : {"contents":[{"authorList":["佚名"],"categoryList":["娱乐/儿歌","生活常识/启蒙教育","专家推荐","今日推荐"],"characteristicList":["喜剧","动物","欢快"],"contentid":"EEEEEAAAAA11111111","contentlang":"zh","contenttype":"file","descriptionI18List":[{"language":"zh","value":"幼幼熊系列儿歌"}],"duration":413,"imgtitleurl":"contentstorage/misc/test/儿歌/imgtitle.jpg","imgtitleurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/contentstorage/misc/test/%E5%84%BF%E6%AD%8C/imgtitle.jpg?Expires=1526885031&OSSAccessKeyId=STS.NKBPu5LbME96iLz2w2m2cEhps&Signature=OM0Aw8E1MY3Klq0ZDYyXrX0cA20%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4j3G8%2BBob1s8vvda2rL1ndnYb1Pqq3bkTz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPEtQLgFqF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABnX%2Bqh7EpBv5TFC%2Bl90NEn3mNa%2BmOvSPKNqGD0e0pvoTcr5Adz7K9f%2F5KQKA1ksFuaTrH3EHgQP1fFu429%2FaPod56nHKORxKghTRkV1jPnoOiVxU4S4%2Fa%2BKyr0nSzgOlq761eYxkbeM5g77GiAcxxF6L5%2BoRneC0d%2B7k0RkgNi8Y%3D","keywordList":["七个小矮人","小小童"],"likeCount":0,"lyric":"小呀嘛小呀嘛小二郎","mediaList":[{"duration":216,"headerimgurl":"","headerimgurlhttpURL":"","httpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/contentstorage/misc/test/%E5%84%BF%E6%AD%8C/%E6%88%91%E7%88%B1%E5%A6%88%E5%A6%88%E7%9A%84%E7%9C%BC%E7%9D%9B.mp3?Expires=1526885031&OSSAccessKeyId=STS.NKBPu5LbME96iLz2w2m2cEhps&Signature=K8gXxC0kGiqYXNiD%2B5uZD6KY2PE%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4j3G8%2BBob1s8vvda2rL1ndnYb1Pqq3bkTz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPEtQLgFqF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABnX%2Bqh7EpBv5TFC%2Bl90NEn3mNa%2BmOvSPKNqGD0e0pvoTcr5Adz7K9f%2F5KQKA1ksFuaTrH3EHgQP1fFu429%2FaPod56nHKORxKghTRkV1jPnoOiVxU4S4%2Fa%2BKyr0nSzgOlq761eYxkbeM5g77GiAcxxF6L5%2BoRneC0d%2B7k0RkgNi8Y%3D","lrcformat":"","mediacontent":"contentstorage/misc/test/儿歌/我爱妈妈的眼睛.mp3","mediacontentype":"keyonoss","mediaid":"EEEEEAAAAA11111111-我爱妈妈的眼睛.mp3","medialrc":"","medialrchttpURL":"","mediasubtype":"mp3","mediatitle":"我爱妈妈的眼睛","mediatype":"audio"},{"duration":197,"headerimgurl":"","headerimgurlhttpURL":"","httpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/contentstorage/misc/test/%E5%84%BF%E6%AD%8C/%E9%87%87%E8%98%91%E8%8F%87%E7%9A%84%E5%B0%8F%E5%A7%91%E5%A8%98.mp3?Expires=1526885031&OSSAccessKeyId=STS.NKBPu5LbME96iLz2w2m2cEhps&Signature=%2BEoRrSoWjblNT%2FfryWl7fB0%2Fxr0%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4j3G8%2BBob1s8vvda2rL1ndnYb1Pqq3bkTz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPEtQLgFqF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABnX%2Bqh7EpBv5TFC%2Bl90NEn3mNa%2BmOvSPKNqGD0e0pvoTcr5Adz7K9f%2F5KQKA1ksFuaTrH3EHgQP1fFu429%2FaPod56nHKORxKghTRkV1jPnoOiVxU4S4%2Fa%2BKyr0nSzgOlq761eYxkbeM5g77GiAcxxF6L5%2BoRneC0d%2B7k0RkgNi8Y%3D","lrcformat":"","mediacontent":"contentstorage/misc/test/儿歌/采蘑菇的小姑娘.mp3","mediacontentype":"keyonoss","mediaid":"EEEEEAAAAA11111111-采蘑菇的小姑娘.mp3","medialrc":"","medialrchttpURL":"","mediasubtype":"mp3","mediatitle":"采蘑菇的小姑娘","mediatype":"audio"}],"mylike_id":"","nameI18List":[{"language":"zh","value":"幼幼熊儿歌系列"}],"orientUser":{"age_max":6,"age_min":2,"sex":"any"},"quality":"perfect","sourceorigin":"internet+http://www.google.com.cn/aaa","status":"enable","target":"app+device","vendor":"none"}]}
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
        private List<ContentsBean> contents;

        public List<ContentsBean> getContents() {
            return contents;
        }

        public void setContents(List<ContentsBean> contents) {
            this.contents = contents;
        }

        public static class ContentsBean {
            /**
             * authorList : ["佚名"]
             * categoryList : ["娱乐/儿歌","生活常识/启蒙教育","专家推荐","今日推荐"]
             * characteristicList : ["喜剧","动物","欢快"]
             * contentid : EEEEEAAAAA11111111
             * contentlang : zh
             * contenttype : file
             * descriptionI18List : [{"language":"zh","value":"幼幼熊系列儿歌"}]
             * duration : 413
             * imgtitleurl : contentstorage/misc/test/儿歌/imgtitle.jpg
             * imgtitleurlhttpURL : http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/contentstorage/misc/test/%E5%84%BF%E6%AD%8C/imgtitle.jpg?Expires=1526885031&OSSAccessKeyId=STS.NKBPu5LbME96iLz2w2m2cEhps&Signature=OM0Aw8E1MY3Klq0ZDYyXrX0cA20%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4j3G8%2BBob1s8vvda2rL1ndnYb1Pqq3bkTz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPEtQLgFqF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABnX%2Bqh7EpBv5TFC%2Bl90NEn3mNa%2BmOvSPKNqGD0e0pvoTcr5Adz7K9f%2F5KQKA1ksFuaTrH3EHgQP1fFu429%2FaPod56nHKORxKghTRkV1jPnoOiVxU4S4%2Fa%2BKyr0nSzgOlq761eYxkbeM5g77GiAcxxF6L5%2BoRneC0d%2B7k0RkgNi8Y%3D
             * keywordList : ["七个小矮人","小小童"]
             * likeCount : 0
             * lyric : 小呀嘛小呀嘛小二郎
             * mediaList : [{"duration":216,"headerimgurl":"","headerimgurlhttpURL":"","httpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/contentstorage/misc/test/%E5%84%BF%E6%AD%8C/%E6%88%91%E7%88%B1%E5%A6%88%E5%A6%88%E7%9A%84%E7%9C%BC%E7%9D%9B.mp3?Expires=1526885031&OSSAccessKeyId=STS.NKBPu5LbME96iLz2w2m2cEhps&Signature=K8gXxC0kGiqYXNiD%2B5uZD6KY2PE%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4j3G8%2BBob1s8vvda2rL1ndnYb1Pqq3bkTz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPEtQLgFqF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABnX%2Bqh7EpBv5TFC%2Bl90NEn3mNa%2BmOvSPKNqGD0e0pvoTcr5Adz7K9f%2F5KQKA1ksFuaTrH3EHgQP1fFu429%2FaPod56nHKORxKghTRkV1jPnoOiVxU4S4%2Fa%2BKyr0nSzgOlq761eYxkbeM5g77GiAcxxF6L5%2BoRneC0d%2B7k0RkgNi8Y%3D","lrcformat":"","mediacontent":"contentstorage/misc/test/儿歌/我爱妈妈的眼睛.mp3","mediacontentype":"keyonoss","mediaid":"EEEEEAAAAA11111111-我爱妈妈的眼睛.mp3","medialrc":"","medialrchttpURL":"","mediasubtype":"mp3","mediatitle":"我爱妈妈的眼睛","mediatype":"audio"},{"duration":197,"headerimgurl":"","headerimgurlhttpURL":"","httpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/contentstorage/misc/test/%E5%84%BF%E6%AD%8C/%E9%87%87%E8%98%91%E8%8F%87%E7%9A%84%E5%B0%8F%E5%A7%91%E5%A8%98.mp3?Expires=1526885031&OSSAccessKeyId=STS.NKBPu5LbME96iLz2w2m2cEhps&Signature=%2BEoRrSoWjblNT%2FfryWl7fB0%2Fxr0%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4j3G8%2BBob1s8vvda2rL1ndnYb1Pqq3bkTz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPEtQLgFqF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABnX%2Bqh7EpBv5TFC%2Bl90NEn3mNa%2BmOvSPKNqGD0e0pvoTcr5Adz7K9f%2F5KQKA1ksFuaTrH3EHgQP1fFu429%2FaPod56nHKORxKghTRkV1jPnoOiVxU4S4%2Fa%2BKyr0nSzgOlq761eYxkbeM5g77GiAcxxF6L5%2BoRneC0d%2B7k0RkgNi8Y%3D","lrcformat":"","mediacontent":"contentstorage/misc/test/儿歌/采蘑菇的小姑娘.mp3","mediacontentype":"keyonoss","mediaid":"EEEEEAAAAA11111111-采蘑菇的小姑娘.mp3","medialrc":"","medialrchttpURL":"","mediasubtype":"mp3","mediatitle":"采蘑菇的小姑娘","mediatype":"audio"}]
             * mylike_id :
             * nameI18List : [{"language":"zh","value":"幼幼熊儿歌系列"}]
             * orientUser : {"age_max":6,"age_min":2,"sex":"any"}
             * quality : perfect
             * sourceorigin : internet+http://www.google.com.cn/aaa
             * status : enable
             * target : app+device
             * vendor : none
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
                 * age_max : 6
                 * age_min : 2
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
                 * language : zh
                 * value : 幼幼熊系列儿歌
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
                 * duration : 216
                 * headerimgurl :
                 * headerimgurlhttpURL :
                 * httpURL : http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/contentstorage/misc/test/%E5%84%BF%E6%AD%8C/%E6%88%91%E7%88%B1%E5%A6%88%E5%A6%88%E7%9A%84%E7%9C%BC%E7%9D%9B.mp3?Expires=1526885031&OSSAccessKeyId=STS.NKBPu5LbME96iLz2w2m2cEhps&Signature=K8gXxC0kGiqYXNiD%2B5uZD6KY2PE%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4j3G8%2BBob1s8vvda2rL1ndnYb1Pqq3bkTz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPEtQLgFqF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABnX%2Bqh7EpBv5TFC%2Bl90NEn3mNa%2BmOvSPKNqGD0e0pvoTcr5Adz7K9f%2F5KQKA1ksFuaTrH3EHgQP1fFu429%2FaPod56nHKORxKghTRkV1jPnoOiVxU4S4%2Fa%2BKyr0nSzgOlq761eYxkbeM5g77GiAcxxF6L5%2BoRneC0d%2B7k0RkgNi8Y%3D
                 * lrcformat :
                 * mediacontent : contentstorage/misc/test/儿歌/我爱妈妈的眼睛.mp3
                 * mediacontentype : keyonoss
                 * mediaid : EEEEEAAAAA11111111-我爱妈妈的眼睛.mp3
                 * medialrc :
                 * medialrchttpURL :
                 * mediasubtype : mp3
                 * mediatitle : 我爱妈妈的眼睛
                 * mediatype : audio
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
            }

            public static class NameI18ListBean {
                /**
                 * language : zh
                 * value : 幼幼熊儿歌系列
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
    }
}
