<#include "/header/header_course.ftl">

   <!-- Masthead -->
   <header class="masthead text-center"  style="width:60%; position: absolute; top: 15%; left: 20%;">
      <div class="wrapper fadeInDown">
      <div id="formContent">
         <!-- Tabs Titles -->
         <!-- Icon -->

         <h2>
            Create Course
         </h2>
            <div class="card" style="padding:1rem">
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
               <div class="form-group">
                   <label for="instructor">Course Instructor</label>
                   <select class="form-control" id="instructor" name="instructor" required>
                       <option selected>User Name</option>
                       <#list users>
                            <#items as user>

                                <option value="${user.id}">${user.first_name}</option>
                            </#items>
                        </#list>
                   </select>
                 </div>
               <button type="submit" class="btn btn-primary">Create</button>
               <a href="/course" class="btn btn-danger">Cancel</a>

            </form>
            </div>
         </div>
      </div>
   </header>
   <!-- Icons Grid -->
