package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = bean.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
    }

    /*
    * @Autowired 는 타입으로 조회한다.
    *  1. 타입 매칭
    *  2. 매칭 결과 2개 이상일시 필드 명, 파라미터 명으로 빈 이름 매칭
    *
    *
    * 조회 빈이 2개 이상인 경우 해결방법 >>
    * 1. 필드명 매칭
    * @Autowired가 타입 매칭 시도 > 여러 빈이 있을시 필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭한다.
    *
    * 2. @Quilifier 사용
    * 추가 구분자를 붙여주는 방법. 주입시 추가적인 방법을 제공하는 것이지만 빈 이름을 변경하는 것은 아니다.
    * quilifier를 못찾으면 같은 이름의 스프링 빈을 추가로 찾는다. quilifier를 찾는 용도로만 사용하는게 명확하고 좋다.
    *
    * 3. @Primary 사용
    * 우선 순위를 지정하는 방법이다. 여러개가 매칭되면 @Primary가 우선권을 가진다.
    *
    * @Quilifier VS @Primary시
    * @Quilifier가 우선순위가 높다.
    * */

}
