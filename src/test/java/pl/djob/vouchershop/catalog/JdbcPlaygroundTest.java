package pl.djob.vouchershop.catalog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JdbcPlaygroundTest {

    public static final String PRODUCT_ID = "7f6d29c4-26b8-4331-830e-543e0f6bd61a";
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate.execute("DROP TABLE `products_catalog__products` IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE `products_catalog__products` (" +
                "`id` varchar(100) NOT NULL," +
                "`description` varchar(255)," +
                "`picture` varchar(255)," +
                "`price` DECIMAL(12,2)," +
                "PRIMARY KEY(id)" +
                ");");

    }

    @Test
    public void itCountProducts() {
        //Act
        int productCount = jdbcTemplate.queryForObject(
                "Select count(*) from `products_catalog__products`", Integer.class);
        //Assert
        assertThat(productCount).isEqualTo(0);
    }
    @Test
    public void itAllowToAddProduct() {
        jdbcTemplate.execute("INSERT INTO `products_catalog__products` (`id`, `description`, `picture`, `price`) values " +
                "('p1', 'p1 description', 'p1 picture', 9.90)," +
                "('p2', 'p2 description', 'p2 picture', 12.12)" +
                "; ");


        int productCount = jdbcTemplate.queryForObject(
                "Select count(*) from `products_catalog__products`", Integer.class);
        //Assert
        assertThat(productCount).isEqualTo(2);
    }
    @Test
    public void itAllowsToLoadProduct (){
        jdbcTemplate.execute("INSERT INTO `products_catalog__products` (`id`, `description`, `picture`, `price`) values " +
                "('7f6d29c4-26b8-4331-830e-543e0f6bd61a', 'p1 description', 'p1 picture', 9.90)," +
                "('7f6d29c4-26b8-4331-830e-543e0f6bd62b', 'p2 description', 'p2 picture', 12.12)" +
                "; ");

        var query = "Select * from `products_catalog__products` where id = ?";
                Product product = jdbcTemplate.queryForObject(
                        query,
                        new Object[]{PRODUCT_ID},
                        new ProductRowMapper()
                );
        assertThat(product.getId()).isEqualTo(PRODUCT_ID);
    }
    @Test
    public void itAllowToLoadAllProduct() {
        jdbcTemplate.execute("INSERT INTO `products_catalog__products` (`id`, `description`, `picture`, `price`) values " +
                "('7f6d29c4-26b8-4331-830e-543e0f6bd61a', 'p1 description', 'p1 picture', 9.90)," +
                "('7f6d29c4-26b8-4331-830e-543e0f6bd62b', 'p2 description', 'p2 picture', 12.12)" +
                "; ");

        var query = "Select * from `products_catalog__products`";
        List <Product> productList = jdbcTemplate.query(
                query,
                (rs, i) -> {
                    Product p = new Product(UUID.fromString(rs.getString("id")));
                    p.setDescription(rs.getString("description"));
                    p.setPicture(rs.getString("picture"));
                    return p;
                }
        );

        assertThat(productList)
                .hasSize(2)
                .extracting(Product::getId)
                .contains(PRODUCT_ID);
    }
    @Test
    public void itAllowToStoreProduct() {
        Product product = new Product(UUID.randomUUID());
        product.setDescription("nice one");

        jdbcTemplate.update("INSERT INTO `products_catalog__products` " + "(`id`, `description`, `picture`, `price`) values " + "(?,?,?,?)",
                product.getId(),
                product.getDescription(),
                product.getPicture(),
                product.getPrice()
        );

        List<Product> productList = jdbcTemplate.query(
                "select * from `products_catalog__products`",
                new ProductRowMapper()
        );

        assertThat(productList)
                .hasSize(1)
                .extracting(Product::getId)
                .contains(product.getId());
    }


    class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int i) throws SQLException {
            Product product = new Product(UUID.fromString(rs.getString("id")));
            product.setDescription(rs.getString("description"));
            product.setPicture(rs.getString("picture"));
            return product;
        }
    }


}
