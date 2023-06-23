package com.myweb.www.handler;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.BFileVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@AllArgsConstructor
@Component
public class FileHandler {
	private final String UP_DIR = "D:\\_myweb\\_java\\fileUpload";

	public List<BFileVO> uploadFiles(MultipartFile[] files) {
		LocalDate date = LocalDate.now();
		String today = date.toString(); // 2022-01-10
		today = today.replace("-", File.separator); // 2022\01\10(win), 2022/01/10(linux)
		
		File folders = new File(UP_DIR, today);
		
		if (!folders.exists()) {
			folders.mkdirs();
		}
		List<BFileVO> bfList = new ArrayList<BFileVO>();
		for (MultipartFile file : files) {
			BFileVO fvo = new BFileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			log.debug(">>> originalFileName : {}", originalFileName);
			
			String onlyFileName = originalFileName.substring(
					originalFileName.lastIndexOf(File.separator)+1);
			fvo.setFileName(onlyFileName);
			
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());
			// 여기까지는 fvo에 저장할 파일 정보 생성
			
			// 아래부터는 디스크에 저장할 파일 객체 생성
			String fullFileName = uuid.toString() + "_" + onlyFileName;
			File storeFile = new File(folders, fullFileName);
			
			try {
				file.transferTo(storeFile); //원본객체를 저장을 위한 형태로 만든 객체로 복사
				if (isImageFile(storeFile)) {
					fvo.setFileType(1);
					File thumbNail = new File(folders,
							uuid.toString() + "_th_" + onlyFileName);
					Thumbnails.of(storeFile).size(100, 100).toFile(thumbNail);
				}
			} catch (Exception e) {
				log.debug(">>> File 생성 오류!!!");
				e.printStackTrace();
			}
			bfList.add(fvo);
		}				
		return bfList;
	}

	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile);		
		return mimeType.startsWith("image") ? true : false;
	}
}
