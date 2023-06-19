package com.myweb.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.FileVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@AllArgsConstructor
@Slf4j
@Component
public class FileHandler {
	private final String UP_DIR = "D:\\_myweb\\_java\\fileUpload";
	
	public List<FileVO> uploadFiles(MultipartFile[] files){
		LocalDate date = LocalDate.now();
		String today = date.toString();
		today = today.replace("-", File.separator);
		
		File folders = new File(UP_DIR, today);
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		List<FileVO> flist = new ArrayList<FileVO>();
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSave_dir(today);
			fvo.setFile_size(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String onlyFileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1);
			fvo.setFile_name(onlyFileName);
			
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());
			
			String fullfileName = uuid.toString()+"_"+onlyFileName;
			File storeFile = new File(folders, fullfileName);
			
			try {
				file.transferTo(storeFile);
				if(isImageFile(storeFile)) {
					fvo.setFile_type(1);
					File thumbNail = new File(folders, uuid.toString()+"_th_"+onlyFileName);
					Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			flist.add(fvo);
		}
		return flist;
	}
	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile);
		return mimeType.startsWith("image")? true : false;
	}
}
