<#include "/header/header_course.ftl">

   <!-- Masthead -->
   <header class="masthead text-center"  style="width:60%; position: absolute; top: 15%; left: 20%;">
      <div class="wrapper fadeInDown">
      <div id="formContent">
         <!-- Tabs Titles -->
         <!-- Icon -->
         <h2>
            Edit User
         </h2>
         <#if msg_type == "error">
            <div style="font-size:1rem" class="alert alert-danger" role="alert">
            ${msg}
            </div>
        <#elseif msg_type == "notification">
            <div style="font-size:1rem" class="alert alert-success" role="alert">
               ${msg}
            </div>
        </#if>
        <div class="card" style="padding:1rem">
            <!-- Login Form -->
            <form action="/user/update/${user.id}" method="post">


               <div class="form-group">
                 <label for="first_name" class="col-form-label">First Name</label>
                 <textarea class="form-control" id="first_name" name="first_name" maxlength="255">${user.first_name}</textarea>
              </div>
               <div class="form-group">
                  <label for="last_name" class="col-form-label">Last Name </label>
                  <input type="text" class="form-control" id="last_name" name="last_name" value=${user.last_name} required>
               </div>
               <div class="form-group">
                 <label for="email" class="col-form-label">Email</label>
                 <input type="text" class="form-control" id="email" min=0 name="email" value=${user.email} readonly>
              </div>


                <div class="form-group">
                     <label for="userTypeID" class="col-form-label">User Type</label>
                  <select class="form-control" id="userTypeID" name="userTypeID" required>
                   <option>User Type</option>
                   <#if user.user_type_id == 1>
                       <option value="1" selected>Admin</option>
                   <#else>
                        <option value="1">Admin</option>
                   </#if>
                   <#if user.user_type_id == 2>
                      <option value="2" selected>Professor</option>
                    <#else>
                       <option value="2">Professor</option>
                    </#if>
                    <#if user.user_type_id == 3>
                        <option value="3" selected>Learner</option>
                    <#else>
                         <option value="3">Learner</option>
                    </#if>

                  </select>
                </div>

                <div class="form-group">
                <label for="status" class="col-form-label">User Status</label>
                  <select class="form-control" id="status" name="status" required>
                   <option>User Status</option>
                   <#if user.status == 1>
                       <option value="1" selected>Active</option>
                   <#else>
                        <option value="1">Active</option>
                   </#if>
                   <#if user.status == 0>
                      <option value="0" selected>Inactive</option>
                    <#else>
                       <option value="0">Inactive</option>
                    </#if>


                  </select>
                </div>

               <button type="submit" class="btn btn-primary">Update</button>
               <a href="/user/${user.id}" class="btn btn-danger">Cancel</a>

            </form>
           </div>
         </div>
      </div>
   </header>
   <!-- Icons Grid -->
