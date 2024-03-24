<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Product List</title>
</head>
<body>
    <h2>Product List</h2>
    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Material</th>
            <th>Origin</th>
            <th>Category</th> <!-- Thêm cột danh mục -->
            <th>Image URL</th>
            <th>Price</th>
        </tr>	

        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.material}</td>
                <td>${product.origin}</td>
                <td>${product.category}</td> <!-- Hiển thị danh mục -->
                <td><img${product.imageUrl}/></td>
                <td>${product.price}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
