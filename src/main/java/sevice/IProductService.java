package sevice;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductService {
    List<Product> listProduct();
    public void insertProduct(Product product) throws SQLException;
    String getNameProductByID(int id);
    void update(int id, Product product);
    void delete(int id);
}
