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

            <a class="btn btn-default" href="/profile">Profile</a>
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
 <#include "/footer/footer.ftl">
