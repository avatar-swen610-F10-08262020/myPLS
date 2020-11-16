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
             Current Course Status:  <b><#if learnerCourse.completed = 0> Not Graded <#elseif learnerCourse.completed = 1> Fail <#else> Pass </#if></b>
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

                <a href="/course/pass/${courseId}/${learnerID}" class="btn btn-sm btn-success" style="margin-right:1em;">Pass</a>
                <a href="/course/fail/${courseId}/${learnerID}" class="btn btn-sm btn-danger" style="margin-right:1em;">Fail</a>



            <#else>
                 <div class="card" style="padding: 1rem;">
                     No Quiz Attempted yet.
                 </div>
            </#if>

         </div>
      </div>
   </header>
   <!-- Icons Grid -->
