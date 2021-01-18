package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //bean명 ("지정가능")
public class MemberServiceImpl implements  MemberService{

//    private  final MemberRepository memberRepository = new MemoryMemberRepository();//추상화와 구현체 모두 의존하므로 DIP를 위반하게 된다.
    private  final MemberRepository memberRepository;

    @Autowired //자동 의존관계 주입. 마치 ac.getBean(MemberRepository.class)처럼 동작한다고 이해하면 편하다
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
