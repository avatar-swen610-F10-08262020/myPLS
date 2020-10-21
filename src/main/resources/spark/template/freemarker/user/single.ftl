<#include "/header/header_course.ftl">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

   <!-- Masthead -->
   <header class="masthead">
      <section class="bg-light">
        <div class="container">
        <h2>User Name: ${user.first_name} ${user.last_name}</h2>

        <ul class="nav nav-tabs">
            <li class="active bt btn-md btn-info" style="padding:1%"><a data-toggle="tab" href="#user" style="color:white;">User Details</a></li>
           <#if user.user_type_id !=1 >
            <li class="bt btn-md btn-info" style="padding:1%"><a data-toggle="tab" href="#course" style="color:white;"> <#if user.user_type_id ==2 > Assigned Courses <#elseif user.user_type_id ==3 > Enrolled Course</#if></a></li>
           </#if>
           <li class="active bt btn-md btn-info" style="padding:1%"><a data-toggle="tab" href="#activity" style="color:white;">User Activity</a></li>

          </ul>

          <div class="tab-content">
            <div id="user" class="tab-pane in active">
                <div class="container">
                   <section class="member-details">
                       <div class="container">
                           <div class="row">
                               <div class="col-lg-12 col-md-12">
                                   <div class="member_designation">
                                       <h2><a href="/user/edit/${user.id}" class="btn btn-sm btn-warning">Edit</a>

                                   </div>
                                   <div class="member_desc">

                                       <ul class="styled_list">
                                           <li class=""><i class="fa fa-chevron-circle-right" aria-hidden="true"></i> User Type: <#if UserType ==2 > Professor <#elseif UserType ==3 > Learner <#else> Admin</#if></li>
                                           <li class=""><i class="fa fa-chevron-circle-right" aria-hidden="true"></i> Email:  ${user.email}</li>
                                       </ul>
                                   </div>


                               </div>

                           </div>
                       </div>
                   </section>
               </div>
            </div>
            <div id="course" class="tab-pane fade">
              <div class="container">
                    <#list courses as courseList>
                         <#list courseList as professorcourse>

                             <div class="card" style="padding: 1rem;">
                               <div class="card-body">

                                     <!-- inc. date and time -->
                                     <div class="comment_details">
                                         <ul>

                                             <li><i class="fa fa-pencil"></i> <span class="user">${professorcourse.course.course_name}</span></li>
                                         </ul>
                                     </div>
                                     <b>${professorcourse.course.course_code}</b>
                                 </div>
                                 <div class="comment_body">
                                     ${professorcourse.course.description}
                                     </br>

                                 </div>

                             </div>

                             <#break>

                          </#list>

                     </#list>
               </div>
            </div>
            <div id="activity" class="tab-pane fade">
              <div class="container">
                <div class="card" style="padding: 1rem;">
                    <div class="card-body">
                         User Signed in on 29th October.</br>
                     </div>
                 </div>
                 <div class="card" style="padding: 1rem;">
                     <div class="card-body">
                          User Signed in on 29th October.</br>
                      </div>
                  </div>
                  <div class="card" style="padding: 1rem;">
                      <div class="card-body">
                           User Signed in on 29th October.</br>
                       </div>
                   </div>
                   <div class="card" style="padding: 1rem;">
                       <div class="card-body">
                            User Signed in on 29th October.</br>
                        </div>
                    </div>
               </div>
            </div>
          </div>
        </div>
      </section>
   </header>

