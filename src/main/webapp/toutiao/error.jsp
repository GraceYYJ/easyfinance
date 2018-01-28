<!doctype html>
<html>
<jsp:include page="include.jsp"></jsp:include>
<style type="text/css">
.input {
    border: 1px solid #e9e9e9;
    border-radius: 4px;
    width: 310px;
    margin-left: auto;
	margin-right: auto;
}
.input label {
    font-size: 16px;
    color: #6b6b6b;
    padding: 13px 10px;
}
.name {
    background-position: 10px 10px;
}

.name, .login-dialog .password {
    border: 0;
    color: #5e5e5e;
    font-size: 16px;
    height: 18px;
    width: 227px;
    padding: 0;
    outline: 0;
    padding: 13px 10px;
}

.input-group {
    margin-bottom: 15px;
}

.password {
    background-position: 10px -45px;
    border: 0;
    color: #5e5e5e;
    font-size: 16px;
    height: 18px;
    width: 227px;
    padding: 0;
    outline: 0;
    padding: 13px 10px;
}

h3{
	font-size: 18px;
	font-weight: 700;
	color: #4f5157;
	line-height: 1px;
}

.login-dialog-header {
    font-size: 18px;
    font-weight: 700;
    color: #4f5157;
    line-height: 1px;
    padding-top: 28px;
    padding-bottom: 20px;
    padding-left: 84px;
    padding: 28px 0 20px 84px;
    
}

.submit-btn {
    margin-top: 20px;
    height: auto;
    padding: 11px 66px;
    line-height: 18px;
    font-size: 16px;
    background-color: #ff635c;
    border-radius: 4px;
    color: #fff;
    border: 0;
    outline: 0;
    cursor: pointer;
    box-shadow: 0 2px 2px rgba(14,5,9,.16);
}

</style>
<body class="night3">
	<jsp:include page="header.jsp"></jsp:include>
	<div id="container" class="bg-white clearfix">
		<div class="container-main">
			<div id="ibody"></div>
			<div class="article-detail">
				<div class="article-content" id="bodytext"></div>
			</div>
			<div id="pagelet-detailbar" class="clearfix"
				data-groupid="6256962119779188993"></div>

<div>
	<a class="btn" data-node="close">
		<i class="icon icon-close"></i>
	</a>
	
	<div class="login-dialog-inner" data-node="inner">
		<div class="login-pannel bottom-line">
			<form method="POST"  action="../admin/login"  data-node="loginForm"> 
				<ul>
					<li>
						<div class="input-group">
							<div class="input">
								<label>账号</label>
									<input class="name" name="username" placeholder="请输入您的用户名" autocomplete="off" spellcheck="false" type="text">
							</div>
						</div>
					</li>
					<li>
						<div class="input-group">
							<div class="input">
								<label>密码</label>
									<input class="password" name="password" data-node="password" placeholder="密码" autocomplete="off" spellcheck="false" type="password">
							</div>
						</div>
					</li>
					<li>
						<div class="input-group" style="text-align: center;">
                            <input class="submit-btn"  style="background-color: rgb(7,103,200);" value="登录" type="submit">
                            <p class="error" data-node="errorMsg"></p>
                        </div>
					</li>
				</ul>
			</form>
		</div>
	</div>
</div>
			

			<script type="text/javascript"
				src="//s2.pstatp.com/resource/toutiao_web/static/pkg/newindex_84b6a1a.js"></script>
			<script type="text/javascript"
				src="//s2.pstatp.com/resource/toutiao_web/static/pkg/core_d0cc667.js"></script>
			<script type="text/javascript"
				src="//s2.pstatp.com/resource/toutiao_web/static/pkg/detail_d9a48a7.js"></script>
				
			</div>	
			<jsp:include page="footer.jsp"></jsp:include>
</body>
<script src="../js/d3.min.js" charset="utf-8"></script>


</html>
