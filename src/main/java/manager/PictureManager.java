package manager;

import db.DBConnectionProvider;
import model.Picture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PictureManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();


    public List<Picture> getPictureByItemId(int itemId) {
        List<Picture> pictures = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("Select * from item_picture where item_id =" + itemId);
            while (resultSet.next()) {
                pictures.add(Picture.builder()
                        .id(resultSet.getInt(1))
                        .itemId(itemId)
                        .picUrl(resultSet.getString(3))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pictures;
    }

    public void add(Picture picture) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into item_picture(item_id,pic_url)VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, picture.getItemId());
            preparedStatement.setString(2, picture.getPicUrl());
            preparedStatement.executeUpdate();
            ResultSet resultset = preparedStatement.getGeneratedKeys();
            if (resultset.next()) {
                int anInt = resultset.getInt(1);
                picture.setId(anInt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
