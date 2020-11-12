package sevice;

import com.mysql.cj.CacheAdapter;
import model.Category;

import java.sql.*;
import java.util.List;

public class CategorySeviceImpl implements ICategorySevice{

    private String jdbcURL = "jdbc:mysql://localhost:3306/exam?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Vuoncodai123";

    private static final String SELECT_CATEGORY_BY_ID = "select * from exam.category WHERE id_category = ?;";
    private static final String SELECT_CATEGORY_BY_NAME = "select * from exam.category WHERE name_category = ?;";


    public CategorySeviceImpl() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Category> listCategori() {
        return null;
    }

    @Override
    public Category getCategoryByID(int id) {
        Category category = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID)) {
            preparedStatement.setInt(1,id);

            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()) {
                int idCategoti = rs.getInt("id_category");
                String nameCategoti = rs.getString("name_category");

                category =new Category(idCategoti,nameCategoti);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return category;
    }

    @Override
    public Category getCategoryByName(int id) {
        Category category = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_NAME)) {
            preparedStatement.setInt(1,id);

            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()) {
                int idCategoti = rs.getInt("id_category");
                String nameCategoti = rs.getString("name_category");

                category =new Category(idCategoti,nameCategoti);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return category;
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public boolean update(Category category) {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
