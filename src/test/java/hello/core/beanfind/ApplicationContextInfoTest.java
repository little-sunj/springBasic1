package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//junit 5부터는 public 설정을 하지 않아도 된다.
class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        //junit 5부터는 public 설정을 하지 않아도 된다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //iter + tab : for문 자동완성
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            //soutv
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        //junit 5부터는 public 설정을 하지 않아도 된다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //iter + tab : for문 자동완성
        for (String beanDefinitionName : beanDefinitionNames) {
            //bean에 대한 정보를 꺼내볼 수 있다.
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            //ROLE_APPLICATION : 개발자가 개발을 위해 등록한 빈, 혹은 외부 라이브러리
            //ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            //스프링 내부 빈을 제외한 나머지 빈 정보가 출력
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                //soutv
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }

}
