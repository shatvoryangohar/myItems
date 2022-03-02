package manager;

import db.DBConnectionProvider;
import model.Item;
import model.Picture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private UserManager userManager = new UserManager();
    private CategoryManager categoryManager = new CategoryManager();
    private PictureManager pictureManager = new PictureManager();

    public void addItem(Item item) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into item(title,price,description,user_id,category_id)VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setInt(4, item.getUser().getId());
            preparedStatement.setInt(5, item.getCategory().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int anInt = resultSet.getInt(1);
                item.setId(anInt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Item getItemById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM item WHERE id = " + id);
            if (resultSet.next()) {
                return Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .description(resultSet.getString(4))
                        .user(userManager.getUserById(resultSet.getInt(5)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(6)))
                        .pictureList(pictureManager.getPictureByItemId(id))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM item");
            while (resultSet.next()) {
                Item item = Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .description(resultSet.getString(4))
                        .user(userManager.getUserById(resultSet.getInt(5)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(6)))
                        .build();
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void deleteItemById(int id) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from item where id = " + id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getAllUserItems(int userId) {
        List<Item> items = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM item WHERE user_id = " + userId);
            while (resultSet.next()) {
                items.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .description(resultSet.getString(4))
                        .user(userManager.getUserById(resultSet.getInt(5)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(6)))
                        .build());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> getLast20Items() {
        Statement statement = null;
        List<Item> items = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM item order by id DESC limit 20 ");
            while (resultSet.next()) {
                items.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .description(resultSet.getString(4))
                        .user(userManager.getUserById(resultSet.getInt(5)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(6)))
                        .pictureList(pictureManager.getPictureByItemId(resultSet.getInt(1)))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;

    }

    public List<Item> getLast20ItemsByCategory(int categoryId) {
        Statement statement = null;
        List<Item> items = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM item where category_id" + categoryId + "order by id DESC limit 20 ");
            while (resultSet.next()) {
                items.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .description(resultSet.getString(4))
                        .user(userManager.getUserById(resultSet.getInt(5)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(6)))
                       .pictureList(pictureManager.getPictureByItemId(resultSet.getInt(1)))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;

    }

}
