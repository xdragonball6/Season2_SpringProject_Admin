package com.javalec.bbs.homecontroller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.command.ADeleteReviewCommand2_pjh;
import com.javalec.bbs.command.AProductSearchCommand2;
import com.javalec.bbs.command.AdminOrdermanageCommand_kkg;
import com.javalec.bbs.command.CartCommand;
import com.javalec.bbs.command.CartOrderCommand;
import com.javalec.bbs.command.CategoryPorductCommand;
import com.javalec.bbs.command.DetailedCommand;
import com.javalec.bbs.command.DetailsCommand;
import com.javalec.bbs.command.HomePorductCommand;
import com.javalec.bbs.command.IdCommand;
import com.javalec.bbs.command.InsertCommand;
import com.javalec.bbs.command.JoinCommand;
import com.javalec.bbs.command.Kms_BigCommentActionCommand;
import com.javalec.bbs.command.Kms_BigCommentActionCommand1;
import com.javalec.bbs.command.Kms_CommentActionCommand;
import com.javalec.bbs.command.Kms_CommentDeleteCommand;
import com.javalec.bbs.command.Kms_ForumSearchCommand;
import com.javalec.bbs.command.Kms_ForumViewCommand;
import com.javalec.bbs.command.Kms_OrderingListCommand;
import com.javalec.bbs.command.Kms_ReplyActionCommand;
import com.javalec.bbs.command.Kms_WriteForumCommand;
import com.javalec.bbs.command.Kms_WriteListCommand;
import com.javalec.bbs.command.MCommand;
import com.javalec.bbs.command.MypageCommand;
import com.javalec.bbs.command.NoticeCommand;
import com.javalec.bbs.command.OrderCommand;
import com.javalec.bbs.command.OrderProductCommand;
import com.javalec.bbs.command.Pjh_ANoticeDelete_Command;
import com.javalec.bbs.command.Pjh_ANoticeModifyPage_Command;
import com.javalec.bbs.command.Pjh_ANoticeModify_Command;
import com.javalec.bbs.command.Pjh_ANoticeViewCommand;
import com.javalec.bbs.command.Pjh_ANoticeWrite_Command;
import com.javalec.bbs.command.Pjh_AWriteReply_Command;
import com.javalec.bbs.command.Pjh_BigCommentActionCommand;
import com.javalec.bbs.command.Pjh_CheckCommentActionCommand;
import com.javalec.bbs.command.Pjh_CommentActionCommand;
import com.javalec.bbs.command.Pjh_CommentDeleteCommand;
import com.javalec.bbs.command.Pjh_ForumViewCommand;
import com.javalec.bbs.command.Pjh_ReplyActionCommand;
import com.javalec.bbs.command.PwCommand;
import com.javalec.bbs.command.QnAList_Command_pjh;
import com.javalec.bbs.command.ReviewCommand;
import com.javalec.bbs.command.aDeleteProductCommand_pjh;
import com.javalec.bbs.command.aDeleteReviewCommand_pjh;
import com.javalec.bbs.command.aHomeCommand_kkg;
import com.javalec.bbs.command.aModifyProductCommand_pjh;
import com.javalec.bbs.command.aProductAddCommand_pjh;
import com.javalec.bbs.command.aProductListCommand_pjh;
import com.javalec.bbs.command.aProductSearchCommand_pjh;
import com.javalec.bbs.command.aQnAuploadCommand_pjh;
import com.javalec.bbs.command.aReviewSearchCommand_pjh;
import com.javalec.bbs.command.aReview_List_Command_pjh;
import com.javalec.bbs.command.aUserListCommnad_kkg;
import com.javalec.bbs.command.searchCommdand;
import com.javalec.bbs.command.showOrderListCommand_kkg;
 
/**
 * Servlet implementation class MController
 */
@WebServlet("*.do")
public class MController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);

		String viewPage = null;
		MCommand command = null;

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();

		String com = uri.substring(conPath.length());

		switch (com) {

		/* ***** PART I 시작. 킹갓더제너럴 5STAR 강대규팀장님 part 입니다. 일동 기립. 경례. 쉬어.***** */

		case ("/home.do"):
			session.removeAttribute("orderList");
			session.removeAttribute("Memo");
			session.removeAttribute("Payment");
			session.removeAttribute("Cname");
			session.removeAttribute("Cphone");
			command = new HomePorductCommand();
			command.execute(request, response);
			viewPage = "home.jsp";
			break;
		// 로그아웃
		case ("/logout.do"):
			session.invalidate();
			viewPage = "home.do";
			break;
		// 회원가입 창
		case ("/join.do"):
			viewPage = "join.jsp";
			break;

		// 아이디 찾기
		case ("/findId.do"):
			viewPage = "FindId.jsp";
			break;
		case ("/pindIdview.do"):
			command = new IdCommand();
			command.execute(request, response);
			viewPage = "FindIdview.jsp";
			break;

		// 비밀번호 찾기
		case ("/findPw.do"):
			viewPage = "Findpw.jsp";
			break;
		case ("/pindPwview.do"):
			command = new PwCommand();
			command.execute(request, response);
			viewPage = "FindPwview.jsp";
			break;
		// 마이페이지
		case ("/mypageview.do"):
			command = new MypageCommand();
			command.execute(request, response);
			viewPage = "mypage.jsp";
			break;
		case ("/CategoryView.do"):
			command = new CategoryPorductCommand();
			command.execute(request, response);
			viewPage = (String) request.getAttribute("view");
			break;
		case ("/cart.do"):
			command = new CartCommand();
			command.execute(request, response);
			viewPage = "userCart.jsp";
			break;
		case("/cartorder.do"):
			command = new CartOrderCommand();
			command.execute(request, response);
			viewPage = "OrderView.jsp";
			break;
		//구매 완료
		case("/orderProduct.do"):
			command = new OrderProductCommand();
			command.execute(request, response);
			session.removeAttribute("bid");
			viewPage = (String) request.getAttribute("view");
			break;
		//구매 내역 페이지
		case("/details.do"):
			command = new DetailsCommand();
			command.execute(request, response);
			viewPage = "DetailsView.jsp";
			
		case("/search.do"):
			command = new searchCommdand();
			command.execute(request, response);
			viewPage = "searchView.jsp";
			break;

		/* PART I 종료. 킹갓더제너럴 강대규팀장님 part 입니다. 일동 기립. 경례. 쉬어. */
		// *************************************************************//

		/* PART II 시작. 큐티보이 김종진뀨 part 입니다. 뀨 일동 say 뀨~. 쉬어. */

		case ("/detailedpage.do"):
			command = new DetailedCommand();
			command.execute(request, response);
			viewPage = "detailedpage.jsp";
			break;
		case ("/myreview.do"):
			command = new ReviewCommand();
			command.execute(request, response);
			viewPage = "myreview.jsp";
			break;
		case ("/productcart.do"):
			command = new InsertCommand();
			command.execute(request, response);
			viewPage = (String) request.getAttribute("view");
			break;
		case ("/OrderView.do"):
			command = new OrderCommand();
			command.execute(request, response);
			viewPage = "OrderView.jsp";
			break;
		case ("/notice.do"):
			command = new NoticeCommand();
			command.execute(request, response);
			viewPage = "notice.jsp";
			break; 
			

		/* PART II 종료. 큐티보이 김종진뀨 part 입니다. 뀨 일동 say 뀨~. 쉬어. */
		// *************************************************************//

		/* PART III 시작. 스윗남자 박지환 서윗남 part 입니다. 일용할 스윗함에 고마움을 :). */
		case("/APlist.do") :
			command = new aProductListCommand_pjh();
			command.execute(request, response);
			viewPage = "Admin_ProductList_pjh.jsp";
			break;
			case("/APinsert.do") :
			viewPage = "Admin_ProductAdd_pjh.jsp";
			break;
			//상품등록 페이지에서 업로드 버튼
			case("/AProductInsert.do") :
			command = new aProductAddCommand_pjh();
			command.execute(request, response);
			viewPage = "APlist.do";
			break;
			//상품관리 페이지에서 상품 삭제
			case ("/deleteProduct.do"):
			command = new aDeleteProductCommand_pjh();
			command.execute(request, response);
			viewPage = "APlist.do";
			break;
			//상품 관리 페이지에서 상품 변경 버튼 눌렀을 때		
			case ("/editProduct.do"):	
			command = new aModifyProductCommand_pjh();	
			command.execute(request, response);	
			viewPage = "APlist.do";	
			break;
			//상품 검색		
			case ("/productQuery.do"):		
			command = new aProductSearchCommand_pjh();
			command.execute(request, response);
			viewPage = "Admin_ProductList_pjh.jsp";
			break;
			case ("/AReviewList.do"):		
			command = new aReview_List_Command_pjh();
			command.execute(request, response);
			viewPage = "Admin_Review_List.jsp";
			break;
			case ("/productInformation.do"):		
			command = new AProductSearchCommand2();
			command.execute(request, response);
			viewPage = "Admin_ProductList_pjh.jsp";
			break;
			case ("/noticewrite.do"):		
			viewPage = "Admin_QnA_pjh.jsp";
			break;
			case ("/ANoticeUpload.do"):		
			command = new aQnAuploadCommand_pjh();
			command.execute(request, response);
			viewPage = "noticelist.do";
			break;
			case ("/deleteReview.do"):		
			command = new aDeleteReviewCommand_pjh();
			command.execute(request, response);
			viewPage = "reviewlist.do";
			break;
			case ("/reviewQuery.do"):		
			command = new aReviewSearchCommand_pjh();
			command.execute(request, response);
			viewPage = "Admin_Review_List.jsp";
			break;
			case ("/AForumView.do"):
			command = new Pjh_ForumViewCommand();
			command.execute(request, response);
			viewPage = "AForumView.jsp";
			break;
			case ("/Awritelist.do"):
			command = new Kms_WriteListCommand();
			command.execute(request, response);
			viewPage = "pjh_WriteList.jsp";
			break;
			case ("/Aforumsearch.do"):
			command = new Kms_ForumSearchCommand();
			command.execute(request, response);
			viewPage = "pjh_WriteList.jsp";
			break;
			case ("/Acommentwrite.do"):
			command = new Pjh_CommentActionCommand();
			command.execute(request, response);
			viewPage = "AForumView.do";
			break;
			case ("/Acommentdelete.do"):
			command = new Pjh_CommentDeleteCommand();
			command.execute(request, response);
			viewPage = "AForumView.do?fid="+request.getParameter("page");
			break;
			case ("/pjh_WriteReply.do"):
			command = new Pjh_AWriteReply_Command();
			command.execute(request, response);
			viewPage = "pjh_WriteReply.jsp";
			break;
			case ("/ABigCommentWrite.do"):
			command = new Pjh_BigCommentActionCommand();
			command.execute(request, response);
			viewPage = "AForumView.do?fid="+request.getParameter("page");
			break;
			case ("/Areplywrite.do"):
			command = new Pjh_ReplyActionCommand();
			command.execute(request, response);
			viewPage = "AForumView.do?fid="+request.getParameter("page");
			break;
			case ("/ANoticeView.do"):
			command = new Pjh_ANoticeViewCommand();
			command.execute(request, response);
			viewPage = "Pjh_notice.jsp";
			break;
			case ("/AnoticeModifyPage.do"):
			command = new Pjh_ANoticeModifyPage_Command();
			command.execute(request, response);
			viewPage = "ANoticeModifyPage.jsp";
			break;
			case ("/AnoticeModify.do"):
			command = new Pjh_ANoticeModify_Command();
			command.execute(request, response);
			viewPage = "ANoticeView.do?nid="+request.getParameter("nid");
			break;
			case ("/AdminNoticeWrite.do"):
			command = new Pjh_ANoticeWrite_Command();
			command.execute(request, response);
			viewPage = "Admin_QnA_pjh.jsp";
			break;
			case ("/AdeleteNotice.do"):
			command = new Pjh_ANoticeDelete_Command();
			command.execute(request, response);
			viewPage = "Awritelist.do?ftype="+request.getParameter("ftype");
			break;
			case ("/deleteRevieworQnA.do"):		
			command = new ADeleteReviewCommand2_pjh();
			command.execute(request, response);
			viewPage = "Awritelist.do?ftype="+request.getParameter("ftype");
			break;
			case ("/Acheckcommentwrite.do"):
			command = new Pjh_CheckCommentActionCommand();
			command.execute(request, response);
			viewPage = "AReviewList.do";
			break;

		/* PART III 종료. 스윗남자 박지환 서윗남 part 입니다. 일동 .일용할 스윗함에 고마움을 :) */
		// *************************************************************//

		/* PART IV 시작. 세상Cool 남자 김민성군의 Part 입니다. 평균연령 낮춰줘서 고맙다 민성아. */

		case ("/writelist.do"):
			command = new Kms_WriteListCommand();
			command.execute(request, response);
			viewPage = "Kms_WriteList.jsp";
			break;

		case ("/replywrite.do"):
			command = new Kms_ReplyActionCommand();
			command.execute(request, response);
			viewPage = "writelist.do";
			break;

		case ("/forumwrite.do"):
			command = new Kms_WriteForumCommand();
			command.execute(request, response);
			viewPage = "writelist.do";
			break;
		case ("/ForumView.do"):
			command = new Kms_ForumViewCommand();
			command.execute(request, response);
			viewPage = "ForumView.jsp";
			break;
		case ("/commentwrite.do"):
			command = new Kms_CommentActionCommand();
			command.execute(request, response);
			viewPage = "ForumView.do";
			break;
		case ("/forumsearch.do"):
			command = new Kms_ForumSearchCommand();
			command.execute(request, response);
			viewPage = "Kms_WriteList.jsp";
			break;
			
		case "/BigCommentWrite.do":
		    if ("0".equals(request.getParameter("freforder"))) {
		        command = new Kms_BigCommentActionCommand1();
		        command.execute(request, response);
			    viewPage = "ForumView.do?fid=" + request.getParameter("page");
		    } else {
		        command = new Kms_BigCommentActionCommand();
		        command.execute(request, response);
		        viewPage = "ForumView.do?fid=" + request.getParameter("page");
		    }
		    break;
		case ("/commentdelete.do"):
			command = new Kms_CommentDeleteCommand();
			command.execute(request, response);
			viewPage = "ForumView.do?fid="+request.getParameter("page");
			break;
		case ("/orderinglist.do"):
			command = new Kms_OrderingListCommand();
			command.execute(request, response);
			viewPage = "Kms_OrderingList.jsp";break;	
			
			
			

		/* PART IV 종료. 세상Cool 남자 김민성군의 Part 입니다. 평균연령 낮춰줘서 고맙다 민성아. */
		// *************************************************************//

		/* PART V 시작. 나 강경구 파트다. 돈트 터치 디스 에어리어. 디스 이즈 사유지. ㅋㅋㅋㅋㅋㅋㅋ */

		case ("/adminHome.do"):
			command = new aHomeCommand_kkg();
			command.execute(request, response);
			viewPage = "adminHome.jsp";
			break;

		case ("/AUserlist.do"):
			request.setAttribute("startDate", Date.valueOf(LocalDate.now().minusDays(7)));
			request.setAttribute("endDate", Date.valueOf(LocalDate.now()));
			request.setAttribute("pnum",1);
			
			command = new aUserListCommnad_kkg();
			command.execute(request, response);
			viewPage = "adminUserlist.jsp";
			
			request.removeAttribute("startDate");
			request.removeAttribute("endDate");
			request.removeAttribute("pnum");
			break;
			
		case("/Salemanage.do"):
			request.setAttribute("startDate_chart", Date.valueOf(LocalDate.now().minusDays(30)));
			request.setAttribute("endDate_chart", Date.valueOf(LocalDate.now()));
			request.setAttribute("pnum",1);

			command = new showOrderListCommand_kkg();
			command.execute(request, response);
			viewPage = "adminSalemanage.jsp";
			
			request.removeAttribute("startDate");
			request.removeAttribute("endDate");
			request.removeAttribute("pnum");
			break;
			
		case("/Ordermanage.do"):
		
		
		command = new AdminOrdermanageCommand_kkg();
		command.execute(request, response);
		viewPage = "adminOrder.jsp";
		

		break;
			
			
			

		

		/* PART V 종료. 나 강경구 파트다. 돈트 터치 디스 에어리어. 디스 이즈 사유지. ㅋㅋㅋㅋㅋㅋㅋ */
		// *************************************************************//

		}// switch 구문 끝

		System.out.println(viewPage + "로 이동합니다.");
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
