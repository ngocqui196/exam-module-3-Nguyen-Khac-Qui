package sevice;

import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IProductService{

    private String jdbcURL = "jdbc:mysql://localhost:3306/exam?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Vuoncodai123";

    private static final String UPDATE_PRODUCT_SQL = "update exam.product set name = ?,price= ?, amount =? where id = ?;";
    private static final String SELECT_PRODUCT_BY_ID_SQL = "select * from exam.product where id = ?";
    private static final String SELECT_ALL_PRODUCT_SQL = "select * from exam.product;";
    private static final String DELETE_PRODUCT_SQL = "delete from exam.product where id = ?;";
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO exam.product"
            + " (name, price,amount,color,detail,id_category) VALUES " +
            " (?, ?, ?, ?, ?, ?);";
    private static final String SEARCH_PRODUCT_SQL = "select * from exam.product where name like concat('%',?,'%');";

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
    public List<Product> listProduct() {
        ICategorySevice iCategorySevice = new CategorySeviceImpl();
        List<Product> productList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PRODUCT_SQL)) {
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt("id");
                String nameProduct = rs.getString("name");
                double priceProduct = rs.getDouble("price");
                int amountProduct = rs.getInt("amount");
                String colorProduct = rs.getString("color");
                String detailProduct = rs.getString("detail");
                int idCategori = rs.getInt("id_category");
                Category categori = iCategorySevice.getCategoryByName(idCategori);
                productList.add(new Product(idProduct,nameProduct,priceProduct,amountProduct,colorProduct,detailProduct,categori));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }

    @Override
    public void insertProduct(Product product) throws SQLException {
        System.out.println(INSERT_PRODUCT_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getAmount());
            ps.setString(4, product.getColor());
            ps.setString(5, product.getDetail());
            ps.setInt(6, product.getCategory().getIdCategory());
            System.out.println(ps);
            ps.executeUpdate();
        }
    }

    @Override
    public Product selectProduct(int id) {
        Product product = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID_SQL);) {

            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("id");
                String email = rs.getString("name");
                double price = Double.parseDouble(rs.getString("price"));

                product = new Product(id, name,price);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    @Override
    public List<Product> searchProduct(String name) throws SQLException {
        List<Product> productList = new ArrayList<>();
        ICategorySevice iCategorySevice = new CategorySeviceImpl();

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCT_SQL);) {
            preparedStatement.setString(1,name);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt("id");
                String nameProduct = rs.getString("name");
                double priceProduct = rs.getDouble("price");
                int amountProduct = Integer.parseInt(rs.getString("amount"));
                String colorProduct = rs.getString("color");
                String detailProduct = rs.getString("detail");
                int idCateGory = Integer.parseInt(rs.getString("id_category"));
                Category categori = iCategorySevice.getCategoryByID(idCateGory);
                productList.add(new Product(idProduct, nameProduct, priceProduct, amountProduct,colorProduct,detailProduct,categori));
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return productList;
    }


    @Override
    public boolean update(Product product) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getAmount());
            statement.setInt(4,product.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }


    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
