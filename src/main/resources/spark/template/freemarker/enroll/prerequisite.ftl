<div class="container">
<h2><#if (dependent_size > 0)> ${dependent_size} <#else> No </#if> Prerequisites</h2>
<#if (dependent_size > 0)>
<table id="enroll-index-table" class="table table-hover">
    <thead>
        <tr>
            <th>Course Name</th>
            <th>Course Code</th>
        </tr>
    </thead>
    <tbody>
        <#list dependent_course as course>
            <tr>
                <td>${course.course_name}</a></td>
                <td>${course.course_code}</td>
            </tr>
        </#list>
    </tbody>
</table>
</#if>
</div>