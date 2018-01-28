<!doctype html>
<html>
<link rel="stylesheet" type="text/css" href="../css/yyj.css">
	<jsp:include page="include.jsp"></jsp:include>
<body class="night3">
    <jsp:include page="header.jsp"></jsp:include>
    <div id="container" class="bg-white clearfix">
        <div class="container-main">
           <div class="article-detail">
               <div class="title">
                   <h1 id="h1"></h1>
                   <div class="subtitle clearfix">
                        <span class="src" id="source"></span>
                        <span class="time" id="time"></span>
                   </div>
               </div>
               <div class="article-content" id="bodytext">
               </div>
           </div>   
           <div id="pagelet-detailbar" class="clearfix"
     data-groupid="6256962119779188993">

    <ul data-node="tagList" class="tag-list">
       <i class="lb">相关标签：</i>
    </ul>
</div>

    <jsp:include page="footer.jsp"></jsp:include>
    <script type="text/javascript">
	$(function() {
		initNews();
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
		$("#h1").html(news[vTitle[1]]);
		$("#source").html(news[vTitle[3]]);
		$("#time").html(news[vTitle[2]]);
		$("#bodytext").html(news[vTitle[4]]);
		
		vBody += "<h3>" + news[vTitle[1]] + "</h3><h4>"
				+ news[vTitle[3]] + "</h4><h4>" + news[vTitle[2]]
				+ "</h4><h4>" + news[vTitle[4]] + "</h4>";
		//alert("vbody  "+ vBody);
		return vBody;
	}

	function initNews() {
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
				//$("#ibody").html(vbody);
				// $("#ibody").children().remove();
				//$("#ibody").append(vbody);
			}
		});
	}
    </script>
    <script type="text/javascript">
    (function(){
        var host = location.host;
        if(host != 'toutiao.com') {
            var $el = $('#secondaryCompanyWrapper').show();
            var $cName = $el.find('.J-company-name');

            $('#toutiaoCompanyWrapper').hide();
            var html = $cName.html();
            html = html.replace('toutiao.com', host);
            $cName.html(html);
        }
    }());
    </script>
    <script>
        window.scrollTo(0, 0);
        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-28423340-5']);
        _gaq.push(['_trackPageview']);
        _gaq.push(['_addOrganic','baidu','wd',true]);
        _gaq.push(['_addOrganic','image.baidu','word',true]);
        _gaq.push(['_addOrganic','pic.baidu','word',true]);
        _gaq.push(['_addOrganic','haosou','q',true]);
        _gaq.push(['_addOrganic','so','q',true]);
        _gaq.push(['_addOrganic','sogou','query',true]);
        _gaq.push(['_addOrganic','google','q',true]);
        _gaq.push(['_addOrganic','bing','q',true]);
        _gaq.push(['_addOrganic','youdao','q',true]);
    
        window.gaqpush = function (ga_event, ga_label){
            _gaq.push(['_trackEvent', 'event', ga_event, ga_label, 1]);
        };

        (function() {
            var ga = document.createElement('script');
            ga.type = 'text/javascript'; 
            ga.src = 'http://www.google-analytics.com/ga.js';
            var s = document.getElementsByTagName('script')[0]; 
            s.parentNode.insertBefore(ga, s);
         })();

        $("body").on('click', '[ga_event]', function(e){
             var $this =  $(this);
             var ga_category = $this.attr('ga_category') || '/group/?type=article&amp;tag=news_finance';
             var ga_event = $this.attr('ga_event');
             var ga_label = $this.attr('ga_label');
             var ga_value = $this.attr('ga_value')||1;
             if(ga_category != '/') {
                _gaq.push(['_trackEvent', ga_category, ga_event, ga_label, ga_value]);   
             }
        });

        $.ajaxSetup({
            cache: false,
            dataType: 'json',
            beforeSend: function(xhr, settings) {
                if (!(/^http:.*/.test(settings.url) || /^https:.*/.test(settings.url))) {
                    // Only send the token to relative URLs i.e. locally.
                    xhr.setRequestHeader("X-CSRFToken", $.cookie('csrftoken'));
                }
            },
            dataFilter: function(data, type) {
                try{
                    data = data.replace(/"group_id": (\d+)/g, function(str, p, offset, s) {
                        var items = str.split(':');
                        items[1] = '"'+$.trim(items[1])+'"';
                        str = items.join(':');
                        return str;
                    });
                }catch(e){}
                return data;
            }
        });
        $(function(){
            if(document.referrer && document.referrer.indexOf("toutiao.org")>-1) {
                if(window.location.host != "toutiao.org"){
                    window.location.host = "toutiao.org";
                }
            }
        });
    </script>
    
    <script type="text/javascript" src="//s2.pstatp.com/resource/toutiao_web/static/pkg/newindex_84b6a1a.js"></script>
<script type="text/javascript" src="//s2.pstatp.com/resource/toutiao_web/static/pkg/core_d0cc667.js"></script>
<script type="text/javascript" src="//s2.pstatp.com/resource/toutiao_web/static/pkg/detail_d9a48a7.js"></script>

    <script>
        /*jshint ignore:start*/
        require('pagelet/nav/nav.js').create({
            'type': 'home'
        });
        require('pagelet/detailbar/detailbar.js').create({});

        
         
        require('pagelet/comment/comment.js').create({});
        require('pagelet/navline/nav_line.js').create({});

        /*jshint ignore:end*/
    </script>

</body>
</html>
