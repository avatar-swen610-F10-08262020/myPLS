<#include "/header/header_course.ftl">

   <!-- Masthead -->
   <header class="masthead text-center"  style="width:60%; position: absolute; top: 15%; left: 20%;">
      <div class="wrapper fadeInDown">
          <div id="formContent">
             <!-- Tabs Titles -->
             <!-- Icon -->
             <h2>
                Quiz Performance
             </h2>
             <div class="row">
                <a href="/course/${courseId}" class="btn btn-sm btn-warning" style="margin:1em;">Back</a>
                </br>

             </div>
             <div class="row">
             Current Grade:  <b><#if learnerCourse.completed = 0> Not Graded <#elseif learnerCourse.completed = 1> Fail <#else> ${learnerCourse.grade} </#if></b>
             </div>

             <#if notAttemptedQuiz?size gt 0>
                 <#list notAttemptedQuiz as quiz>
                     <div class="card" style="padding: 1rem;">
                         <div>
                            <span>Not attempted Quiz: ${quiz.quiz_description}</span>
                         </div>
                         <div class="card-body">
                           Quiz Start Date: ${quiz.start_date} </br>
                           Quiz End Date: ${quiz.end_date} </br>
                           Score: Not Participated

                         </div>
                     </div>
                     <#else>

                  </#list>
              </#if>
            <#if attemptedQuiz?size gt 0>
                 <#list attemptedQuiz as quiz>
                    <div class="card" style="padding: 1rem;">
                        <div>
                           <span>Attempted Quiz: ${quiz.quiz.quiz_description}</span>
                        </div>
                        <div class="card-body">
                          Quiz Start Date: ${quiz.quiz.start_date} </br>
                          Quiz End Date: ${quiz.quiz.end_date} </br>
                          Score: ${quiz.percentage} %

                        </div>
                    </div>
                    <#else>

                 </#list>

                <!--a href="/course/pass/${courseId}/${learnerID}" class="btn btn-sm btn-success" style="margin-right:1em;">Pass</a-->
                <button class="btn btn-sm btn-success" style="margin-right:1em;" data-toggle="collapse" data-target="#quiz_grade">Grade</button>

                <!--a href="/course/fail/${courseId}/${learnerID}" class="btn btn-sm btn-danger" style="margin-right:1em;">Fail</a-->

                 <div id="quiz_grade" class="collapse" style="margin-bottom:2em; margin-top:1em;">
                        <h1>Grade the Performance</h1>
                        <div class="card" style="padding:1rem;width:100%">
                            <form action="/course/grade/${courseId}/${learnerID}" method="post">
                                  <div class="form-group">
                                     <select class="form-control" id="course_grade" name="course_grade" required>
                                         <option selected>Select Grade</option>
                                         <option value="A">A</option>
                                         <option value="B">B</option>
                                         <option value="C">C</option>
                                         <option value="D">D</option>
                                         <option value="F">F</option>
                                     </select>
                                  </div>
                                  <button type="submit" class="btn btn-sm btn-primary" style="margin-right:1em;">Submit Grade</button>
                                  <button class="btn btn-sm btn-danger" data-toggle="collapse" data-target="#quiz_grade">Cancel</button>

                             </form>
                        </div>
                 </div>

            <#else>
                 <div class="card" style="padding: 1rem;">
                     No Quiz Attempted yet.
                 </div>
            </#if>

         </div>
      </div>
   </header>
   <!-- Icons Grid -->
