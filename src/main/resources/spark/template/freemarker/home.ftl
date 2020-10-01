<!DOCTYPE html>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>MyPLS</title>

  <!-- Bootstrap core CSS -->
  <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="/vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template -->
  <link href="css/landing-page.min.css" rel="stylesheet">

  <style>

    /* BASIC */





    /* STRUCTURE */

    .wrapper {
      display: flex;
      align-items: center;
      flex-direction: column;
      justify-content: center;
      width: 100%;
      min-height: 100%;
      padding: 20px;
    }

    #formContent {
      -webkit-border-radius: 10px 10px 10px 10px;
      border-radius: 10px 10px 10px 10px;
      background: #fff;
      padding: 30px;
      width: 90%;
      max-width: 450px;
      position: relative;
      padding: 0px;
      -webkit-box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3);
      box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3);
      text-align: center;
    }

    #formFooter {
      background-color: #f6f6f6;
      border-top: 1px solid #dce8f1;
      padding: 25px;
      text-align: center;
      -webkit-border-radius: 0 0 10px 10px;
      border-radius: 0 0 10px 10px;
    }



    /* TABS */

    h2.inactive {
      color: #cccccc;
    }

    h2.active {
      color: #0d0d0d;
      border-bottom: 2px solid #5fbae9;
    }



    /* FORM TYPOGRAPHY*/

    input[type=button], input[type=submit], input[type=reset]  {
      background-color: #56baed;
      border: none;
      color: white;
      padding: 15px 80px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      text-transform: uppercase;
      font-size: 13px;
      -webkit-box-shadow: 0 10px 30px 0 rgba(95,186,233,0.4);
      box-shadow: 0 10px 30px 0 rgba(95,186,233,0.4);
      -webkit-border-radius: 5px 5px 5px 5px;
      border-radius: 5px 5px 5px 5px;
      margin: 5px 20px 40px 20px;
      -webkit-transition: all 0.3s ease-in-out;
      -moz-transition: all 0.3s ease-in-out;
      -ms-transition: all 0.3s ease-in-out;
      -o-transition: all 0.3s ease-in-out;
      transition: all 0.3s ease-in-out;
    }

    input[type=button]:hover, input[type=submit]:hover, input[type=reset]:hover  {
      background-color: #39ace7;
    }

    input[type=button]:active, input[type=submit]:active, input[type=reset]:active  {
      -moz-transform: scale(0.95);
      -webkit-transform: scale(0.95);
      -o-transform: scale(0.95);
      -ms-transform: scale(0.95);
      transform: scale(0.95);
    }

    input[type=text], input[type=password] {
      background-color: #f6f6f6;
      border: none;
      color: #0d0d0d;
      padding: 15px 32px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 16px;
      margin: 5px;
      width: 85%;
      border: 2px solid #f6f6f6;
      -webkit-transition: all 0.5s ease-in-out;
      -moz-transition: all 0.5s ease-in-out;
      -ms-transition: all 0.5s ease-in-out;
      -o-transition: all 0.5s ease-in-out;
      transition: all 0.5s ease-in-out;
      -webkit-border-radius: 5px 5px 5px 5px;
      border-radius: 5px 5px 5px 5px;
    }

    input[type=text]:focus {
      background-color: #fff;
      border-bottom: 2px solid #5fbae9;
    }

    input[type=text]:placeholder {
      color: #cccccc;
    }



    /* ANIMATIONS */

    /* Simple CSS3 Fade-in-down Animation */
    .fadeInDown {
      -webkit-animation-name: fadeInDown;
      animation-name: fadeInDown;
      -webkit-animation-duration: 1s;
      animation-duration: 1s;
      -webkit-animation-fill-mode: both;
      animation-fill-mode: both;
    }

    @-webkit-keyframes fadeInDown {
      0% {
        opacity: 0;
        -webkit-transform: translate3d(0, -100%, 0);
        transform: translate3d(0, -100%, 0);
      }
      100% {
        opacity: 1;
        -webkit-transform: none;
        transform: none;
      }
    }

    @keyframes fadeInDown {
      0% {
        opacity: 0;
        -webkit-transform: translate3d(0, -100%, 0);
        transform: translate3d(0, -100%, 0);
      }
      100% {
        opacity: 1;
        -webkit-transform: none;
        transform: none;
      }
    }

    /* Simple CSS3 Fade-in Animation */
    @-webkit-keyframes fadeIn { from { opacity:0; } to { opacity:1; } }
    @-moz-keyframes fadeIn { from { opacity:0; } to { opacity:1; } }
    @keyframes fadeIn { from { opacity:0; } to { opacity:1; } }

    .fadeIn {
      opacity:0;
      -webkit-animation:fadeIn ease-in 1;
      -moz-animation:fadeIn ease-in 1;
      animation:fadeIn ease-in 1;

      -webkit-animation-fill-mode:forwards;
      -moz-animation-fill-mode:forwards;
      animation-fill-mode:forwards;

      -webkit-animation-duration:1s;
      -moz-animation-duration:1s;
      animation-duration:1s;
    }

    .fadeIn.first {
      -webkit-animation-delay: 0.4s;
      -moz-animation-delay: 0.4s;
      animation-delay: 0.4s;
    }

    .fadeIn.second {
      -webkit-animation-delay: 0.6s;
      -moz-animation-delay: 0.6s;
      animation-delay: 0.6s;
    }

    .fadeIn.third {
      -webkit-animation-delay: 0.8s;
      -moz-animation-delay: 0.8s;
      animation-delay: 0.8s;
    }

    .fadeIn.fourth {
      -webkit-animation-delay: 1s;
      -moz-animation-delay: 1s;
      animation-delay: 1s;
    }

    /* Simple CSS3 Fade-in Animation */
    .underlineHover:after {
      display: block;
      left: 0;
      bottom: -10px;
      width: 0;
      height: 2px;
      background-color: #56baed;
      content: "";
      transition: width 0.2s;
    }

    .underlineHover:hover {
      color: #0d0d0d;
    }

    .underlineHover:hover:after{
      width: 100%;
    }



    /* OTHERS */

    *:focus {
        outline: none;
    }

    #icon {
      width:60%;
    }

  </style>


</head>
<body>

  <!-- Navigation -->
  <nav class="navbar navbar-light bg-light static-top inline">
    <div class="container">
        <a class="navbar-brand" href="/login">MyPLS Home</a>

        <div class="float-right">

            <#if UserType == 1>
                <a class="btn btn-default" href="/home">Home</a>
                <a class="btn btn-default" href="#">Courses</a>
                <a class="btn btn-default" href="#">Users</a>
                <a class="btn btn-default" href="#">Announcement</a>
            <#elseif UserType == 2>
                <a class="btn btn-default" href="/home">Home</a>
                <a class="btn btn-default" href="#">Courses</a>
                <a class="btn btn-default" href="#">Announcement</a>
             <#else>
                 <a class="btn btn-default" href="/home">Home</a>
                 <a class="btn btn-default" href="#">Courses</a>
                 <a class="btn btn-default" href="#">Announcement</a>
            </#if>

            <a class="btn btn-default" href="#">${Username}</a>
            <a class="btn btn-default" href="/logout">Logout</a>

        </div>
    </div>

  </nav>

  <!-- Masthead -->
   <header class="masthead text-center">



  <!-- Icons Grid -->
  <section class="bg-light text-center">

      <#if UserType == 1>
        <div class="container">
          <h2>User List</h2>
             <input type="text" style="width:15%" id="firstName" class="fadeIn second" name="firstName" placeholder="first name" required>
             <input type="text" style="width:15%" id="lastName" class="fadeIn second" name="lastName" placeholder="last name" required>
              <input type="text" style="width:15%" id="email" class="fadeIn second" name="email" placeholder="email" required>

                 <select style="width:15%" lass="fadeIn third" id="userTypeID" name="userTypeID" required>
                  <option selected>User Type</option>
                   <option value="3">Learner</option>
                   <option value="2">Professor</option>
                 </select>

                <select style="width:15%" class="fadeIn third" id="userStatus" name="userStatus" required>
                 <option selected>User Status</option>
                  <option value="0">Inactive</option>
                  <option value="1">Active</option>
                </select>
              <a class="btn btn-primary" href="#">Search</a>
          <table class="table table-hover">
            <thead>
              <tr>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Email</th>
                <th>User Type</th>
                <th>Status</th>

              </tr>
            </thead>
            <tbody>
              <tr>
                <td><a href="#">John</a></td>
                <td>Doe</td>
                <td>john@example.com</td>
                <th>Professor</th>
                <th>Active</th>
              </tr>
              <tr>
                <td><a href="#">John</a></td>
                <td>Ray</td>
                <td>johr.ray@example.com</td>
                <th>Learner</th>
                <th>Inactive</th>
              </tr>
              <tr>
                <td><a href="#">Boris</a></td>
                <td>Johnson</td>
                <td>boris.john@example.com</td>
                <th>Learner</th>
                <th>Active</th>
              </tr>
            </tbody>
          </table>
        </div>
      <#elseif UserType == 2 || UserType == 3>
            <div class="container">
             <h2>Course List</h2>
              <input type="text" style="width:15%" id="course_name" class="fadeIn second" name="course_name" placeholder="course name" required>
              <input type="text" style="width:15%" id="course_code" class="fadeIn second" name="course_code" placeholder="course code" required>
              <input type="text" style="width:15%" id="user_id" class="fadeIn second" name="user_id" placeholder="instructor" required>

                 <select style="width:15%" lass="fadeIn third" id="enrolled" name="enrolled" required>
                  <option selected>Enrolled</option>
                   <option value="0">YES</option>
                   <option value="1">NO</option>
                 </select>
              <a class="btn btn-primary" href="#">Search</a>


              <table class="table table-hover">
                <thead>
                  <tr>
                    <th>Course Name</th>
                    <th>Course Code</th>
                    <th>Rating</th>
                    <th>Instructor</th>
                    <th>Enrolled</th>

                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td><a href="#">Computer Architecture</a></td>
                    <td>1001</td>
                    <td>4</td>
                    <th>Jain Ray</th>
                    <th>Yes</th>
                  </tr>
                  <tr>
                    <td><a href="#">Computer Vision</a></td>
                    <td>1020</td>
                    <td>3.5</td>
                    <th>Meng Chao</th>
                    <th>No</th>
                  </tr>
                  <tr>
                    <td><a href="#">Game Design</a></td>
                    <td>2055</td>
                    <td>4.8</td>
                    <th>Boris Johnson</th>
                    <th>No</th>
                  </tr>
                </tbody>
              </table>
            </div>

      </#if>
    </section>
</header>
  <!-- Footer -->
  <footer class="footer bg-light" style="position:absolute; bottom:0; width:100%;">
    <div class="container">
      <div class="row">
        <div class="col-lg-6 h-100 text-center text-lg-left my-auto">
          <ul class="list-inline mb-2">
            <li class="list-inline-item">
              <a href="#">About</a>
            </li>
            <li class="list-inline-item">&sdot;</li>
            <li class="list-inline-item">
              <a href="#">Contact</a>
            </li>
            <li class="list-inline-item">&sdot;</li>
            <li class="list-inline-item">
              <a href="#">Terms of Use</a>
            </li>
            <li class="list-inline-item">&sdot;</li>
            <li class="list-inline-item">
              <a href="#">Privacy Policy</a>
            </li>
          </ul>
          <p class="text-muted small mb-4 mb-lg-0">&copy; Avatar 2020. All Rights Reserved.</p>
        </div>
        <div class="col-lg-6 h-100 text-center text-lg-right my-auto">
          <ul class="list-inline mb-0">
            <li class="list-inline-item mr-3">
              <a href="#">
                <i class="fab fa-facebook fa-2x fa-fw"></i>
              </a>
            </li>
            <li class="list-inline-item mr-3">
              <a href="#">
                <i class="fab fa-twitter-square fa-2x fa-fw"></i>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="#">
                <i class="fab fa-instagram fa-2x fa-fw"></i>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="/vendor/jquery/jquery.min.js"></script>
  <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>
