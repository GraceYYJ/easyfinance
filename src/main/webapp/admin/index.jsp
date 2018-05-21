<%@ page language="java" import="java.util.*,com.yangyujuan.jdbc.domain.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html>
<jsp:include page="include.jsp"></jsp:include>
<body class="night3">
<jsp:include page="header.jsp"></jsp:include>
<div id="container" class="bg-white clearfix">
    <div class="container-main" style="width:100%;">
        <div id="pagelet-feedlist" style="font-size: 16px; color: #4f5157;">

        </div>

        <div class="pagination pagination-centered">
            <ul>
                <li class="disabled">
                    <a onclick='changePageNum(0)'>&laquo;</a></li>
                <li class="active"><a id='currentPage'><s:property value="#gCurPageNum"/></a></li>
                <li class="disabled"><a onclick='changePageNum(1)'>&raquo;</a></li>
            </ul>
            <div>
                <input id="hidPageCount" style="display:none" value="<s:property value="#iPageCount" />"></input>
                <input id='inputPageNum' type="text" style="width: 53px"
                       placeholder="共<s:property value="#iPageCount" />页" class="form-control"
                       aria-label="Amount (to the nearest dollar)">
                <button id='btGo' type="button" onclick="goPage()"
                        class="btn btn-default" style="margin-top: -10px">Go
                </button>
            </div>
        </div>

    </div>

</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
<script type="text/javascript">

    var gCurPageNum = 1;
    $(function () {
        RefreshPage(gCurPageNum);
        initPageCount();
    });

    var gPageCount;
    function initPageCount() {
        $.ajax({
            url: '/news/getNewsPageCount',
            type: 'post',
            data: "{}",
            dataType: 'json',
            success: function (result) {
                gPageCount = result.pageCount;
                $("#inputPageNum").attr("placeholder", "共" + gPageCount + "页");
                //alert(result);
                //var vbody = getBody(newslist);
                //$("#pagelet-feedlist").html(vbody);
                // $("#ibody").children().remove();
                //$("#ibody").append(vbody);
            }
        });
    }

    function getBody(newslist) {
        var vTitle = new Array();
        vTitle[0] = "id";
        vTitle[1] = "title";
        vTitle[2] = "pubTime";
        vTitle[3] = "source";
        vTitle[4] = "bodytext";
        vTitle[5] = "visits";
        var vLen = newslist.length;
        var vBody = "";
        for (var vi = 0; vi < vLen; vi++) {

            vBody += "<ul><li class='item' style=\"cursor:pointer;padding: 15px 0 15px;\" onclick=\"goNewsDetail('" + newslist[vi][vTitle[0]] + "')\"><div style=\"font-weight: 700;\">" + newslist[vi][vTitle[1]] + "</div><p style=\"height: 24.63px;\"><span class='other'>" + newslist[vi][vTitle[3]] + "</span><span class='footer-right'>" + newslist[vi][vTitle[2]] + "</span></p></li></ul>";
        }
        return vBody;
    }
    function RefreshPage(pageNum) {
        $.ajax({
            url:'/news/getNewsListJson',  //得到json格式的新闻列表
            type:'post',
            data:"pageNum=" + pageNum,
            dataType:'json',
            success:function (resultlist) {
                var newslist = resultlist;
                //alert(newslist);
                //alert(resultlist);
                var vbody = getBody(newslist);
                $("#pagelet-feedlist").html(vbody);
                // $("#ibody").children().remove();
                //$("#ibody").append(vbody);
            }
        });
    }
    //0表示向左，即减一
    //1表示向右，即加一
    function changePageNum(flag) {
        //alert("vPageCount" +vPageCount);
        var vCurPage = $("#currentPage").text();
        var gPageCount = $("#hidPageCount").val() * 1;
        var nextPage = 0;
        if (flag == 0 && vCurPage > 1) {
            nextPage = vCurPage * 1 - 1;
        } else if (flag == 1 && vCurPage < gPageCount) {
            nextPage = vCurPage * 1 + 1;
        }
        if (nextPage <= gPageCount && nextPage > 0) {
            gCurPageNum = nextPage;
            $("#currentPage").text(nextPage);
            window.location.href = "adminIndex?pageNum=" + gCurPageNum;
        }
    }

    function goPage() {
        var vPageNum = $("#inputPageNum").val();
        var gPageCount = $("#hidPageCount").val() * 1;
        if (vPageNum <= gPageCount && vPageNum > 0) {
            $("#currentPage").text(vPageNum);
            window.location.href = "adminIndex?pageNum=" + vPageNum;
        } else {
            alert("页码超出范围");
        }
    }

    function showDetail(id) {
        window.open("detail?id=" + id);
    }


    function deleteNews2(id) {
        var msg = "确认删除吗？";
        if (confirm(msg) == true) {
            window.location.href = "delete?id=" + id;
        } else {
            return false;
        }
    }

    function deleteNews(id) {
        var msg = "确认删除吗？";
        if (confirm(msg) == true) {
            $.ajax({
                url: '/admin/delete',
                type: 'post',
                data: {
                    id: id
                },
                dataType: 'json',
                success: function (result) {
                    //var gPageCount = $("#hidPageCount").val()*1;
                    //window.location.href="adminIndex.action?pageNum="+gCurPageNum;
                    window.location.reload();
                }
            });
        } else {
            return false;
        }
    }
</script>

</body>
</html>
