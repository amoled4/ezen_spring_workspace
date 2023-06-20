package com.myweb.www.handler;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myweb.www.domain.FileVO;
import com.myweb.www.repository.FileDAO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class FileSweeper {
	private final String UP_DIR = "D:\\_myweb\\_java\\fileUpload\\";
	@Inject
	private FileDAO fdao;
	
	@Scheduled(cron="1 16 21 ***")
	public void fileSweeper() throws Exception {
		List<FileVO> flist = fdao.getFileAllList();
		List<String> currFiles = new ArrayList<String>();
		
		for(FileVO fvo : flist) {
			String filePath = fvo.getSave_dir() + "\\" + fvo.getUuid();
			String fileName = fvo.getFile_name();
			currFiles.add(UP_DIR + filePath + "_" + fileName);
			
			if(fvo.getFile_type()>0) {
				currFiles.add(UP_DIR+ filePath+"_th_"+fileName);
			}
		}
		
		LocalDate now = LocalDate.now();
		String today = now.toString();
		today = today.replace("-", File.separator);
		
		File dir = Paths.get(UP_DIR + today).toFile();
		File[] allFileObjects = dir.listFiles();
		
		for(File file : allFileObjects) {
			String storedFileName = file.toPath().toString();
			if(!currFiles.contains(storedFileName)) {
				file.delete();
			}
		}
	}
}
