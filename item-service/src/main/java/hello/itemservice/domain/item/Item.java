package hello.itemservice.domain.item;


import lombok.Data;

// 실무에서 lombok 사용 시 @Data는 예측하지 못한 상황을 초래할 수 있기 때문에 사용에 주의
// @Getter @Setter를 사용하는 식으로 대체
@Data
public class Item {
    private Long id;
    private String itemName;
    private Integer price; // null인 경우를 고려하여 Integer 사용
    private Integer quantity;

    // Alt + Insert 로 Generate 기능 사용
    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
