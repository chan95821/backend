package springPractice.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import springPractice.demo.domain.Member;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //실무에서 공유되는 변수일 때는 concurrent Hashmap을 사용해야 함
    private static long sequence = 0L; // key 값을 생성하는 변수, 마찬가지 이유로 atomic long 사용해야 함

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;

    }

    @Override
    public List<Member> findAll() {
        // TODO Auto-generated method stub
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //감싸서 반환 - 질의한 id가 없을 때 상황 제어 가능
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<Member> findByName(String name) {
        // Map.values() - map의 value값을 Collection<V> type으로 반환
        store.values().stream() //stream은 lazy evalu
            .filter(member->member.getName().equals(name))
            .findAny(); //가장 먼저 탐색되는 요소 반환 -stream에서
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    public void clearStore() {
        store.clear();
    }
    
}
