package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 굳이 public으로 하지 않아도 됨
 * 테스트코드의 장점:
 *      빨리 실행됨
 *      반복 실행 가능함
 *      여러 테스트를 한번에 실행할 수 있음
 */
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * 콜백 메소드. 테스트 메소드 하나가 끝날 때마다 이 메소드를 실행함
     * @AfterEach : 한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있다.
     * 이렇게 되면 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있다.
     * @AfterEach 어노테이션을 사용하면 각 테스트가 종료될 때마다 이 기능을 실행한다.
     * 여기서는 메모리 DB에 저장된 데이터를 삭제한다.
     *
     * 테스트는 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존 관계가 있는 것은 좋은 테스트가 아니다.
     *
     * 테스트 순서는 보장 되지 않음 --> 모든 테스트는 순서에 상관 없이 Method 별로 따로 실행되도록 설계해야 함
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(member, result);    // param: expected, actual, jupiter
        Assertions.assertThat(result).isEqualTo(member);    // actual, expected, assertj
        //assertThat(result).isEqualTo(member);
    }

   @Test
    public void findByName() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        Member result = repository.findByName("spring1").get();

        // then
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        List<Member> result = repository.findAll();

        // then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
