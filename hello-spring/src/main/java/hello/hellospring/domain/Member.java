package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // JPA가 관리
public class Member {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 전략
    private Long id; // user의 id가 아닌 DB에 저장하기 위한 index

    // @Column 어노테이션을 통해 특정 컬럼과 직접 매핑을 시킬 수도 있음(이름이 다른 경우 등)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
