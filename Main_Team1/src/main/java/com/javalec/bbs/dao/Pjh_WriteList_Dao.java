package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.Admin_Product_Dto;
import com.javalec.bbs.dto.Admin_WriteList_Dto;
import com.javalec.bbs.dto.Kms_WriteList_Dto;

public class Pjh_WriteList_Dao {

	
	
	// Field
	DataSource dataSource;
	
	
	
	// Constructor
	public Pjh_WriteList_Dao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//Method
	
	public void commentAction(int f_pid, String ftitle, int fid) {
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		
		try {
			connection = dataSource.getConnection();
			String query2 = "insert into forum (f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, ftitle, fcontent, finsertdate, fmotherid, fanswernum)";
			String query3 = " select 'dummy','admin', ?, 3 , max(fref) + 1, 0, 0, ?, ?, now(),?,0 from forum";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
			preparedStatement1.setInt(1, f_pid);
			preparedStatement1.setString(2, ftitle);
			preparedStatement1.setString(3, ftitle);
			preparedStatement1.setInt(4, fid);
			
			
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
			String query3 = " values ('dubmmy','admin', ?, 1 ,?, ? + 1, ? + 1,? ,? ,now(), 0, 0)";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
//			preparedStatement1.setString(1, f_cid);
			preparedStatement1.setInt(1, f_pid);
			preparedStatement1.setInt(2, fref);
			preparedStatement1.setInt(3, a);
			preparedStatement1.setInt(4, fstep);
			preparedStatement1.setString(5, ftitle);
			preparedStatement1.setString(6, fcontent);
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
	        String query3 = " values ('dummy', 'admin', ?, 3, ?, ?, ? + 1, ? + ? + 1, ?, ?, now(), ?, 0)";
	        preparedStatement1 = connection.prepareStatement(query2 + query3);
//	        preparedStatement1.setString(1, f_cid);
	        preparedStatement1.setInt(1, f_pid);
	        preparedStatement1.setInt(2, fref);
	        preparedStatement1.setInt(3, freforder);
	        preparedStatement1.setInt(4, fstep);
	        preparedStatement1.setInt(5, fsteporder);
	        preparedStatement1.setInt(6, a);
	        preparedStatement1.setString(7, ftitle);
	        preparedStatement1.setString(8, fcontent);
	        preparedStatement1.setInt(9, fmotherid);
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
			String query3 = " values ('dummy','admin', ?, 3 ,?, ? + ? + 1 ,1,1 ,?, ? ,now(), ?, 0)";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
//			preparedStatement1.setString(1, f_cid);
			preparedStatement1.setInt(1, f_pid);
			preparedStatement1.setInt(2, fref);
			preparedStatement1.setInt(3, freforder);
			preparedStatement1.setInt(4, fanswernum);
			preparedStatement1.setString(5, ftitle);
			preparedStatement1.setString(6, ftitle);
			preparedStatement1.setInt(7, fmotherid); // 대댓글 입력하기
			
			
			
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
	
	
	
	public Admin_WriteList_Dto noticeView(int nid) {
		Admin_WriteList_Dto dto= null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			String query = "SELECT n_aid, ntitle, ncontent, ninsertdate FROM notice WHERE nid = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, nid);
			resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next()) {
	            String n_aid = resultSet.getString("n_aid");
	            String ntitle = resultSet.getString("ntitle");
	            String ncontent = resultSet.getString("ncontent");
	            Timestamp ninsertdate = resultSet.getTimestamp("ninsertdate");
	            
	            dto = new Admin_WriteList_Dto(n_aid, ntitle, ncontent, ninsertdate);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
	    }
		return dto;
	}
	
	public void modify(String ntitle, String ncontent, int nid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			String query = "update notice set ntitle=?, ncontent=?, ninsertdate=now() where nid= ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ntitle);
			preparedStatement.setString(2, ncontent);
			preparedStatement.setInt(3, nid);
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
	
	public void delete(String nid) {
	    if (nid == null) {
	        // pid 배열이 null인 경우 처리
	        System.out.println("No products selected for deletion.");
	        return;
	    }
	    Connection connection = null;
	    PreparedStatement deleteInboundStatement = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        connection = dataSource.getConnection();
	        // Delete from product table
	        String deletedateInsert= "update notice set ndeletedate= now() where nid = ?";
	        preparedStatement = connection.prepareStatement(deletedateInsert);
	            preparedStatement.setInt(1, Integer.parseInt(nid));
	            preparedStatement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (deleteInboundStatement != null)
	                deleteInboundStatement.close();
	            if (preparedStatement != null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void checkcommentAction(int f_pid, String ftitle, int fid) {
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		
		try {
			connection = dataSource.getConnection();
			String query2 = "insert into forum (f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, ftitle, fcontent, finsertdate, fmotherid, fanswernum)";
			String query3 = " select 'dummy','admin', ?, 3 , max(fref) + 1, 0, 0, ?, ?, now(),?,0 from forum";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
			preparedStatement1.setInt(1, f_pid);
			preparedStatement1.setString(2, ftitle);
			preparedStatement1.setString(3, ftitle);
			preparedStatement1.setInt(4, fid);
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
	
	
	
	
	
}
