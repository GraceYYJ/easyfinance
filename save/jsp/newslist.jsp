<!DOCTYPE html>
<html>
 <jsp:include page="pagehead.jsp"></jsp:include>
 <script type="text/javascript">
  //当前的页数
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
	  var vUrl = _path + "/jsp/newsdetail.jsp?newsid="+newsid;
	  window.open(vUrl, "_blank");
  }
  
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
  
  function RefreshPage(pageNum) {
	      $.ajax({  
	          url:'getNewsListJson.action',  //得到json格式的新闻列表
	          type:'post',  
	          data:"pageNum=" + pageNum, 
	          dataType:'json',  
	          success:function (resultlist) {  
	        	  var newslist = JSON.parse(resultlist);
	        	  //alert(newslist);
	        	  //alert(resultlist);
	        	  var vbody = getBody(newslist);
	        	  $("#ibody").html(vbody);
	        	 // $("#ibody").children().remove();
	        	  //$("#ibody").append(vbody);
	          }  
	      });  
  }
  var gPageCount ;
  function initPageCount(){
	  $.ajax({  
          url:'getNewsPageCount.action',  
          type:'post',  
          data:"{}", 
          dataType:'json',  
          success:function (result) {  
        	  gPageCount = JSON.parse(result)["pageCount"];
        	  $("#inputPageNum").attr("placeholder","共"+gPageCount+"页");
        	  //alert(result);
        	  var vbody = getBody(newslist);
        	  $("#ibody").html(vbody);
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
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="page">
		<div class="page-container">
			<div class="container">
				<div class="row">
					<div class="span12">
						<a href="#newUserModal" data-toggle="modal" class="btn pull-right">New
							User</a>
						<h4 class="header">Users</h4>
						<table class="table table-striped sortable">
							<thead>
								<tr>
									<th>标题</th>
									<th>来源</th>
									<th>点击量</th>
									<th>发表时间</th>
								</tr>
							</thead>
							<tbody id="ibody">

							</tbody>
						</table>
						<div class="pagination pagination-centered">
							<ul>
								<li class="disabled"><a onclick='changePageNum(0)'>&laquo;</a></li>
								<li class="active"><a id='currentPage'>1</a></li>
								<!-- <li><a onclick='changePageNum(3)'>3</a></li>
								<li><a onclick='changePageNum(2)'>2</a></li>
								<li><a onclick='changePageNum(3)'>3</a></li>
								<li><a onclick='changePageNum(4)'>4</a></li>
								<li><a onclick='changePageNum(5)'>5</a></li>  -->
								<li><a onclick='changePageNum(1)'>&raquo;</a></li>
							</ul>
							<div>
							    <input id='inputPageNum' type="text"  style="width:53px"  placeholder="共45页"  class="form-control" aria-label="Amount (to the nearest dollar)">
							    <button id='btGo' type="button" onclick="goPage()" class="btn btn-default" style="margin-top:-10px">Go</button>
						    </div>
						</div>
					</div>
				</div>
			</div>
			<div id="newUserModal" class="modal hide fade">
				<div class="modal-header">
					<button type="button" data-dismiss="modal" aria-hidden="true"
						class="close">&times;</button>
					<h3>New User</h3>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" />
					<div class="control-group">
						<label for="inputEmail" class="control-label">Email </label>
						<div class="controls">
							<input id="inputEmail" type="text" placeholder="Email" />
						</div>
					</div>
					<div class="control-group">
						<label for="inputCurrentPassword" class="control-label">Username
						</label>
						<div class="controls">
							<input id="inputCurrentPassword" type="password"
								placeholder="Username" />
						</div>
					</div>
					</form>
				</div>
				<div class="modal-footer">
					<a href="#" data-dismiss="modal" class="btn">Close</a><a href="#"
						data-dismiss="modal" class="btn btn-primary">Save Changes</a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script src="../js/d3-setup.js"></script>

</html>