package hello.core.member;

public class MemberServiceImpl implements  MemberService{

//    private  final MemberRepository memberRepository = new MemoryMemberRepository();//추상화와 구현체 모두 의존하므로 DIP를 위반하게 된다.
    private  final MemberRepository memberRepository;

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
}
