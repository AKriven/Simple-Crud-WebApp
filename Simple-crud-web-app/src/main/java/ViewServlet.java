import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<a href='index.html'>Add New Book</a>");
        out.println("<h1>Books List</h1>");

        List<Book> list=BookDao.getAllBooks();

        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Id</th><th>Book</th><th>Title</th><th>Edit</th><th>Delete</th></tr>");
        for(Book e:list){
            out.print("<tr><td>"+e.getId()+"</td><td>"+e.getAuthor()+"</td><td>"+e.getTitle()+"</td><td><a href='EditServlet?id="+e.getId()+"'>edit</a></td><td><a href='DeleteServlet?id="+e.getId()+"'>delete</a></td></tr>");
        }
        out.print("</table>");

        out.close();
    }
}  