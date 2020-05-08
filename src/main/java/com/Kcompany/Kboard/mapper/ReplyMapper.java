package com.Kcompany.Kboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.Kcompany.Kboard.common.paging.ReplyPageCriteria;
import com.Kcompany.Kboard.vo.ReplyVO;

@Mapper
public interface ReplyMapper {

	@Select("select * from reply where b_index=#{index} order by r_index desc "
			+ "limit #{pc.pageStart},#{pc.perPageNum}")
	List<ReplyVO> selectlist(@Param("index") int index, @Param("pc") ReplyPageCriteria pc);
	
	@Select("select count(r_index) from reply where b_index=#{index}")
	int totalCount(int index);
	
	@Select("select r_index from reply where r_index=(select MAX(r_index) from reply)")
	int recentIndex();
	
	@Insert("insert into reply values(#{reply.r_index},#{sessionId},#{reply.r_content},now(),#{reply.b_index})")
	int insert(@Param("reply") ReplyVO reply, @Param("sessionId") String sessionId);
	
	@Update("update reply set r_content=#{reply.r_content} where r_index=#{reply.r_index} and r_memId=#{sessionId} and b_index=#{reply.b_index}")
	int update(@Param("reply")ReplyVO reply, @Param("sessionId") String sessionId);
	
	@Delete("delete from reply where r_index=#{r_index} and r_memId=#{sessionId}")
	int delete(@Param("r_index") int r_index, @Param("sessionId") String sessionId);
	
	@Delete("delete from reply where b_index=#{b_index}")
	int deleteBoard(@Param("b_index") int b_index);

}
