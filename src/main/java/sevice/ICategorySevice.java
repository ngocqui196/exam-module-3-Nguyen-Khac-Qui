package sevice;

import model.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategorySevice {
    List<Category> listCategori();
    Category getCategoryByID(int id);
    Category getCategoryByName(int id);
    void save(Category category);
    boolean update(Category category);
    boolean delete(int id) throws SQLException;
}
