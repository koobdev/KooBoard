package com.Kcompany.Kboard.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.Kcompany.Kboard.vo.MemberVO;

@Mapper
public interface MemberMapper {

	@Select("select * from member where memId=#{memId} and memPw=#{memPw}")
	MemberVO select(MemberVO member);
	
	@Insert("insert into member value(#{memId},#{memPw},#{memMail},#{memPhone},now())")
	int insert(MemberVO member);
	
	@Update("update member set memId=#{memId}, memPw=#{memPw}, memMail=#{memMail}, "
			+ "memPhone=#{memPhone} where memId=#{memId}")
	int update(MemberVO member);
	
	@Delete("delete from member where memId=#{memId} and memPw=#{memPw}")
	int delete(MemberVO member);
	
}
