<#include "/header/header_course.ftl">
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
      <section class="bg-light text-center">
        <div class="container">
           <h2>Course List</h2>
           <button id="course-create-btn" type="button" data-toggle="modal" data-target="#course-create-modal" class="btn btn-sm btn-primary">Create Course</button>
           <table id="course-index-table" class="table table-hover">
            <thead>
                <tr>
                    <th>Course Name</th>
                    <th>Course Code</th>
                    <th>Seat Limit</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <#list courses as course>
                    <tr>
                        <td><a href="/course/${course.id}" >${course.course_name}</a></td>
                        <td>${course.course_code}</td>
                        <td>${course.class_size}</td>
                        <td>
                            <a href="/course/${course.id}" class="btn btn-sm btn-success">Show</a>
                            <a href="/course/update/${course.id}" class="btn btn-sm btn-warning">Update</a>
                            <a href="/course/delete/${course.id}" class="btn btn-sm btn-danger">Delete</a>
                        </td>
                    </tr>
                </#list>
            </tbody>
           </table>
        </div>
      </section>

      <div class="modal fade" id="course-create-modal" tabindex="-1" role="dialog" aria-labelledby="course-create-label" aria-hidden="true">
         <div class="modal-dialog" role="document">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title" id="course-create-label">New Course</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                  </button>
               </div>
               <div class="modal-body">
                  <form action="/course/create" method="post">
                     <div class="form-group">
                        <label for="course_name" class="col-form-label">Course Name</label>
                        <input type="text" class="form-control" id="course_name">
                     </div>
                     <div class="form-group">
                        <label for="course_code" class="col-form-label">Course Code</label>
                        <input type="text" class="form-control" id="course_code">
                     </div>
                     <div class="form-group">
                        <label for="description" class="col-form-label">Description</label>
                        <textarea class="form-control" id="course_name"></textarea>
                     </div>
                     <div class="form-group">
                        <label for="class_size" class="col-form-label">Class Size</label>
                        <input type="number" class="form-control" id="class_size" min=0>
                     </div>
                     <div class="form-group">
                        <label for="start_date" class="col-form-label">Start Date</label>
                        <input type="date" class="form-control" id="course_name">
                     </div>
                     <div class="form-group">
                        <label for="end_date" class="col-form-label">End Date</label>
                        <input type="date" class="form-control" id="end_date">
                     </div>
                  </form>
               </div>
               <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                  <button type="button" class="btn btn-primary">Create</button>
               </div>
            </div>
         </div>
      </div>
   </header>

