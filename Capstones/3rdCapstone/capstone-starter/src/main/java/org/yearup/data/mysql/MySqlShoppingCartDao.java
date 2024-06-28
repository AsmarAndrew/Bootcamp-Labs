package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import java.util.List;

@Repository
public class MySqlShoppingCartDao implements ShoppingCartDao {

    private final JdbcTemplate jdbcTemplate;
    private final ProductDao productDao;

    @Autowired
    public MySqlShoppingCartDao(JdbcTemplate jdbcTemplate, ProductDao productDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.productDao = productDao;
    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        ShoppingCart cart = new ShoppingCart();

        String sql = "SELECT * FROM shopping_cart WHERE user_id = ?";
        List<ShoppingCartItem> items = jdbcTemplate.query(sql, (rs, rowNum) -> {
            int productId = rs.getInt("product_id");
            Product product = productDao.getById(productId);

            ShoppingCartItem item = new ShoppingCartItem();
            item.setProduct(product);
            item.setQuantity(rs.getInt("quantity"));

            return item;
        }, userId);

        for (ShoppingCartItem item : items) {
            cart.add(item);
        }

        return cart;
    }

    @Override
    public void addProduct(int userId, int productId) {
        String checkSql = "SELECT COUNT(*) FROM shopping_cart WHERE user_id = ? AND product_id = ?";
        int count = jdbcTemplate.queryForObject(checkSql, new Object[]{userId, productId}, Integer.class);

        if (count == 0) {
            String insertSql = "INSERT INTO shopping_cart (user_id, product_id, quantity) VALUES (?, ?, 1)";
            jdbcTemplate.update(insertSql, userId, productId);
        } else {
            String updateSql = "UPDATE shopping_cart SET quantity = quantity + 1 WHERE user_id = ? AND product_id = ?";
            jdbcTemplate.update(updateSql, userId, productId);
        }
    }

    @Override
    public void updateQuantity(int userId, int productId, int quantity) {
        String sql = "UPDATE shopping_cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
        jdbcTemplate.update(sql, quantity, userId, productId);
    }

    @Override
    public void clearCart(int userId) {
        String sql = "DELETE FROM shopping_cart WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }
}
