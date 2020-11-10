<div class="container">
 <#list lessonList as lesson>
 <div class="card" style="padding: 1rem;">
    <head>Week ${lesson.week_number}</head>
    <#list lesson.content_lesson as content>
      <div class="card-body">
        <a href="/lesson/${content.lesson_content_path}" download>${content.lesson_content_path}</a>
        <div class="fb-share-button" data-href="https://developers.facebook.com/docs/plugins/" data-layout="button_count" data-size="small">
             <a target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fdevelopers.facebook.com%2Fdocs%2Fplugins%2F&amp;src=sdkpreparse" class="fb-xfbml-parse-ignore">
                Share
             </a>
        </div>
        <div class="row" style="padding:1rem">
            <button style="margin-right:1em" class="btn btn-sm btn-info" data-toggle="collapse" data-target="#lesson_update_${content.id}">Update Lesson</button>
            <form action="/lesson/delete/${course.id}/${content.id}" method="post">
                <input type="text" name="dummy" value="dummy" hidden/>
                <button type="submit"  class="btn btn-sm btn-danger">Delete</button>
            </form>
        </div>
        <div id="lesson_update_${content.id}" class="collapse">
                <div class="card" style="padding:1rem;width:50%">
                    <form action="/lesson/update/${course.id}/${content.id}" method="post" enctype="multipart/form-data">
                       <div class="form-group">
                          <label for="file_name" class="col-form-label">File Name</label>
                          <input type="text" class="form-control" id="file_name" value="" name="file_name" required>
                       </div>

                       <div class="form-group">
                           <label for="week_id">Week Number</label>
                           <select class="form-control" id="week_id" name="week_id" required readonly>
                               <option value="${lesson.week_number}" selected>${lesson.week_number}</option>

                           </select>
                         </div>
                         <div class="form-group">
                             <label for="document">Select Class Document</label>
                             <input type="file" class="form-control-file" name="lesson_content_path" value="${content.lesson_content_path}" id="lesson_content_path" required>
                           </div>
                        <button type="submit" class="btn btn-primary">Update</button>
                        <button style="margin:2em" class="btn btn-danger" data-toggle="collapse" data-target="#lesson_update_${content.id}">Cancel</button>

                    </form>
                </div>
            </div>
      </div>
    </#list>
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
                     <input type="file" class="form-control-file" name="lesson_content_path" id="lesson_content_path" required>
                   </div>
                <button type="submit" class="btn btn-primary">Add</button>
                <button style="margin:2em" class="btn btn-danger" data-toggle="collapse" data-target="#lesson_add">Cancel</button>

            </form>
        </div>
    </div>

</div>