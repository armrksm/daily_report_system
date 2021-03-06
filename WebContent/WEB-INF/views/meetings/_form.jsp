<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="meeting_date">商談日</label><br />
<input type="date" name="meeting_date" value="<fmt:formatDate value='${meeting.meeting_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">取引先</label><br />
<c:out value="${sessionScope.select_company.name}"></c:out>
<br /><br/>

<label for="client">担当者名</label><br />
<c:out value="${sessionScope.select_company.client}"></c:out>
<br /><br />


<label for="name">営業担当</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<label for="title">タイトル</label><br />
<input type="text" name="title" value="${meeting.title}" />
<br /><br />

<label for="content">内容</label><br />
<textarea name="content" rows="10" cols="50">${meeting.content}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>