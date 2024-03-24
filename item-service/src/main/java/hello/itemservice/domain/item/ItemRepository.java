package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository { // Ctrl + Shift + T로 Test 이동

    // 멀티 스레드 환경에서는 Thread Safety한 객체를 사용해야 함(ConcurrentHashMap, AtomicLong 등)
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }
    public Item findById(Long id) {
        return store.get(id);
    }
    public List<Item> findAll() {
        return new ArrayList<>(store.values()); // 실제 데이터에 영향을 주지 않기 위해 복사해서 리턴
    }
    public void update(Long itemId, Item updateParam) { // 원래는 DTO 객체를 새로 만들어야 함
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }
    public void clearStore() {
        store.clear();
    }
}