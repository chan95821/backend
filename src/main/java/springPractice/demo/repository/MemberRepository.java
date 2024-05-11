package springPractice.demo.repository;

import java.util.List;
import java.util.Optional;

import springPractice.demo.domain.Member;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional <Member> findByName(String name); //반환할 객체가 없을 때 Null로 반환할 수 있도록 함
    List<Member> findAll();
}
