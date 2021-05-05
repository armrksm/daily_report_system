<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>商談状況　一覧</h2>
        <table id="meeting_list">
            <tbody>
                <tr>
                    <th class="meeting_name">営業担当</th>
                    <th class="meeting_date">商談日</th>
                    <th class="meeting_title">タイトル</th>
                    <th class="meeting_action">操作</th>
                </tr>

                <c:forEach var="meeting" items="${meeting}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="meeting_name"><c:out value="${meeting.emplyee.name}" /></td>
                        <td class="meeting_date"><fmt:formatDate value='${meeting.meeting_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="meeting_title">${meeting.title}</td>
                        <td class="meeting_action"><a href="<c:url value='/meetings/show?id=${meeting.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${meetings_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((meetings_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/meetings/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/meetings/new' />">新規商談の登録</a></p>

    </c:param>
</c:import>