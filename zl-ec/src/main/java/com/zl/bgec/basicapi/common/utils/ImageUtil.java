package com.zl.bgec.basicapi.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;
@Service
public class ImageUtil {
	@Resource
    private ContentPublisher contentPublisher;
	public String uploadImg(File file,String path,String catNo) {
        List<String> imgFiles = new ArrayList<String>();
        String str = "/";
		imgFiles.add(catNo+str+file.getName());
        this.contentPublisher.publish(path, imgFiles);
        return catNo+"/"+file.getName();
	}
	
	public String saveImg(String imgStr,String catNo,String picType)throws Exception{
			imgStr = imgStr == null?imgStr:imgStr.trim();	
		   if (imgStr == null||imgStr.equals("")) //图像数据为空  
	            return ""; 
		   if(!imgStr.startsWith("data:image")){
			   return imgStr;
		   }
		   imgStr = imgStr.replace(" ", "+");
		   String[] imgs = imgStr.split(",");
		   String imgPre = imgs[0];
		   imgStr = imgs[1];
		   String fileType = imgPre.substring(imgPre.indexOf("/")+1,imgPre.indexOf(";"));
		   fileType = fileType!=null&&fileType.toLowerCase().equals("jpeg")?"jpg":fileType;
	        BASE64Decoder decoder = new BASE64Decoder();  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i< b.length;++i)  
            {  
                if(b[i]< 0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            String absolutePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            absolutePath = absolutePath.replaceAll("/WEB-INF/classes/", "");
            absolutePath = absolutePath.substring(1);
            //生成jpeg图片  
            String str = "/";
            String imgFilePath = absolutePath+str+picType+str+catNo+str+System.currentTimeMillis()+new Random().nextInt()+"."+fileType;//新生成的图片 
            File fileDir = new File(absolutePath+str+picType+str+catNo+str);
            if(!fileDir.exists()){
            	fileDir.mkdirs();
            }
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return uploadImg(new File(imgFilePath),absolutePath+str,picType+str+catNo);  
	}
public static void main(String[] args) {
//	String path = "/Users/Stanley/Documents/workspaces/workspace_2015/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/EDS_COMMODITY/WEB-INF/classes";
//	System.out.println(path.replaceAll("/WEB-INF/classes", ""));
//	System.out.println(str);
	
	String str = "data:image/jpeg;";
	System.out.println(str.substring(str.indexOf("/")+1,str.indexOf(";")));
}
}
