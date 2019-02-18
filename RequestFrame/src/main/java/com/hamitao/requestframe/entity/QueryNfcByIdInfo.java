package com.hamitao.requestframe.entity;

import java.util.List;

/**
 * @data on 2018/7/11 11:00
 * @describe:
 */

public class QueryNfcByIdInfo {

    /**
     * responseDataObj : {"NFCCards":[{"NFCID":"barcode1111","contentDesc":{"authorList":["三毛"],"categoryList":["儿歌类/益智","专家推荐","今日推荐"],"characteristicList":["悲伤的"],"contentid":"contentid","contentlang":"contentlang","contenttype":"contenttype","descriptionI18List":[{"language":"en","value":"description"}],"imgtitleurl":"imgtitleurl","keywordList":["关键字","关键字2"],"lyric":"lyric","mediaList":[{"httpURL":"","lrcformat":"lrcformat","mediacontent":"mediacontent","mediacontentype":"mediacontentype","mediaid":"mediaid","medialrc":"medialrc","mediasubtype":"mediasubtype","mediatitle":"mediatitle","mediatype":"mediatype"}],"nameI18List":[{"language":"zh","value":"张三"}],"orientUser":{"age_max":3,"age_min":1,"sex":"any"},"quality":"quality","sourceorigin":"sourceorigin","status":"status","target":"target","vendor":"vendor"},"creator":"sz00002_customer_8eaed3c66ed745fda86774876bbef85a","info":"contentid","infotype":"contentid","seqid":"d1b03b6c0251490396af27df0e908d2f"}]}
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
        private List<NFCCardsBean> NFCCards;

        public List<NFCCardsBean> getNFCCards() {
            return NFCCards;
        }

        public void setNFCCards(List<NFCCardsBean> NFCCards) {
            this.NFCCards = NFCCards;
        }

        public static class NFCCardsBean {
            /**
             * NFCID : barcode1111
             * contentDesc : {"authorList":["三毛"],"categoryList":["儿歌类/益智","专家推荐","今日推荐"],"characteristicList":["悲伤的"],"contentid":"contentid","contentlang":"contentlang","contenttype":"contenttype","descriptionI18List":[{"language":"en","value":"description"}],"imgtitleurl":"imgtitleurl","keywordList":["关键字","关键字2"],"lyric":"lyric","mediaList":[{"httpURL":"","lrcformat":"lrcformat","mediacontent":"mediacontent","mediacontentype":"mediacontentype","mediaid":"mediaid","medialrc":"medialrc","mediasubtype":"mediasubtype","mediatitle":"mediatitle","mediatype":"mediatype"}],"nameI18List":[{"language":"zh","value":"张三"}],"orientUser":{"age_max":3,"age_min":1,"sex":"any"},"quality":"quality","sourceorigin":"sourceorigin","status":"status","target":"target","vendor":"vendor"}
             * creator : sz00002_customer_8eaed3c66ed745fda86774876bbef85a
             * info : contentid
             * infotype : contentid
             * seqid : d1b03b6c0251490396af27df0e908d2f
             */

            private String NFCID;
            private ContentDescBean contentDesc;
            private String creator;
            private String info;
            private String infotype;
            private String seqid;

            public String getNFCID() {
                return NFCID;
            }

            public void setNFCID(String NFCID) {
                this.NFCID = NFCID;
            }

            public ContentDescBean getContentDesc() {
                return contentDesc;
            }

            public void setContentDesc(ContentDescBean contentDesc) {
                this.contentDesc = contentDesc;
            }

            public String getCreator() {
                return creator;
            }

            public void setCreator(String creator) {
                this.creator = creator;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getInfotype() {
                return infotype;
            }

            public void setInfotype(String infotype) {
                this.infotype = infotype;
            }

            public String getSeqid() {
                return seqid;
            }

            public void setSeqid(String seqid) {
                this.seqid = seqid;
            }

            public static class ContentDescBean {
                /**
                 * authorList : ["三毛"]
                 * categoryList : ["儿歌类/益智","专家推荐","今日推荐"]
                 * characteristicList : ["悲伤的"]
                 * contentid : contentid
                 * contentlang : contentlang
                 * contenttype : contenttype
                 * descriptionI18List : [{"language":"en","value":"description"}]
                 * imgtitleurl : imgtitleurl
                 * keywordList : ["关键字","关键字2"]
                 * lyric : lyric
                 * mediaList : [{"httpURL":"","lrcformat":"lrcformat","mediacontent":"mediacontent","mediacontentype":"mediacontentype","mediaid":"mediaid","medialrc":"medialrc","mediasubtype":"mediasubtype","mediatitle":"mediatitle","mediatype":"mediatype"}]
                 * nameI18List : [{"language":"zh","value":"张三"}]
                 * orientUser : {"age_max":3,"age_min":1,"sex":"any"}
                 * quality : quality
                 * sourceorigin : sourceorigin
                 * status : status
                 * target : target
                 * vendor : vendor
                 */

                private String contentid;
                private String contentlang;
                private String contenttype;
                private String imgtitleurl;
                private String lyric;
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

                public String getImgtitleurl() {
                    return imgtitleurl;
                }

                public void setImgtitleurl(String imgtitleurl) {
                    this.imgtitleurl = imgtitleurl;
                }

                public String getLyric() {
                    return lyric;
                }

                public void setLyric(String lyric) {
                    this.lyric = lyric;
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
                     * age_max : 3
                     * age_min : 1
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
                     * httpURL :
                     * lrcformat : lrcformat
                     * mediacontent : mediacontent
                     * mediacontentype : mediacontentype
                     * mediaid : mediaid
                     * medialrc : medialrc
                     * mediasubtype : mediasubtype
                     * mediatitle : mediatitle
                     * mediatype : mediatype
                     */

                    private String httpURL;
                    private String lrcformat;
                    private String mediacontent;
                    private String mediacontentype;
                    private String mediaid;
                    private String medialrc;
                    private String mediasubtype;
                    private String mediatitle;
                    private String mediatype;

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
        }
    }
}
