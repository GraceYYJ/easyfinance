<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
	<jsp:include page="include.jsp"></jsp:include>
	<script src="../js/jquery-2.1.1.js"></script>
	<script src="../js/artDialog/artDialog.js?skin=default"></script>
	<script src="../js/artDialog/plugins/iframeTools.js"></script>
	<script src="../js/artDialog/jquery.artDialog.js"></script>
	<script src="../js/artDialog/artDialog.js"></script>
<body class="night3">
    <jsp:include page="header.jsp"></jsp:include>
    <div id="container" class="bg-white clearfix">
		<div class="container-main" style="width:100%;">
				<div id="pagelet-feedlist" style="font-size: 16px; color: #4f5157;padding-left: 70px; padding-right: 80px; padding-top: 20px;">
					<table>
						<thead>
						<tr style="font-size: 80%; font-weight: 700;">
							<td>网站名</td>
							<td>域名</td>
							<td>起始页</td>
							<td><span style="margin-left:20px;">操作</span></td>
						</tr>
						</thead>
						<tbody>
						<tr style="height:30px;">
							<td style="width: 120px;">
							
									<a id='addSpider'  style="color: rgb(7,103,200);" target="_blank"  type="button"  href="addSpider.jsp" class="btn btn-default" >添加</a>
							</td>
							<td></td>
							<td></td>
							<td>
									<a id='addSpider'  style="margin-left:20px;cursor: pointer;color: rgb(7,103,200);"  target="_blank"  type="button"  href="" class="btn btn-default" >批量爬取</a>
							</td>
						</tr>
						
						<s:iterator value="list" id="list">
							<tr style="height:30px;">
								<td style="width:180px;cursor: pointer;" onclick="showDetail(<s:property value='#list.id' escape="false" />)">
									<span>
										<s:property value='#list.sitename' escape="false" />
									</span>
								</td>
								<td style="width:200px;">
									<span>
										<s:property value='#list.domain' escape="false" />
									</span>
								</td>
								<td style="width:400px;">
									<span>
										<s:property value='#list.starturl' escape="false" />
									</span>
								</td>
								<td style="width: 160px;">
									<a class="btn"  style="margin-left:20px;cursor:pointer;"  onclick="startSpider(<s:property value='#list.id' />)">爬取</a>
									<a class="btn"  href="modifySpider.action?id=<s:property value='#list.id' />">编辑</a>
									<a class="btn"  style="cursor:pointer;" onclick="deleteSpider(<s:property value='#list.id' />)"/>删除</a>
								</td>
							</tr>
						</s:iterator>
						
						</tbody>
					</table>
				</div>

				<div class="pagination pagination-centered">
					<ul>
						<li class="disabled" >
						<a onclick='changePageNum(0)'>&laquo;</a></li>
						<li class="active"><a id='currentPage'><s:property value="#gCurPageNum" /></a></li>
						<li class="disabled" ><a onclick='changePageNum(1)'>&raquo;</a></li>
					</ul>
					<div>
						<input id="hidPageCount" style="display:none" value="<s:property value="#iPageCount" />" ></input>
						<input id='inputPageNum' type="text" style="width: 53px"
							placeholder="共<s:property value="#iPageCount" />页" class="form-control"
							aria-label="Amount (to the nearest dollar)">
						<button id='btGo' type="button" onclick="goPage()"
							class="btn btn-default" style="margin-top: -10px">Go</button>
					</div>
				</div>
		</div>
	</div>
    <jsp:include page="footer.jsp"></jsp:include>
    <script src="../js/jquery-ui.js"></script>
    <script src="../js/layer/layer.js"></script>
    <script type="text/javascript">

    function startSpider(id){
    	var myDialog = art.dialog("数据爬取中，请稍等。。。");
    	$.ajax({  
	          url:'../admin/startSpider.action',  //得到json格式的新闻列表
	          type:'post',  
	          data:{
	        	  id:id
	          },
	          dataType:'json',  
	          success:function (result) {  
	        	  //alert("新爬取"+result+"条新闻");
	        	  myDialog.content("新爬取"+result+"条新闻");
	        	  //myDialog.close();
	          }  
	      });  
    }
    
    function startSpider2(id){
    	$.ajax({  
	          url:'../admin/startSpider.action',  //得到json格式的新闻列表
	          type:'post',  
	          data:{
	        	  id:id
	          },
	          dataType:'json',  
	          success:function (result) {  
	        	  alert("新爬取"+result+"条新闻");
	          }  
	      });  
    }
    
    function deleteSpider(id){
    	var msg = "确认删除吗？";
    	  if (confirm(msg)==true){
    		  $.ajax({  
    	          url:'../admin/deleteSpider.action',  
    	          type:'post',  
    	          data:{
    	        	  id:id
    	          },
    	          dataType:'json',  
    	          success:function (result) {  
    	        	  window.location.reload();
    	          }  
    	      });  
    	}else{
    		return false;
    	}
    }
   
    </script>
    
</body>
</html>
