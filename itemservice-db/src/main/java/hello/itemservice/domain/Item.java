package hello.itemservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", length = 10)
    private String itemName;
    private Integer price; // 컬럼명과 필드명이 같으면 별도로 작성하지 않아도 됨
    private Integer quantity;

    public Item() { // JPA는 기본 생성자가 필수
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
