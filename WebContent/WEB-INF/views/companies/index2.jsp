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
        <h2>会社　一覧</h2>
        <table id="company_list">
            <tbody>
                <tr>
                    <th class="company_name">会社名</th>
                    <th class="company_client">担当者名</th>
                    <th class="company_tell">電話番号</th>
                    <th class="company_address">住所</th>
                    <th class="company_action">操作</th>
                </tr>
                <c:forEach var="company" items="${companies}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="company_name"><c:out value="${company.employee.name}" /></td>
                        <td class="company_name">${comany.name}</td>
                        <td class="company_client">${company.client}</td>
                        <td class="company_tell">${company.tell}</td>
                        <td class="company_address">${company.address}</td>
                        <td class="report_action"><a href="<c:url value='/companis/show?id=${companies.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${companies_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((companies_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/companies/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/companies/new' />">新規会社登録</a></p>

    </c:param>
</c:import>
