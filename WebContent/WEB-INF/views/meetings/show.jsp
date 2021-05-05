<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${meeting != null}">
                <h2>商談　詳細ぺージ</h2>

                <table>
                    <tbody>
                        <tr>
                        	<th>取引先</th>
                        	<td><c:out value="${meeting.company.name}"></c:out></td>
                        </tr>
                        <tr>
                        	<th>担当者名</th>
                        	<td><c:out value="${meeting.company.client}"></c:out></td>
                        </tr>
                        <tr>
                            <th>営業担当</th>
                            <td><c:out value="${meeting.employee.name}" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${meeting.meeting_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${meeting.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${meeting.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${meeting.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_employee.id == meeting.employee.id}">
                    <p><a href="<c:url value="/meetings/edit?id=${meeting.id}" />">この商談を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/meetings/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>