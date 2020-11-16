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
                        <td><button class="btn btn-sm btn-primary" data-toggle="collapse" data-target="#quiz_performance">Show</button> </td>
                        <td>
                            <#if users.completed == 0>
                                <button type="submit" class="btn btn-sm btn-success" style="margin-right:1em;">Pass</button>
                             <#else>
                                <button type="submit" class="btn btn-sm btn-danger" style="margin-right:1em;">Fail</button>
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
