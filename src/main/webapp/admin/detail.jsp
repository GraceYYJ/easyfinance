<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html>
<jsp:include page="include.jsp"></jsp:include>
<body class="night3">
<jsp:include page="header.jsp"></jsp:include>
<div id="container" class="bg-white clearfix">
    <div class="container-main">
        <div class="article-detail">
            <div style="font-size: 18px;margin-top: 15px;cursor:pointer;">
                <a onclick='modify(${news.id})' target="_self">编辑</a>
            </div>
            <div class="title">
                <h1 id="h1" style="margin-top: 18px;">
                    ${news.title}
                </h1>
                <div class="subtitle clearfix">
                        <span class="src" id="source">
                            ${news.source}
                        </span>
                    <span class="time" id="time">
                        ${news.pubtime}
                    </span>
                </div>
            </div>
            <div class="article-content" id="bodytext">
                ${news.bodytext}
            </div>
        </div>
        <div id="pagelet-detailbar" class="clearfix"
             data-groupid="6256962119779188993">


        </div>

        <jsp:include page="footer.jsp"></jsp:include>

        <script type="text/javascript">
            function modify(id) {
                window.open("../modify/"+id);
            }
        </script>

</body>
</html>
