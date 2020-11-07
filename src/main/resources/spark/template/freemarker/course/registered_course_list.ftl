<#include "/header/header_course.ftl">


 <!-- Masthead -->
   <header class="masthead text-center">
      <section class="bg-light text-center">
        <div class="container">
           <h2>My Course List</h2>


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
                        </td>
                    </tr>
             </#list>
            </tbody>
           </table>
        </div>
      </section>

   </header>