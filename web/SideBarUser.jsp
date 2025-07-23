<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">Core</div>

                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                   data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                    <div class="sb-nav-link-icon"><i class="fa-solid fa-book"></i></div>
                    View Book
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse show" id="collapseLayouts" aria-labelledby="headingOne"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="MainController?action=viewbook">All Book</a>
                        <c:forEach items="${listBook}" var="i">
                            <p class="nav-link">${i.category}</p>
                        </c:forEach>
                    </nav>
                </div>


                <div class="sb-sidenav-menu-heading">Action</div>

                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                   data-bs-target="#collapseLayoutss" aria-expanded="false" aria-controls="collapseLayoutss">
                    <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                    Borrow
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse show" id="collapseLayoutss" aria-labelledby="headingOne"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">                        
                        <a class="nav-link" href="MainController?action=borrowBook">Borrow Cart</a>                        
                    </nav>
                    <nav class="sb-sidenav-menu-nested nav">                        
                        <a class="nav-link" href="MainController?action=borrowProcess">Borrow Process</a>                        
                    </nav>
                    <nav class="sb-sidenav-menu-nested nav">                        
                        <a class="nav-link" href="MainController?action=borrowHistory">Borrow History</a>                        
                    </nav>
                </div>                        
            </div>
        </div>
        <div class="sb-sidenav-footer">
            <div class="small">Logged in as:</div>
            <c:choose>
                <c:when test="${not empty sessionScope.USER}">
                    ${sessionScope.USER.name}
                </c:when>
                <c:otherwise>
                    Guest
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
</div>
