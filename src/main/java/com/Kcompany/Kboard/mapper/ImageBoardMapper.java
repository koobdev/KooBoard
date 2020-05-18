package com.Kcompany.Kboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.Kcompany.Kboard.common.paging.IBoardPageCriteria;
import com.Kcompany.Kboard.vo.IBoardVO;

@Mapper
public interface ImageBoardMapper {

	@Select("select * from iboard where b_index > 0 order by b_index desc "
			+ "limit #{pageStart}, #{perPageNum}")
	List<IBoardVO> list(IBoardPageCriteria pc);
	
	@Select("select count(b_index) from iboard where b_index > 0")
	int total();
	
	@Select("select * from iboard where b_index=#{index}")
	IBoardVO read(int index);
	
	@Update("update iboard set b_hit=(b_hit+1) where b_index=#{b_index}")
	int hitCnt(IBoardVO board);
	
	@Select("select b_index from iboard where b_index=(select MAX(b_index) from iboard)")
	int recentIndex();
	
	@Insert("insert into iboard values(#{b_index},#{b_title},#{b_content},#{b_fileName},0,0,0,#{b_memId},now())")
	int insert(IBoardVO board);
	
	@Update("update iboard set b_index=#{b_index}, b_title=#{b_title}, b_content=#{b_content}, b_fileName=#{b_fileName}, b_hit=#{b_hit}, "
			+ "b_recommend=#{b_recommend}, b_replyCount=#{b_replyCount}, b_memId=#{b_memId} where b_index=#{b_index}")
	int update(IBoardVO board);
	
	@Delete("delete from iboard where b_index=#{b_index}")
	int delete(int b_index);
	
	@Update("update iboard set b_recommend=(b_recommend+1) where b_index=#{b_index}")
	int recommandCnt(int index);
}








