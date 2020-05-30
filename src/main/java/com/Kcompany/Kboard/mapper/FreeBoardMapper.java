package com.Kcompany.Kboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.Kcompany.Kboard.common.paging.BoardPageCriteria;
import com.Kcompany.Kboard.vo.FBoardVO;

@Mapper
public interface FreeBoardMapper {

	@Select("select * from board where b_index > 0 order by b_index desc "
			+ "limit #{pageStart}, #{perPageNum}")
	List<FBoardVO> list(BoardPageCriteria pc);
	
	@Select("select count(b_index) from board where b_index > 0")
	int total();
	
	@Select("select * from board where b_index=#{index}")
	FBoardVO read(int index);
	
	@Update("update board set b_hit=(b_hit+1) where b_index=#{b_index}")
	int hitCnt(FBoardVO board);
	
	@Select("select b_index from board where b_index=(select MAX(b_index) from board)")
	int recentIndex();
	
	@Insert("insert into board values(#{b_index},#{b_title},#{b_content},0,0,0,#{b_memId},now())")
	int insert(FBoardVO board);
	
	@Update("update board set b_index=#{b_index}, b_title=#{b_title}, b_content=#{b_content}, b_hit=#{b_hit}, "
			+ "b_recommend=#{b_recommend}, b_replyCount=#{b_replyCount}, b_memId=#{b_memId} where b_index=#{b_index}")
	int update(FBoardVO board);
	
	@Delete("delete from board where b_index=#{b_index}")
	int delete(int b_index);
	
	@Update("update board set b_recommend=(b_recommend+1) where b_index=#{b_index}")
	int recommendCnt(int index);
}








