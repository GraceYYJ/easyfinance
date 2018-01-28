<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
	<jsp:include page="include.jsp"></jsp:include>
<body class="night3">
	<jsp:include page="header.jsp"></jsp:include>
<div align="center">
		<table>
			<thead></thead>
			<tbody>
				<tr>
					<td>网站名：</td>
					<td>
							<input id="sitename" class="modify" > </input>
					</td>
				</tr>
				<tr>
					<td>域名：</td>
					<td>
							<input id="domain" class="modify" > </input>
					</td>
				</tr>
				<tr>
					<td>起始页面：</td>
					<td>
							<input id="starturl" class="modify" > </input>
					</td>
				</tr>
				<tr>
					<td>目标链接正则表达式：</td>
					<td>
							<input id="linkstr" class="modify" > </input>
					</td>
				</tr>
				<tr>
					<td>列表链接正则表达式：</td>
					<td>
							<input id="linkstr" class="modify" > </input>
					</td>
				</tr>
				<tr>
					<td>标题Xpath表达式：</td>
					<td>
							<input id="titlestr" class="modify" > </input>
					</td>
				</tr>
				<tr>
					<td>时间Xpath表达式：</td>
					<td>
							<input id="pubtimestr" class="modify" > </input>
					</td>
				</tr>
				<tr>
					<td>来源Xpath表达式：</td>
					<td>
							<input id="sourcestr" class="modify" > </input>
					</td>
				</tr>
				<tr>
					<td>正文Xpath表达式：</td>
					<td>
							<input id="bodytextstr" class="modify" > </input>
					</td>
				</tr>
			</tbody>
		</table>
		
		
		<div>
			<input type="button"  class="yyjbtn" value="添加"  onclick="savaSpider()"/>
		</div>
	</div>
    <jsp:include page="footer.jsp"></jsp:include>
    <script type="text/javascript">
    function savaSpider(){
    	var sitename = $("#sitename").val();
    	var domain = $("#domain").val();
    	var starturl = $("#starturl").val();
    	var linkstr = $("#linkstr").val();
    	var titlestr = $("#titlestr").val();
    	var pubtimestr = $("#pubtimestr").val();
    	var sourcestr = $("#sourcestr").val();
    	var bodytextstr = $("#bodytextstr").val();
    	var helpurlstr = $("#helpurlstr").val();
    	$.ajax({  
	          url:'../admin/addSpider.action',  //得到json格式的新闻列表
	          type:'post',  
	          data:{
	        	  sitename:sitename, 
	        	  domain:domain,
	        	  starturl:starturl,
	        	  linkstr:linkstr,
	        	  titlestr:titlestr,
	        	  pubtimestr:pubtimestr,
	        	  sourcestr:sourcestr,
	        	  bodytextstr:bodytextstr,
	        	  helpurlstr:helpurlstr
	          },
	          dataType:'json',  
	          success:function (result) {  
	        	  if(result=="success"){
	        		  alert("添加成功");
	        	  }else{
	        		  alert("添加失败");
	        	  }
	          }  
	      });  
    }
    </script>
    
</body>
</html>
