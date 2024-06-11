<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cust.model.*"%>

<% //見com.cust.controller.CustServlet.java第163行存入req的custVO物件 (此為從資料庫取出的custVO, 也可以是輸入格式有錯誤時的custVO物件)
   CustVO custVO = (CustVO) request.getAttribute("custVO");
%>
--<%= custVO==null %>--${custVO.deptId}-- <!-- line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>客製化資料修改 - update_cust_input.jsp</title>

<style>
  table#table-1 {
        background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
        width: 450px;
        background-color: white;
        margin-top: 1px;
        margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
        <tr><td>
                 <h3>客製化資料修改 - update_cust_input.jsp</h3>
                 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
        </td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${Idt custty errorMsgs}">
        <font style="color:red">請修正以下錯誤:</font>
        <ul>
                <c:forEach var="message" items="${errorMsgs}">
                        <li style="color:red">${message}</li>
                </c:forEach>
        </ul>
</c:if>

<FORM METHOD="post" ACTION="cust.do" name="form1">
<table>
        <tr>
                <td>客製化編號:<font color=red><b>*</b></font></td>
                <td><%=custVO.getCustId()%></td>
        </tr>
        <tr>
                <td>客製化名稱:</td>
                <td><input type="TEXT" name="custName" value="<%=custVO.getCustName()%>" size="45"/></td>
        </tr>
    

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="custId" value="<%=custVO.getCustId()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

</html>