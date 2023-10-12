package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.Kms_WriteList_Dto;

public class Kms_WriteList_Dao {
	
	DataSource dataSource;
	
	public Kms_WriteList_Dao() {
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Kms_WriteList_Dto> list(int Ftype){
		ArrayList<Kms_WriteList_Dto> dtos = new ArrayList<Kms_WriteList_Dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select f.*,p.pname,c.cname from forum f,product p, customer c";
			String query1 = " where p.pid = f.f_pid and c.cid = f.f_cid and ftype = ? and fdeletedate is null order by fref desc,freforder";
			preparedStatement = connection.prepareStatement(query + query1);
			preparedStatement.setInt(1, Ftype);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int fid = resultSet.getInt(1);
				String f_cid = resultSet.getString(2);
				String f_aid = resultSet.getString(3);
				int f_pid = resultSet.getInt(4);
				int ftype = resultSet.getInt(5);
				int fref = resultSet.getInt(6);
				int freforder = resultSet.getInt(7);
				int fstep = resultSet.getInt(8);
				int fsteporder = resultSet.getInt(9);
				String ftitle = resultSet.getString(10);
				String fcontent = resultSet.getString(11);
				Timestamp finsertdate = resultSet.getTimestamp(12);
				Timestamp fdeletedate = resultSet.getTimestamp(13);
				int fmotherid = resultSet.getInt(14);
				int fanswernum = resultSet.getInt(15);
				String pname = resultSet.getString(16);
				String cname = resultSet.getString(17);
				
				
				
				Kms_WriteList_Dto dto = new Kms_WriteList_Dto(fid, f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, ftitle, fcontent, 
						finsertdate, fdeletedate, fmotherid, fanswernum, fsteporder, pname, cname);
				dtos.add(dto);
				
			
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
		
	} // list
	
	public void forumAction(String f_cid, int f_pid,String ftitle, String fcontent) {
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		
		try {
			connection = dataSource.getConnection();
			String query2 = "insert into forum (f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, fsteporder,ftitle, fcontent, finsertdate, fmotherid, fanswernum)";
			String query3 = " select ?,'admin', ?, 1 , max(fref) + 1, 0,0, 0,?,?,now(),0,0 from forum";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
			preparedStatement1.setString(1, f_cid);
			preparedStatement1.setInt(2, f_pid);
			preparedStatement1.setString(3, ftitle);
			preparedStatement1.setString(4, fcontent);
			
			
			preparedStatement1.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement1 != null) preparedStatement1.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	} // 답글 
	
	public void replyAction(int fid, String f_cid, int f_pid, int fref, int fstep, int freforder,
			String ftitle, String fcontent, int fmotherid, int fanswernum) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;
		int a = freforder + fanswernum;
		try {
			connection = dataSource.getConnection();
			String query = "update forum set freforder = freforder + 1 where freforder >=" + freforder + " + " + fanswernum + " + 1";
			String query1 = " and fref = " + fref;
			preparedStatement = connection.prepareStatement(query + query1);
			preparedStatement.executeUpdate();
			
			String query2 = "insert into forum (f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, ftitle, fcontent, finsertdate, fmotherid, fanswernum)";
			String query3 = " values (?,'admin', ?, 1 ,?, ? + 1, ? + 1,? ,? ,now(), 0, 0)";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
			preparedStatement1.setString(1, f_cid);
			preparedStatement1.setInt(2, f_pid);
			preparedStatement1.setInt(3, fref);
			preparedStatement1.setInt(4, a);
			preparedStatement1.setInt(5, fstep);
			preparedStatement1.setString(6, ftitle);
			preparedStatement1.setString(7, fcontent);
			preparedStatement1.executeUpdate();
				
			String query4 = "update forum set fanswernum = fanswernum + 1 where fid = " + fid;
			preparedStatement2 = connection.prepareStatement(query4);
			preparedStatement2.executeUpdate();
			
			String query5 = "update forum set fanswernum = fanswernum + 1 where fref =" + fref + " and freforder =" + freforder;
			preparedStatement3 = connection.prepareStatement(query5);
			preparedStatement3.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(preparedStatement1 != null) preparedStatement1.close();
				if(preparedStatement2 != null) preparedStatement2.close();
				if(preparedStatement3 != null) preparedStatement3.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	} // 답글 
	
	public Kms_WriteList_Dto ForumView(int fid) {
		Kms_WriteList_Dto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select f.*, p.pname from forum f, product p where p.pid = f.f_pid and f.fid = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, fid);
			rs = preparedStatement.executeQuery();
			
			if(rs.next()) { // 한 줄 짜리니까 while 말고 if 쓰자
				int Fid = rs.getInt(1);
				String f_cid = rs.getString(2);
				String f_aid = rs.getString(3);
				int f_pid = rs.getInt(4);
				int ftype = rs.getInt(5);
				int fref = rs.getInt(6);
				int freforder = rs.getInt(7);
				int fstep = rs.getInt(8);
				int fsteporder = rs.getInt(9);
				String ftitle = rs.getString(10);
				String fcontent = rs.getString(11);
				Timestamp finsertdate = rs.getTimestamp(12);
				Timestamp fdeletedate = rs.getTimestamp(13);
				int fmotherid = rs.getInt(14);
				int fanswernum = rs.getInt(15);
				String pname = rs.getString(16);
				
				
				dto = new Kms_WriteList_Dto(Fid, f_cid, f_aid, f_pid, ftype, fref, freforder, 
						fstep, ftitle, fcontent, finsertdate, fdeletedate, fmotherid, fanswernum, pname, fsteporder);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return dto;

	} // content view
	
	public ArrayList<Kms_WriteList_Dto> commentList(int fid){
		ArrayList<Kms_WriteList_Dto> dtos1 = new ArrayList<Kms_WriteList_Dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select f.*, c.cname from forum f, customer c where f.f_cid = c.cid and ftype = 3 and fmotherid = ? order by fref desc,freforder,fsteporder,fstep";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, fid);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int Fid = resultSet.getInt(1);
				String f_cid = resultSet.getString(2);
				String f_aid = resultSet.getString(3);
				int f_pid = resultSet.getInt(4);
				int ftype = resultSet.getInt(5);
				int fref = resultSet.getInt(6);
				int freforder = resultSet.getInt(7);
				int fstep = resultSet.getInt(8);
				int fsteporder = resultSet.getInt(9);
				String ftitle = resultSet.getString(10);
				String fcontent = resultSet.getString(11);
				Timestamp finsertdate = resultSet.getTimestamp(12);
				Timestamp fdeletedate = resultSet.getTimestamp(13);
				int fmotherid = resultSet.getInt(14);
				int fanswernum = resultSet.getInt(15);
				String cname = resultSet.getString(16);
				
				
				
				Kms_WriteList_Dto dto = new Kms_WriteList_Dto(Fid, f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, 
						ftitle, fcontent, finsertdate, fdeletedate, fmotherid, fanswernum, cname, fsteporder);
				dtos1.add(dto);
				
			
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtos1;
		
	} // 댓글list
	
	public void commentAction(String f_cid, int f_pid, String ftitle, int fid,int fsteporder) {
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		
		try {
			connection = dataSource.getConnection();
			String query2 = "insert into forum (f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, fsteporder, ftitle, fcontent, finsertdate, fmotherid, fanswernum)";
			String query3 = " select ?,'admin', ?, 3 , max(fref) + 1, 0, 0, 0,?, ?, now(),?,0 from forum";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
			preparedStatement1.setString(1, f_cid);
			preparedStatement1.setInt(2, f_pid);
			preparedStatement1.setString(3, ftitle);
			preparedStatement1.setString(4, ftitle);
			preparedStatement1.setInt(5, fid);
			
			
			preparedStatement1.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement1 != null) preparedStatement1.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	} // 댓글 
	
	public Kms_WriteList_Dto bigCommentAction(int fid, String f_cid, int f_pid, int fref, int fstep, int freforder,
	        String ftitle, String fcontent, int fmotherid, int fanswernum, int fsteporder) {
	    Connection connection = null;
	    PreparedStatement preparedStatement4 = null;
	    PreparedStatement preparedStatement3 = null;
	    PreparedStatement preparedStatement = null;
	    PreparedStatement preparedStatement1 = null;
	    PreparedStatement preparedStatement2 = null;
	    ResultSet rs = null;
	    ResultSet rs1 = null;
	    Kms_WriteList_Dto writeListDto = null;
	    int a = 0;
	    try {
	        connection = dataSource.getConnection();
	        String query = "SELECT SUM(fanswernum) AS totalFanswernum" +
                    " FROM (" +
                    "  SELECT *, " +
                    "    @found := IF(fstep = ? AND ( " +
                    "        SELECT fstep FROM forum WHERE fid > sub.fid AND freforder = ? ORDER BY fid LIMIT 1 " +
                    "      ) IS NULL, 1, @found) AS found " +
                    "  FROM forum sub " +
                    "  CROSS JOIN (SELECT @found := 0) AS vars " +
                    "  WHERE freforder = ? AND fsteporder >= ? " +
                    "  ORDER BY fid " +
                    ") AS sub " +
                    "WHERE found = 1";
	        preparedStatement4 = connection.prepareStatement(query);
	        preparedStatement4.setInt(1, fstep);
	        preparedStatement4.setInt(2, freforder);
	        preparedStatement4.setInt(3, freforder);
	        preparedStatement4.setInt(4, fsteporder);
	        rs = preparedStatement4.executeQuery();
	        String query5 = "SELECT fanswernum" +
	        		" FROM ( " +
	        		"  SELECT *, " +
	        		"    @found := IF(fstep = ? AND fsteporder >= ?, 1, @found) AS found " +
	        		"  FROM forum " +
	        		"  CROSS JOIN (SELECT @found := 0) AS vars " +
	        		"  WHERE freforder = ? " +
	        		"  ORDER BY fsteporder " +
	        		") AS sub " +
	        		"WHERE found = 1 " +
	        		"ORDER BY fid DESC " +
	        		"LIMIT 1";
	        preparedStatement3 = connection.prepareStatement(query5);
	        preparedStatement3.setInt(1, fstep);
	        preparedStatement3.setInt(2, fsteporder);
	        preparedStatement3.setInt(3, freforder);
	        rs1 = preparedStatement3.executeQuery();

	        
	        if (rs.next()) {
	            int totalFanswernum = rs.getInt("totalFanswernum");
	            if (rs1.next()) {
	                int Fanswernum = rs1.getInt("fanswernum");
	                a = totalFanswernum - Fanswernum;
	                writeListDto = new Kms_WriteList_Dto(a);
	            }
	        }

	        

	        

	        String query1 = "update forum set fsteporder = fsteporder + 1 where fsteporder >= ? + ? + 1 and freforder = ? and fref = ?";
	        preparedStatement = connection.prepareStatement(query1);
	        preparedStatement.setInt(1, fsteporder);
	        preparedStatement.setInt(2, a);
	        preparedStatement.setInt(3, freforder);
	        preparedStatement.setInt(4, fref);
	        preparedStatement.executeUpdate();

	        String query2 = "INSERT INTO forum (f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, fsteporder, ftitle, fcontent, finsertdate, fmotherid, fanswernum)";
	        String query3 = " values (?, 'admin', ?, 3, ?, ?, ? + 1, ? + ? + 1, ?, ?, now(), ?, 0)";
	        preparedStatement1 = connection.prepareStatement(query2 + query3);
	        preparedStatement1.setString(1, f_cid);
	        preparedStatement1.setInt(2, f_pid);
	        preparedStatement1.setInt(3, fref);
	        preparedStatement1.setInt(4, freforder);
	        preparedStatement1.setInt(5, fstep);
	        preparedStatement1.setInt(6, fsteporder);
	        preparedStatement1.setInt(7, a);
	        preparedStatement1.setString(8, ftitle);
	        preparedStatement1.setString(9, fcontent);
	        preparedStatement1.setInt(10, fmotherid);
	        preparedStatement1.executeUpdate();

	        String query4 = "update forum set fanswernum = fanswernum + 1 where fid = ?";
	        preparedStatement2 = connection.prepareStatement(query4);
	        preparedStatement2.setInt(1, fid);
	        preparedStatement2.executeUpdate(); // 다음 대댓글을 위한 작업1

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (preparedStatement4 != null) {
	                preparedStatement4.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (preparedStatement1 != null) {
	                preparedStatement1.close();
	            }
	            if (preparedStatement2 != null) {
	                preparedStatement2.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return writeListDto;
	}

	public void bigCommentAction1(int fid, String f_cid, int f_pid, int fref, int fstep, int freforder,
			String ftitle, String fcontent, int fmotherid, int fanswernum,int fsteporder) {
		Connection connection = null;
		
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		
		try {
			connection = dataSource.getConnection();

			
			String query2 = "INSERT INTO forum (f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, fsteporder,ftitle, fcontent, finsertdate, fmotherid, fanswernum)";
			String query3 = " values (?,'admin', ?, 3 ,?, ? + ? + 1 ,1,1 ,?, ? ,now(), ?, 0)";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
			preparedStatement1.setString(1, f_cid);
			preparedStatement1.setInt(2, f_pid);
			preparedStatement1.setInt(3, fref);
			preparedStatement1.setInt(4, freforder);
			preparedStatement1.setInt(5, fanswernum);
			preparedStatement1.setString(6, ftitle);
			preparedStatement1.setString(7, ftitle);
			preparedStatement1.setInt(8, fmotherid); // 대댓글 입력하기
			
			
			
			preparedStatement1.executeUpdate();
			
			String query4 = "update forum set fanswernum = fanswernum + 1 where fid =" + fid;
			preparedStatement2 = connection.prepareStatement(query4);
			preparedStatement2.executeUpdate(); // 다음 대댓글을 위한 작업1
			
//			String query5 = "update forum set fanswernum = fanswernum - 1 where fref =" + fref + " and freforder <=" + freforder + " and fstep > 0 and fstep <=" + fstep;
//			preparedStatement3 = connection.prepareStatement(query5);
//			preparedStatement3.executeUpdate(); // 다음 대댓글을 위한 작업2
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
				if(preparedStatement1 != null) preparedStatement1.close();
				if(preparedStatement2 != null) preparedStatement2.close();
				
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	} // 대댓글 
	
	public void commentdelete(int fid) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    try {
	        connection = dataSource.getConnection();
	        String query = "UPDATE forum SET fdeletedate = CURRENT_TIMESTAMP, ftitle = '삭제된 댓글입니다'";
	        String query1 = " WHERE fid = ?";
	        preparedStatement = connection.prepareStatement(query + query1);
	        
	        // fid 파라미터 설정
	        preparedStatement.setInt(1, fid);
	        
	        preparedStatement.executeUpdate();
	            
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (preparedStatement != null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	public ArrayList<Kms_WriteList_Dto> forumsearch(int Ftype, String content){
		ArrayList<Kms_WriteList_Dto> dtos = new ArrayList<Kms_WriteList_Dto>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select f.*, p.pname,c.cname from forum f, product p, customer c";
			String Where2 = " where p.pid = f.f_pid and c.cid = f.f_cid and ftype =" + Ftype + " and fdeletedate = null p.pname like ? order by fref desc, freforder";
			ps = connection.prepareStatement(query + Where2);
			ps.setString(1, "%" + content + "%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int f_id = rs.getInt(1);
				String f_cid = rs.getString(2);
				String f_aid = rs.getString(3);
				int f_pid = rs.getInt(4);
				int ftype = rs.getInt(5);
				int fref = rs.getInt(6);
				int freforder = rs.getInt(7);
				int fstep = rs.getInt(8);
				int fsteporder = rs.getInt(9);
				String ftitle = rs.getString(10);
				String fcontent = rs.getString(11);
				Timestamp finsertdate = rs.getTimestamp(12);
				Timestamp fdeletedate = rs.getTimestamp(13);
				int fmotherid = rs.getInt(14);
				int fanswernum = rs.getInt(15);
				String pname = rs.getString(16);
				String cname = rs.getString(17);

				
				
				Kms_WriteList_Dto dto = new Kms_WriteList_Dto(f_id, f_cid, f_aid, f_pid, ftype, fref, 
						freforder, fstep, ftitle, fcontent, finsertdate, fdeletedate, fmotherid, fanswernum, fsteporder, pname, cname);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
	} // list 출력
	
	public void orderingUpdate(int oid) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    try {
	        connection = dataSource.getConnection();
	        String query = "update ordering set odelivery = 3 where oid = " + oid;
	        preparedStatement = connection.prepareStatement(query);
	        
	        
	        preparedStatement.executeUpdate();
	            
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (preparedStatement != null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void QnAWriteAction(String f_cid, int f_pid,String ftitle, String fcontent) {
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		
		try {
			connection = dataSource.getConnection();
			String query2 = "insert into forum (f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, fsteporder, ftitle, fcontent, finsertdate, fmotherid, fanswernum)";
			String query3 = " select ?,'admin', ?, 2 , max(fref) + 1,0, 0, 0,?,?,now(),0,0 from forum";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
			preparedStatement1.setString(1, f_cid);
			preparedStatement1.setInt(2, f_pid);
			preparedStatement1.setString(3, ftitle);
			preparedStatement1.setString(4, fcontent);
			
			
			preparedStatement1.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement1 != null) preparedStatement1.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}