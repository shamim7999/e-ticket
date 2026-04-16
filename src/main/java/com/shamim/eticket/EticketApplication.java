package com.shamim.eticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class EticketApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(EticketApplication.class, "shaua");
		int beanCount = context.getBeanDefinitionCount();
		String [] beanDefinitions = context.getBeanDefinitionNames();
		System.out.println("Bean Count: " + beanCount);
		for(String beanDefinition : beanDefinitions) {
			Object bean = context.getBean(beanDefinition);
			if (bean.getClass().getPackageName().startsWith("com.shamim")) {
				System.out.println(beanDefinition + " -> " + bean.getClass());
			}
			if(bean instanceof org.springframework.data.repository.Repository){
				System.out.println(beanDefinition + " -> " + bean.getClass());
			}

		}
	}

}
