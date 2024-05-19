package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id; // DB 아이디

    @NotEmpty
    private String loginId; // login 시 작성하는 아이디

    @NotEmpty
    private String name; // 사용자 이름

    @NotEmpty
    private String password;
}
