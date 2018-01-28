<!DOCTYPE html>
<html>
<jsp:include page="pagehead.jsp"></jsp:include>
  <body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="page">
		<div class="page-container">
			<div class="container">
				<div class="row">
					<div class="span12">
						<div id="ibody" style="height:600px;">
							<div style="margin:0 auto;width:510px;">
								<input id="keyword" style="width:400px;height:33px;"></input>
								<input type="submit"  class="btn" style="width:96px;height:40px;" value="搜 索" onclick="Search()"></input>
							</div>
							<div style="height:20px;"></div>
							<table id="itable" class="table table-striped sortable" style="margin:0 auto;display:none">
							<thead>
								<tr>
									<th>标题</th>
									<th>来源</th>
									<th>点击量</th>
									<th>发表时间</th>
								</tr>
							</thead>
							<tbody id="searchresult">
							</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
  <script src="../js/d3-setup.js"></script>
  <script src="../js/yyj.js"></script>
  <script>
			$(function() {
				
			});

			  function getBody(newslist){
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
			  
			  function Search() {
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
				        	  $("#itable").css("display","block");
				        	  var vbody = getBody(newslist);
				        	  $("#searchresult").html(vbody);
				          }
				      });  
				  }
			  }
			
			  function goNewsDetail(newsid){
				  var vUrl = _path + "/jsp/newsdetail.jsp?newsid="+newsid;
				  window.open(vUrl, "_blank");
			  }
		</script>
</html>