<#include "/header/header.ftl">
<body>
   <!-- Navigation -->
   <nav class="navbar navbar-light bg-light static-top inline">
      <div class="container">
         <a class="navbar-brand" href="/login">MyPLS Home</a>
         <div class="float-right">
            <a class="btn btn-default" href="/home">Home</a>
            <a class="btn btn-default" href="/course">Courses</a>
            <a class="btn btn-default" href="#">Announcement</a>
            <a class="btn btn-default" href="/profile">Profile</a>
            <a class="btn btn-default" href="/logout">Logout</a>
         </div>
      </div>
   </nav>
   <!-- Masthead -->
   <header class="masthead text-center">
      <div class="wrapper fadeInDown">
      <div id="formContent">
         <!-- Tabs Titles -->
         <!-- Icon -->
         <#if msg_type == "delerror">
             <div style="font-size:1rem" class="alert alert-danger" role="alert">
             ${msg}
             </div>
         <#elseif msg_type == "delmsg">
             <div style="font-size:1rem" class="alert alert-success" role="alert">
                ${msg}
             </div>
         </#if>
         <h2>
            Create Course
         </h2>
         <#if msg_type == "error">
            <div style="font-size:1rem" class="alert alert-danger" role="alert">
            ${msg}
            </div>
        <#elseif msg_type == "notification">
            <div style="font-size:1rem" class="alert alert-success" role="alert">
               ${msg}
            </div>
        </#if>
            <!-- Login Form -->
            <form action="/course/register" method="post">
               <div class="form-group">
                  <label for="course_name" class="col-form-label">Course Name</label>
                  <input type="text" class="form-control" id="course_name" name="course_name" required>
               </div>
               <div class="form-group">
                  <label for="course_code" class="col-form-label">Course Code</label>
                  <input type="text" class="form-control" id="course_code" name="course_code" required>
               </div>
               <div class="form-group">
                 <label for="class_size" class="col-form-label">Class Size</label>
                 <input type="number" class="form-control" id="class_size" min=0 name="class_size" required>
              </div>
               <div class="form-group">
                  <label for="description" class="col-form-label">Description</label>
                  <textarea class="form-control" id="description" name="description" maxlength="255"></textarea>
               </div>
               <div class="form-group">
                  <label for="start_date" class="col-form-label">Start Date</label>
                  <input type="date" class="form-control" id="start_date" name="start_date">
               </div>
               <div class="form-group">
                  <label for="end_date" class="col-form-label">End Date</label>
                  <input type="date" class="form-control" id="end_date" name="end_date">
               </div>
               <button type="submit" class="btn btn-primary">Create</button>
            </form>
         </div>
      </div>
   </header>
   <!-- Icons Grid -->
