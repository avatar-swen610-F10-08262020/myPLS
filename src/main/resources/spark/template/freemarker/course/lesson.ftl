<div class="container">
 <#list lessonList as lesson>
 <div class="card" style="padding: 1rem;">
    <head>Week ${lesson.week_id}</head>
  <div class="card-body">
    <a href="/lesson/${lesson.lesson_content_path}" download>${lesson.lesson_content_path}</a>
    <#if UserType!=3>
    <a href="/course/${course.id}/delete/${lesson.id}" class="btn btn-sm btn-danger">Delete</a>
    </#if>
    <div class="fb-share-button col-sm-4" data-href="https://developers.facebook.com/docs/plugins/" data-layout="button_count" data-size="small">
         <a target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fdevelopers.facebook.com%2Fdocs%2Fplugins%2F&amp;src=sdkpreparse" class="fb-xfbml-parse-ignore">
            Share
         </a>
    </div>

  </div>
 </div>

 <#else>
  <div class="card" style="padding: 1rem;">
     <head>Week 1</head>
    <div class="card-body">
        No Item added yet.
    </div>
  </div>
 </#list>
<button style="margin:2em" class="btn btn-info" data-toggle="collapse" data-target="#lesson_add">Add Lesson</button>

    <div id="lesson_add" class="collapse">
        <div class="card" style="padding:1rem;width:50%">
            <form action="/lesson/add/${course.id}" method="post" enctype="multipart/form-data">
               <div class="form-group">
                  <label for="file_name" class="col-form-label">File Name</label>
                  <input type="text" class="form-control" id="file_name" name="file_name" required>
               </div>

               <div class="form-group">
                   <label for="week_id">Week Number</label>
                   <select class="form-control" id="week_id" name="week_id" required>
                       <option selected>Week Number</option>
                       <#list 1..12 as x>
                       <option value="${x}">${x}</option>
                       </#list>
                   </select>
                 </div>
                 <div class="form-group">
                     <label for="document">Select Class Document</label>
                     <input type="file" class="form-control-file" name="lesson_content_path" id="lesson_content_path">
                   </div>
               <button type="submit" class="btn btn-primary">Add</button>
               <a href="/course" class="btn btn-danger">Cancel</a>

            </form>
        </div>
    </div>

</div>