<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>SignUp</title>
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
                                        <h3 class="text-center font-weight-light my-4">Create Account</h3>
                                    </div>
                                    <div class="card-body">
                                        <form action="MainController" method="POST">
                                            <input type="hidden" name="action" value="Register">
                                            <div class="mb-3">
                                                <div class="form-floating">
                                                    <input value="${user.name}" class="form-control" id="inputName" type="text" placeholder="Enter your Name" name="name" minlength="1" required />
                                                    <label for="inputName">Name</label>
                                                </div>
                                            </div>

                                            <div class="mb-3">
                                                <div class="form-floating">
                                                    <input value="${user.email}" class="form-control" id="inputEmail" type="email" name="txtemail" placeholder="Enter your email" minlength="1" required/>
                                                    <label for="inputEmail">Email</label>
                                                </div>
                                            </div>                                         
                                            <div class="mb-3">
                                                <div class="form-floating">
                                                    <input class="form-control" id="inputPassword" name="password" type="password" placeholder="Create a password" required />
                                                    <label for="inputPassword">Password</label>
                                                </div>
                                            </div>

                                            <div class="mb-3">
                                                <div class="form-floating">
                                                    <input class="form-control" id="inputPasswordConfirm" name="passwordConfirm" type="password" placeholder="Confirm password" required />
                                                    <label for="inputPasswordConfirm">Confirm Password</label>
                                                </div>
                                            </div>

                                            <h5 style="color: red">${message}</h5>

                                            <div class="mt-4 mb-0">
                                                <div class="d-grid">
                                                    <input class="btn btn-primary" type="submit" value="Create Account" />
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small"><a href="MainController?action=login">Have an account? Go to login</a></div>
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
