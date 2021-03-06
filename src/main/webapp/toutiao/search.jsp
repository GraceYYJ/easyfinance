﻿<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<link rel="dns-prefetch" href="http://s0.pstatp.com">
<link rel="dns-prefetch" href="http://s2.pstatp.com">

<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, minimal-ui">
<meta name="baidu-site-verification" content="bMFrzWZThd" />
<meta name="360-site-verification"
	content="b96e1758dfc9156a410a4fb9520c5956" />
<meta name="google-site-verification"
	content="3PYTTW0s7IAfkReV8wAECfjIdKY-bQeSkVTyJNZpBKE" />
<meta name="shenma-site-verification"
	content="34c05607e2a9430ad4249ed48faaf7cb_1432711730" />

<script type="text/javascript">
        try{document.execCommand("BackgroundImageCache", false, true);}catch(e){};
        if(!('console' in window)) {window.console = {log: function(msg) {}};}
    </script>
<link type="text/css" rel="stylesheet"
	href="//s0.pstatp.com/resource/toutiao_web/static/style/base_1e6e8c2.css">


<title>网络财经资讯动态采集与检索系统</title>

<link rel="stylesheet" type="text/css"
	href="//s2.pstatp.com/resource/toutiao_web/static/pkg/core_8e911bc.css">
<link rel="stylesheet" type="text/css"
	href="//s2.pstatp.com/resource/toutiao_web/pagelet/searchbar/searchbar_0ec570c.css">
<link rel="stylesheet" type="text/css"
	href="//s0.pstatp.com/resource/toutiao_web/static/pkg/home_52ceb76.css">
<link rel="stylesheet" type="text/css"
	href="//s0.pstatp.com/resource/toutiao_web/pagelet/rightbanner/rightbanner_fb75ac4.css">
<link rel="stylesheet" type="text/css" href="../css/yyj.css">

<script type="text/javascript" charset="utf-8"
	src="//s2.pstatp.com/resource/toutiao_web/static/pkg/lib_74f0d37.js"></script>
<script type="text/javascript" data-single="true"
	src="//s2.pstatp.com/resource/toutiao_web/static/pkg/asyncmap_8016c55.js"></script>


</head>
<body class="night3">
	<div id="wrapper">
		<div id="pagelet-nav" data-test="toutiao.com" style="box-shadow: 0 2px 0 rgba(7,103,200,.9);">
			<div class="nav-inner clearfix">
				<div class="nav-logo">
					<a href="#" class="logo-box" ga_event="nav_icon"> <img
						src="../img/2.png" class="logo" style="height:35px;width:412px">
					</a>
				</div>
				<div class="nav-title">
					<ul class="clearfix">
						<li><a class="navbtn" href="/toutiao/index.jsp">
							<span>首页</span>
						</a></li>
						<li><a  class="navbtn" href="/news/hotwordindex">
							<span>热词</span>
						</a></li>
					</ul>
				</div>
				<div class="nav-subtitle">
					<ul class="nav-list clearfix">
					</ul>
				</div>
			</div>
		</div>
		<div id="container" class="clearfix">
			<div class="container-main">
				<div id="pagelet-searchbar">
					<div class="search-group clearfix">
						<div class="search">
							<input id="keyword" type="text" style="font-size: 15px;"
								placeholder="请输入搜索关键词" value="${keyword}">
						</div>
						<div class="search-btn" onclick="Search()">搜索</div>
					</div>
					<h2>搜索结果：</h2>
				</div>
				<div id="pagelet-feedlist" style="font-size: 22px; color: #4f5157;">
					<c:forEach var="item" items="${list}">
						<ul>
							<li class="item" style="cursor: pointer"
								onclick="goNewsDetail(${item.id})">
								<div>
									<span>${item.title}</span>
									<span class='other' style="color:rgb(7,103,200);width: 88px;position: absolute;right: 0px;">[ 评分：${item.score}&nbsp]</span>
								</div>
								<p>
									<span class='other'>${item.source}</span>
									<span class='footer-right'>${item.pubtime}</span>
								</p>
							</li>
						</ul>
					</c:forEach>
				</div>

				<div class="pagination pagination-centered">
					<ul>
						<li class="disabled" >
						<a onclick='changePageNum(0)' style="cursor:pointer;">&laquo;</a></li>
						<li class="active"><a id='currentPage'>${pagenum}</a></li>
						<li class="disabled" ><a onclick='changePageNum(1)' style="cursor:pointer;">&raquo;</a></li>
					</ul>
					<div>
						<input id='inputPageNum' type="text" style="width: 53px"
							placeholder="共${allPageNum}页" class="form-control"
							aria-label="Amount (to the nearest dollar)">
						<button id='btGo' type="button" onclick="goPage()"
							class="btn btn-default" style="margin-top: -10px">Go</button>
						<input id="allPageNum" value="${allPageNum}" style="display:none;" />
					</div>
				</div>
			</div>
			<div class="container-right">
				<div id="pagelet-appintro">
					<div class="img-box">
						<a>
							<img src="../img/dasai.jpg" style="width: 250px;height: 365px;">
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
	<script src="../js/d3-setup.js"></script>
	<script src="../js/yyj.js"></script>
	<script>
			  function getBody0(newslist){
				  var vTitle = new Array();
			      vTitle[0] = "id";
			      vTitle[1] = "title";
			      vTitle[2] = "pubTime";
			      vTitle[3] = "source";
			      vTitle[4] = "bodytext";
			      vTitle[5] = "visits";
			      var vLen = newslist.length;
			      var vBody ="";
			      for(var vi=0 ; vi<vLen ; vi++){
			    	  vBody +="<tr style=\"cursor:pointer\" onclick=\"goNewsDetail('" + newslist[vi][vTitle[0]] + "')\"><td>"+newslist[vi][vTitle[1]]+"</td><td>"+newslist[vi][vTitle[3]]+"</td><td>"+newslist[vi][vTitle[5]]+"</td><td>"+newslist[vi][vTitle[2]]+"</td></tr>";
			      }
				  return vBody ;
			  }
			  
			  function getBody(newslist){
		    	  var vTitle = new Array();
		          vTitle[0] = "id";
		          vTitle[1] = "title";
		          vTitle[2] = "pubTime";
		          vTitle[3] = "source";
		          vTitle[4] = "bodytext";
		          vTitle[5] = "visits";
		          vTitle[6] = "score";
		          var vLen = newslist.length;
		          var vBody ="";
		          for(var vi=0 ; vi<vLen ; vi++){
		        	  vBody +="<ul><li class='item' style=\"cursor:pointer\" onclick=\"goNewsDetail('" + newslist[vi][vTitle[0]] + "')\"><div>"+newslist[vi][vTitle[1]]+"<span class='other' style='color:rgb(7,103,200);width: 88px;position: absolute;right: 0px;'>[ 评分："+newslist[vi][vTitle[6]]+"&nbsp]</span></div><p><span class='other'>"+newslist[vi][vTitle[3]]+"</span><span class='footer-right'>"+newslist[vi][vTitle[2]]+"</span></p></li></ul>";
		          }
		    	  return vBody ;
		      }
			  
			  function Search2() {
				  var keyword = $("#keyword").val();
				  if(keyword==null||keyword==""||keyword==undefined){
					  
				  }else{
					  $.ajax({  
				          url:'getSearchResult.action',  //得到json格式的新闻列表
				          type:'post',  
				          data:"keyword=" + keyword, 
				          dataType:'json',  
				          success:function (resultlist) {  
				        	  var newslist = JSON.parse(resultlist);
				        	  //alert(newslist);
				        	  //alert(resultlist);
				        	  //$("#itable").css("display","block");
				        	  var vbody = getBody(newslist);
				        	  $("#pagelet-feedlist").html(vbody);
				          }
				      });  
				  }
			  }
			
			  function goNewsDetail(newsid){
				  var vUrl = _path + "/toutiao/detail.jsp?newsid="+newsid;
				  window.open(vUrl, "_blank");
			  }
			  
			  
			  
			  function Search(){
				  var keyword = $("#keyword").val();
				  if(keyword==null||keyword==""||keyword==undefined){}
				  else{
					  window.location.href = "indexSearch?keyword="+keyword+"&searchPagenum=1";
				  }
			  }
			  
			  
			    //0表示向左，即减一
			    //1表示向右，即加一
			    function changePageNum(flag){
			  	  var gPageCount = $("#allPageNum").val();
			  	  var vCurPage = $("#currentPage").text();
			  	  var nextPage = 0;
			  	  if(flag==0 && vCurPage>1){
			  		  nextPage = vCurPage*1 - 1;
			  	  }else if(flag==1 && vCurPage<gPageCount){
			  		  nextPage = vCurPage*1 + 1;
			  	  }
			  	  if(nextPage<=gPageCount&&nextPage>0){
			  		  gCurPageNum = nextPage;
			  		  $("#currentPage").text(nextPage);
			  		  var keyword = $("#keyword").val();
			  		  window.location.href = "indexSearch?keyword="+keyword+"&searchPagenum="+nextPage;
			  		  
			  	  }
			    }
			    
			    function goPage(){
			      var gPageCount = $("#allPageNum").val();
			  	  var vPageNum = $("#inputPageNum").val();
			  	  if(vPageNum<=gPageCount&&vPageNum>0){
			  		  $("#currentPage").text(vPageNum);
			  		  var keyword = $("#keyword").val();
			  		  window.location.href = "indexSearch?keyword="+keyword+"&searchPagenum="+vPageNum;
			  	  }else{
			  		  alert("页码超出范围");
			  	  }
			    }
		</script>


</body>
</html>
