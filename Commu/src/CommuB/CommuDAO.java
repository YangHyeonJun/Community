package CommuB;
import java.sql.*;
import java.util.ArrayList;
import CommuB.CommuDTO;
	// 전달 데이터단위인 (DTO : Data Transfer Object)를 사용하면서 DB 데이터를 직접 처리하는
	// DAO(Data Access Object)
	
	public class CommuDAO {
		Connection conn = null;
		PreparedStatement pstmt = null;

		/* MySQL 연결정보 */
		String jdbc_driver = "com.mysql.jdbc.Driver";
		
		String jdbc_url = "jdbc:mysql://127.0.0.1/jspdb?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";

		
		/******************************************************************************************/
		// DB연결 메서드
		/******************************************************************************************/
		void connect() {
			try {
				Class.forName(jdbc_driver);

				conn = DriverManager.getConnection(jdbc_url,"jspbook","1234");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		/******************************************************************************************/
		// DB 연결해제 메소드
		/******************************************************************************************/
		void disconnect() {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		/******************************************************************************************/
		// 입력 메서드
		/******************************************************************************************/
		public boolean insertDB(CommuDTO commuDTO) {
			
			
			connect();
			
			// id 는 자동 등록 되므로 입력하지 않는다.				
			String sql ="insert into Commu(writehead, writer, writeDate, writetext) values(?,?,?,?)";
			
			try {
				
				pstmt = conn.prepareStatement(sql);

				// SQL문에 변수 입력
				pstmt.setString(1,commuDTO.getWritehead());
				pstmt.setString(2,commuDTO.getWriter());
				pstmt.setString(3,commuDTO.getWriteDate());
				pstmt.setString(4,commuDTO.getWritetext());
				
				//SQL문 실행
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			finally {
				disconnect();
			}
			return true;
		}
		
		
		/******************************************************************************************/
		// 목록 조회 메서드
		/******************************************************************************************/
		public ArrayList<CommuDTO> getDBList() {
			
			connect();
			
			ArrayList<CommuDTO> commuList = new ArrayList<CommuDTO>();
			
			String sql = "select id, writehead, writer, writeDate, writetext from AccountTransfer";

			try {
				
				pstmt = conn.prepareStatement(sql);
				
				//SQL문 실행
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					// DO 객체 생성
					CommuDTO commuDTO = new CommuDTO();
					
					// DB Select결과를 DO 객체에 저장
					commuDTO.setId(rs.getInt("id"));
					commuDTO.setWritehead(rs.getString("writehead"));
					commuDTO.setWriter(rs.getString("writer"));
					commuDTO.setWriteDate(rs.getString("writeDate"));
					commuDTO.setWritetext(rs.getString("writetext"));
					
					commuList.add(commuDTO);
				}
				rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				disconnect();
			}
			return commuList;
		}

		/******************************************************************************************/
		// edit용  1건 조회 메서드
		/******************************************************************************************/
		public CommuDTO getDB(int id) {
			
			connect();
			
			CommuDTO commuDTO = new CommuDTO();
			
			String sql = "select * from Commu where id = ?";
			
			try {
				
				pstmt = conn.prepareStatement(sql);
				
				// SQL문에 조회조건 입력
				pstmt.setInt(1,id);

				//SQL문 실행
				ResultSet rs = pstmt.executeQuery();

				// 데이터가 하나만 있으므로 rs.next()를 한번만 실행 한다.
				rs.next();
				
				// DB Select결과를 DO 객체에 저장
				commuDTO.setId(rs.getInt("id"));
				commuDTO.setWritehead(rs.getString("writehead"));
				commuDTO.setWriter(rs.getString("writer"));
				commuDTO.setWriteDate(rs.getString("writeDate"));
				commuDTO.setWritetext(rs.getString("writetext"));
				rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				disconnect();
			}
			return commuDTO;
		}


		/******************************************************************************************/
		// 수정 메서드
		/******************************************************************************************/
		public boolean updateDB(int id, CommuDTO commuDTO) {
			
			
			connect();
			
			// id로 매칭하여 update(계좌이체일자와 계좌이체금액만 수정 가능)				
			String sql ="update Commu set writehead=?, writetext=? where id=?";
			try {
				
				pstmt = conn.prepareStatement(sql);

				// SQL문에 변수 입력
				pstmt.setString(1,commuDTO.getWritehead());
				pstmt.setString(2,commuDTO.getWritetext());
				pstmt.setInt(3,id);
			
				//SQL문 실행
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			finally {
				disconnect();
			}
			return true;
		}
		
		
		/******************************************************************************************/
		// 삭제 메서드
		/******************************************************************************************/
		public boolean deleteDB(int id) {
			
			
			connect();
			
			// id로 매칭하여 delete				
			String sql ="delete from Commu where id=?";
			
			try {
				
				pstmt = conn.prepareStatement(sql);

				// SQL문에 변수 입력
				pstmt.setInt(1,id);
							
				//SQL문 실행
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			finally {
			}
			return true;
		}
}
