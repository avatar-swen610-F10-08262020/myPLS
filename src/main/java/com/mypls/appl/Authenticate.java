package com.mypls.appl;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authenticate {
    UserAuthentication.MethodName processorMethod() default UserAuthentication.MethodName.doNothing;


}
