package hello.itemservice.domain.item;

import lombok.Data;

// hibernate에서만 동작
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

// 표준으로 동작
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
// @ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000")
public class Item {

    // @NotNull(groups = UpdateCheck.class) // 수정 요구사항 추가
    private Long id;

    // @NotBlank(groups = {SaveCheck.class, UpdateCheck.class}) -> 빈 값 + 공백만 있는 경우를 불허
    private String itemName;

    // @NotNull(groups = {SaveCheck.class, UpdateCheck.class}) -> null 불허
    // @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class}) -> 해당 범위 안의 값이어야 함
    private Integer price;

    // @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    // @Max(value = 9999, groups = {SaveCheck.class}) -> 최대값 제한
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
