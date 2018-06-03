﻿<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	try {
		document.execCommand("BackgroundImageCache", false, true);
	} catch (e) {
	};
	if (!('console' in window)) {
		window.console = {
			log : function(msg) {
			}
		};
	}
</script>

<link type="text/css" rel="stylesheet"
	href="//s0.pstatp.com/resource/toutiao_web/static/style/base_1e6e8c2.css">

<title>网络财经资讯动态采集与检索系统</title>

<link rel="stylesheet" type="text/css"
	href="//s0.pstatp.com/resource/toutiao_web/static/pkg/newindex_017c73b.css">
<link rel="stylesheet" type="text/css"
	href="//s2.pstatp.com/resource/toutiao_web/static/pkg/core_18a2e5d.css">
<link rel="stylesheet" type="text/css"
	href="//s0.pstatp.com/resource/toutiao_web/static/pkg/home_52ceb76.css">
<link rel="stylesheet" type="text/css"
	href="//s0.pstatp.com/resource/toutiao_web/pagelet/rightbanner/rightbanner_fb75ac4.css">
<link rel="stylesheet" type="text/css"
	href="//s2.pstatp.com/resource/toutiao_web/static/pkg/dynamic_edc1968.css">
<link rel="stylesheet" type="text/css"
	href="//s2.pstatp.com/resource/toutiao_web/pagelet/gameentry/gameentry_21002a2.css">
<script type="text/javascript" charset="utf-8"
	src="//s2.pstatp.com/resource/toutiao_web/static/pkg/lib_74f0d37.js"></script>
<script type="text/javascript" data-single="true"
	src="//s2.pstatp.com/resource/toutiao_web/static/pkg/asyncmap_fc619f4.js"></script>


<script src="../js/artDialog/jquery.artDialog.js?skin=common"></script>
<script src="../js/artDialog/plugins/iframeTools.js"></script>
<script type="text/javascript"  src="../js/yyj.js"></script>
<link rel="stylesheet" type="text/css" href="../css/yyj.css">
</head>
<body class="night3">
    <div id="wrapper">
    <div id="pagelet-nav" data-test="toutiao.com" style="box-shadow: 0 2px 0 rgba(7,103,200,.9);">
    <div class="nav-inner clearfix">
        <div class="nav-logo">
            <a href="#" class="logo-box" ga_event="nav_icon">
                <img src="../img/2.png" class="logo" style="height:35px;width:412px;">
            </a>
        </div>
        <div class="nav-title">
            <ul class="clearfix">
                <li>
                    <a class="navbtn" href="toutiao/index.jsp">
                        <span>首页</span>
                    </a>
                </li>
                <li>
                    <a class="navbtn" href="toutiao/d3char.jsp">
                        <span>热词</span>
                    </a>
                </li>
            </ul>
        </div>
        <div class="nav-subtitle">
            <ul class="nav-list clearfix">
            	<li class="username-box nav-item"  style="padding-left: 170px;">
                    <a class="btn"  style="cursor:pointer;"  target="_blank"  onclick="Login()">
                        <span>管理员登录</span>
                    </a>
                    <i class="line"></i>
                </li>
            </ul>
        </div>
    </div>
</div>
    <div id="pagelet-toolbar">
    <div data-node="toolbarInner" class="inner" style="background: rgb(7,103,200);">
        <div class="toolbar-inner clearfix">
            <div data-node="searchBox" class="search-box">
                <form action="/news/indexSearch" method="get" data-node="searchForm" target="_blank">
                    <div class="input-group clearfix" style="background-color: rgb(225, 234, 242);border: 1px solid rgb(4, 91, 178);">
                        <input style="background-color: rgb(225, 234, 242);color:black" autocomplete="off" ga_event="nav_search_input"
                               data-node="searchInput" name="keyword" type="text" placeholder="请输入搜索关键词" >
                        <div class="btn-submit">
                            <button style="background-color: #0e78e7;" ga_event="nav_search" type="submit" href="javascript:;">
                                <i class="icon icon-search icon-large"></i>
                            </button>
                        </div>
                    </div>
                </form>
                <div class="layer">
                    <ul data-node="searchMenu">
                    </ul>
                </div>
            </div>
        </div>
        <div class="custom-box" data-node="customBox">
            <div class="custom-inner">
                <p class="msg" data-node="msg">点击添加新闻分类</p>
                <ul class="hv-list clearfix" data-node="otherCategory">
                </ul>
            </div>
        </div>
    </div>
</div>
		<div id="container" class="bg-white clearfix">
			<div class="container-main">
				<div id="pagelet-feedlist" style="font-size: 22px; color: #4f5157;">
					<p class="alert-msg" data-node="alertMsg">
						<img
							src="//s0.pstatp.com/resource/toutiao_web/static/style/image/loading_50c5e3e.gif">
						<span>推荐数据加载中...</span>
					</p>
					<div class="unread" data-node="unread" ga_event="click_refresh">
						<div class="inner">
							<span>您有未读新闻，点击查看</span>
						</div>
					</div>
					<ul data-node="listBox" id="listBox">
					</ul>
					<li class="ad-item clearfix" data-node="adItem"><a
						target="_blank" data-node="adLink"> <img class="ad-img"
							data-node="adImg">
					</a> <span class="ad-close" data-node="adClose"></span> <span
						class="ad-label">推广</span></li>
					<p class="loadmore" data-node="loadMore">正在为您加载更多...</p>
				</div>

				<div class="pagination pagination-centered">
					<ul>
						<li class="disabled" >
						<a onclick='changePageNum(0)' style="cursor:pointer;">&laquo;</a></li>
						<li class="active"><a id='currentPage'  >1</a></li>
						<li class="disabled" ><a onclick='changePageNum(1)' style="cursor:pointer;">&raquo;</a></li>
					</ul>
					<div>
						<input id='inputPageNum' type="text" style="width: 53px;"
							placeholder="共45页" class="form-control">
						<button id='btGo' type="button" onclick="goPage()"
							class="btn btn-default" style="margin-top: -10px">Go</button>
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
 

    <script type="text/javascript">
    //当前页面数
    var gCurPageNum = 1;
    
    $(function () {
  	  RefreshPage(gCurPageNum);
  	  initPageCount();
    }); 
    
    var _path = getRootPath();

    function getRootPath() {
        var strFullPath = window.document.location.href;
        var strPath = window.document.location.pathname;
        var pos = strFullPath.indexOf(strPath);
        var prePath = strFullPath.substring(0, pos);
        if (strPath.split('/').length == 3)
            return prePath;
        var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
        return (prePath + postPath);
    }
    
    function goNewsDetail(newsid){
  	  var vUrl = _path + "/toutiao/detail.jsp?newsid="+newsid;
  	  window.open(vUrl, "_blank");
    }
    
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
          vTitle[2] = "pubtime";
          vTitle[3] = "source";
          vTitle[4] = "bodytext";
          vTitle[5] = "visits";
          var vLen = newslist.length;
          var vBody ="";
          for(var vi=0 ; vi<vLen ; vi++){
        	  vBody +="<ul><li class='item' style=\"cursor:pointer;padding: 15px 0 15px;\" onclick=\"goNewsDetail('" + newslist[vi][vTitle[0]] + "')\"><div style=\"font-weight: 700;\">"+newslist[vi][vTitle[1]]+"</div><p style=\"height: 24.63px;\"><span class='other'>"+newslist[vi][vTitle[3]]+"</span><span class='footer-right'>"+newslist[vi][vTitle[2]]+"</span></p></li></ul>";
          }
    	  return vBody ;
      }
    
    function RefreshPage(pageNum) {
  	      $.ajax({  
  	          url:'/news/getNewsListJson',  //得到json格式的新闻列表
  	          type:'post',  
  	          data:"pageNum=" + pageNum, 
  	          dataType:'json',  
  	          success:function (resultlist) {  
  	        	  var newslist = resultlist;
  	        	  //alert(newslist);
  	        	  //alert(resultlist);
  	        	  var vbody = getBody(newslist);
  	        	  $("#pagelet-feedlist").html(vbody);
  	        	 // $("#ibody").children().remove();
  	        	  //$("#ibody").append(vbody);
  	          }  
  	      });  
    }
    var gPageCount ;
    function initPageCount(){
  	  $.ajax({  
            url:'/news/getNewsPageCount',
            type:'post',  
            data:"{}", 
            dataType:'json',  
            success:function (result) {  
          	  gPageCount = result.pageCount;
          	  $("#inputPageNum").attr("placeholder","共"+gPageCount+"页");
          	  //alert(result);
          	  //var vbody = getBody(newslist);
          	  //$("#pagelet-feedlist").html(vbody);
          	 // $("#ibody").children().remove();
          	  //$("#ibody").append(vbody);
            }  
        });  
    }
    
    //0表示向左，即减一
    //1表示向右，即加一
    function changePageNum(flag){
  	  //alert("vPageCount" +vPageCount);
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
  		  RefreshPage(nextPage);
  	  }
    }
    
    function goPage(){
  	  var vPageNum = $("#inputPageNum").val();
  	  if(vPageNum<=gPageCount&&vPageNum>0){
  		  $("#currentPage").text(vPageNum);
  		  RefreshPage(vPageNum);
  	  }else{
  		  alert("页码超出范围");
  	  }
    }
    </script>
    

</body>
</html>
