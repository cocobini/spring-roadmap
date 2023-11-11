package hello.core.singleton;

// 기존 서비스에 영향을 주지 않게 test 하위에서 싱글톤 클래스를 테스트해보자.
public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    // private으로 생성자를 다른 클래스에서 호출할 수 없게 함 -> getInstance를 통해서만 잡근 가능
    private SingletonService() {

    }
}
