package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        //destroyMethod는 기본값이 {추론}으로 되어있다.
        //이 기능을 사용하기 싫다면 destroyMethod="" 처럼 빈 공백을 지정하면 된다.
        @Bean //(initMethod="init", destroyMethod="close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return  networkClient;
        }
    }
}


/*
* 스프링 빈은 객체를 생성하고 의존관계 주입이 다 끝난 다음에야 필요한 데이터를 사용할 수 있는 준비가 완료된다.
* 따라서 초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출해야 한다.
*
* 스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해 초기화 시점을 알려주는 다양한 기능을 제공한다.
* 또한 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 준다. 따라서 안전하게 종료 작업을 진행할 수 있다.
* 
* 
*  - 스프링 빈의 이벤트 라이프 사이클
* 1. 스프링 컨테이너 생성
* 2. 스프링 빈 생성
* 3. 의존관계 주입
* 4. 초기화 콜백
* 5. 사용
* 6. 소멸전 콜백
* 7. 스프링 종료
*
* * 객체의 생성과 초기화를 분리하자.
* 생성자는 필수 정보 (파라미터)를 받고 메모리를 할당해서 객체를 생성하는 책임을 가진다.
* 반면 초기화는 이렇게 생성된 값들을 활용해 외부 커넥션을 연결하는 등 무거운 동작을 수행한다.
*
* *
*
* */