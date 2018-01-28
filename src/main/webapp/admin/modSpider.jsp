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
							<input id="sitename" class="modify" value="<s:property value="#obj.sitename"/>"> </input>
					</td>
				</tr>
				<tr>
					<td>域名：</td>
					<td>
							<input id="domain" class="modify" value="<s:property value="#obj.domain"/>"> </input>
					</td>
				</tr>
				<tr>
					<td>起始页面：</td>
					<td>
							<input id="starturl" class="modify" value="<s:property value="#obj.starturl"/>"> </input>
					</td>
				</tr>
				<tr>
					<td>目标链接正则表达式：</td>
					<td>
							<input id="linkstr" class="modify" value="<s:property value="#obj.linkstr"/>"> </input>
					</td>
				</tr>
				<tr>
					<td>列表链接正则表达式：</td>
					<td>
							<input id="helpurlstr" class="modify" value="<s:property value="#obj.helpurlstr"/>"> </input>
					</td>
				</tr>
				<tr>
					<td>标题Xpath表达式：</td>
					<td>
							<input id="titlestr" class="modify" value="<s:property value="#obj.titlestr"/>"> </input>
					</td>
				</tr>
				<tr>
					<td>时间Xpath表达式：</td>
					<td>
							<input id="pubtimestr" class="modify" value="<s:property value="#obj.pubtimestr"/>"> </input>
					</td>
				</tr>
				<tr>
					<td>来源Xpath表达式：</td>
					<td>
							<input id="sourcestr" class="modify" value="<s:property value="#obj.sourcestr"/>"> </input>
					</td>
				</tr>
				<tr>
					<td>正文Xpath表达式：</td>
					<td>
							<input id="bodytextstr" class="modify" value="<s:property value="#obj.bodytextstr"/>"> </input>
					</td>
				</tr>
			</tbody>
		</table>
		
		<div id="objId" style="display: none; ">
			<s:property value="#obj.id" escape="false" />
		</div>
		<div>
			<input type="button"  class="yyjbtn" value="保存"  onclick="savaSpider()"/>
		</div>
	</div>
    <jsp:include page="footer.jsp"></jsp:include>
    <script type="text/javascript">
    function savaSpider(){
    	var id = $("#objId").text();
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
	          url:'../admin/updateSpider.action',  //得到json格式的新闻列表
	          type:'post',  
	          data:{
	        	  id:id,
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
	        		  alert("保存成功");
	        		  window.location.href = "spiderIndex";
	        	  }else{
	        		  alert("保存失败");
	        	  }
	          }  
	      });  
    }
    </script>
    
</body>
</html>
