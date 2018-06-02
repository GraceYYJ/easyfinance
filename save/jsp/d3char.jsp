<!DOCTYPE html>
<meta charset="utf-8">
<script src="../js/d3.min.js" charset="utf-8"></script>
<style>

text {
  font: 10px sans-serif;
}

</style>
<html>
<jsp:include page="pagehead.jsp"></jsp:include>
  <body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="page">
		<div class="page-container">
			<div class="container">
				<div class="row">
					<div class="span12">
						<div id="ibody" style="height:600px;margin:0 auto;width:600px;">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
  <script src="../js/d3-setup.js"></script>
  <script src="../js/yyj.js"></script>
  <script>

  var diameter = 600,
      format = d3.format(",d"),
      color = d3.scale.category20c();

  var bubble = d3.layout.pack()
      .sort(null)
      .size([diameter, diameter])
      .padding(1.5);

  var svg = d3.select("#ibody").append("svg")
      .attr("width", diameter)
      .attr("height", diameter)
      .attr("class", "bubble");
  
  //"flare.json
  d3.json("flare4.json", function(error, root) {
   if (error) throw error;
    
    var node = svg.selectAll(".node")
        .data(bubble.nodes(classes(root))
        .filter(function(d) { return !d.children; }))
      .enter().append("g")
        .attr("class", "node")
        .attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; });

    node.append("title")
        .text(function(d) { return d.className + ": " + format(d.value); });

    node.append("circle")
       .attr("r", function(d) { return d.r; })
       .style("fill", function(d) { 
    	   if(d.r<14){
    		   return "rgb(249,205,173)";
    	   }else if(d.r<16){
    		   return "rgb(200,200,169)";
    	   }else if(d.r<18){
    		   return "rgb(252,157,154)";
    	   }else if(d.r<20){
    		   return "rgb(131,175,155)";
    	   }else if(d.r<22){
    		   return "rgb(254,67,101)";
    	   }
    	   else{
    		   return "rgb(254,67,101)";
    	   }
    	    });
       
    //color(d.packageName)
 

    node.append("text")
        .attr("dy", ".3em")
        .style("text-anchor", "middle")
        .text(function(d) { return d.className.substring(0, d.r / 3); });
 });

 
  
  // Returns a flattened hierarchy containing all leaf nodes under the root.
  function classes(root) {
    var classes = [];
    function recurse(name, node) {
      if (node.children) node.children.forEach(function(child) { recurse(node.name, child); });
      else classes.push({packageName: name, className: node.name, value: node.size});
    }
    recurse(null, root);
    return {children: classes};
  }

  d3.select(self.frameElement).style("height", diameter + "px");
  
  
		</script>
</html>