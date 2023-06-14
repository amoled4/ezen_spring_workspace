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
		log.info(">>> date : "+date);
		String today = date.toString();    // 2023-06-14 <= date 객체를 String 변환
		// 해가 바뀌면 월/일이 겹치기 때문에 년/월/일 계층적으로 폴더 생성
		// today => 폴더 구조로 변경 2023\06\14(window) 2023/01/01(Linux)
		today = today.replace("-", File.separator);
		
		// today 폴더 구성
		File folders = new File(UP_DIR, today);   // 경로 구성
		
		// 폴더가 기존에 있다면 생성X, 없다면 생성O
		if(!folders.exists()) {
			folders.mkdirs();      // 폴더 생성 명령어
		}
		
		// 경로 설정
		List<FileVO> fList = new ArrayList<FileVO>();
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSave_dir(today);            // 파일 경로 설정
			fvo.setFile_size(file.getSize());  // 파일 사이즈 설정(return형태가 Long)
			
			// 경로가 포함되어 있을 수도 있는 파일명
			String originalFileName = file.getOriginalFilename();
			String onlyFileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1); // 실제 파일명만 추출
			log.info(">>> 파일명 : "+onlyFileName);
			fvo.setFile_name(onlyFileName);   // 파일 이름 설정
			
			// UUID 생성
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());     // uuid 설정(toString으로 설정해야 함)
			
			// <-------- 여기까지 fvo에 저장할 정보 생성
			
			// 디스크에 파일 저장할 객체 생성 후 저장
			String fullfileName = uuid.toString()+"_"+onlyFileName;
			File storeFile = new File(folders, fullfileName);
			
			try {
				file.transferTo(storeFile);     // 원본 객체를 저장하기 위한 형태의 객체로 복사
				// 파일 타입 결정 후 이미지 파일이라면 섬네일 생성
				if(isImageFile(storeFile)) {
					fvo.setFile_type(1);
					File thumbNail = new File(folders, uuid.toString()+"_th_"+onlyFileName);
					Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
				}
			} catch (Exception e) {
				log.info(">>> 파일 생성 오류");
				e.printStackTrace();
			}
			fList.add(fvo);
		}
		return fList;
	}
	// tika를 사용하여 파일 형식 체크 -> 이미지 파일이 맞는지 체크
	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile);   // 해당하는 이미지의 타입을 추출 : image/jpg, image/png
		return mimeType.startsWith("image")? true : false;  // startWith("") : ""가 앞부분에 있는지 확인
	}
}
