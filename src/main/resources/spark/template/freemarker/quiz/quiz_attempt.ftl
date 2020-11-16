<#include "/header/header_course.ftl">
<!-- Masthead -->
<header class="masthead "  style="width:60%; position: absolute; top: 15%; left: 20%;">
   <div class="wrapper fadeInDown">
      <div id="formContent">
         <!-- Tabs Titles -->
         <!-- Icon -->
         <h2>
            Quiz Details
         </h2>
         <div class="" style="padding: 1rem;">
            <form action="/quiz/submit/${quizId}" method="post" id="quiz_attempt">
                <#list QuizQuestionOptions as quizQuestionOption>
                    <div class="form-group">
                        <h5>Question ${quizQuestionOption.quiz_questions.question_number}: ${quizQuestionOption.quiz_questions.question_description}</h5>
                        <select id="q_${quizQuestionOption.quiz_questions.question_number}" name="q_${quizQuestionOption.quiz_questions.question_number}" required>
                            <option id="${quizQuestionOption.quiz_questions.question_number}_default" value="">Choose an answer</option>
                            <#list quizQuestionOption.quiz_optionslist as quizOption>
                                <option value="${quizOption.option_number}">${quizOption.description}</option>
                            </#list>
                        </select>
                    </div>
                </#list>
                <input type="submit" id="submit_quiz" class="btn btn-sm btn-success" value="Submit">
            </form>
         </div>
      </div>
   </div>
</header>
<!-- Icons Grid -->
