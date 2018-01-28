<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
	<link href="../js/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="../js/umeditor/third-party/jquery.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="../js/umeditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../js/umeditor/umeditor.min.js"></script>
    <script type="text/javascript" src="../js/umeditor/lang/zh-cn/zh-cn.js"></script>
<html>
	<jsp:include page="include.jsp"></jsp:include>

<body class="night3">
    <jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<label>标题：</label> 
			<input id="title" class="modify" value="<s:property value="#news.title"/>"> 
			</input></br> 
		<label>来源：</label>
			<input id="source" class="modify" value="<s:property value="#news.source"/>"> 
			</input></br> 
		<label>时间：</label>
			<input id="time" class="modify" value="<s:property value="#news.pubTime"/>"> 
		</input>
		<div id="newsid" style="display: none; ">
			<s:property value="#news.id" escape="false" />
		</div>
		<div id="bodytext" style="display: none;">
			<s:property value="#news.bodytext" escape="false" />
		</div>
		<div style="margin-top: 10px;">
			<script type="text/plain" id="myEditor" style="width: 1000px; height: 240px;">
			</script>
		</div>
		<div>
			<input type="button"  class="yyjbtn" value="保存"  onclick="savaModify()"/>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
    <script type="text/javascript">
    var um = UM.getEditor('myEditor');
    um.addListener('blur',function(){
        $('#focush2').html('编辑器失去焦点了')
    });
    um.addListener('focus',function(){
        $('#focush2').html('')
    });
    
    setContent('hahah');
    //按钮的操作
    function insertHtml() {
        var value = prompt('插入html代码', '');
        um.execCommand('insertHtml', value)
    }
    function isFocus(){
        alert(um.isFocus())
    }
    function doBlur(){
        um.blur()
    }
    function createEditor() {
        enableBtn();
        um = UM.getEditor('myEditor');
    }
    function getAllHtml() {
        alert(UM.getEditor('myEditor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UM.getEditor('myEditor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UM.getEditor('myEditor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用umeditor')方法可以设置编辑器的内容");
        var bodytext = $("#bodytext").html();
        UM.getEditor('myEditor').setContent(bodytext, isAppendTo);
       // alert(arr.join("\n"));
    }
    function setDisabled() {
        UM.getEditor('myEditor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UM.getEditor('myEditor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UM.getEditor('myEditor').selection.getRange();
        range.select();
        var txt = UM.getEditor('myEditor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UM.getEditor('myEditor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UM.getEditor('myEditor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UM.getEditor('myEditor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UM.getEditor('myEditor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            domUtils.removeAttributes(btn, ["disabled"]);
        }
    }
    
    
    function savaModify(){
    	var id = $("#newsid").text();
    	var title = $("#title").val();
    	var source = $("#source").val();
    	var time = $("#time").val();
    	var bodytext = UM.getEditor('myEditor').getContent();
    	$.ajax({  
	          url:'../admin/updateNews.action',  //得到json格式的新闻列表
	          type:'post',  
	          data:{
	        	  id:id,
	        	  title:title, 
	        	  source:source,
	        	  time:time,
	        	  bodytext:bodytext
	          },
	          dataType:'json',  
	          success:function (result) {  
	        	  if(result=="success"){
	        		  alert("保存成功");
	        		  window.location.href = "detail.action?id="+id;
	        	  }else{
	        		  alert("保存失败");
	        	  }
	          }  
	      });  
    	
    }
    </script>

</body>
</html>
