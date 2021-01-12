package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulserviceTest {

    @Test
    @DisplayName("Stateful 설계의 문제확인")
    void statefulServiceSingleTon() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        Statefulservice statefulservice1 = ac.getBean(Statefulservice.class);
        Statefulservice statefulservice2 = ac.getBean(Statefulservice.class);

        //ThreadA 가정 : A 사용자가 10000원 주문
        int userAPrice = statefulservice1.order("userA", 10000);
        //ThreadB 가정 : B 사용자가 20000원 주문
        int userBPrice = statefulservice2.order("userB", 20000);

        //ThreadA 가정 : A 사용자가 주문 금액 조회
        //int price = statefulservice1.getPrice();
        System.out.println("price = " + userAPrice);

        //Assertions.assertThat(statefulservice1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public Statefulservice statefulservice() {
            return new Statefulservice();
        }
    }

}