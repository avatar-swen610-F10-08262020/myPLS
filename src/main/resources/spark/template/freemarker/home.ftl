<#include "/header/header_home.ftl">



  <!-- Masthead -->
   <header class="masthead text-center">



  <!-- Icons Grid -->
  <section class="bg-light text-center">


        <div class="container">
          <h2>User List</h2>

         <table id="user-index-table" class="table table-hover">

            <thead>
              <tr>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Email</th>
                <th>Status</th>
                 <th>User Type</th>

              </tr>
            </thead>
            <tbody>
               <#list users as user>
                
                   <tr>
                       <td>${user.first_name}</td>
                       <td>${user.last_name}</td>
                       <td>${user.email}</td>
                       <th>${user.status}</th>
                        <th>${user.user_type_id}</th>
                   </tr>
               </#list>
            </tbody>
          </table>
        </div>

    </section>
</header>
