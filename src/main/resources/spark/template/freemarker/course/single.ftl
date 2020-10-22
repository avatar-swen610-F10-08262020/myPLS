<#include "/header/header_course.ftl">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

   <!-- Masthead -->
   <header class="masthead">
      <section class="bg-light">
        <div class="container">
        <h2>Course: ${course.course_name}</h2>
        <span>Code: ${course.course_code}</span>
        <ul class="nav nav-tabs">
            <li class="active bt btn-md btn-info" style="padding:1%"><a data-toggle="tab" href="#course" style="color:white;">Course Details</a></li>
            <li class="bt btn-md btn-info" style="padding:1%"><a data-toggle="tab" href="#material" style="color:white;">Course Material</a></li>
            <li class="bt btn-md btn-info" style="padding:1%"><a data-toggle="tab" href="#feedback" style="color:white;">Course Feedback</a></li>
            <li class="bt btn-md btn-info" style="padding:1%"><a data-toggle="tab" href="#feedback" style="color:white;">Discussion</a></li>

          </ul>

          <div class="tab-content">
            <div id="course" class="tab-pane in active">
                <div class="container">
                   <section class="member-details">
                       <div class="container">
                           <div class="row">
                               <div class="col-lg-12 col-md-12">
                                   <div class="member_designation">
                                       <h2><a href="/course/edit/${course.id}" class="btn btn-sm btn-warning">Edit</a>
                                                                                            <a href="/course/delete/${course.id}" class="btn btn-sm btn-danger">Delete</a></h2>

                                   </div>
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
                                                           <p>4.5</p>
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
                                               Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium.
                                           </p>
                                       </div>
                                       <div class="col-lg-6 member_desc">
                                           <h4>Review 2</h4>
                                           <p>
                                               Cepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium
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
               </div>
            </div>
            <div id="material" class="tab-pane fade">
              <div class="container">
                 <div class="card" style="padding: 1rem;">
                   <div class="card-body">
                    <head3>Week 1</head>
                    <div>No Item added yet.</div>
                   </div>
                 </div>
                 <div class="card" style="padding: 1rem;">
                    <div class="card-body">
                     <head3>Week 2</head>
                     <div>No Item added yet.</div>
                    </div>
                  </div>
                  <div class="card" style="padding: 1rem;">
                     <div class="card-body">
                      <head3>Week 3</head>
                      <div>No Item added yet.</div>
                     </div>
                   </div>
                   <div class="card" style="padding: 1rem;">
                      <div class="card-body">
                       <head3>Week 4</head>
                       <div>No Item added yet.</div>
                      </div>
                    </div>
              </div>
            </div>
          </div>
        </div>
      </section>
   </header>

