package com.spring.util;

import org.springframework.context.ApplicationContext;
import com.spring.common.ApplicationContextProvider;

public class BeanUtils {
 
    public static Object getBean(String beanName) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        return applicationContext.getBean(beanName);
    }
 
}