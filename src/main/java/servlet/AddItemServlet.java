package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.PictureManager;
import manager.UserManager;
import model.Category;
import model.Item;
import model.Picture;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;


@WebServlet(urlPatterns = "/addItem")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class AddItemServlet extends HttpServlet {
    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();
    private PictureManager pictureManager = new PictureManager();
    private static final String UPLOAD_DIR = "C:\\Users\\Gohar\\IdeaProjects\\myItems\\image\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryManager.getAllCategories());
        req.getRequestDispatcher("/WEB-INF/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        int catId = Integer.parseInt(req.getParameter("cat_id"));
        Category category = categoryManager.getCategoryById(catId);
        Item item = Item.builder()
                .title(title)
                .price(price)
                .description(description)
                .category(category)
                .user(user)
                .build();
        itemManager.addItem(item);

        Collection<Part> parts = req.getParts();
        for (Part part : parts) {
            if(part.getName().equals("picture")) {
                String fileName = part.getSubmittedFileName();
                String picUrl = System.nanoTime() + "_" + fileName;
                part.write(UPLOAD_DIR + picUrl);
                Picture picture = new Picture();
                picture.setItemId(item.getId());
                picture.setPicUrl(picUrl);
              pictureManager.add(picture);
            }
        }

        resp.sendRedirect("/home");
    }

}


