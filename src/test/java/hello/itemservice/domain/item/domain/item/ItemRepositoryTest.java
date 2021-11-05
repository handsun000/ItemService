package hello.itemservice.domain.item.domain.item;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        //when
        Item savedItem1 = itemRepository.save(item1);
        Item savedItem2 = itemRepository.save(item2);

        //then
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList.size()).isEqualTo(2);
        assertThat(itemList).contains(savedItem1, savedItem2);
    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("item", 10000, 10);
        Item savedItemA = itemRepository.save(item);
        Long savedItemAId = savedItemA.getId();

        //when
        Item updateItem = new Item("updateItem", 20000, 20);
        itemRepository.update(savedItemAId, updateItem);

        Item findItem = itemRepository.findById(savedItemAId);
        //given

        assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateItem.getQuantity());
    }
}