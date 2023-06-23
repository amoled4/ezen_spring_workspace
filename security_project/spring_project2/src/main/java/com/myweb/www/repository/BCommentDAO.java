package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.BCommentVO;
import com.myweb.www.domain.PagingVO;

public interface BCommentDAO {

	int postComment(BCommentVO cvo);

	int selectOneBCommentTotalCount(long bno);

	List<BCommentVO> selectListBComment(@Param("bno") long bno, @Param("pgvo") PagingVO pgvo);

	int updateBComment(BCommentVO cvo);

	int deleteBComment(long cno);

	void deleteAllBComment(long bno);

	Long selectOneBno(long cno);

}
