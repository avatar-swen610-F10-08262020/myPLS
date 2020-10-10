<#include "/header/header_profile.ftl">

<body>

  <!-- Navigation -->
  <nav class="navbar navbar-light bg-light static-top inline">
    <div class="container">
        <a class="navbar-brand" href="/login">MyPLS </a>
        <div class="float-right">

            <a class="btn btn-default" href="#">Courses</a>
            <a class="btn btn-default" href="#">Users</a>
            <a class="btn btn-default" href="#">Announcements</a>
            <a class="btn btn-default" href="#">${firstName}</a>
        </div>
    </div>

  </nav>

  <!-- Masthead -->
   <header class="masthead text-center">

   </header>

   <!-- Icons Grid -->
   <section class="features-icons bg-light text-center">
   <div class="container light-style flex-grow-1 container-p-y">

       <h4 class="font-weight-bold py-3 mb-4">
         Hello, ${firstName} ${lastName}
       </h4>
        <#if msg_type == "error">
            <div style="font-size:1rem" class="alert alert-danger" role="alert">${message}
        <#elseif msg_type == "notification">
            <div style="font-size:1rem" class="alert alert-success" role="alert">
              ${message}
            </div>
        </#if>
       <div class="card overflow-hidden">
         <div class="row no-gutters row-bordered row-border-light">
           <div class="col-md-12">
             <div class="tab-content">
               <div class="tab-pane fade active show" id="account-general">

                 <div class="card-body media align-items-center">
                   <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="" class="d-block ui-w-80">
                   <div class="media-body ml-4">
                     <label class="btn btn-outline-primary">
                       Upload new photo
                       <input type="file" class="account-settings-fileinput">
                     </label> &nbsp;
                     <button type="button" class="btn btn-default md-btn-flat">Reset</button>

                     <div class="text-light small mt-1">Allowed JPG, GIF or PNG. Max size of 800K</div>
                   </div>
                 </div>
                 <hr class="border-light m-0">

                <form action="/profile/update" method="post">
                 <div class="card-body">
                   <div class="form-group">
                     <label class="form-label">First name</label>
                     <input type="text" class="form-control mb-1" value=${firstName} id="firstName" name="firstName">
                   </div>
                   <div class="form-group">
                     <label class="form-label">Last name</label>
                     <input type="text" class="form-control" value=${lastName} id="lastName" name="lastName">
                   </div>

                   <div class="form-group">
                      <label class="form-label">Current password</label>
                      <input type="password" class="form-control" id="password" name="password">
                   </div>

                   <div class="form-group">
                      <label class="form-label">New password</label>
                      <input type="password" class="form-control" id="newPassword" name="newPassword" onkeyup="check();">
                   </div>

                   <div class="form-group">
                      <label class="form-label">Repeat new password</label>
                      <input type="password" class="form-control" id="repeatPassword" name="repeatPassword" onkeyup="check();">
                      <span id="message"></span>
                   </div>
                 </div>
                 <div class="text-center mt-3">
                    <input type="submit" class="fadeIn fourth btn btn-primary" value="Save Changes">
                 </div>
                </form>

               </div>
             </div>
           </div>
         </div>
       </div>

     </div>
   </section>



