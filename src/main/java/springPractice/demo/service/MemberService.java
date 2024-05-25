package springPractice.demo.service;


import org.springframework.beans.factory.annotation.Autowired;

import springPractice.demo.controller.Member;
import java.util.Optional;
import java.util.List;

public class MemberService {
    
    @Autowired
    MemberRepository memberRepository;

    public void test() {
        memberRepository.save(new Member(1L, "A"));

        Optional<Member> member = memberRepository.findById(1L);
        List<Member> allMembers = memberRepository.findAll();

        memberRepository.deleteById(1L);

        
    }
}
