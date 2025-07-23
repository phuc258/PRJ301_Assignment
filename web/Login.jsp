<%-- 
    Document   : Login
    Created on : Jun 7, 2025, 2:42:43 PM
    Author     : SE190585
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Login</title>
        <link href="./style/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header">
                                        <h3 class="text-center font-weight-light my-4">Login</h3>
                                        <h5 class="text-center text-success font-weight-light my-4">${messagRegister}</h5>
                                    </div>
                                    <div class="card-body">
                                        <form method="POST" action="MainController">
                                            <input type="hidden" name="action" value="loginController"/>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputUsername" type="text" placeholder="Email" name="txtemail"/>
                                                <label for="inputEmail">Email</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputPassword" type="password" placeholder="Password" name="txtpassword"/>
                                                <label for="inputPassword">Password</label>
                                            </div>
                                            <p style="color: red">${requestScope.ERROR}</p>
                                            <div class="d-flex align-items-center justify-content-center mt-4 mb-0">
                                                <input class="btn btn-primary" type="submit" value="login"/>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small"><a href="MainController?action=register">Need an account? Sign up!</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
