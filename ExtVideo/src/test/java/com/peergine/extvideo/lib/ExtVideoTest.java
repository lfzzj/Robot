package com.peergine.extvideo.lib;

import org.testng.annotations.Test;

import static java.lang.Thread.sleep;
import static org.testng.Assert.*;

/**
 * Created by ctkj on 2017/11/13.
 */
public class ExtVideoTest {
    ExtVideo extVideo = new ExtVideo();
    @Test(timeOut = 1200)
    public void testTest1() throws Exception {
        extVideo.test();
        sleep(1100);
    }

    @Test(timeOut = 1200)
    public void test_TimerStart() throws Exception {
        extVideo._TimerInit();
        int t1 = extVideo._TimerStart("hello 1",1);
        int t2 = extVideo._TimerStart("hello 2",1);
        int t3 = extVideo._TimerStart("hello 3",1);
        int t4 = extVideo._TimerStart("hello 4",1);
        sleep(1100);

    }

}