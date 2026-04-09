package practice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {
    private String baseDir= System.getProperty("user.dir");
    private String uploadDir=baseDir+"/build/resources/main/static/upload/";

    public String upload(MultipartFile uploadFile){
        if(uploadFile==null || uploadFile.isEmpty() == true){return null;}
        File uploadPath=new File(uploadDir);
        if(!uploadPath.exists()){    // 업로드 파일 존재
            uploadPath.mkdir();
        }
        String uuid= UUID.randomUUID().toString();
        String fileName=uuid+"_"+uploadFile.getOriginalFilename().replace("_","-");
        File uploadRealPath=new File(uploadDir+fileName);
        try{
            uploadFile.transferTo(uploadRealPath);
            return fileName;
        }catch (Exception e) {
            System.out.println("파일 업로드 문제 = " + e);
        }
        return null;
    }
}
