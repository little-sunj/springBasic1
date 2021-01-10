package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너") //문제점 스터디용
    void pureContainer() {
        AppConfig ac = new AppConfig();
        //1.조회 : 호출할 때마다 객체를 생성
        MemberService memberService1 = ac.memberService();

        //2.조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.memberService();

        //참조값이 다른 것을 확인 - 구현체까지 포함하면 4개가 생성된 셈이다.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        
        //memberService1 은 memberService2와 다름
        assertThat(memberService1).isNotSameAs(memberService2);

        //트래픽이 초당 100이 나오면 초당 100개 객체가 생성되고 소멸되니 효율적이지 못하다.
        //싱글톤을 사용해서 해결
    }

    /*
    * SINGLETON
    *
    * 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴.
    * 객체 인스턴스가 2개 이상 생성되지 못하도록 막는다.
    *
    * 스프링 컨테이너를 사용하면 스프링이 기본적으로 config내 객체들을 싱글톤으로 만들어 관리해준다.
    * */

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        //같은 객체 인스턴스가 반환된다.
        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);

        assertThat(instance1).isSameAs(instance2);
        // sameAs == 참조비교
        // equal

    }
}
