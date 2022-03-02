package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(urlPatterns = "/getImage")
public class ImageDownloadServlet extends HttpServlet {
    private final String UPLOAD_DIR = "C:\\Users\\Gohar\\IdeaProjects\\myItems\\image\\";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String picUrl = req.getParameter("pic_url");
        String filePath = UPLOAD_DIR + picUrl;
        File downloadFile = new File(filePath);
        if (downloadFile.exists()) {
            try (FileInputStream inputStream = new FileInputStream(downloadFile)) {
                resp.setContentType("image/jpq");
                resp.setContentLength((int) downloadFile.length());
                OutputStream outputStream = resp.getOutputStream();
                byte[] buffer = new byte[1046];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}