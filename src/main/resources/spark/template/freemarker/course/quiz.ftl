<div class="container">
 <#list lessonList as lesson>
 <div class="card" style="padding: 1rem;">
    <head>Week ${lesson.week_id}</head>
  <div class="card-body">
    <a href="/lesson/${lesson.lesson_content_path}" download>${lesson.lesson_content_path}</a>

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
<button style="margin:2em" class="btn btn-info" data-toggle="collapse" data-target="#quiz_add">Add Quiz</button>

    <div id="quiz_add" class="collapse">
        <h1>Add Quiz</h1>
        <div class="card" style="padding:1rem;width:100%">
            <form action="/quiz/add/${course.id}" method="post">
               <div class="row">
                  <div class="form-group" style="margin-left:1em; margin-right:1em;">
                    <label for="quiz_description" class="col-form-label">Quiz Description</label>
                    <input type="text" class="form-control" id="quiz_description" name="quiz_description" required>
                 </div>
                 <div class="form-group" style="margin-right:1em;">
                     <label for="quiz_length" class="col-form-label">Quiz Length(in Minute)</label>
                     <input type="number" class="form-control" id="quiz_length" name="quiz_length" required>
                  </div>
                  <div class="form-group" style="margin-right:1em;">
                    <label for="start_date" class="col-form-label">Start Date</label>
                    <input type="date" class="form-control" id="start_date" name="start_date">
                  </div>
                  <div class="form-group">
                    <label for="end_date" class="col-form-label">End Date</label>
                    <input type="date" class="form-control" id="end_date" name="end_date">
                  </div>
               </div>
               <div class="row clearfix">
               		<div class="col-md-12 column">
               			<table class="table table-bordered table-hover" id="tab_logic">
               				<thead>
               					<tr>
               						<th class="text-center">
               							#
               						</th>
               						<th class="text-center">
               							Question Description
               						</th>
               						<th class="text-center">
                                        Answer Option 1
                                    </th>
                                    <th class="text-center">
                                        Answer Option 2
                                    </th>
                                    <th class="text-center">
                                        Answer Option 3
                                    </th>
                                    <th class="text-center">
                                        Answer Option 4
                                    </th>
                                    <th class="text-center">
                                        Answer Option 5
                                    </th>
                                    <th class="text-center">
                                        Correct Answer
                                    </th>
               					</tr>
               				</thead>
               				<tbody>
               					<tr id='addr0'>
               						<td>1</td>
               						<td>
               						    <input type="text" name='question1'  placeholder='Question' class="form-control"/>
               						</td>
               						<td id='option0'>

                                        <input type="text" name='options11'  placeholder='Option' class="form-control"/>
                                    </td>
                                    <td id='option1'>

                                        <input type="text" name='options12'  placeholder='Option' class="form-control"/>
                                    </td>
                                    <td id='option2'>
                                        <input type="text" name='options13'  placeholder='Option' class="form-control"/>
                                    </td>
                                    <td id='option3'>
                                        <input type="text" name='options14'  placeholder='Option' class="form-control"/>
                                    </td>
                                    <td id='option4'>
                                        <input type="text" name='options15'  placeholder='Option' class="form-control"/>
                                    </td>

                                     <td id='option5'>
                                        <div class="form-group">
                                           <select class="form-control" id="correct_ans" name="correct_ans_1" required>
                                               <option selected>Correct Ans</option>
                                               <option value="1">1</option>
                                               <option value="2">2</option>
                                               <option value="3">3</option>
                                               <option value="4">4</option>
                                               <option value="5">5</option>
                                           </select>
                                         </div>
                                     </td>

               					</tr>
                                   <tr id='addr1'></tr>

               				</tbody>
               			</table>
               		</div>
               	</div>
               	<div class="row" style="padding:1em;">
               	    <a id="add_row" class="btn btn-success pull-left" style="margin-right:1em;">Add Question</a><a id='delete_row' class="pull-right btn btn-warning">Delete Question</a>
                </div>
                <button type="submit" class="btn btn-primary" style="margin-right:1em;">Submit Quiz</button> <button class="btn btn-danger" data-toggle="collapse" data-target="#quiz_add">Cancel</button>

            </form>
        </div>
    </div>

</div>

<script>
$(document).ready(function(){
      var i=1;
     $("#add_row").click(function(){b=i-1;

     var correct_ans='<td id="option5"><div class="form-group"> <select class="form-control" id="correct_ans" name="correct_ans_'+(i+1)+'" required> <option selected>Correct Ans</option> <option value="1">1</option> <option value="2">2</option> <option value="3">3</option> <option value="4">4</option> <option value="5">5</option> </select> </div> </td>';
      $('#tab_logic').append('<tr id="addr'+(i+1)+'"><td>'+(i+1)+'</td><td><input type="text" name="question'+(i+1)+'"  placeholder="Question" class="form-control"/></td><td id="option0"> <input type="text" name="options'+(i+1)+'1"  placeholder="Option" class="form-control"/></td><td id="option0"> <input type="text" name="options'+(i+1)+'2"  placeholder="Option" class="form-control"/></td><td id="option0"> <input type="text" name="options'+(i+1)+'3"  placeholder="Option" class="form-control"/></td><td id="option0"> <input type="text" name="options'+(i+1)+'4"  placeholder="Option" class="form-control"/></td><td id="option0"> <input type="text" name="options'+(i+1)+'5"  placeholder="Option" class="form-control"/></td>'+correct_ans+'</tr>');

      i++;
  });
     $("#delete_row").click(function(){
    	 if(i>1){
		 $("#addr"+(i-1)).html('');
		 i--;
	    }
	 });

});
</script>