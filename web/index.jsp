<%-- 
   Document   : index
   Created on : May 31, 2025, 1:42:14 PM
   Author     : toila
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>User Page</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="./style/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>

    </head>

    <body class="sb-nav-fixed" style="overflow-x: hidden">
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

            <div id="layoutSidenav_content" style="background-color: #eee;">
                <main >
                    <div class="container py-5">
                        <c:forEach items="${requestScope.listBook}" var="i">
                            <div class="row justify-content-center mb-3">
                                <div class="col-md-10">
                                    <div class="card shadow-0 border rounded-3">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-3 mb-3">
                                                    <img src="${i.url}" class="img-fluid" />
                                                </div>
                                                <div class="col-md-9">
                                                    <h5 class="text-primary">${i.title}</h5>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <p><strong>Author:</strong> ${i.author}</p>
                                                            <p><strong>Category:</strong> ${i.category}</p>
                                                            <p class="${i.available_copies == 0 ? 'text-danger' : ''}">
                                                                <strong>Current:</strong> ${i.available_copies}
                                                            </p>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <p><strong>Published:</strong> ${i.published_year}</p>
                                                            <p><strong>ISBN:</strong> ${i.isbn}</p>
                                                        </div>
                                                    </div>
                                                    <c:choose>
                                                        <c:when test="${sessionScope.USER == null}">
                                                            <form action="Login.jsp" method="get">
                                                                <button type="submit" class="btn btn-primary btn-sm mt-2"
                                                                        ${i.available_copies > 0 ? "" : "disabled"}>
                                                                    Borrow
                                                                </button>
                                                            </form>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form action="MainController" method="post">
                                                                <input type="hidden" name="action" value="borrowBook" />
                                                                <input type="hidden" name="bookId" value="${i.id}" />
                                                                <button type="submit" class="btn btn-primary btn-sm mt-2"
                                                                        ${i.available_copies > 0 ? "" : "disabled"}>
                                                                    Borrow
                                                                </button>
                                                            </form>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
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

