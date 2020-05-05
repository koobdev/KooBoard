package com.Kcompany.Kboard.dao;

//import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

import com.Kcompany.Kboard.vo.MemberVO;

@Repository
public class MemberDAO implements IMemberDAO{

	private Connection conn = null;
	private PreparedStatement psmt = null;
	//private Statement smt = null;
	private ResultSet rs = null;
	private String dbID = "root";
	private String dbPW = "root";
	private String dbURL = "jdbc:mysql://localhost:3306/kooboarddb?serverTimezone=UTC";
	
	public MemberDAO() {
		//Connection °´Ã¼
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error here 1");
		} catch(ClassNotFoundException e) {
			System.out.println("error here 2");
			e.printStackTrace();
		}
	}
	
	
	public MemberVO select(MemberVO member) {
		
		MemberVO mem = null;
		String sql = "select * from member where memId=? and memPw=?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getMemId());
			psmt.setString(2, member.getMemPw());
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				mem = new MemberVO();
				mem.setMemId(rs.getString("memId"));
				mem.setMemPw(rs.getString("memPw"));
				mem.setMemMail(rs.getString("memMail"));
				mem.setMemPhone(rs.getString("memPhone"));
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
		
		return mem;
		
	}
	
	
	
	
	public int insert(MemberVO member) {
		
		Timestamp today = Timestamp.valueOf(LocalDateTime.now());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = sdf.format(today);
		
		int result = 0;
		String sql = "insert into member value(?,?,?,?,?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getMemId());
			psmt.setString(2, member.getMemPw());
			psmt.setString(3, member.getMemMail());
			psmt.setString(4, member.getMemPhone());
			psmt.setString(5, time);
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
			System.out.println("insert Error!");
		}
		
		return result;
	}
	
	public int update(MemberVO member, MemberVO memSession) {
		
		int result = 0;
		String sql = "update member set memId=?, memPw=?, memMail=?, memPhone=?"
				+ "where memId=? and memPw=?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getMemId());
			psmt.setString(2, member.getMemPw());
			psmt.setString(3, member.getMemMail());
			psmt.setString(4, member.getMemPhone());
			psmt.setString(5, memSession.getMemId());
			psmt.setString(6, memSession.getMemPw());
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
			System.out.println("update Error!");
		}
		
		return result;
	}
	
	public int delete(MemberVO memberSession) {
		
		int result = 0;
		String sql = "delete from member where memId=? and memPw=?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, memberSession.getMemId());
			psmt.setString(2, memberSession.getMemPw());
			result = psmt.executeUpdate();
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
		
		if(result != 1) {
			System.out.println("delete Error!");
		}
		
		return result;
	}
}















