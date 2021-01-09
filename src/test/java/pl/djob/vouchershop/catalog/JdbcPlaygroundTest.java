package pl.djob.vouchershop.catalog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JdbcPlaygroundTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void itCountProducts() {
        //Arrange
        jdbcTemplate.execute("DROP TABLE `products_catalog__products` IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE `products_catalog__products` (" +
                "`id` varchar(100) NOT NULL," +
                "`description` varchar(255)," +
                "`picture` varchar(255)," +
                "`price` DECIMAL(12,2)," +
                "PRIMARY KEY(id)" +
                ");");
        //Product table exists
        //Act
        int productCount = jdbcTemplate.queryForObject(
                "Select count(*) from `products_catalog__products`", Integer.class);
        //Assert
        assertThat(productCount).isEqualTo(0);
    }
}
