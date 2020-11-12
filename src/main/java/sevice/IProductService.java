package sevice;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductService {
    List<Product> listProduct();
    public void insertProduct(Product product) throws SQLException;
    public Product selectProduct(int id);
    public List<Product> searchProduct(String name) throws SQLException;
    boolean update(Product product) throws SQLException;
    boolean delete(int id) throws SQLException;
}
