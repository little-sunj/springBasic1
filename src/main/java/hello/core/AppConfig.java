package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //설정정보, 구성정보
public class AppConfig {
    //application 전체를 설정 / 구성하는 파일

    /*
    * AppConfig는 애플리케이션의 실제 동작에 필요한 '구현 객체를 생성' 한다.
    * 생성한 객체 인스턴스의 참조(레퍼런스)를 '생성자를 통해서 주입(연결)' 한다.
    *
    * 어떤 구현 객체를 주입할지는 오직 외부(appconfig)에서 결정하고
    * 구현체는 '의존관계에 대한 고민은 외부에 맡기고' 로직 실행에만 집중하면 된다.
    *
    * 관심사가 분리되고 serviceImpl은 추상에만 의존하면 되게 되므로 DIP가 완성된다.
    * */

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); //생성자 주입
        //ctrl + alt + m - extract method
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();

    }

}
