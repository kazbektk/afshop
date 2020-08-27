<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="categoryDao" class="com.epam.afshop.dao.impl.CategoryDaoImpl" />
<jsp:useBean id="productDao" class="com.epam.afshop.dao.impl.ProductDaoImpl"/>
<jsp:useBean id="teamDao" class="com.epam.afshop.dao.impl.TeamDaoImpl"/>

<div class="d-flex justify-content-around">

    <c:forEach var="product" items="${productDao.getAll()}">
        <%System.out.println("works");%>
        <div class="card" style="width: 18rem;">
            <img src="..." class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">${product.getName()}</h5>
                <p class="card-text">
                    Team: ${teamDao.getById(product.getTeamId())}
                    <%
                        System.out.println(teamDao.getById(2).getName());
                        teamDao.getAll().forEach(team -> System.out.println(team.getName()));
                    %>

                </p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
        </div>
    </c:forEach>

</div>