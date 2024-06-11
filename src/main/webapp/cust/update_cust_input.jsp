<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cust.model.*"%>

<% //��com.cust.controller.CustServlet.java��163��s�Jreq��custVO���� (�����q��Ʈw���X��custVO, �]�i�H�O��J�榡�����~�ɪ�custVO����)
   CustVO custVO = (CustVO) request.getAttribute("custVO");
%>
--<%= custVO==null %>--${custVO.deptId}-- <!-- line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�Ȼs�Ƹ�ƭק� - update_cust_input.jsp</title>

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
                 <h3>�Ȼs�Ƹ�ƭק� - update_cust_input.jsp</h3>
                 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
        </td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${Idt custty errorMsgs}">
        <font style="color:red">�Эץ��H�U���~:</font>
        <ul>
                <c:forEach var="message" items="${errorMsgs}">
                        <li style="color:red">${message}</li>
                </c:forEach>
        </ul>
</c:if>

<FORM METHOD="post" ACTION="cust.do" name="form1">
<table>
        <tr>
                <td>�Ȼs�ƽs��:<font color=red><b>*</b></font></td>
                <td><%=custVO.getCustId()%></td>
        </tr>
        <tr>
                <td>�Ȼs�ƦW��:</td>
                <td><input type="TEXT" name="custName" value="<%=custVO.getCustName()%>" size="45"/></td>
        </tr>
    

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="custId" value="<%=custVO.getCustId()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

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