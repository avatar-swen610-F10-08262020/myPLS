<#include "/header/header_course.ftl">

   <!-- Masthead -->
   <header class="masthead text-center"  style="width:60%; position: absolute; top: 15%; left: 20%;">
      <div class="wrapper fadeInDown">
          <div id="formContent">
             <!-- Tabs Titles -->
             <!-- Icon -->
             <h2>
                Quiz Details
             </h2>
                <a href="/quiz/edit/${quizId}" class="btn btn-sm btn-warning">Edit</a>
               <#list QuizQuestionOptions as quizQuestionOption>
                    <div class="card" style="padding: 1rem;">
                       <head>Question ${quizQuestionOption.quiz_questions.question_number}: ${quizQuestionOption.quiz_questions.question_description}</a></head>
                        <#list quizQuestionOption.quiz_optionslist as quizOption>
                          <div class="card-body">
                            Option ${quizOption.option_number}: ${quizOption.description}
                             <#if quizOption.option_status==1>
                             <button type="button" class="btn btn-info btn-sm">
                               <i class="fa fa-check" style="font-size:14px"></i>
                             </button>
                             </#if>
                          </div>
                        </#list>
                    </div>


                </#list>
         </div>
      </div>
   </header>
   <!-- Icons Grid -->
