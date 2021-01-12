<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#page-top"><img src="images/Logo/logo_c08.png" alt="" /> </a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars ml-1"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav text-uppercase ml-auto">
                <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#services">HOME</a></li>
                <li class="dropdown order-1 nav-item">
                    <button type="button" id="dropdownMenu1" data-toggle="dropdown" class="btn nav-link">LOGIN<span class="caret"></span></button>
                    <ul class="dropdown-menu dropdown-menu-right mt-2">
                        <li class="px-3 py-2">
                            <form class="form" role="form" action="autentication" method="post">
                                <input type="hidden" value="2" name="flag">
                                <div class="form-group">
                                    <input id="username" placeholder="Username" class="form-control form-control-sm" type="text" name="username">
                                </div>
                                <div class="form-group">
                                    <input id="password" placeholder="Password" class="form-control form-control-sm" type="text" name="password">
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary btn-block">Login</button>
                                </div>
                            </form>
                            <form action="autentication" method="get">
                                <div class="form-group">
                                    <input type="hidden" value="-1" name="flag">
                                    <button type="submit" class="btn btn-primary btn-block">Registrazione</button>
                                </div>
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>

        </div>
    </div>
</nav>
