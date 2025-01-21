package controller.item;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements ItemService{
    @Override
    public boolean addItem(Item item) {
        return false;
    }

    @Override
    public boolean deleteItem(String itemCode) {
        return false;
    }

    @Override
    public boolean updateItem(Item item) {
        return false;
    }

    @Override
    public Item searchItem(String itemCode) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from item WHERE itemcode=" + "'" + itemCode + "'");
            resultSet.next();
            Item item = new Item(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5));
            return item;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Item> getAll() {

        List<Item> itemList= new ArrayList<>();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from item;");


            while (resultSet.next()){
                Item item = new Item(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5));

                itemList.add(item);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }

    public ObservableList<String> getItemIds(){
        List<Item> allItemList=getAll();
        ObservableList<String> itemCodeList = FXCollections.observableArrayList();
        allItemList.forEach(item->{
            itemCodeList.add(item.getItemCode());
        });

        return itemCodeList;
    }

}
