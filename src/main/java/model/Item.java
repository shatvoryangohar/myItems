package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
private int id;
private String title;
private  double price;
private String description;
private User user;
private Category category;
private List<Picture> pictureList;
}
