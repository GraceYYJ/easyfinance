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
						<div id="ibody">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
  <script src="../js/d3-setup.js"></script>
  <script>
			$(function() {
				static();
			});

			function getNewsId() {
				var url = location.search; //获取url中"?"符后的字串
				if (url.indexOf("?") != -1) { //判断是否有参数
					var str = url.substr(1); //从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
					strs = str.split("="); //用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔 再用等号进行分隔）
					//alert(strs[1]);          //直接弹出第一个参数 （如果有多个参数 还要进行循环的）
					return strs[1];
				}
			}

			function getBody(news) {
				var vTitle = new Array();
				vTitle[0] = "id";
				vTitle[1] = "title";
				vTitle[2] = "pubTime";
				vTitle[3] = "source";
				vTitle[4] = "bodytext";
				vTitle[5] = "visits";
				var vBody = "";
				vBody += "<h3>" + news[vTitle[1]] + "</h3><h4>"
						+ news[vTitle[3]] + "</h4><h4>" + news[vTitle[2]]
						+ "</h4><h4>" + news[vTitle[4]] + "</h4>";
				//alert("vbody  "+ vBody);
				return vBody;
			}

			function static() {
				var vNewsId = getNewsId();
				//alert("vNewsId  "+vNewsId);
				$.ajax({
					url : 'getSingleNewsJson.action',
					type : 'post',
					data : "newsid=" + vNewsId,
					dataType : 'json',
					success : function(result) {
						// alert("result  "+ result);
						var newslist = JSON.parse(result);
						// alert("newslist  "+newslist);
						// alert("resultlist  "+ result);
						var vbody = getBody(newslist);
						//alert("vbody  "+ vbody);
						$("#ibody").html(vbody);
						// $("#ibody").children().remove();
						//$("#ibody").append(vbody);
					}
				});
			}

			protocol = window.location.protocol === 'http:' ? 'ws://'
					: 'wss://';
			address = protocol + window.location.host
					+ window.location.pathname + '/ws';
			socket = new WebSocket(address);
			socket.onmessage = function(msg) {
				msg.data == 'reload' && window.location.reload()
			}
		</script>
</html>