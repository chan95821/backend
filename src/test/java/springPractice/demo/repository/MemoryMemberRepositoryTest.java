package springPractice.demo.repository;

// 협업시 테스트 코드 무조건 필요 - 심화 공부 필요
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import springPractice.demo.domain.Member;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //Test 함수 실행 순서는 정해지지 않음 -> 각 테스트 함수가 끝나고 repository 요소를 모두 지움
    public void afterEach()
    {
        repository.clearStore();
    }
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member res = repository.findById(member.getId()).get();
       

    }
}
