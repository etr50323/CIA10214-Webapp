package com.cust.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.cust.model.*;

public class CustServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("custId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入客製化選項編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isCustty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/cust/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer custId = null;
			try {
				custId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("客製化選項編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isCustty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/cust/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			CustService custSvc = new CustService();
			CustVO custVO = custSvc.getOneCust(custId);
			if (custVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isCustty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/cust/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("custVO", custVO); // 資料庫取出的custVO物件,存入req
			String url = "/cust/listOneCust.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCust.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllCust.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer custId = Integer.valueOf(req.getParameter("custId"));

			/*************************** 2.開始查詢資料 ****************************************/
			CustService custSvc = new CustService();
			CustVO custVO = custSvc.getOneCust(custId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("custVO", custVO); // 資料庫取出的custVO物件,存入req
			String url = "/cust/update_cust_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_cust_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_cust_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer custId = Integer.valueOf(req.getParameter("custId").trim());

			String custName = req.getParameter("ename");
			String custNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (custName == null || custName.trim().length() == 0) {
				errorMsgs.add("客製化選項名稱: 請勿空白");
			} else if (!custName.trim().matches(custNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("客製化選項名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}


			CustVO custVO = new CustVO();
			custVO.setCustId(custId);
			custVO.setCustName(custName);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isCustty()) {
				req.setAttribute("custVO", custVO); // 含有輸入格式錯誤的custVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/cust/update_cust_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			CustService custSvc = new CustService();
			custVO = custSvc.updateCust(custId, custName);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("custVO", custVO); // 資料庫update成功後,正確的的custVO物件,存入req
			String url = "/cust/listOneCust.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCust.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addCust.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String custName = req.getParameter("custName");
			String custNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (custName == null || custName.trim().length() == 0) {
				errorMsgs.add("客製化選項名稱: 請勿空白");
			} else if (!custName.trim().matches(custNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("客製化選項名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			

			CustVO custVO = new CustVO();
			custVO.setCustName(custName);
	
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isCustty()) {
				req.setAttribute("custVO", custVO); // 含有輸入格式錯誤的custVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/cust/addCust.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			CustService custSvc = new CustService();
			custVO = custSvc.addCust(custName);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/cust/listAllCust.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCust.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllCust.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer custId = Integer.valueOf(req.getParameter("custId"));

			/*************************** 2.開始刪除資料 ***************************************/
			CustService custSvc = new CustService();
			custSvc.deleteCust(custId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/cust/listAllCust.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
