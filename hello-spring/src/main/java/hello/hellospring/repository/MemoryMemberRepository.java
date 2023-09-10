package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// Component를 메타 애노테이션으로 갖는 Repository 애노테이션을 이용해 Bean 등록
// @Repository
public class MemoryMemberRepository implements MemberRepository{

    // 실무에서는 동시성 문제를 겪을 수 있기 때문에 공유되는 객체의 경우 ConcurrentHashMap을 사용한다.
    private static Map<Long,Member> store = new HashMap<>();

    // long도 마찬가지로 동시성 문제가 우려되는 경우 AtomicLong을 사용해야 함
    private static long sequence = 0L;

    @Override
    public Member save(Member member) { // name은 고객이 작성해서 이미 셋팅이 된 상태
        member.setId(++sequence); // store에 넣기 전에 sequence 셋팅(id는 시스템이 정해줌)
        store.put(member.getId(), member); // store에 저장 후 저장한 객체를 리턴
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // Null일 경우를 대비해 Optional로 감싸줌
    }

    @Override
    public Optional<Member> findByname(String name) {
        return store.values().stream() // 루프 실행
                .filter(member -> member.getName().equals(name)) // 매개변수와 현재 조회 중인 객체의 이름이 같으면 필터에 걸림
                .findAny(); // findAny는 하나라도 일치하면 리턴
        // 아무것도 못 찾을 경우 Optional로 null 리턴
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 전체를 리스트로 리턴
    }

    public void clearStore() {
        store.clear();
    }
}
