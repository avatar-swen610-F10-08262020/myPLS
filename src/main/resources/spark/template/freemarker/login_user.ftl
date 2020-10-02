<#include "/header/header.ftl">
<body>

  <!-- Navigation -->
  <nav class="navbar navbar-light bg-light static-top inline">
    <div class="container">
        <a class="navbar-brand" href="/login">Welcome To My Personal Learning Space </a>
        <div class="float-right">

            <a class="btn btn-default" href="/signup/user">Sign Up</a>
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
              Welcome to your Learning Space!
            </h2>
            <#if (error)??>
            <div class="alert alert-danger" role="alert">
              ${error}
            </div>
            </#if>

            <#if (message)??>
            <div class="alert alert-success" role="alert">
              ${message}
            </div>
            </#if>

            <!-- Login Form -->
            <form action="/login" method="post">
              <input type="text" id="login" class="fadeIn second" name="email" placeholder="login" required>
              <input type="password" id="password" class="fadeIn third" name="password" placeholder="password" required>
              <input type="submit" class="fadeIn fourth" value="Log In">
            </form>

            <!-- Remind Passowrd -->
            <div id="formFooter">
              <a class="underlineHover" href="/forgot/password">Forgot Password?</a>
            </div>

          </div>
        </div>
</header>

  <!-- Icons Grid -->
  <section class="features-icons bg-light text-center">
   </section>

<#include "/footer/footer.ftl">
