<!doctype html>
<html>
<jsp:include page="include.jsp"></jsp:include>
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

			<jsp:include page="footer.jsp"></jsp:include>

			<script type="text/javascript"
				src="//s2.pstatp.com/resource/toutiao_web/static/pkg/newindex_84b6a1a.js"></script>
			<script type="text/javascript"
				src="//s2.pstatp.com/resource/toutiao_web/static/pkg/core_d0cc667.js"></script>
			<script type="text/javascript"
				src="//s2.pstatp.com/resource/toutiao_web/static/pkg/detail_d9a48a7.js"></script>
</body>
<script src="../js/d3.min.js" charset="utf-8"></script>

<script type="text/javascript">
	$(function() {
		getHotWordJson();
	});
	var diameter = 600, format = d3.format(",d"), color = d3.scale
			.category20c();

	var bubble = d3.layout.pack().sort(null).size([ diameter, diameter ])
			.padding(1.5);

	var svg = d3.select("#ibody").append("svg").attr("width", diameter).attr(
			"height", diameter).attr("class", "bubble");

	function getHotWordJson() {
		var jResult;
		$.ajax({
			url : 'getHotWord.action',
			type : 'post',
			data : "{}",
			dataType : 'json',
			success : function(result) {
				jResult = JSON.parse(result);
				var node = svg.selectAll(".node").data(
						bubble.nodes(classes(jResult)).filter(function(d) {
							return !d.children;
						})).enter().append("g").attr("class", "node").attr(
						"transform", function(d) {
							return "translate(" + d.x + "," + d.y + ")";
						});

				node.append("title").text(function(d) {
					return d.className + ": " + format(d.value);
				});

				node.append("circle").attr("r", function(d) {
					return d.r;
				}).style("fill", function(d) {
					//if (d.r < 20) {
					//	return "rgb(249,205,173)";
					//} else if (d.r < 25) {
					//	return "rgb(200,200,169)";
					//} else if (d.r < 40) {
					//	return "rgb(252,157,154)";
					//} else if (d.r < 50) {
					//	return "rgb(131,175,155)";
					//} else if (d.r < 60) {
					//	return "rgb(254,67,101)";
					//} else {
					//	return "rgb(254,67,101)";
					//}
					
					if (d.r < 26.5) {
						return "rgb(240,200,170)";
					} else if (d.r < 30.5) {
						return "rgb(176,196,222)";//135,206,250  200,200,160
					} else if (d.r < 37.5) {
						return "rgb(250,150,150)";
					} else if (d.r < 50) {
						return "rgb(250,60,100)";
					} else if (d.r < 60) {
						return "rgb(250,60,100)";
					} else {
						return "rgb(250,60,100)";
					}
					
					//if (d.r < 20) {
					//	return "rgb(133,201,235)";
					//} else if (d.r < 30) {
					//	return "rgb(75,174,226)";//135,206,250  200,200,160
					//} else if (d.r < 40) {
					//	return "rgb(29,141,229)";
					//} else if (d.r < 50) {
					//	return "rgb(0,112,192)";
					//} else if (d.r < 60) {
					//	return "rgb(0,112,192)";
					//} else {
					//	return "rgb(0,112,192)";
					//}
				});
				//color(d.packageName)
				node.append("text").attr("dy", ".3em").style("text-anchor",
						"middle").text(function(d) {
					return d.className.substring(0, d.r / 3);
				});
			}
		});
	}

	// Returns a flattened hierarchy containing all leaf nodes under the root.
	function classes(root) {
		var classes = [];
		function recurse(name, node) {
			if (node.children)
				node.children.forEach(function(child) {
					recurse(node.name, child);
				});
			else
				classes.push({
					packageName : name,
					className : node.name,
					value : node.size
				});
		}
		recurse(null, root);
		return {
			children : classes
		};
	}

	d3.select(self.frameElement).style("height", diameter + "px");
</script>
</html>
