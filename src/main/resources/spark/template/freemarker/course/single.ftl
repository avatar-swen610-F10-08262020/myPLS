<#include "/header/header_course.ftl">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

   <!-- Masthead -->
   <header class="masthead">
      <section class="bg-light">
        <div class="container">
        <h2>Course: ${course.course_name}</h2>
        <div>
        <span>Code: ${course.course_code}</span>
        <#if UserType==3>
            <#if alreadyFedback == 0>
                <#if attemptedQuiz?size != 0>
                    <button type="button" class="btn btn-sm btn-info" style="float: right;" data-toggle="modal" data-target="#feedbackModalCenter">
                      Provide Course Feedback
                    </button>

                    <div class="modal fade" id="feedbackModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                      <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true">&times;</span>
                            </button>
                          </div>
                          <div class="modal-body">
                            <form action="/feedback/${course.id}" method="POST">
                                <div class="form-group">
                                    <label for="feedback_text">Enter Feedback</label>
                                    <textarea class="form-control" id="feedback_text" rows="5" name="feedback_text" required></textarea>
                                </div>
                                <div class="form-group">
                                    <p>Rating: <span id="rating_text"></span></p>
                                    <label for="rating">Course Rating</label>
                                    <input type="range" class="custom-range" min="0" max="5" id="rating" name="rating_value">
                                </div>
                                <input class="btn btn-sm btn-primary" type="submit" value="Submit">
                            </form>
                          </div>
                        </div>
                      </div>
                    </div>
                </#if>
            </#if>
        </#if>
        </div>
        <ul class="nav nav-tabs">
            <li class="active bt btn-md btn-info" style="padding:1%"><a data-toggle="tab" href="#course" style="color:white;">Course Details</a></li>
            <li class="bt btn-md btn-info" style="padding:1%"><a data-toggle="tab" href="#material" style="color:white;">Course Material</a></li>

            <li class="bt btn-md btn-info" style="padding:1%"><a data-toggle="tab" href="#quiz" style="color:white;">Course Quiz</a></li>
            <#if UserType!=3>
                <li class="bt btn-md btn-info" style="padding:1%"><a data-toggle="tab" href="#enrolled" style="color:white;">Enrolled Students</a></li>

            </#if>

            <#if UserType==1>
                <li class="bt btn-md btn-info" style="padding:1%"><a data-toggle="tab" href="#feedback" style="color:white;">Course Feedback</a></li>
            </#if>

            <#if UserType==4>
            <li class="bt btn-md btn-info" style="padding:1%"><a data-toggle="tab" href="#feedback" style="color:white;">Discussion</a></li>
            </#if>


          </ul>
          <div class="tab-content">
            <div id="course" class="tab-pane in active">
                <div class="container">
                   <section class="member-details">
                       <div class="container">
                           <div class="row">
                               <div class="col-lg-12 col-md-12">
                                   <#if UserType==1>
                                   <div class="member_designation">
                                       <h2><a href="/course/edit/${course.id}" class="btn btn-sm btn-warning">Edit</a>
                                       <a href="/course/delete/${course.id}" class="btn btn-sm btn-danger">Delete</a></h2>

                                   </div>
                                   </#if>
                                   <div class="member_desc">
                                       <p>
                                           ${course.description}
                                       </p>
                                       <ul class="styled_list">
                                           <li class=""><i class="fa fa-chevron-circle-right" aria-hidden="true"></i> Class size: ${course.class_size}</li>
                                           <li class=""><i class="fa fa-chevron-circle-right" aria-hidden="true"></i> Start date: ${course.start_date}</li>
                                           <li class=""><i class="fa fa-chevron-circle-right" aria-hidden="true"></i> End date: ${course.end_date}</li>
                                       </ul>
                                   </div>
                                   <div class="bg-image " style="background-image: url('https://www.bootdey.com/img/Content/bg_element.jpg');">
                                       <div class="member_contact">
                                           <div class="row">
                                               <div class="col-lg-4  mb-3 mb-lg-0">
                                                   <div class="media-box">
                                                       <div class="media-icon">
                                                           <i class="fa fa-user" aria-hidden="true"></i>
                                                       </div>
                                                       <div class="media-content">
                                                           <h5>Professor</h5>
                                                           <p>${professor.first_name} ${professor.last_name}</p>
                                                       </div>
                                                   </div>
                                               </div>
                                               <div class="col-lg-4  mb-3 mb-lg-0">
                                                   <div class="media-box">
                                                       <div class="media-icon">
                                                           <i class="fa fa-star" aria-hidden="true"></i>
                                                       </div>
                                                       <div class="media-content">
                                                           <h5>Rating</h5>
                                                           <p>${rating} out of 5</p>
                                                       </div>
                                                   </div>
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                                   <div class="row ">
                                       <div class="col-lg-6 member_desc">
                                           <h4>Review 1</h4>
                                           <p>
                                               Sample Review.
                                           </p>
                                       </div>
                                       <div class="col-lg-6 member_desc">
                                           <h4>Review 2</h4>
                                           <p>
                                               Sample Review
                                           </p>
                                       </div>
                                   </div>
                               </div>

                           </div>
                       </div>
                   </section>
               </div>
            </div>
            <div id="feedback" class="tab-pane fade">
              <div class="container">
                 <#if UserType==1>
                     <#list feedbackList as feedbackData>
                         <#list feedbackData as feedback>

                             <div class="card" style="padding: 1rem;">
                                 <div class="card-body">
                                     ${feedback.rating}.0/5.0
                                     </br>
                                     <b>${feedback.feedback}</b>
                                 </div>
                                 <div class="comment_toolbar">

                                     <!-- inc. date and time -->
                                     <div class="comment_details">
                                         <ul>
                                             <li><i class="fa fa-clock-o"></i> 13:94</li>
                                             <li><i class="fa fa-calendar"></i> 04/01/2020</li>
                                             <li><i class="fa fa-pencil"></i> <span class="user">${feedback.user.first_name} ${feedback.user.last_name}</span></li>
                                         </ul>
                                     </div>
                                 </div>
                             </div>
                             <#break>
                          </#list>

                     </#list>
                 </#if>
               </div>
            </div>
           <div id="material" class="tab-pane fade">
             <#include "lesson.ftl">
           </div>
           <div id="enrolled" class="tab-pane fade">
            <#include "/user/list.ftl">
           </div>
            <div id="quiz" class="tab-pane fade">
              <#include "quiz.ftl">
            </div>

          </div>
        </div>
      </section>
   </header>

   <script>
   $(document).ready(function(){
       var rating_slider = document.getElementById("rating");
       var output = document.getElementById("rating_text");
       output.innerHTML = 0;
       rating_slider.oninput = function() {
         output.innerHTML = this.value;
       }
    });

   $.ajax({
         url: '/feedback/' + a + '&b='+b,
         success: function(data) {
           $('#result').text(data);
         }
       });
   </script>

