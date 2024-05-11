package springPractice.demo.domain;

public class Member {
    
    private Long id; //system이 user를 구분하기 위한 id
    private String name;
    public Long getId() {
        return id;
    }public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
