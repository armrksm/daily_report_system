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

<label for="name">会社名</label><br />
<input type="text" name="name" value="${company.name}" />
<br /><br />

<label for="name">営業担当</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />


<label for="tell">電話番号</label><br />
<input type="text" name="tell" value="${company.tell}" />
<br /><br />

<label for="address">住所</label><br />
<input type="text" name="address" value="${company.address}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>