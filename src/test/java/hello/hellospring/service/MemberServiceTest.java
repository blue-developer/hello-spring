package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Crtl + Shift + T 를 통해서 자동으로 생성한 테스트 클래스
 */
class MemberServiceTest {

    // 1. clear를 해주고 싶은데 MemverService 밖에 없어서 Repository 클리어 불가능하므로 Repository 를 선언하였다.
    // 2. MemberService 내에서도 Repository를 선언하고 있으므로, 아래의 MemoryMemberRepository는 또 다른 객체가 되어 애매해짐(두개를 쓸 이유가 없음)
//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // 3. Service 생성시 Repository를 주입하도록 수정함
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 테스트 실행 전에 Repository, Service 각각 초기화함
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // MemberService에서 new 선언 부분을 지우고 DI 가능하게 변경해줌

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            //then
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}