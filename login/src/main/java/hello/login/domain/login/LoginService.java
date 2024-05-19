package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    /**
     * @return null이면 로그인 실패
     */
    public Member login(String loginId, String password) {

        // get()으로 가져와서(없으면 에러 터짐) password가 같은지 확인하고, 없으면 null을 리턴하는 코드와 같음
        return memberRepository.findByLoginId(loginId) // Optional로 리턴
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

}
