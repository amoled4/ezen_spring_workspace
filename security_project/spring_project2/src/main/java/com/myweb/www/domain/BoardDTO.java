package com.myweb.www.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Setter
@Getter
public class BoardDTO {
	private BoardVO bvo;
	private List<BFileVO> bfList;
}
