package hello.core.singleton;

// 실무에서 자주 발생하는 문제에 대한 예시이므로 잘 기억해두기
public class StatefulService {
//    private int price; // 상태를 유지하는 필드
//
//    public void order(String name, int price) {
//        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 문제지점
//    }

    // price라는 상태값을 없애고, getPrice까지 한 번에 할 수 있는 메서드로 바꿈
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

}
