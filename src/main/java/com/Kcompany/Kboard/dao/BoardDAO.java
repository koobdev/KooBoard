package com.Kcompany.Kboard.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import com.Kcompany.Kboard.common.paging.BoardPageCriteria;
import com.Kcompany.Kboard.vo.BoardVO;

@Repository
public class BoardDAO implements IBoardDAO {

	
	private String dbId = "root";
	private String dbPw = "root";
	private String dbUrl = "jdbc:mysql://localhost:3306/kooboarddb?serverTimezone=UTC";
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	// 객체가 생성될 때 DB연동
	public BoardDAO() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	// 전체 게시글을 List로 가져옴
	public List<BoardVO> list(BoardPageCriteria pc){
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO boardVO = null;
		try {
			String sql = "select * from board where b_index > 0 order by b_index desc "
					+ "limit ?, ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, pc.getPageStart());
			psmt.setInt(2, pc.getPerPageNum());
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				boardVO = new BoardVO();
				Timestamp t = Timestamp.valueOf(rs.getString("b_createDate"));
				
				boardVO.setB_index(Integer.parseInt(rs.getString("b_index")));
				boardVO.setB_title(rs.getString("b_title"));
				boardVO.setB_content(rs.getString("b_content"));
				boardVO.setB_hit(Integer.parseInt(rs.getString("b_hit")));
				boardVO.setB_recommand(Integer.parseInt(rs.getString("b_recommand")));
				boardVO.setB_replyCount(Integer.parseInt(rs.getString("b_replyCount")));
				boardVO.setB_memId(rs.getString("b_memId"));
				boardVO.setB_createDate(t);
				
				list.add(boardVO);
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
		
		return list;
	}
	
	// 전체 게시글 수 구하기
	public int total() {
		
		int result = 0;
		String sql = "select count(b_index) from board where b_index > 0";
		try {
			
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
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
		
		return result;
	}
	
	
	// 클릭한 게시글을 읽어옴
	public BoardVO read(int index) {
		
		BoardVO boardVO = null;
		
		try {
			
			String sql = "select * from board where b_index=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, index);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				boardVO = new BoardVO();
				Timestamp t = Timestamp.valueOf(rs.getString("b_createDate"));
				
				boardVO.setB_index(Integer.parseInt(rs.getString("b_index")));
				boardVO.setB_title(rs.getString("b_title"));
				boardVO.setB_content(rs.getString("b_content"));
				boardVO.setB_hit(Integer.parseInt(rs.getString("b_hit")));
				boardVO.setB_recommand(Integer.parseInt(rs.getString("b_recommand")));
				boardVO.setB_replyCount(Integer.parseInt(rs.getString("b_replyCount")));
				boardVO.setB_memId(rs.getString("b_memId"));
				boardVO.setB_createDate(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("BoardDAO : read() Error!!");
		} finally {
			try {
				if(psmt != null) psmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return boardVO;
	}

	// 게시글의 조회수를 가져옴
	public int hitCnt(BoardVO board) {
		
		int result = 0;		
		int hitCnt = board.getB_hit();
		try {
			String sql = "update board set b_hit=? where b_index=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, ++hitCnt);
			psmt.setInt(2, board.getB_index());
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
			System.out.println("BoardDAO : hitCnt() Error!!");
		}
		
		return result;
	}
	
	// 가장 최근 데이터의 index값 가져오기
	public int recentIndex() {
		
		int result = 0;
		String sql = "select b_index from board where b_index=(select MAX(b_index) from board)";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) result = rs.getInt("b_index");
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
			System.out.println("BoardDAO : recentIndex() is '0'");
		}
		
		return result;
	}

	// 글 쓰기
	public int insert(BoardVO board, String writer) {

		int result = 0;
		int recentIndex = recentIndex();
		
		Timestamp today = Timestamp.valueOf(LocalDateTime.now());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(today);
		
		String sql = "insert into board values(?,?,?,?,?,?,?,?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, ++recentIndex);
			psmt.setString(2, board.getB_title());
			psmt.setString(3, board.getB_content());
			psmt.setInt(4, 0);
			psmt.setInt(5, 0);
			psmt.setInt(6, 0);
			psmt.setString(7, writer);
			psmt.setString(8,  time);
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
			System.out.println("BoardDAO : insert() Error!!!");
			return result;
		} else return recentIndex;
	}

	
	// 글 수정하기
	public int update(BoardVO board, String writer) {
		
		int result = 0;
		
		Timestamp today = Timestamp.valueOf(LocalDateTime.now());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = sdf.format(today);
		
		try {
			String sql = "update board set b_index=?, b_title=?, b_content=?, b_hit=?,"
					+ "b_recommand=?, b_replyCount=?, b_memId=?, b_createDate=? where b_index=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, board.getB_index());
			psmt.setString(2, board.getB_title());
			psmt.setString(3, board.getB_content());
			psmt.setInt(4, board.getB_hit());
			psmt.setInt(5, board.getB_recommand());
			psmt.setInt(6, board.getB_replyCount());
			psmt.setString(7, writer);
			psmt.setString(8, time);
			psmt.setInt(9, board.getB_index());
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
			System.out.println("BoardDAO : update() Failed");
		}
		return result;
	}

	
	// 글 삭제하기
	public int delete(int b_index) {
		
		int result = 0;
		try {
			String sql = "delete from board where b_index=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, b_index);
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
			System.out.println("BoardDAO : delete() Error!!");
		}
		return result;
	}

	
	// 게시글의 추천수를 올림
	public BoardVO recommandCnt(int index) {
		
		int result = 0;		
		try {
			String sql = "update board set b_recommand=(b_recommand+1) where b_index=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, index);
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
			System.out.println("BoardDAO : recommandCnt() Error!!");
		}
		
		BoardVO boardVO = read(index);
		return boardVO;
		
	}

}
