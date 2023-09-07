package hello.hellospring.domain;

public class Member {
    private Long id; // user의 id가 아닌 DB에 저장하기 위한 index
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
