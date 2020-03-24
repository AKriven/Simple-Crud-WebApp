import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static int save(Book e) { //решение проблемы с ClassNotFoundExeption - помещение jar в папку web->WEB-INF->lib(если нет, создать)
        int status = 0;
        try {
            Connection con = BookDao.getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("insert into books (author,title) values (?,?)");
            ps.setString(1, e.getAuthor());
            ps.setString(2, e.getTitle());


            status = ps.executeUpdate();

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }
    public static List<Book> getAllBooks(){
        List<Book> list=new ArrayList<Book>();

        try{
            Connection con=BookDao.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from books");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Book e=new Book();
                e.setId(rs.getInt(1));
                e.setAuthor(rs.getString(2));
                e.setTitle(rs.getString(3));
                list.add(e);
            }
            con.close();
        }catch(Exception e){e.printStackTrace();}

        return list;
    }
}
