package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

//        MemberRepository memberRepository1 = memberService.getMemberRepository();
//        MemberRepository memberRepository2 = orderService.getMemberRepository();
        
        //동일한 객체 참조값임을 확인 - 같은 인스턴스
//        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
//        System.out.println("orderSerice -> memberRepository2 = " + memberRepository2);
//        System.out.println("memberRepository = " + memberRepository);

//        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
//        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        //class hello.core.AppConfig$$EnhancerBySpringCGLIB$$fc97ebeb

        //순수한 클래스라면 hello.core.AppConfig 이렇게 출력될 것
        // EnhancerBySpringCGLIB : 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해
        // AppConfig 클래스를 상속받는 임의의 다른 클래스를 만들고 그 다른 클래스를 스프링 빈으로 등록한 것이다.
        //이 임의의 다른 클래스가 싱클톤이 보장되도록 해주는 것이다.
    }
}
