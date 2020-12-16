<#include "/header/header_course.ftl">
<!-- Masthead -->
   <header class="masthead text-center">
      <section class="bg-light text-center">
        <div class="container">
           <p>Course Name: ${dependentCourse.course_name}</p>
           <p>Course Code: ${dependentCourse.course_code}</p>
           <h5>Please Select Prerequisite course from below:</h5>
           <form action="/course/prerequisite/create/${dependentCourse.id}" method="post">

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
                    <#list parentCourses as course>
                        <tr>
                            <td><a href="/course/${course.id}" >${course.course_name}</a></td>
                            <td>${course.course_code}</td>
                            <td>${course.class_size}</td>
                            <td>
                                <div class="form-check">
                                  <input class="form-check-input" type="checkbox" checked="checked" name="${course.id}" value="${course.id}" id="parentCourse">
                                  <label class="form-check-label" for="parentCourse">

                                  </label>
                                </div>
                            </td>
                        </tr>
                    </#list>
                    <#list courses as course>
                        <tr>
                            <td><a href="/course/${course.id}" >${course.course_name}</a></td>
                            <td>${course.course_code}</td>
                            <td>${course.class_size}</td>
                            <td>
                                <div class="form-check">
                                  <input class="form-check-input" type="checkbox" value="${course.id}" name="${course.id}" id="parentCourse">
                                  <label class="form-check-label" for="parentCourse">

                                  </label>
                                </div>
                            </td>
                        </tr>
                    </#list>


            </tbody>
           </table>
              <button type="submit" class="btn btn-primary">Save</button>
              <a href="/course" class="btn btn-danger">Cancel</a>

           </form>
        </div>
      </section>


   </header>
