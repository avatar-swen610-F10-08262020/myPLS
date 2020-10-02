<#include "/header/header_signup.ftl">

<body>

  <!-- Navigation -->
  <nav class="navbar navbar-light bg-light static-top inline">
    <div class="container">
        <a class="navbar-brand" href="/login">Welcome To My Personal Learning Space </a>
        <div class="float-right">

            <a class="btn btn-primary" href="/login/user">Sign In</a>
        </div>
    </div>

  </nav>

  <!-- Masthead -->
   <header class="masthead text-center">
        <div class="wrapper fadeInDown">
          <div id="formContent">
            <!-- Tabs Titles -->

            <!-- Icon -->

           <h2>
                One Step to On-board
           <h2>
            <#if (error)??>
            <div style="font-size:1rem" class="alert alert-danger" role="alert">
              ${error}
            </div>
            </#if>

            <!-- Signup Form -->
            <form action="/signup/user" method="post">
                <input type="text" id="firstName" class="fadeIn second" name="firstName" placeholder="first name" required>
                <input type="text" id="lastName" class="fadeIn second" name="lastName" placeholder="last name" required>
                 <input type="text" id="email" class="fadeIn second" name="email" placeholder="email" required>
                 <input type="password" id="password" class="fadeIn third" name="password" placeholder="password" required>
                 <input type="password" id="repeatPassword" class="fadeIn third" name="repeatPassword" placeholder="Repeat password" required>

                 <div class="form-group">

                   <select class="fadeIn third" id="userTypeID" name="userTypeID" required>
                    <option selected>User Type</option>
                     <option value="3">Learner</option>
                     <option value="2">Professor</option>
                   </select>
                 </div>


              <input type="submit" class="fadeIn fourth" value="Signup">
            </form>

            <!-- Remind Passowrd -->


          </div>
        </div>
</header>

  <!-- Icons Grid -->
  <section class="features-icons bg-light text-center">
    </section>

  <#include "/footer/footer.ftl">

