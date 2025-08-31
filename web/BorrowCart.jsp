<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Borrow Cart</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="./style/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>

    </head>

    <body class="sb-nav-fixed" style="overflow-x: hidden">
        <c:set var="USER" value="${sessionScope.USER}"/>
        <c:if test="${empty USER}">
            <jsp:forward page="index.jsp"/>
        </c:if>
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="#">Library Manager</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" ><i
                    class="fas fa-bars"></i></button>
            <form method="get" action="MainController" class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input type="hidden" name="action" value="SearchBookUser"/>

                    <!-- Ô nhập từ khóa -->
                    <input name="keyword" class="form-control" type="text" placeholder="Search ..."
                           aria-label="Search for..." aria-describedby="btnNavbarSearch" />

                    <!-- Select để chọn loại tìm kiếm -->
                    <select name="filter" class="form-select">
                        <option value="name">Name</option>
                        <option value="author">Author</option>
                        <option value="category">Category</option>
                    </select>

                    <!-- Nút tìm -->
                    <button class="btn btn-primary" id="btnNavbarSearch" type="submit">
                        <i class="fas fa-search"></i>
                    </button> 
                </div>
            </form>
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <c:choose>
                            <c:when test="${sessionScope.USER == null}">
                                <li><a class="dropdown-item" href="MainController?action=login">Login</a></li>
                                </c:when>
                                <c:otherwise>
                                <li><a class="dropdown-item" href="MainController?action=viewProfile">View Profile</a></li>
                                <li><a class="dropdown-item" href="MainController?action=logout">Logout</a></li>
                                </c:otherwise>
                            </c:choose>
                    </ul>
                </li>
            </ul>
        </nav>

        <div id="layoutSidenav">
            <!-- Sidebar  -->
            <%@include file="./SideBarUser.jsp" %>

            <div id="layoutSidenav_content">
                <main>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="main-box clearfix">
                                <div class="table-responsive">
                                    <h1 style="text-align: center; margin: 10px 0;" class="text-primary">Borrow Cart</h1>

                                    <table class="table user-list">
                                        <thead>
                                            <tr>
                                                <th class="text-center"><span>ID</span></th>
                                                <th class="text-center"><span>Book</span></th>
                                                <th class="text-center"><span>Status</span></th>
                                                <th class="text-center"><span>Request Date</span></th>                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.BORROW_REQUESTS}" var="i">
                                                <tr>
                                                    <td class="text-center">${i.id}</td>
                                                    <td class="text-center" style="font-weight: 500">${i.bookTitle}</td>
                                                    <td class="text-center">
                                                        <c:choose>
                                                            <c:when test="${i.status eq 'pending'}">
                                                                <span class="text-warning">${i.status}</span>
                                                            </c:when>
                                                            <c:when test="${i.status eq 'approved'}">
                                                                <span class="text-success">${i.status}</span>
                                                            </c:when>
                                                            <c:when test="${i.status eq 'rejected'}">
                                                                <span class="text-danger">${i.status}</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${i.status}
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td class="text-center">
                                                        ${i.requestDate}"
                                                    </td>                                                   
                                                    
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <c:if test="${empty requestScope.BORROW_REQUESTS}">
                                        <div class="alert alert-info text-center mt-3">
                                            Your borrow cart is currently empty.
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>

                <!-- Footer -->
                <%@include file="./includes/footer.jsp"%>
            </div> 
        </div> 
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
        <script>
                                                                window.addEventListener('DOMContentLoaded', event => {
                                                                    const sidebarToggle = document.body.querySelector('#sidebarToggle');
                                                                    if (sidebarToggle) {
                                                                        sidebarToggle.addEventListener('click', event => {
                                                                            event.preventDefault();
                                                                            document.body.classList.toggle('sb-sidenav-toggled');
                                                                            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
                                                                        });
                                                                    }

                                                                });
        </script>

    </body>

</html>
