
<div class="content">
    <div class="container">
        <#assign var = 0>
        {#list courses as course}
            {#if var % 3 == 0}
                <div class="row">
            {/#if}
                    <div class="col-lg-4">
                        <div class="text-center card-box">
                            <div class="member-card pt-2 pb-2">
                                <div class="thumb-lg member-thumb mx-auto"><img src="https://bootdey.com/img/Content/avatar/avatar2.png" class="rounded-circle img-thumbnail" alt="profile-image"></div>
                                <div class="">
                                    <h4>${course.course_name}</h4>
                                    <p class="text-muted"><span>${course.course_code}</span></p>
                                </div>
                                <a class="btn btn-primary mt-3 btn-rounded waves-effect w-md waves-light">Details</button>
                                <div class="mt-4">
                                    <div class="row">
                                        <div class="col-4">
                                            <div class="mt-3">
                                                <h4>${course.class_size}</h4>
                                                <p class="mb-0 text-muted">Class list</p>
                                            </div>
                                        </div>
                                        <div class="col-4">
                                            <div class="mt-3">
                                                <h4>${course.start_date}</h4>
                                                <p class="mb-0 text-muted">Start date</p>
                                            </div>
                                        </div>
                                        <div class="col-4">
                                            <div class="mt-3">
                                                <h4>${course.end_date}</h4>
                                                <p class="mb-0 text-muted">End date</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            {#if var % 3 == 0}
                </div>
            {/#if}
            <#assign var = var + 1>
        {/#list}
    </div>
    <!-- container -->
</div>