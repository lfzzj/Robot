package com.hamitao.requestframe.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @data on 2018/6/29 16:35
 * @describe:
 */

public class GetContentTreeInfo {


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
        private List<ContentTreeNodesBean> contentTreeNodes;

        public List<ContentTreeNodesBean> getContentTreeNodes() {
            return contentTreeNodes;
        }

        public void setContentTreeNodes(List<ContentTreeNodesBean> contentTreeNodes) {
            this.contentTreeNodes = contentTreeNodes;
        }

        public static class ContentTreeNodesBean {

            private String nodeid;
            private List<ChildrenBeanX> children;

            public String getNodeid() {
                return nodeid;
            }

            public void setNodeid(String nodeid) {
                this.nodeid = nodeid;
            }

            public List<ChildrenBeanX> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBeanX> children) {
                this.children = children;
            }

            public static class ChildrenBeanX {
                /**
                 * children : [{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/你真棒","nodename":"你真棒","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"},{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/别碰我的小火车","nodename":"别碰我的小火车","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"},{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/妈妈去上班","nodename":"妈妈去上班","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"},{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/尝一尝这是什么味道","nodename":"尝一尝这是什么味道","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"},{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/我会关心妈妈","nodename":"我会关心妈妈","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"},{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/我绝不跟陌生人走","nodename":"我绝不跟陌生人走","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"},{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/排排队","nodename":"排排队","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"},{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/热闹的美术课","nodename":"热闹的美术课","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"},{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/男孩女孩","nodename":"男孩女孩","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"},{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/种子不见了","nodename":"种子不见了","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"},{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/竹兜危险","nodename":"竹兜危险","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"},{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/美味糖果屋","nodename":"美味糖果屋","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"},{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/该睡觉了","nodename":"该睡觉了","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"},{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/谁扔的最远","nodename":"谁扔的最远","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"},{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/超级竹兜","nodename":"超级竹兜","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"},{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/趣味运动会","nodename":"趣味运动会","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"},{"children":[],"contentid":"","nodedesc":"","nodeheaderimgurl":"","nodeheaderimgurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","nodeid":"/7.竹兜快乐家庭系列25主题全集2-5/音乐寻宝记","nodename":"音乐寻宝记","nodetype":"nonleaf","parentnodeid":"/7.竹兜快乐家庭系列25主题全集2-5","scenario":"动画"}]
                 * contentDesc : {"authorList":["红白蓝"],"categoryList":["启蒙教育"],"characteristicList":["欢快"],"contentid":"zhudoukuailejiatingxilie","contentlang":"zh","contenttype":"file","descriptionI18List":[{"language":"zh","value":"竹兜快乐家庭系列"}],"imgauthorurl":"","imgauthorurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","imgtitleurl":"contentstorage/解忧杂货店/动画/7.竹兜快乐家庭系列25主题全集2-5/imgtitle","imgtitleurlhttpURL":"http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/contentstorage/%E8%A7%A3%E5%BF%A7%E6%9D%82%E8%B4%A7%E5%BA%97/%E5%8A%A8%E7%94%BB/7.%E7%AB%B9%E5%85%9C%E5%BF%AB%E4%B9%90%E5%AE%B6%E5%BA%AD%E7%B3%BB%E5%88%9725%E4%B8%BB%E9%A2%98%E5%85%A8%E9%9B%862-5/imgtitle?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=W6W17D%2BOaHSH6ro%2B8ey%2FxS4AyG0%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D","keywordList":["竹兜","竹兜快乐家庭"],"lyric":"","mediaList":[],"nameI18List":[{"language":"zh","value":"竹兜快乐家庭系列"}],"orientUser":{"age_max":3,"age_min":2,"sex":"any"},"quality":"perfect","scenario":"misc","sourceorigin":"internet+http://www.google.com.cn/aaa","status":"enable","target":"app+device","vendor":"none"}
                 * contentid : zhudoukuailejiatingxilie
                 * nodedesc :
                 * nodeheaderimgurl :
                 * nodeheaderimgurlhttpURL : http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D
                 * nodeid : /7.竹兜快乐家庭系列25主题全集2-5
                 * nodename : 7.竹兜快乐家庭系列25主题全集2-5
                 * nodetype : leaf
                 * parentnodeid :
                 * scenario : 动画
                 */

                private ContentDescBean contentDesc;
                private String contentid;
                private String nodedesc;
                private String nodeheaderimgurl;
                private String nodeheaderimgurlhttpURL;
                private String nodeid;
                private String nodename;
                private String nodetype;
                private String parentnodeid;
                private String scenario;
                private List<ChildrenBean> children;

                public ContentDescBean getContentDesc() {
                    return contentDesc;
                }

                public void setContentDesc(ContentDescBean contentDesc) {
                    this.contentDesc = contentDesc;
                }

                public String getContentid() {
                    return contentid;
                }

                public void setContentid(String contentid) {
                    this.contentid = contentid;
                }

                public String getNodedesc() {
                    return nodedesc;
                }

                public void setNodedesc(String nodedesc) {
                    this.nodedesc = nodedesc;
                }

                public String getNodeheaderimgurl() {
                    return nodeheaderimgurl;
                }

                public void setNodeheaderimgurl(String nodeheaderimgurl) {
                    this.nodeheaderimgurl = nodeheaderimgurl;
                }

                public String getNodeheaderimgurlhttpURL() {
                    return nodeheaderimgurlhttpURL;
                }

                public void setNodeheaderimgurlhttpURL(String nodeheaderimgurlhttpURL) {
                    this.nodeheaderimgurlhttpURL = nodeheaderimgurlhttpURL;
                }

                public String getNodeid() {
                    return nodeid;
                }

                public void setNodeid(String nodeid) {
                    this.nodeid = nodeid;
                }

                public String getNodename() {
                    return nodename;
                }

                public void setNodename(String nodename) {
                    this.nodename = nodename;
                }

                public String getNodetype() {
                    return nodetype;
                }

                public void setNodetype(String nodetype) {
                    this.nodetype = nodetype;
                }

                public String getParentnodeid() {
                    return parentnodeid;
                }

                public void setParentnodeid(String parentnodeid) {
                    this.parentnodeid = parentnodeid;
                }

                public String getScenario() {
                    return scenario;
                }

                public void setScenario(String scenario) {
                    this.scenario = scenario;
                }

                public List<ChildrenBean> getChildren() {
                    return children;
                }

                public void setChildren(List<ChildrenBean> children) {
                    this.children = children;
                }

                public static class ContentDescBean {
                    /**
                     * authorList : ["红白蓝"]
                     * categoryList : ["启蒙教育"]
                     * characteristicList : ["欢快"]
                     * contentid : zhudoukuailejiatingxilie
                     * contentlang : zh
                     * contenttype : file
                     * descriptionI18List : [{"language":"zh","value":"竹兜快乐家庭系列"}]
                     * imgauthorurl :
                     * imgauthorurlhttpURL : http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=%2FU30bXSwOVSABNz%2FKH7EoD6hdCk%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D
                     * imgtitleurl : contentstorage/解忧杂货店/动画/7.竹兜快乐家庭系列25主题全集2-5/imgtitle
                     * imgtitleurlhttpURL : http://hamitaocontent.oss-cn-hangzhou.aliyuncs.com/contentstorage/%E8%A7%A3%E5%BF%A7%E6%9D%82%E8%B4%A7%E5%BA%97/%E5%8A%A8%E7%94%BB/7.%E7%AB%B9%E5%85%9C%E5%BF%AB%E4%B9%90%E5%AE%B6%E5%BA%AD%E7%B3%BB%E5%88%9725%E4%B8%BB%E9%A2%98%E5%85%A8%E9%9B%862-5/imgtitle?Expires=1530846331&OSSAccessKeyId=STS.NHaT8EGf7Q9sStbPxZMQQ9npJ&Signature=W6W17D%2BOaHSH6ro%2B8ey%2FxS4AyG0%3D&security-token=CAISjAJ1q6Ft5B2yfSjIr4vUH4LxqrkW5vuYUVLTtHgPQd591qvbqDz2IHxFfXBvA%2BgbvvwxlW5S7P8elqVoRoReREvCKM1565kPO%2Bl85ySF6aKP9rUhpMCPKwr6UmzGvqL7Z%2BH%2BU6mqGJOEYEzFkSle2KbzcS7YMXWuLZyOj%2BwIDLkQRRLqL0AFZrFsKxBltdUROFbIKP%2BpKWSKuGfLC1dysQcO7gEa4K%2BkkMqH8Uic3h%2BoiM1t%2Ft6hecD9M5MxY8YuA47qg9YbLPSRjHRijDFR77pzgaB%2B%2FjPKg8qQGVE54W%2Fdb7uNq4I1dFIpPvZrRfQe9aTm7PRgovbUk4mywh1GMPpOVD%2FEVAk6c2t8rCjDGoABruVl%2FMPS%2B44RwPwlohVX5Chn4QQUYZnLe%2Fy4RT5V8NRhpKF88HBRBeXoHbPi2HwMFvecxIzDNP0Ro9aLF7tXELR51bl4zXx5TaD7LNUZFoYH6axhjSAIhqOQj2Z3W4Cyny2c0smWeIkPtsd7qvNe58enhNDIj1mk7jwLVJpixLQ%3D
                     * keywordList : ["竹兜","竹兜快乐家庭"]
                     * lyric :
                     * mediaList : []
                     * nameI18List : [{"language":"zh","value":"竹兜快乐家庭系列"}]
                     * orientUser : {"age_max":3,"age_min":2,"sex":"any"}
                     * quality : perfect
                     * scenario : misc
                     * sourceorigin : internet+http://www.google.com.cn/aaa
                     * status : enable
                     * target : app+device
                     * vendor : none
                     */

                    private String contentid;
                    private String contentlang;
                    private String contenttype;
                    private String imgauthorurl;
                    private String imgauthorurlhttpURL;
                    private String imgtitleurl;
                    private String imgtitleurlhttpURL;
                    private String lyric;
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
                    private List<?> mediaList;
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

                    public String getImgauthorurl() {
                        return imgauthorurl;
                    }

                    public void setImgauthorurl(String imgauthorurl) {
                        this.imgauthorurl = imgauthorurl;
                    }

                    public String getImgauthorurlhttpURL() {
                        return imgauthorurlhttpURL;
                    }

                    public void setImgauthorurlhttpURL(String imgauthorurlhttpURL) {
                        this.imgauthorurlhttpURL = imgauthorurlhttpURL;
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

                    public List<?> getMediaList() {
                        return mediaList;
                    }

                    public void setMediaList(List<?> mediaList) {
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
                         * value : 竹兜快乐家庭系列
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

                    public static class NameI18ListBean {
                        /**
                         * language : zh
                         * value : 竹兜快乐家庭系列
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

                public static class ChildrenBean implements Serializable{

                    private String contentid;
                    private String nodedesc;
                    private String nodeheaderimgurl;
                    private String nodeheaderimgurlhttpURL;
                    private String nodeid;
                    private String nodename;
                    private String nodetype;
                    private String parentnodeid;
                    private String scenario;
                    private List<?> children;

                    public String getContentid() {
                        return contentid;
                    }

                    public void setContentid(String contentid) {
                        this.contentid = contentid;
                    }

                    public String getNodedesc() {
                        return nodedesc;
                    }

                    public void setNodedesc(String nodedesc) {
                        this.nodedesc = nodedesc;
                    }

                    public String getNodeheaderimgurl() {
                        return nodeheaderimgurl;
                    }

                    public void setNodeheaderimgurl(String nodeheaderimgurl) {
                        this.nodeheaderimgurl = nodeheaderimgurl;
                    }

                    public String getNodeheaderimgurlhttpURL() {
                        return nodeheaderimgurlhttpURL;
                    }

                    public void setNodeheaderimgurlhttpURL(String nodeheaderimgurlhttpURL) {
                        this.nodeheaderimgurlhttpURL = nodeheaderimgurlhttpURL;
                    }

                    public String getNodeid() {
                        return nodeid;
                    }

                    public void setNodeid(String nodeid) {
                        this.nodeid = nodeid;
                    }

                    public String getNodename() {
                        return nodename;
                    }

                    public void setNodename(String nodename) {
                        this.nodename = nodename;
                    }

                    public String getNodetype() {
                        return nodetype;
                    }

                    public void setNodetype(String nodetype) {
                        this.nodetype = nodetype;
                    }

                    public String getParentnodeid() {
                        return parentnodeid;
                    }

                    public void setParentnodeid(String parentnodeid) {
                        this.parentnodeid = parentnodeid;
                    }

                    public String getScenario() {
                        return scenario;
                    }

                    public void setScenario(String scenario) {
                        this.scenario = scenario;
                    }

                    public List<?> getChildren() {
                        return children;
                    }

                    public void setChildren(List<?> children) {
                        this.children = children;
                    }
                }
            }
        }
    }
}
