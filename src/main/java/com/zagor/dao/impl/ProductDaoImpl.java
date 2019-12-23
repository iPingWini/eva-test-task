package com.zagor.dao.impl;

import com.zagor.dao.ProductDao;
import com.zagor.entities.Product;
import com.zagor.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl extends JdbcDaoSupport implements ProductDao {

    @Autowired
    public ProductDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<Product> getProducts() {
        System.out.println("Repository is working");
        String sql = "select * from sites.products";
        Object[] params = new Object[] {  };
        ProductMapper mapper = new ProductMapper();
        try {
            List<Product> products = this.getJdbcTemplate().query(sql, params, mapper);
            return products;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
