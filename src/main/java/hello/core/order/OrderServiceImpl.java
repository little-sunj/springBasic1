package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor //final이 붙은 값을 가지고 생성자를 만들어준다.
public class OrderServiceImpl implements OrderService{

    /*롬복이 자바의 애노테이션프로세서라는 기능을 이용해 컴파일 시점에 생성자 코드를 자동으로 만들어준다.   * */

    //필드주입 방법. : 이름 그대로 필드에 바로 주입.
    //@Autowired private MemberRepository memberRepository;
    /*코드가 간결해서 많은 개발자들을 유혹하지만 외부에서 변경이 불가능해서 테스트 하기 힘들다는 치명적인 단점이 있다.
    DI프레임워크가 없으면 아무것도 할 수 없다.
    가급적 쓰지 않도록 한다...!
    * */
    private final DiscountPolicy discountPolicy; //인터페이스에만 의존하도록 설계와 코드 변경
    private final MemberRepository memberRepository;

    //@Autowired의 기본 동작은 주입 대상이 없으면 오류가 발생한다.
    //(required=false)로 지정해 주입 대상이 없어도 동작하게 할 수 있다.

    //setter 주입 (수정자 주입) : 선택, 변경 가능성이 있는 의존관계에 사용.
    //자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.
//    @Autowired  //(required = false)
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    // 생성자 주입 : 불변, 필수 의존관계에 사용. 항상 생성자 주입을 선택할것!
    // 중요! : 생성자가 1개만 있다면 @Autowired를 생략해도 좋다.
    // 필드에 final 키워드를 사용할 수 있다. 혹시라도 생성자에서 값이 설정되지 않는 오류를 컴파일 시점에 막아준다.
    // "컴파일 오류는 세상에서 가장 빠르고, 좋은 오류다!"
    // 프레임워크에 의존하지 않고 순수한 자바 언어의 특징을 잘 살리는 방법이기도 하다.
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    } //롬복 사용


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
