package com.hamitao.requestframe.entity;

/**
 * @data on 2018/4/11 9:30
 * @describe:
 */

public class GenHttpURLFromOSSKeyInfo {

    /**
     * responseDataObj : {"httpurlfromosskey":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/devicestorage/2222222/123456?Expires=1523413592&OSSAccessKeyId=STS.CfqjNt34RMSP35ccEGwr9Mkrx&Signature=P%2FnQjm%2BEHXb1IVTSX9%2FIaEPogmw%3D&security-token=CAISjAJ1q6Ft5B2yfSjIoqXEIfTA3utz%2BpG7MRPSh0USe%2F0Voq7Zmjz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPOL9Tn0mF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABRHlPWMtMCPYsdJe1N7v6w1oz3omy9HYoCsa7cHEUKi3yYuLTLH85tYQd%2Bc2aN9S8aS%2FpXB0%2B3wv6SX%2FD4H7w87y77nMOtu9OICRglAMK8iRjL8gAUt8POMKyQYB0Y7iY82mBmvJe5am6GJrwbznMvSiL8TaevGMt8oLQVC2oRuY%3D"}
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
         * httpurlfromosskey : http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/devicestorage/2222222/123456?Expires=1523413592&OSSAccessKeyId=STS.CfqjNt34RMSP35ccEGwr9Mkrx&Signature=P%2FnQjm%2BEHXb1IVTSX9%2FIaEPogmw%3D&security-token=CAISjAJ1q6Ft5B2yfSjIoqXEIfTA3utz%2BpG7MRPSh0USe%2F0Voq7Zmjz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPOL9Tn0mF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABRHlPWMtMCPYsdJe1N7v6w1oz3omy9HYoCsa7cHEUKi3yYuLTLH85tYQd%2Bc2aN9S8aS%2FpXB0%2B3wv6SX%2FD4H7w87y77nMOtu9OICRglAMK8iRjL8gAUt8POMKyQYB0Y7iY82mBmvJe5am6GJrwbznMvSiL8TaevGMt8oLQVC2oRuY%3D
         */

        private String httpurlfromosskey;

        public String getHttpurlfromosskey() {
            return httpurlfromosskey;
        }

        public void setHttpurlfromosskey(String httpurlfromosskey) {
            this.httpurlfromosskey = httpurlfromosskey;
        }
    }
}
