package com.Kcompany.Kboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.Kcompany.Kboard.common.paging.ReplyPageCriteria;
import com.Kcompany.Kboard.vo.IReplyVO;

@Mapper
public interface IReplyMapper {

	@Select("select * from ireply where b_index=#{index} order by r_index desc "
			+ "limit #{pc.pageStart},#{pc.perPageNum}")
	List<IReplyVO> selectlist(@Param("index") int index, @Param("pc") ReplyPageCriteria pc);
	
	@Select("select count(r_index) from ireply where b_index=#{index}")
	int totalCount(int index);
	
	@Select("select r_index from ireply where r_index=(select MAX(r_index) from ireply)")
	int recentIndex();
	
	@Insert("insert into ireply values(#{ireply.r_index},#{sessionId},#{ireply.r_content},now(),#{ireply.b_index})")
	int insert(@Param("ireply") IReplyVO reply, @Param("sessionId") String sessionId);
	
	@Update("update ireply set r_content=#{ireply.r_content} where r_index=#{ireply.r_index} and r_memId=#{sessionId} and b_index=#{ireply.b_index}")
	int update(@Param("ireply")IReplyVO reply, @Param("sessionId") String sessionId);
	
	@Delete("delete from ireply where r_index=#{r_index} and r_memId=#{sessionId}")
	int delete(@Param("r_index") int r_index, @Param("sessionId") String sessionId);
	
	@Delete("delete from ireply where b_index=#{b_index}")
	int deleteBoard(@Param("b_index") int b_index);

}
