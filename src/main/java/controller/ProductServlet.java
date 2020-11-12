package controller;

import model.Category;
import model.Product;
import sevice.CategorySeviceImpl;
import sevice.ICategorySevice;
import sevice.IProductService;
import sevice.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@javax.servlet.annotation.WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends javax.servlet.http.HttpServlet {
    ICategorySevice iCategorySevice = new CategorySeviceImpl();
    IProductService iProductService = new ProductServiceImpl();
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String acction = request.getParameter("action");
        if (acction == null) acction = "";
        try {
            switch (acction) {
                case "create":
                    insertProduct(request,response);
                    break;
                case "update":
                    break;
                default:
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showCreateProduct(request,response);
                    break;
                case "edit":
//                showEditForm(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "sort":
//                    sortListProduct(request,response);
                    break;
                case "categoriList":
//                showListCategory(request,response);
                default:
                    listProduct(request, response);
                    break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException , ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            iProductService.delete(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        listProduct(request,response);
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = iProductService.listProduct();
        System.out.println(productList);
        request.setAttribute("listProducts", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("create.jsp");
        dispatcher.forward(request, response);
    }

//    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Product newProduct =
//        User existingUser = userDAO.selectUser(id);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
//        request.setAttribute("user", existingUser);
//        dispatcher.forward(request, response);
//    }
    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        String nameProduct = request.getParameter("name");
        double priceProduct = Double.parseDouble(request.getParameter("price"));
        int amountProduct = Integer.parseInt(request.getParameter("amount"));
        String colorProduct = request.getParameter("color");
        String detailProduct = request.getParameter("detail");
        int idCategory = Integer.parseInt(request.getParameter("id_category"));
        Category category = iCategorySevice.getCategoryByID(idCategory);

        Product product = new Product(nameProduct,priceProduct,amountProduct,colorProduct,detailProduct,category);
        iProductService.insertProduct(product);
        listProduct(request,response);
    }

}
