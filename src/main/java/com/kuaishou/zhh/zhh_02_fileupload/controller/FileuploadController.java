package com.kuaishou.zhh.zhh_02_fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping(value="/fileUpload")
public class FileuploadController {
	@RequestMapping(value="/upload",method=RequestMethod.GET)
	@ResponseBody
	public String upload(){
		System.out.println("hello------------");
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/multiUpload", method = RequestMethod.POST)
	public String multiUpload(HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest mre;
		if(!(request instanceof MultipartHttpServletRequest)){
			Class<? extends HttpServletRequest> cla = request.getClass();
			Class cz = Class.forName("org.springframework.security.web.servletapi.HttpServlet3RequestFactory$Servlet3SecurityContextHolderAwareRequestWrapper");
			List<Object> list = new ArrayList<Object>();
			while(cz!=null){
				boolean bb = false;
				cz = cz.getSuperclass();
				if(cz!=null){
					Field[] ls = cz.getDeclaredFields();
					for(Field ld:ls){
						if("request".equals(ld.getName())){
							System.out.println("-----------xxxxx---------");
							ld.setAccessible(true);
							System.out.println("field request-----"+ld.get(request));
							list.add(ld.get(request));
							bb = true;
						}
					}
				}
				if(bb){
					break;
				}
			}
			Class ll = Class.forName("org.springframework.security.web.context.HttpSessionSecurityContextRepository$Servlet3SaveToSessionRequestWrapper");
			Field[] ft = ll.getDeclaredFields();
			for(Field f:ft){
				f.setAccessible(true);
				System.out.println(f.getName()+"----"+f.get(list.get(0)));
			}
			Object destobj = null;
			while(ll!=null){
				boolean bb = false;
				ll = ll.getSuperclass();
				if(ll!=null){
					Field[] ls = ll.getDeclaredFields();
					for(Field ld:ls){
						if("request".equals(ld.getName())){
							System.out.println("-----------xxxxx---------");
							ld.setAccessible(true);
							System.out.println("field request-----"+ld.get(list.get(0)));
							destobj = ld.get(list.get(0));
							bb = true;
						}
					}
				}
			}
			
			Object oo = findRequest(destobj.getClass(),destobj);
			
			
			mre = (MultipartHttpServletRequest)oo;
			
			MultipartHttpServletRequest re = (MultipartHttpServletRequest)oo;
			
			
			
		}else{
			mre = (MultipartHttpServletRequest)request;
		}
		
		
		
		//Class<?> calz = cla.getDeclaredClasses()[0];
		List<MultipartFile> files = mre.getFiles("files");
		//List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("files");
		//List<MultipartFile> files = re.getFiles("files");
		for(int i=0;i<files.size();i++){
			MultipartFile file = files.get(i);
			if(file.isEmpty()){
				return "上传第"+i+"个文件失败";
			}
			//String fileName = file.getOriginalFilename();
			String fileName = file.getName();
			System.out.println("fileName===="+fileName);
			File dest;
			if(fileName.lastIndexOf(".")<0){
				dest = new File("/Users/zhonghanhui/software/eclipse/workspace/zhh-02-fileupload/temp.xlsx");
			}else{
				String type = fileName.substring(fileName.lastIndexOf("."));
				dest = new File("/Users/zhonghanhui/software/eclipse/workspace/zhh-02-fileupload/"+i+type);
			}
			
			System.out.println(dest.getAbsolutePath());
			try{
				file.transferTo(dest);
				
			}catch(IOException e){
				e.printStackTrace();
				return "上传第"+i+"哥文件失败";
			}
			
		}
		return "上传成功";
	}
	
	private Object findRequest(Class zz, Object o) throws Exception{
		while(zz!=null){
			boolean bb = false;
			zz = zz.getSuperclass();
			if(zz!=null){
				Field[] ls = zz.getDeclaredFields();
				for(Field ld:ls){
					if("request".equals(ld.getName())){
						System.out.println("-----------xxxxx---------");
						ld.setAccessible(true);
						System.out.println("field request-----"+ld.get(o));
						return ld.get(o);
					}
				}
			}
		}
		return null;
	}
}
