<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${company != null}">
                <h2>id : ${company.id} の会社の詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                        <tr>
                            <th>会社名</th>
                            <td><c:out value="${company.name}" /></td>
                        </tr>
                        <tr>
                        	<th>担当者名</th>
                         	<td><c:out value="${company.client}"></c:out></td>
                        </tr>
                        <tr>
                        	<th>営業担当</th>
                        	<td><c:out value="${company.employee.name}"></c:out></td>
                        </tr>
                        <tr>
                        	<th>電話番号</th>
                        	<td><c:out value="${company.tell}"></c:out></td>
                        </tr>
                        <tr>
                        	<th>住所</th>
                        	<td><c:out value="${company.address}"></c:out></td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${company.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${company.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>


				<c:if test="${sessionScope.login_employee.id ==company.employee.id}">
                <p><a href="<c:url value='/companies/edit?id=${company.id}' />">この会社情報を編集する</a></p>
                </c:if>

            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
		<p><a href="<c:url value='/meetings/new'/>">新規商談登録</a></p>
        <p><a href="<c:url value='/companies/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>
