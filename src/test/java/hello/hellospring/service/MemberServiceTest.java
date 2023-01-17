package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository repository;

    @BeforeEach
    void beforeEach(){
        repository = new MemoryMemberRepository();
        memberService = new MemberService(repository);
    }

    @AfterEach
    void afterEach(){
        repository.clearStore();
    }

    @Test
    void 회원가입() {
        // given = 이게 주어졌을 때,
        Member member1 = new Member();
        member1.setName("hello");

        // when = 이거를 할 때,
        memberService.join(member1);

        // then = 결과는?
        Member findMember = memberService.findOne(member1.getId()).get();
        assertThat(member1.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member1));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");

        /*
        try {
                memberService.join(member2);
                fail();
            }catch (IllegalStateException e){
                assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입11니다.");
            }
        */

        // then

    }

    @Test
    void 전체회원조회() {
    }

    @Test
    void 회원조회() {
    }
}