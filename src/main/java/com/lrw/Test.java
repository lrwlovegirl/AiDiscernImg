package com.lrw;

import com.lrw.utils.img.VCodeCheckUtils;

public class Test {
	public static void main(String[] args) {
		String ocrvCode = VCodeCheckUtils.OCRVCode("https://new.cnzz.com/v1/images/validate.php?t=0.31848118645817425");
		System.out.println(ocrvCode);
	}
}
