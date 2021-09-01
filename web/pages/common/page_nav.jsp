<%--
  Created by IntelliJ IDEA.
  User: renhongchang
  Date: 2021/8/30
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="page_nav">
    <%--页码大于1显示上一页的按钮--%>
    <c:if test="${ requestScope.page.pageNo > 1 }">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${ requestScope.page.pageNo-1 }">上一页</a>
    </c:if>

    <%--页码输出的开始--%>

    <c:choose>
        <%--情况一： 总页码小于5的情况，页码范围：1-总页码 --%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:forEach begin="1" end="${ requestScope.page.pageTotal }" var="i">
                <c:if test="${i == requestScope.page.pageNo}">
                    【${i}】
                </c:if>
                <c:if test="${i != requestScope.page.pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${i}">${ i }</a>
                </c:if>
            </c:forEach>
        </c:when>
        <%--总页码大于5的情况--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--小情况一：当前页码是前边三页,页码范围1-5--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:forEach begin="1" end="5" var="i">
                        <c:if test="${i == requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i != requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${ i }</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <%--小情况二：当前页码是最后三页，页码范围 总页码-4 To 总页码--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal - 3 }">
                    <c:forEach begin="${ requestScope.page.pageTotal - 4 }" end="${ requestScope.page.pageTotal }" var="i">
                        <c:if test="${i == requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i != requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${ i }</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <%--小情况三：当前页码不是前三页也不是最后三页，页码范围 当前页码-2 To 当前页码+2 --%>
                <c:otherwise>
                    <c:forEach begin="${ requestScope.page.pageNo - 2 }" end="${ requestScope.page.pageNo + 2 }" var="i">
                        <c:if test="${i == requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i != requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${ i }</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <%--页码输出的结束--%>

    <%--如果已经是最后一页则不显示下一页和末页--%>
    <c:if test="${ requestScope.page.pageNo < requestScope.page.pageTotal }">
        <a href="${requestScope.page.url}&pageNo=${ requestScope.page.pageNo+1 }">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${ requestScope.page.pageTotal }">末页</a>
    </c:if>
    共${ requestScope.page.pageTotal }页，${ requestScope.page.pageTotalCount }条记录
    到第<input value="${ param.pageNo }" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">


    <script type="text/javascript">
        $(function (){
            /*跳到指定的页码*/
            $("#searchPageBtn").click(function (){

                let pageNo = $("#pn_input").val();

                //获取总页码
                let pageTotal = ${ requestScope.page.pageTotal };
                //如果跳转输入小于1或者大于总页码 则不跳转，输入框显示当前也，不跳转
                if (pageNo < 1 || pageNo > pageTotal) {
                    $("#pn_input").val(${ param.pageNo });
                    return false;
                }
                //JavaScript中提供了一个location地址栏对象
                // location的href属性可以获取地址栏的内容
                // href属性可读、可写
                location.href = "${ pageScope.basePath }${requestScope.page.url}&pageNo=" + pageNo;

            })
        });
    </script>

</div>
