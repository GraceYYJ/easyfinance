<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
	<jsp:include page="include.jsp"></jsp:include>
<body class="night3">
    <jsp:include page="header.jsp"></jsp:include>
    <div id="container" class="bg-white clearfix">
        <div class="container-main">
           <div class="article-detail">
               <div style="font-size: 18px;margin-top: 15px;cursor:pointer;">
               	    <a href="modify.action?id=<s:property value='#news.id' />"  target="_self" >编辑</a>
               </div>
               <div class="title">
                   <h1 id="h1" style="margin-top: 18px;">
                   		<s:property value="#news.title"/>
                   </h1>
                   <div class="subtitle clearfix">
                        <span class="src" id="source">
                        	<s:property value="#news.source"/>
                        </span>
                        <span class="time" id="time">
                        	<s:property value="#news.pubTime"/>
                        </span>
                   </div>
               </div>
               <div class="article-content" id="bodytext">
               		<s:property value="#news.bodytext" escape="false" />
               </div>
           </div>   
           <div id="pagelet-detailbar" class="clearfix"
     data-groupid="6256962119779188993">

    
</div>

    <jsp:include page="footer.jsp"></jsp:include>


</body>
</html>
