package controller.item;

import model.Item;

import java.util.List;

public interface ItemService {
    boolean addItem(Item item);
    boolean deleteItem(String itemCode);
    boolean updateItem(Item item);
    Item searchItem(String itemCode);
    List<Item> getAll();


}
