<%@ page language="java" import="java.util.*,com.gracyya.model.Myuser" %>
    <div id="wrapper">
    <div id="pagelet-nav" data-test="toutiao.com" style="box-shadow: 0 2px 0 rgba(7,103,200,.9);">
    <div class="nav-inner clearfix">
        <div class="nav-logo" style="width: 412px;">
            <a href="#" class="logo-box" ga_event="nav_icon">
                <img src="../img/2.png" class="logo" style="height:35px;width:412px;">
            </a>
        </div>
        <div class="nav-title" style="margin-left: 120px;">
            <ul class="clearfix">
                <li>
                    <a class="navbtn" href="index.jsp">
                        <span>后台首页</span>
                    </a>
                </li>
                <li>
                    <a class="navbtn" href="spiderIndex">
                        <span>爬虫配置</span>
                    </a>
                </li>
                <li>
                    <a  class="navbtn" href="../toutiao/index.jsp">
                        <span>返回前台</span>
                    </a>
                </li>
            </ul>
        </div>
        <div style="width:20px;height:50px;position:absolute;top:10px;right:120px;">
        <% Myuser obj = (Myuser)session.getAttribute("user");%>
        	<label><%=obj.getName()%></label>
        	<span style="cursor:pointer;" onclick="exit()">注销</span>
        </div>
    </div>
</div>

  <script language="javascript">
       function exit(){
    	   window.location.href = "../toutiao/index.jsp";
         } 
  </script>
</div>