<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="daycares">
    <h2>Daycares</h2>
    
    <table id="daycareTable" class="table table-striped">
        <thead>
        <tr>
            
            <th>Date</th>
            <th>Care type</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${daycares}" var="daycare">
            <tr>
            	<td>
                	<petclinic:localDate date="${daycare.date}" pattern="yyyy-MM-dd"/>
               	</td>
                <td>
                    <c:out value="${daycare.cuidado}"/>
                </td>
                <td>
                    <spring:url value="/daycare/delete/{daycareId}" var="daycareDeleteUrl">
                        <spring:param name="daycareId" value="${daycare.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(daycareDeleteUrl)}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        
    </table>
</petclinic:layout>
