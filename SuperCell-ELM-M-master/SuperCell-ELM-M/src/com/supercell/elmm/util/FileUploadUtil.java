package com.supercell.elmm.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static List<String> upload(String savePath,List<MultipartFile> files){
		List<String> fileNameList = new ArrayList<>();
		for (MultipartFile file:files) {
			String fileName = file.getOriginalFilename();
			if (!fileName.equals("")) {
				String fileType = fileName.substring(fileName.lastIndexOf("."));
				File file2 = new File(savePath,new Date().getTime()+UUID.randomUUID().toString()+fileType);
//				System.out.println(fileName+"::::::"+file2.getName());
				try {
					file.transferTo(file2);
					fileNameList.add(file2.getName());
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileNameList;
	}
}
