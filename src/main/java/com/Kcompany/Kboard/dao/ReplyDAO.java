package com.Kcompany.Kboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.Kcompany.Kboard.common.paging.ReplyPageCriteria;
import com.Kcompany.Kboard.vo.ReplyVO;

@Repository
public class ReplyDAO implements IReplyDAO {

	
	private String dbId = "root";
	private String dbPw = "root";
	private String dbUrl = "jdbc:mysql://localhost:3306/kooboarddb?serverTimezone=UTC";
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null; 
	
	
	public ReplyDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ReplyVO> selectlist(int index, ReplyPageCriteria pc){
		
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		ReplyVO replyVO = null;
		String sql = "select * from reply where b_index=? order by r_index desc "
				+ "limit ?,?";
				
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, index);
			psmt.setInt(2, pc.getPageStart());
			psmt.setInt(3, pc.getPerPageNum());
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				replyVO = new ReplyVO();
				Timestamp t = Timestamp.valueOf(rs.getString("r_createDate"));
				
				replyVO.setR_index(Integer.parseInt(rs.getString("r_index")));
				replyVO.setR_memId(rs.getString("r_memId"));
				replyVO.setR_content(rs.getString("r_content"));
				replyVO.setR_createDate(t);
				replyVO.setB_index(Integer.parseInt(rs.getString("b_index")));
				
				list.add(replyVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(psmt != null) psmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public int totalCount(int index) {
		
		int result = 0;
		String sql = "select count(r_index) from reply where b_index=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, index);
			rs = psmt.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(psmt != null) psmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(result == 0) {
			System.out.println("ReplyDAO : recentIndex() is '0'");
		}
		
		return result;
	}
	
	// 최근 댓글의 인덱스를 구함
	public int recentIndex() {
		
		int result = 0;
		String sql = "select r_index from reply where r_index=(select MAX(r_index) from reply)";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) result = rs.getInt("r_index");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(psmt != null) psmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(result == 0) {
			System.out.println("ReplyDAO : recentIndex() is '0'");
		}
		
		return result;
	}
	
	public ReplyVO select(ReplyVO replyVO, int index) {
		
		ReplyVO reply = null;
		
		String sql = "select * from reply where r_index=? and b_index=?";
		Timestamp today = Timestamp.valueOf(LocalDateTime.now());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = sdf.format(today);
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, replyVO.getR_index());
			psmt.setInt(2, replyVO.getB_index());
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				reply = new ReplyVO();
				reply.setR_index(rs.getInt("r_index"));
				reply.setR_memId(rs.getString("r_memId"));
				reply.setR_content(rs.getString("r_content"));
				reply.setR_createDate(rs.getTimestamp(time));
				reply.setB_index(index);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(psmt != null) psmt.close();
				if(rs!= null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return reply;
	}
	
	public int insert(ReplyVO reply, String memId) {
		
		int result = 0;
		int recentIndex = recentIndex();
		
		String sql = "insert into reply values(?,?,?,?,?)";
		Timestamp today = Timestamp.valueOf(LocalDateTime.now());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = sdf.format(today);
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, ++recentIndex);
			psmt.setString(2, memId);
			psmt.setString(3, reply.getR_content());
			psmt.setString(4, time);
			psmt.setInt(5, reply.getB_index());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result != 1) {
			System.out.println("ReplyDAO : write() Error!!");
			return result;
		} else return recentIndex;
		
	}
	
	public int update(ReplyVO reply, String memId) {
		
		int result = 0;
		String sql = "update reply set r_content=?, r_createDate=? where r_index=? and r_memId=? and b_index=?";
		Timestamp today = Timestamp.valueOf(LocalDateTime.now());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = sdf.format(today);
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, reply.getR_content());
			psmt.setString(2, time);
			psmt.setInt(3, reply.getR_index());
			psmt.setString(4, memId);
			psmt.setInt(5, reply.getB_index());
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(result != 1) {
			System.out.println("ReplyDAO : update() Error!!");
		}
		
		return result;
	}

	public int delete(int r_index, String memId) {
	
		int result = 0;
		try {
			String sql = "delete from reply where r_index=? and r_memId=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, r_index);
			psmt.setString(2, memId);
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(psmt != null) psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}			
		
		if(result != 1) {
			System.out.println("ReplyDAO : delete() Error!!");
		}
		return result;
	}
	
}















