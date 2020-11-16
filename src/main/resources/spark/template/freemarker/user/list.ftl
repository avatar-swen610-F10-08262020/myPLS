<div class="row clearfix">
    <div class="col-md-10 column">
        <table class="table table-bordered table-hover" id="tab_logic">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <td>Performance</td>
                    <td>Pass/Fail?</td>
                </tr>
            </thead>
            <tbody>
                <#list userList as users>
                    <tr>
                        <td>${users.user.first_name} ${users.user.last_name}</td>
                        <td>${users.user.email}</td>
                        <td>
                            <a href="/quiz/performance/${course.id}/${users.user.id}" class="btn btn-sm btn-primary">Show</a>
                        </td>
                        <td>
                            <#if users.completed == 0>
                                Not Graded
                            <#elseif users.completed == 2>
                                <p style="color:green"> <b>Passed</b></p>
                             <#else>
                                <p style="color:red"><b>Failed</b></p>
                             </#if>
                        </td>
                    </tr>
                  <#else>
                    <tr>
                        <td>No Learner Registered yet</td>
                    </tr>
                </#list>

            </tbody>
        </table>
    </div>
</div>
