<#include "/header/header_home.ftl">



  <!-- Masthead -->
   <header class="masthead text-center">



  <!-- Icons Grid -->
  <section class="bg-light text-center">


        <div class="container">
          <h2>User List</h2>
             <input type="text" style="width:15%" id="firstName" class="fadeIn second" name="firstName" placeholder="first name" required>
             <input type="text" style="width:15%" id="lastName" class="fadeIn second" name="lastName" placeholder="last name" required>
              <input type="text" style="width:15%" id="email" class="fadeIn second" name="email" placeholder="email" required>

                 <select style="width:15%" lass="fadeIn third" id="userTypeID" name="userTypeID" required>
                  <option selected>User Type</option>
                   <option value="3">Learner</option>
                   <option value="2">Professor</option>
                 </select>

                <select style="width:15%" class="fadeIn third" id="userStatus" name="userStatus" required>
                 <option selected>User Status</option>
                  <option value="0">Inactive</option>
                  <option value="1">Active</option>
                </select>
              <a class="btn btn-primary" href="#">Search</a>
          <table class="table table-hover">
            <thead>
              <tr>
                <th>Firstname</th>
                <th>Email</th>
                <th>Status</th>


              </tr>
            </thead>
            <tbody>
               <#list users as user>
                
                   <tr>
                       <td>${user.first_name}</td>
                       <td>${user.email}</td>
                       <th>${user.status}</th>

                   </tr>
               </#list>
            </tbody>
          </table>
        </div>

    </section>
</header>
 <#include "/footer/footer_default.ftl">
