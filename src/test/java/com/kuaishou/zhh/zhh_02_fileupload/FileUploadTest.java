package com.kuaishou.zhh.zhh_02_fileupload;

import java.io.FileInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FileUploadTest {
	@Autowired
	private WebApplicationContext wac;
	
	@Test
	public void test() throws Exception{
		MockMultipartFile mockFile = new MockMultipartFile("files", new FileInputStream("/Users/zhonghanhui/Downloads/trafficTemplate.xlsx"));
		MockMvc mock = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
		mock.perform(MockMvcRequestBuilders.multipart("/fileUpload/multiUpload").file(mockFile)).andDo(print());
	}
}
