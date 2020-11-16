<#include "/header/header_course.ftl">

   <!-- Masthead -->
   <header class="masthead text-center">
      <section class="bg-light text-center">
        <div class="container">
            <#if msg_type == "error">
               <div class="alert alert-danger" role="alert">
                 ${msg}
               </div>
           <#elseif msg_type == "success">
               <div class="alert alert-success" role="alert">
                 ${msg}
               </div>
           </#if>
        </div>
      </section>
   </header>

   <script>
    (function(){
         var f = function() {
           var cId = "${courseid}";
           console.log(cId);
            location.href="/course/" + cId;
         };
         window.setInterval(f, 3000);
       })();
   </script>

