<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ page language="java" import="java.util.*,com.gracyya.model.Myuser" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<link href="${pageContext.request.contextPath}/js/umeditor/themes/default/css/umeditor.css" type="text/css"
      rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/umeditor/third-party/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8"
        src="${pageContext.request.contextPath}/js/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
        src="${pageContext.request.contextPath}/js/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/umeditor/lang/zh-cn/zh-cn.js"></script>
<html>
<head>
    <link rel="dns-prefetch" href="http://s0.pstatp.com">
    <link rel="dns-prefetch" href="http://s2.pstatp.com">

    <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.toutiao.com/a6256962119779188993/">

    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=yes, minimal-ui">
    <meta name="baidu-site-verification" content="bMFrzWZThd"/>
    <meta name="360-site-verification" content="b96e1758dfc9156a410a4fb9520c5956"/>
    <meta name="google-site-verification" content="3PYTTW0s7IAfkReV8wAECfjIdKY-bQeSkVTyJNZpBKE"/>
    <meta name="shenma-site-verification" content="34c05607e2a9430ad4249ed48faaf7cb_1432711730"/>

    <script type="text/javascript">
        try {
            document.execCommand("BackgroundImageCache", false, true);
        } catch (e) {
        }
        ;
        if (!('console' in window)) {
            window.console = {
                log: function (msg) {
                }
            };
        }
    </script>

    <link type="text/css" rel="stylesheet" href="//s0.pstatp.com/resource/toutiao_web/static/style/base_1e6e8c2.css">

    <title>网络财经资讯动态采集与检索系统</title>

    <link type="text/css" rel="stylesheet"
          href="//s2.pstatp.com/resource/toutiao_web/static/style/detail/detail_46f41ec.css">
    <link rel="stylesheet" type="text/css" href="//s0.pstatp.com/resource/toutiao_web/static/pkg/newindex_aa036c7.css">
    <link rel="stylesheet" type="text/css" href="//s2.pstatp.com/resource/toutiao_web/static/pkg/core_18a2e5d.css">
    <link rel="stylesheet" type="text/css" href="//s2.pstatp.com/resource/toutiao_web/static/pkg/detail_a789cc0.css">
    <link rel="stylesheet" type="text/css"
          href="//s0.pstatp.com/resource/toutiao_web/static/pkg/home_52ceb76.css">

    <script type="text/javascript" charset="utf-8"
            src="//s2.pstatp.com/resource/toutiao_web/static/pkg/lib_74f0d37.js"></script>
    <script type="text/javascript" data-single="true"
            src="//s2.pstatp.com/resource/toutiao_web/static/pkg/asyncmap_dbc780d.js"></script>


    <script src="${pageContext.request.contextPath}/js/d3-setup.js"></script>
    <script src="${pageContext.request.contextPath}/js/yyj.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/yyj.css">

</head>

<body class="night3">
<div id="wrapper">
    <div id="pagelet-nav" data-test="toutiao.com" style="box-shadow: 0 2px 0 rgba(7,103,200,.9);">
        <div class="nav-inner clearfix">
            <div class="nav-logo" style="width: 412px;">
                <a href="#" class="logo-box" ga_event="nav_icon">
                    <img src="${pageContext.request.contextPath}/img/2.png" class="logo" style="height:35px;width:412px;">
                </a>
            </div>
            <div class="nav-title" style="margin-left: 120px;">
                <ul class="clearfix">
                    <li>
                        <a class="navbtn" href="adminIndex">
                            <span>后台首页</span>
                        </a>
                    </li>
                    <li>
                        <a class="navbtn" href="spiderIndex">
                            <span>爬虫配置</span>
                        </a>
                    </li>
                    <li>
                        <a class="navbtn" href="../toutiao/index.jsp">
                            <span>返回前台</span>
                        </a>
                    </li>
                </ul>
            </div>
            <div style="width:20px;height:50px;position:absolute;top:10px;right:120px;">
                <% Myuser obj = (Myuser) session.getAttribute("user");%>
                <label><%=obj.getName()%>
                </label>
                <span style="cursor:pointer;" onclick="exit()">注销</span>
            </div>
        </div>
    </div>

    <script language="javascript">
        function exit() {
            window.location.href = "../toutiao/index.jsp";
        }
    </script>
</div>
<div align="center">
    <label>标题：</label>
    <input id="title" class="modify" value=${news.title}>
    </input></br>
    <label>来源：</label>
    <input id="source" class="modify" value=${news.source}>
    </input>
    </br>
    <label>时间：</label>
    <input id="time" class="modify" value=${news.pubtime}>
    </input>
    <div id="newsid" style="display: none; ">
        ${news.id}
    </div>
    <div id="bodytext" style="display: none;">
        ${news.bodytext}
    </div>
    <div style="margin-top: 10px;">
        <script type="text/plain" id="myEditor" style="width: 1000px; height: 240px;">
        </script>
    </div>
    <div>
        <input type="button" class="yyjbtn" value="保存" onclick="savaModify()"/>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<script type="text/javascript">
    var um = UM.getEditor('myEditor');
    um.addListener('blur', function () {
        $('#focush2').html('编辑器失去焦点了')
    });
    um.addListener('focus', function () {
        $('#focush2').html('')
    });

    setContent('hahah');
    //按钮的操作
    function insertHtml() {
        var value = prompt('插入html代码', '');
        um.execCommand('insertHtml', value)
    }
    function isFocus() {
        alert(um.isFocus())
    }
    function doBlur() {
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

    function savaModify() {
        var id = $("#newsid").text();
        var title = $("#title").val();
        var source = $("#source").val();
        var pubtime = $("#time").val();
        var bodytext = UM.getEditor('myEditor').getContent();
        $.ajax({
            url: '/admin/modifyNews',  //得到json格式的新闻列表
            type: 'post',
            data: {
                id: id,
                title: title,
                source: source,
                pubtime: pubtime,
                bodytext: bodytext
            },
            dataType: 'json',
            success: function (result) {
                if (result == 1) {
                    alert("保存成功");
                    window.location.href = "../detail/"+id.trim();
                } else {
                    alert("保存失败");
                }
            }
        });

    }
</script>

</body>
</html>
