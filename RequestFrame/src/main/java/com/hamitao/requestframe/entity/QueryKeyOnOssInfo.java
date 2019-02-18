package com.hamitao.requestframe.entity;

/**
 * @data on 2018/3/15 15:07
 * @describe:
 */

public class QueryKeyOnOssInfo {

    /**
     * responseDataObj : {"STS":{"AccessKeyId":"STS.HBgPiVAn9H3kJAjGYdcuCNsFT","AccessKeySecret":"5nM3afXcPrFE5PhrgGv9AoL6wbt5nQG7BQvBvYqfsPaN","Expiration":"2018-03-28T04:23:43Z","RequestId":"8B2E32D5-35FF-45AA-8070-C511B0D7A502","SecurityToken":"CAISjAJ1q6Ft5B2yfSjIqYHSG9PirLEY//GASGfbo1kxb/pvobbttjz2IHxFfXBvA+gbvvwxlW5S7P8elqVoRoReREvCKM1565kPIbMq3USF6aKP9rUhpMCPKwr6UmzGvqL7Z+H+U6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj+wIDLkQRRLqL0AFZrFsKxBltdUROFbIKP+pKWSKuGfLC1dysQcO7gEa4K+kkMqH8Uic3h+oiM1t/t6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB+/jPKg8qQGVE54W/db7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD/EVAk6c2t8rCjDGoABF9xusKeuFAfmFHx6qDTQHZTW8y+fhg8yQWBuniDwsc196C2nhBWP61ZczVQNwAr3Z7rY+86DLPC3Ox+A9njs7uf9vAo7r8/C5Jp5qT47b8s1XiOSk/EBMyG80+mcPfjJ+JruNug9Y6zcXAlN7MdXX0URGFrkPa0mcBL5mAhDPBo="}}
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
        /**
         * STS : {"AccessKeyId":"STS.HBgPiVAn9H3kJAjGYdcuCNsFT","AccessKeySecret":"5nM3afXcPrFE5PhrgGv9AoL6wbt5nQG7BQvBvYqfsPaN","Expiration":"2018-03-28T04:23:43Z","RequestId":"8B2E32D5-35FF-45AA-8070-C511B0D7A502","SecurityToken":"CAISjAJ1q6Ft5B2yfSjIqYHSG9PirLEY//GASGfbo1kxb/pvobbttjz2IHxFfXBvA+gbvvwxlW5S7P8elqVoRoReREvCKM1565kPIbMq3USF6aKP9rUhpMCPKwr6UmzGvqL7Z+H+U6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj+wIDLkQRRLqL0AFZrFsKxBltdUROFbIKP+pKWSKuGfLC1dysQcO7gEa4K+kkMqH8Uic3h+oiM1t/t6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB+/jPKg8qQGVE54W/db7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD/EVAk6c2t8rCjDGoABF9xusKeuFAfmFHx6qDTQHZTW8y+fhg8yQWBuniDwsc196C2nhBWP61ZczVQNwAr3Z7rY+86DLPC3Ox+A9njs7uf9vAo7r8/C5Jp5qT47b8s1XiOSk/EBMyG80+mcPfjJ+JruNug9Y6zcXAlN7MdXX0URGFrkPa0mcBL5mAhDPBo="}
         */

        private STSBean STS;

        public STSBean getSTS() {
            return STS;
        }

        public void setSTS(STSBean STS) {
            this.STS = STS;
        }

        public static class STSBean {
            /**
             * AccessKeyId : STS.HBgPiVAn9H3kJAjGYdcuCNsFT
             * AccessKeySecret : 5nM3afXcPrFE5PhrgGv9AoL6wbt5nQG7BQvBvYqfsPaN
             * Expiration : 2018-03-28T04:23:43Z
             * RequestId : 8B2E32D5-35FF-45AA-8070-C511B0D7A502
             * SecurityToken : CAISjAJ1q6Ft5B2yfSjIqYHSG9PirLEY//GASGfbo1kxb/pvobbttjz2IHxFfXBvA+gbvvwxlW5S7P8elqVoRoReREvCKM1565kPIbMq3USF6aKP9rUhpMCPKwr6UmzGvqL7Z+H+U6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj+wIDLkQRRLqL0AFZrFsKxBltdUROFbIKP+pKWSKuGfLC1dysQcO7gEa4K+kkMqH8Uic3h+oiM1t/t6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB+/jPKg8qQGVE54W/db7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD/EVAk6c2t8rCjDGoABF9xusKeuFAfmFHx6qDTQHZTW8y+fhg8yQWBuniDwsc196C2nhBWP61ZczVQNwAr3Z7rY+86DLPC3Ox+A9njs7uf9vAo7r8/C5Jp5qT47b8s1XiOSk/EBMyG80+mcPfjJ+JruNug9Y6zcXAlN7MdXX0URGFrkPa0mcBL5mAhDPBo=
             */

            private String AccessKeyId;
            private String AccessKeySecret;
            private String Expiration;
            private String RequestId;
            private String SecurityToken;

            public String getAccessKeyId() {
                return AccessKeyId;
            }

            public void setAccessKeyId(String AccessKeyId) {
                this.AccessKeyId = AccessKeyId;
            }

            public String getAccessKeySecret() {
                return AccessKeySecret;
            }

            public void setAccessKeySecret(String AccessKeySecret) {
                this.AccessKeySecret = AccessKeySecret;
            }

            public String getExpiration() {
                return Expiration;
            }

            public void setExpiration(String Expiration) {
                this.Expiration = Expiration;
            }

            public String getRequestId() {
                return RequestId;
            }

            public void setRequestId(String RequestId) {
                this.RequestId = RequestId;
            }

            public String getSecurityToken() {
                return SecurityToken;
            }

            public void setSecurityToken(String SecurityToken) {
                this.SecurityToken = SecurityToken;
            }
        }
    }
}
