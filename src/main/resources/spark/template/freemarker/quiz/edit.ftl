<#include "/header/header_course.ftl">

   <!-- Masthead -->
   <header class="masthead text-center"  style="width:85%; position: absolute; top: 15%; left: 7%;">
        <div class="container">

         <div class="card" style="padding: 1rem;">
          <div class="card-body">


                <div id="quiz_add">
                    <h1>Edit Quiz</h1>
                    <div class="card" style="padding:1rem;width:100%">
                        <form action="/quiz/update/${quiz.id}" method="post">
                           <div class="row">
                              <div class="form-group" style="margin-left:1em; margin-right:1em;">
                                <label for="quiz_description" class="col-form-label">Quiz Description</label>
                                <input type="text" class="form-control" id="quiz_description" name="quiz_description" value="${quiz.quiz_description}" required>
                             </div>
                             <div class="form-group" style="margin-right:1em;">
                                 <label for="quiz_length" class="col-form-label">Quiz Length(in Minute)</label>
                                 <input type="number" class="form-control" id="quiz_length" name="quiz_length" value="${quiz.quiz_length}" required>
                              </div>
                              <div class="form-group" style="margin-right:1em;">
                                <label for="start_date" class="col-form-label">Start Date</label>
                                <input type="date" class="form-control" id="start_date" name="start_date" value=${quiz.start_date}>
                              </div>
                              <div class="form-group">
                                <label for="end_date" class="col-form-label">End Date</label>
                                <input type="date" class="form-control" id="end_date" name="end_date" value=${quiz.end_date}>
                              </div>
                              <div>
                              <button type="submit" class="btn btn-sm btn-primary" style="margin-left:1em;margin-top:2.5em;">Update </button>
                              </div>
                           </div>

                        </form>
                           <div class="row clearfix">
                                <div class="col-md-12 column">
                                    <table class="table table-bordered table-hover" id="tab_logic">
                                        <thead>
                                            <tr>
                                                <th class="text-center">
                                                    #
                                                </th>
                                                <th class="text-center">
                                                    Question Description
                                                </th>
                                                <th class="text-center">
                                                    Answer Option 1
                                                </th>
                                                <th class="text-center">
                                                    Answer Option 2
                                                </th>
                                                <th class="text-center">
                                                    Answer Option 3
                                                </th>
                                                <th class="text-center">
                                                    Answer Option 4
                                                </th>
                                                <th class="text-center">
                                                    Answer Option 5
                                                </th>
                                                <th class="text-center">
                                                    Correct Answer
                                                </th>
                                                <th class="text-center">
                                                    Action
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <#list QuizQuestionOptions as quizQuestionOption>
                                                <tr id='addr${quizQuestionOption.quiz_questions.question_number}'>
                                                    <td>${quizQuestionOption.quiz_questions.question_number}</td>
                                                    <td>
                                                        <form action="/quiz/question/update/${quiz.id}/${quizQuestionOption.quiz_questions.id}" method="post">
                                                        <input type="text" name='question${quizQuestionOption.quiz_questions.question_number}'  placeholder='Question' class="form-control" value="${quizQuestionOption.quiz_questions.question_description}"/>
                                                        <button type="submit" class="btn btn-sm btn-primary" style="margin-top:1em;">Update </button>
                                                        </form>
                                                    </td>
                                                    <form action="/quiz/option/update/${quiz.id}/${quizQuestionOption.quiz_questions.id}" method="post">

                                                    <#list quizQuestionOption.quiz_optionslist as quizOption>
                                                        <td id='option${quizOption.option_number}'>

                                                            <input type="text" name='options${quizQuestionOption.quiz_questions.question_number}${quizOption.option_number}'  placeholder='Option' class="form-control" value="${quizOption.description}"/>

                                                        </td>
                                                    </#list>


                                                     <td id='option6'>
                                                        <div class="form-group">
                                                           <select class="form-control" id="correct_ans" name="correct_ans_${quizQuestionOption.quiz_questions.question_number}" value="" required>
                                                               <option>Correct Ans</option>
                                                                <#list 1..5 as x>
                                                                    <#if quizQuestionOption.result == x>

                                                                        <option value="${x}" selected>${x}</option>
                                                                    <#else>
                                                                        <option value="${x}">${x}</option>
                                                                    </#if>
                                                                </#list>


                                                           </select>
                                                         </div>

                                                     </td>

                                                     <td id='option7'>
                                                        <button type="submit" class="btn btn-sm btn-primary" style="margin-bottom:1em;">Update</button>
                                                        </form>
                                                        <form action="/quiz/question/delete/${quiz.id}/${quizQuestionOption.quiz_questions.id}" method="post">
                                                            <input type="text" name="dummy" value="dummy" hidden/>
                                                            <button type="submit"  class="btn btn-sm btn-danger">Delete</button>
                                                        </form>
                                                     </td>

                                                </tr>
                                            </#list>
                                               <tr id='addr${question_length}'></tr>

                                        </tbody>
                                    </table>
                                    <input id="length" type="text" value="${question_length}" hidden>
                                </div>
                            </div>
                            <div class="row" style="padding:1em;">
                                <button style="margin:2em" class="btn btn-sm btn-info" data-toggle="collapse" data-target="#question_add">Add Question</button>
                                <a href="/quiz/details/${quiz.id}" class="btn btn-sm btn-danger" style= "margin:1.7em">Cancel</a>
                            </div>

                            <#include "/quiz/quiz_question_add.ftl">




                    </div>
                </div>

          </div>
         </div>




        </div>
   </header>
<script>
