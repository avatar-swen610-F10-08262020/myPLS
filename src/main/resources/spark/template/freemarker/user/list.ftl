<div class="row clearfix">
    <div class="col-md-10 column">
        <table class="table table-bordered table-hover" id="tab_logic">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <#list userList as users>
                    <tr>
                        <td>${users.user.first_name} ${users.user.last_name}</td>
                        <td>${users.user.email}</td>

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
