<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Cust: Home</title>

<style>
  table#table-1 {
        width: 450px;
        background-color: #CCCCFF;
        margin-top: 5px;
        margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Cust: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Cust: Home</p>

<h3>��Ƭd��:</h3>
        
<%-- ���~��C --%>
<c:if test="${Idt custty errorMsgs}">
        <font style="color:red">�Эץ��H�U���~:</font>
        <ul>
            <c:forEach var="message" items="${errorMsgs}">
                        <li style="color:red">${message}</li>
                </c:forEach>
        </ul>
</c:if>

<ul>
  <li><a href='listAllCust.jsp'>List</a> all Custs.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="cust.do" >
        <b>��J�Ȼs�ƽs�� (�p1):</b>
        <input type="text" name="custId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="custSvc" scope="page" class="com.cust.model.CustService" />
   
  <li>
     <FORM METHOD="post" ACTION="cust.do" >
       <b>��ܫȻs�ƽs��:</b>
       <select size="1" name="custId">
         <c:forEach var="custVO" items="${custSvc.all}" > 
          <option value="${custVO.custId}">${custVO.custId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="cust.do" >
       <b>��ܫȻs�ƦW��:</b>
       <select size="1" name="custId">
         <c:forEach var="custVO" items="${custSvc.all}" > 
          <option value="${custVO.custId}">${custVO.ename}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�Ȼs�ƺ޲z</h3>

<ul>
  <li><a href='addCust.jsp'>Add</a> a new Cust.</li>
</ul>

</body>
</html>