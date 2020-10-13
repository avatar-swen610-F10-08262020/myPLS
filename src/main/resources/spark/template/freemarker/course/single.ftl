<#include "/header/header_course.ftl">
<body>
   <!-- Navigation -->
   <nav class="navbar navbar-light bg-light static-top inline">
      <div class="container">
         <a class="navbar-brand" href="/login">MyPLS Home</a>
         <div class="float-right">
            <a class="btn btn-default" href="/home">Home</a>
            <a class="btn btn-default" href="/course">Courses</a>
            <a class="btn btn-default" href="#">Announcement</a>
            <a class="btn btn-default" href="/profile">Profile</a>
            <a class="btn btn-default" href="/logout">Logout</a>
         </div>
      </div>
   </nav>
   <!-- Masthead -->
   <header class="masthead">
      <section class="bg-light">
        <div class="container">
           <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
           <div class="container">
               <section class="member-details">
                   <div class="container">
                       <div class="row">
                           <div class="col-lg-12 col-md-12">
                               <div class="member_designation">
                                   <h2>${course.course_name}<a href="/course/update/${course.id}" class="btn btn-sm btn-warning">Update</a>
                                                                                        <a href="/course/delete/${course.id}" class="btn btn-sm btn-danger">Delete</a></h2>
                                   <span>${course.course_code}</span>
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
                                                       <p>Prof. Professorson</p>
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
      </section>
   </header>

