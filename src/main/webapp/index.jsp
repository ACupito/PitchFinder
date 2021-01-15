<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>PitchFinder</title>
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/homepage/style_homepage.css" rel="stylesheet" />
    <link href="css/footer/style_footer.css" rel="stylesheet">
</head>
<body id="page-top">
<!-- Navigation-->
<%@ include file="view/navbar/navbar.jsp"%>
<!-- Masthead-->
<header class="mastheadCarousel">
    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="images/homepage/homeTennis.jpg" alt="First slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="images/homepage/basketHome.jpg" alt="Second slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="images/homepage/calcioHome.jpg" alt="Third slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="images/homepage/pallavoloHome.jpg" alt="Third slide">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</header>
<!-- Services-->
<section class="page-section" id="services">
    <div class="container">
        <div class="text-center">
            <h2 class="section-heading text-uppercase">Servizi</h2>
            <h3 class="section-subheading text-muted">Servizi offerti dalla piattaforma</h3>
        </div>
        <div class="row text-center">
            <div class="col-md-4">
                        <span class="fa-stack fa-4x">
                            <form action="torneoServlet" method="get">
                            <button>
                                <input type="hidden" name="flag" value="3">
                            <i class="fas fa-circle fa-stack-2x text-primary"></i>
                            <i class="fas fa-trophy fa-stack-1x fa-inverse"></i>
                                </button>
                            </form>
                        </span>
                <h4 class="my-3">Tornei</h4>
                <p class="text-muted">Visaulizzare i tornei organizzati dalla Polisportiva con possibilità di iscriversi.</p>
            </div>
                <div class="col-md-4">
                    <form action="Partita" method="get">
                        <span class="fa-stack fa-4x">
                            <button>
                                <i class="fas fa-circle fa-stack-2x text-primary"></i>
                                <i class="fas fa-running fa-stack-1x fa-inverse"></i>
                            </button>
                        </span>
                    </form>
                    <h4 class="my-3">Creazione partita</h4>
                <p class="text-muted">Creare  una partita.</p>
            </div>
            <div class="col-md-4">
                <span class="fa-stack fa-4x">
                    <form action="EventoController" method="get">
                        <button>
                            <i class="fas fa-circle fa-stack-2x text-primary"></i>
                            <i class="fas fa-trophy fa-stack-1x fa-inverse"></i>
                        </button>
                    </form>
                </span>
                <h4 class="my-3">Eventi</h4>
                <p class="text-muted">Visualizzare gli eventi organizzati dalla Polisportiva con possibilità di prenotarsi.
                </p>
            </div>
            <div class="col-md-4">
                        <span class="fa-stack fa-4x">
                            <i class="fas fa-circle fa-stack-2x text-primary"></i>
                            <i class="fas fa-calendar-check fa-stack-1x fa-inverse"></i>
                        </span>
                <h4 class="my-3">Indicare disponibilità</h4>
                <p class="text-muted">Possibilità di rendersi disponibile per un campo in una determinata data e orario.</p>
            </div>
        </div>
    </div>
</section>
<!-- About-->
<section class="page-section" id="about">
    <div class="container">
        <div class="text-center">
            <h2 class="section-heading text-uppercase">About</h2>
            <h3 class="section-subheading text-muted"></h3>
        </div>
        <ul class="timeline">
            <li>
                <div class="timeline-image"><img class="rounded-circle img-fluid" src="assets/img/about/1.jpg" alt="" /></div>
                <div class="timeline-panel">
                    <div class="timeline-heading">
                        <h4>SET 2020-GEN 2021</h4>
                        <h4 class="subheading">V1.0</h4>
                    </div>
                    <div class="timeline-body"><p class="text-muted">Possibilità di prenotare <br>un campo per il Tennis</p></div>
                </div>
            </li>
            <li class="timeline-inverted">
                <div class="timeline-image"><img class="rounded-circle img-fluid" alt="" /></div>
                <div class="timeline-panel">
                    <div class="timeline-heading">
                        <h4>FEB 2021-FEB 2022</h4>
                        <h4 class="subheading">V2.0</h4>
                    </div>
                    <div class="timeline-body"><p class="text-muted">Aggiunta della prenotazione <br>di campi di Calcio</p></div>
                </div>
            </li>
            <li>
                <div class="timeline-image"><img class="rounded-circle img-fluid" alt="" /></div>
                <div class="timeline-panel">
                    <div class="timeline-heading">
                        <h4>IN FUTURO</h4>
                        <h4 class="subheading">V3.0</h4>
                    </div>
                    <div class="timeline-body"><p class="text-muted">Aggiunta delle prenotazioni <br>per i campi di Basket e Pallavolo</p></div>
                </div>
            </li>

            <li class="timeline-inverted">
                <div class="timeline-image">
                    <h4>
                        <br>
                        ISCRIVITI
                    </h4>
                </div>
            </li>
        </ul>
    </div>
</section>
<!-- Footer-->
<%@include file="view/footer/footer.jsp"%>


<!-- Bootstrap core JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Third party plugin JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<!-- Contact form JS-->
<script src="assets/mail/jqBootstrapValidation.js"></script>
<script src="assets/mail/contact_me.js"></script>
<!-- Core theme JS-->
<script src="js/homepage/js_homepage.js"></script>
<script src = "js/homepage/check_login.js"></script>

</body>
</html>
