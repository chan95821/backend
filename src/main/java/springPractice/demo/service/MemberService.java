package springPractice.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import springPractice.demo.domain.Member;
import springPractice.demo.repository.MemberRepository;
import springPractice.demo.repository.MemoryMemberRepository;

@Service
public class MemberService {
    
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public Long join(Member member) {
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent( mem -> {
                throw new IllegalStateException("이미 존재하는 회원");
            });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    
}
