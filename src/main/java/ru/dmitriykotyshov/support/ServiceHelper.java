package ru.dmitriykotyshov.support;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Дмитрий on 21.02.2018.
 */
public class ServiceHelper {

    public static <T> T getInstance(String nameOfInstance) {
        return ServiceHelper.RootAppServiceLocator.getInstance().getService(nameOfInstance);
    }

    public static <T> T getInstance(Class<T> classOfInstance) {
        return ServiceHelper.RootAppServiceLocator.getInstance().getService(classOfInstance);
    }

    private static class RootAppServiceLocator {

        private static final String CONFIG_LOCATION = "springContext.xml";
        private static final ServiceHelper.RootAppServiceLocator INSTANCE = new ServiceHelper.RootAppServiceLocator();
        private ApplicationContext beanFactory;

        public RootAppServiceLocator() {
            beanFactory = new ClassPathXmlApplicationContext(CONFIG_LOCATION);
        }

        public static ServiceHelper.RootAppServiceLocator getInstance() {
            return INSTANCE;
        }

        public <T> T getService(String beanName) {
            return (T) this.beanFactory.getBean(beanName);
        }

        public <T> T getService(Class<T> beanClass) {
            return (T) this.beanFactory.getBean(beanClass);
        }

    }

}
