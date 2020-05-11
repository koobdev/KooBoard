package com.Kcompany.Kboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.Kcompany.Kboard.common.paging.BoardPageCriteria;
import com.Kcompany.Kboard.vo.BoardVO;

@Mapper
public interface BoardMapper {

	@Select("select * from board where b_index > 0 order by b_index desc "
			+ "limit #{pageStart}, #{perPageNum}")
	List<BoardVO> list(BoardPageCriteria pc);
	
	@Select("select count(b_index) from board where b_index > 0")
	int total();
	
	@Select("select * from board where b_index=#{index}")
	BoardVO read(int index);
	
	@Update("update board set b_hit=(b_hit+1) where b_index=#{b_index}")
	int hitCnt(BoardVO board);
	
	@Select("select b_index from board where b_index=(select MAX(b_index) from board)")
	int recentIndex();
	
	@Insert("insert into board values(#{b_index},#{b_title},#{b_content},0,0,0,#{b_memId},now())")
	int insert(BoardVO board);
	
	@Update("update board set b_index=#{b_index}, b_title=#{b_title}, b_content=#{b_content}, b_hit=#{b_hit}, "
			+ "b_recommand=#{b_recommand}, b_replyCount=#{b_replyCount}, b_memId=#{b_memId} where b_index=#{b_index}")
	int update(BoardVO board);
	
	@Delete("delete from board where b_index=#{b_index}")
	int delete(int b_index);
	
	@Update("update board set b_recommand=(b_recommand+1) where b_index=#{b_index}")
	int recommandCnt(int index);
}








