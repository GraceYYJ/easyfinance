<%@ page language="java" import="java.util.*,com.yangyujuan.jdbc.domain.User" %> 
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<jsp:include page="include.jsp"></jsp:include>
<body class="night3">
<jsp:include page="header.jsp"></jsp:include>
		<div id="container" class="bg-white clearfix">
			<div class="container-main"  style="width:100%;">
				<div id="pagelet-feedlist"  style="font-size: 16px; color: #4f5157;">
					<table>
						<thead></thead>
						<tbody>
						<s:iterator value="news" id="news">
							<tr style="height:30px;">
								<td style="width:500px;cursor: pointer;" onclick="showDetail(<s:property value='#news.id' escape="false" />)">
									<span>
										<s:property value='#news.title' escape="false" />
									</span>
								</td>
								<td style="width:180px;">
									<span>
										<s:property value='#news.source' escape="false" />
									</span>
								</td>
								<td style="width:200px;">
									<span>
										<s:property value='#news.pubTime' escape="false" />
									</span>
								</td>
								<td style="width: 60px;">
									<a class="btn"  style="margin-left:20px;" target="_blank"  href="modify.action?id=<s:property value='#news.id' />">编辑</a>
								</td>
								<td style="width: 60px;">
									<a class="btn"  style="cursor:pointer;" onclick="deleteNews(<s:property value='#news.id' />)">删除</a>
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
	</div>

    <jsp:include page="footer.jsp"></jsp:include>
    <script type="text/javascript">
  
    //0表示向左，即减一
    //1表示向右，即加一
    function changePageNum(flag){
  	  //alert("vPageCount" +vPageCount);
  	  var vCurPage = $("#currentPage").text();
  	  var gPageCount = $("#hidPageCount").val()*1;
  	  var nextPage = 0;
  	  if(flag==0 && vCurPage>1){
  		  nextPage = vCurPage*1 - 1;
  	  }else if(flag==1 && vCurPage<gPageCount){
  		  nextPage = vCurPage*1 + 1;
  	  }
  	  if(nextPage<=gPageCount&&nextPage>0){
  		  gCurPageNum = nextPage;
  		  $("#currentPage").text(nextPage);
  		  window.location.href="adminIndex.action?pageNum="+gCurPageNum;
  	  }
    }
    
    function goPage(){
  	  	var vPageNum = $("#inputPageNum").val();
  	  	var gPageCount = $("#hidPageCount").val()*1;
  	  	if(vPageNum<=gPageCount&&vPageNum>0){
  		 		$("#currentPage").text(vPageNum);
  				window.location.href="adminIndex.action?pageNum="+vPageNum;
  	  	}else{
  		  	alert("页码超出范围");
  	  	}
    }
    
    function showDetail(id){
   		window.open("detail.action?id="+id);
   	}
    
    
    function deleteNews2(id){
    	var msg = "确认删除吗？";
    	  if (confirm(msg)==true){
    		  window.location.href="delete.action?id="+id;
    	}else{
    		return false;
    	}
    }
    
    function deleteNews(id){
    	var msg = "确认删除吗？";
    	  if (confirm(msg)==true){
    		  $.ajax({  
    	          url:'../admin/delete.action',  
    	          type:'post',  
    	          data:{
    	        	  id:id
    	          },
    	          dataType:'json',  
    	          success:function (result) {  
    	        	  //var gPageCount = $("#hidPageCount").val()*1;
    	        	  //window.location.href="adminIndex.action?pageNum="+gCurPageNum;
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
