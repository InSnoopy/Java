<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%

	List<MemberVO> memList = (List<MemberVO>) request.getAttribute("memList");
	
	String msg = session.getAttribute("msg") == null ? "" : (String) session.getAttribute("msg");
	session.removeAttribute("msg");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>주소</th>
		</tr>
		
<%
	int memSize = memList.size();
	if(memSize > 0) {
		for(int i=0; i<memSize; i++){
%>
		<tr>
			<td><%out.print(memList.get(i).getMemId());%></td>
			<td><a href="detail.do?memId=<%out.print(memList.get(i).getMemId());%>"><%out.print(memList.get(i).getMemName());%></a></td>
			<!-- 아래 처럼 out.print 생략하고 =으로 대체 가능 -->
			<td><%=memList.get(i).getMemTel()%></td>
			<td><%=memList.get(i).getMemAddr()%></td>
		</tr>
<%
		}
	}else{	
%>		
		<tr>
			<td colspan="4">조회된 데이터가 없습니다.</td>
		</tr>
<%
	};
%>


	<tr align="center">
		<!-- get방식으로.. -->
		<td colspan="4"><a href="insert.do">[회원 등록]</a></td> 
	</tr>

	</table>
<% 
	if(msg.equals("성공")){
%>
<script>
	alert('정상적으로 처리되었습니다.');
</script>
<%
	}
%>	
</body>
</html>