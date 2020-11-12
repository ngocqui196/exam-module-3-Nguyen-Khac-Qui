package sevice;

import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IProductService{

    private String jdbcURL = "jdbc:mysql://localhost:3306/linkkool?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Vuoncodai123";



    private static final String SELECT_ALL_PRODUCT_SQL = "select name,price,amount,color,detail,id_category from exam.product;";
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO exam.product"
            + " (name, price,amount,color,detail,id_category) VALUES " +
            " (?, ?, ?, ?, ?, ?);";

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
                String nameProduct = rs.getString("name");
                double priceProduct = rs.getDouble("price");
                int amountProduct = rs.getInt("amount");
                String colorProduct = rs.getString("color");
                String detailProduct = rs.getString("detail");
                int idCategori = rs.getInt("id_category");
                Category categori = iCategorySevice.getCategoryByName(idCategori);
                productList.add(new Product(nameProduct,priceProduct,amountProduct,colorProduct,detailProduct,categori));
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
            ps.setDouble(6, product.getPrice());
            ps.setInt(3, product.getAmount());
            ps.setString(4, product.getColor());
            ps.setString(5, product.getDetail());
            ps.setInt(6, product.getCategory().getIdCategory());
            System.out.println(ps);
            ps.executeUpdate();
        }
    }

    @Override
    public String getNameProductByID(int id) {
        return null;
    }

    @Override
    public void update(int id, Product product) {

    }

    @Override
    public void delete(int id) {

    }
}
