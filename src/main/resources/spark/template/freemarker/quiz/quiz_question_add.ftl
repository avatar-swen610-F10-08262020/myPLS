<div id="question_add" class="collapse">
    <div class="card" style="padding:1rem;width:50%">
        <form action="/quiz/question/add/${quiz.id}" method="post">
           <div class="form-group">
              <label for="file_name" class="col-form-label">Question</label>
              <input type="text" name='question'  placeholder='Question' class="form-control" required/>
           </div>
           <div class="form-group">
              <label for="file_name" class="col-form-label">Answer Option 1</label>
              <input type="text" name='options1'  placeholder='Option' class="form-control" required/>
           </div>
           <div class="form-group">
              <label for="file_name" class="col-form-label">Answer Option 2</label>
              <input type="text" name='options2'  placeholder='Option' class="form-control" required/>
           </div>
           <div class="form-group">
              <label for="file_name" class="col-form-label">Answer Option 3</label>
              <input type="text" name='options3'  placeholder='Option' class="form-control" required/>
           </div>
           <div class="form-group">
              <label for="file_name" class="col-form-label">Answer Option 4</label>
              <input type="text" name='options4'  placeholder='Option' class="form-control" required/>
           </div>
           <div class="form-group">
              <label for="file_name" class="col-form-label">Answer Option 5</label>
              <input type="text" name='options5'  placeholder='Option' class="form-control" required/>
           </div>


           <div class="form-group">
               <label for="correct_answer">Correct Answer</label>
               <select class="form-control" id="correct_ans" name="correct_ans" required>
                   <option selected>Select Answer</option>
                   <#list 1..5 as x>
                   <option value="${x}">${x}</option>
                   </#list>
               </select>
             </div>

           <button type="submit" class="btn btn-sm btn-primary">Add</button>
           <button data-toggle="collapse" data-target="#question_add" class="btn btn-sm btn-danger" >Cancel</button>

        </form>
    </div>
</div>