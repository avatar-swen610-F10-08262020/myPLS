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
                <th>User Type</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
               <#list users as user>
                
                   <tr>
                       <td><a href="/user/${user.id}" >${user.first_name}</a></td>
                       <td>${user.last_name}</td>
                       <td>${user.email}</td>
                       <th> <#if user.user_type_id == 1 > Admin <#elseif user.user_type_id == 2 > Professor <#else> Learner </#if> </th>
                        <th> <#if user.status == 1> <p style="color:green">Active<p> <#else> <p style="color:red">Inactive<p> </#if></th>
                   </tr>
               </#list>
            </tbody>
          </table>
        </div>

    </section>
</header>
