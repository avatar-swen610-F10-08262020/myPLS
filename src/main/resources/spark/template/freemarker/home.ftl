<#include "/header/header_home.ftl">

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

            <a class="btn btn-default" href="/profile">${Username}</a>
            <a class="btn btn-default" href="/logout">Logout</a>

        </div>
    </div>

  </nav>

  <!-- Masthead -->
   <header class="masthead text-center">



  <!-- Icons Grid -->
  <section class="bg-light text-center">


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
                <th>Email</th>
                <th>Status</th>


              </tr>
            </thead>
            <tbody>
               <#list users as user>
                
                   <tr>
                       <td>${user.first_name}</td>
                       <td>${user.email}</td>
                       <th>${user.status}</th>

                   </tr>
               </#list>
            </tbody>
          </table>
        </div>

    </section>
</header>
 <#include "/footer/footer_default.ftl">
