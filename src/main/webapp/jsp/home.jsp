<%@ page language="java"
	import="java.util.*,java.util.ArrayList,java.util.HashMap,com.yangyujuan.jdbc.domain.*"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
	ArrayList<News> list =(ArrayList<News>)request.getAttribute("list");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>信息查看</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="../js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<link href="../css/bootstrap.css" rel ="stylesheet"/>
</head>
<body>
    <!-- start header -->
    <header class="main-header"  style="background-image:url(./image/header.jpg);height:100px;"">
        <div class="container">
                <h1>杨玉娟的毕业设计哦</h1>
        </div>
    </header>
    <!-- end header -->
    
    <div >
        <div class="col-md-2 col-xs-2 col-sm-2 col-lg-2">
					<%
						request.setAttribute("manageLeftNav", "Gzdt");
					%>
		</div>
		<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
				<div style="margin-top:100px;">
	     <%
					for (int i =0; i <list.size(); i++) {
						String title=String.valueOf(list.get(i).getTitle());
		%>
	    <tr>
		<td>
		    <div align="left">&nbsp;> 
				<a href="#" target="_blank"  title="<%=title%>">
					<%if(title.length()>25)
								out.print(title.substring(0,25)+"...");
							else
								out.print(title);
					%>
				</a>
			</div>
		</td>
		<td><%=list.get(i).getSource()%></td>
		<td style="text-align: center"><%=list.get(i).getPubTime()%></td>
	</tr>
	<%
					}
	%>
             </div>    
		</div>			
    </div>
    
</body>
</html>
