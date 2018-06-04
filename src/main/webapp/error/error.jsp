<!doctype html>
<html>
<link rel="stylesheet" type="text/css" href="../css/yyj.css">
<jsp:include page="include.jsp"></jsp:include>
<div>
	<a class="btn" data-node="close">
		<i class="icon icon-close"></i>
	</a>
	<div class="login-dialog-header">
		<h3>管理员登陆</h3>
	</div>
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


</html>