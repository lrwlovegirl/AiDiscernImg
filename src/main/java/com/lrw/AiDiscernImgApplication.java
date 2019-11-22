package com.lrw;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lrw.utils.img.VCodeCheckUtils;
//import com.lrw.utils.HttpClientUtil;

@SpringBootApplication
public class AiDiscernImgApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiDiscernImgApplication.class, args);
		/*String auth = AuthService.getAuth();
		System.out.println(auth);
		Map<String,String> parmamap = new HashMap<>();
		parmamap.put("access_token", auth);
		String result = HttpClientUtil.doPost("https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic", parmamap);*/
		
	}

}
