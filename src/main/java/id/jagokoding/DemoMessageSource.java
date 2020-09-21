package id.jagokoding;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

public class DemoMessageSource {

	public static void main (String[] args) throws IOException {
        Locale.setDefault(new Locale("in", "ID"));
        AnnotationConfigApplicationContext context =
                            new AnnotationConfigApplicationContext(Config.class);

        MyBean bean = context.getBean(MyBean.class);
        bean.doSomething();
    }

    @Configuration
    public static class Config {

        @Bean
        public MyBean myBean () {
            return new MyBean();
        }

        @Bean
        public MessageSource messageSource () {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("messages/msg");
            return messageSource;
        }
    }

    public static class MyBean {
        @Autowired
        private MessageSource messageSource;

        public void doSomething () {
            System.out.println(messageSource.getMessage("welcome", new Object[]{"Jagokoding"},
                                                        Locale.getDefault()));
        }
    }
}
