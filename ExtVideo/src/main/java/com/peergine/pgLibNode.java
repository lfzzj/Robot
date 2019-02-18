/*************************************************************************
  copyright   : Copyright (C) 2014-2017, Peergine, All rights reserved.
              : www.peergine.com, www.pptun.com
              :
  filename    : pgLibNode.java
  discription : 
  modify      : create, chenbichao, 2017/08/23

*************************************************************************/

package com.peergine;


import android.content.Context;

import com.peergine.plugin.lib.pgLibJNINode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ctkj
 * Node 对象初始化管理类
 */
public class pgLibNode {

	public pgLibNode() {
	}

	/**
	 * 通过上下文初始化Node JNI对象。如果没有一个APP没有这个操作，Node对象的方法将无效。
	 * @param oCtx 上下文
	 * @return true 成功，false 失败
	 */
	public static boolean NodeLibInit(Context oCtx) {
		try {
			boolean bResult = false;
			synchronized(s_iNodeLibInitCount) {
				if (s_iNodeLibInitCount.get() > 0) {
					s_iNodeLibInitCount.getAndIncrement();
					bResult = true;
				}
				else {
					if (pgLibJNINode.Initialize(oCtx)) {
						s_iNodeLibInitCount.getAndIncrement();
						bResult = true;
					}
				}
			}
			return bResult;
		}
		catch (Exception e) {
			OutString("pgLibNode.NodeLibInit: e=" + e.toString());
			return false;
		}
	}

	/**
	 * 清除初始化信息，如果s_iNodeLibInitCount 为0，Node对象的方法将无效。
	 */
	public static void NodeLibClean() {
		try {
			synchronized(s_iNodeLibInitCount) {
				if (s_iNodeLibInitCount.get() > 0) {
					s_iNodeLibInitCount.getAndDecrement();
					if (s_iNodeLibInitCount.get() == 0) {
						pgLibJNINode.Clean();
					}
				}
			}
		}
		catch (Exception e) {
			OutString("pgLibNode.NodeLibClean: e=" + e.toString());
		}		
	}

	// Node lib init count.
	private static AtomicInteger s_iNodeLibInitCount = new AtomicInteger();

	public static void OutString(String sOut) {
		System.out.println(sOut);
	}

	public static int ParseInt(String sVal, int idefVal) {
		try {
			if ("".equals(sVal)) {
				return idefVal;
			}
			return Integer.parseInt(sVal);
		} catch (Exception ex) {
			return idefVal;
		}
	}
}
