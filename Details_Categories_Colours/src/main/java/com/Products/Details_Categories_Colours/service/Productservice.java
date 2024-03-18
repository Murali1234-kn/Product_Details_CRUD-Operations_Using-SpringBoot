package com.Products.Details_Categories_Colours.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Types;

@Service
public class Productservice
{
    @Autowired
    public JdbcTemplate jdbcTemplate;
    public String insertdetails(ProductsdetailsDTO[] productsdetails)
    {
        StringBuilder response = new StringBuilder();

        for (int i = 0; i < productsdetails.length; i++)
        {
            String message = insertProduct(productsdetails[i]);
            response.append(message).append("\n");
        }

        return response.toString();
    }
    private String insertProduct(ProductsdetailsDTO detailsDTO) {
        try {
            jdbcTemplate.update(
                    "CALL pro_productdetail_1234(?, ?, ?, ?, @p_out)",
                    detailsDTO.getid(),
                    detailsDTO.getPname(),
                    detailsDTO.getPquantity(),
                    detailsDTO.getPunitcost());

            String message = jdbcTemplate.queryForObject("SELECT @p_out", String.class);

            return message;
        } catch (Exception e)
        {
            return "Error inserting product with ID " + detailsDTO.getid() + ".";
        }
    }

    public String insertcategory(ProductscategoryDTO[] productscategory )
    {
        StringBuilder response = new StringBuilder();

        for (int i = 0; i < productscategory.length; i++)
        {
            String message = insert(productscategory[i]);
            response.append(message).append("\n");
        }

        return response.toString();
    }

       public String insert(ProductscategoryDTO categoryDTO)
    {
        try
        {
            jdbcTemplate.update(
                    "CALL pro_categorys_1239(?, ?, @p_out)",
                    categoryDTO.getcid(),
                    categoryDTO.getPcategory());

            String message = jdbcTemplate.queryForObject("SELECT @p_out", String.class);
            return message;
        }
        catch (Exception e)
        {
            return "Error inserting product with ID " + categoryDTO.getcid() + ".";
        }
    }
    public String insertcolour(ProductscolourDTO[] productscolour )
    {
        StringBuilder response = new StringBuilder();

        for (ProductscolourDTO colour : productscolour)
        {
            String message = inserted(colour);

            response.append(message).append("\n");
        }

        return response.toString();
    }
    public String inserted(ProductscolourDTO colourDTO)
    {
        try
        {
            jdbcTemplate.update(
                    "CALL pro_colour_129(?, ?, @p_out)",
                    colourDTO.getrid(),
                    colourDTO.getPcolour());

            String message = jdbcTemplate.queryForObject("SELECT @p_out", String.class);

            return message;
        }
        catch (Exception e)
        {
            return "Error inserting product with ID " + colourDTO.getrid() + ".";
        }
    }
    public String updateProductDetails(int id, ProductsdetailsDTO productDetailsDTO)
    {

        String procedureCall = "CALL pro_Updateddetail_565(?, ?, ?, ?, ?)";

        try
        {
            String result = jdbcTemplate.execute((ConnectionCallback<String>) connection ->
            {
                try (CallableStatement cs = connection.prepareCall(procedureCall))
                {
                    cs.setInt(1, id);
                    cs.setString(2, productDetailsDTO.getPname());
                    cs.setInt(3, productDetailsDTO.getPquantity());
                    cs.setInt(4, productDetailsDTO.getPunitcost());
                    cs.registerOutParameter(5, Types.VARCHAR);
                    cs.execute();

                    return cs.getString(5);
                }
            });

            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Error handling JSON response: " + e.getMessage();
        }
    }
    public String updateProductCategory(int cid, ProductscategoryDTO productscategoryDTO) {
        String procedureCall = "{CALL pro_category_56(?, ?, ?)}";

        try {
            String result = jdbcTemplate.execute((ConnectionCallback<String>) connection ->
            {
                try (CallableStatement cs = connection.prepareCall(procedureCall))
                {
                    cs.setInt(1, cid);
                    cs.setString(2, productscategoryDTO.getPcategory());
                    cs.registerOutParameter(3, Types.VARCHAR);
                    cs.execute();
                    return cs.getString(3);
                }});

            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Error handling response: " + e.getMessage();
        }
    }
    public String updateProductColour(int rid, ProductscolourDTO productscolourDTO)
    {
        String procedureCall = "{CALL pro_colour_567(?, ?, ?)}";

        try {
            String result = jdbcTemplate.execute((ConnectionCallback<String>) connection -> {
                try (CallableStatement cs = connection.prepareCall(procedureCall)) {
                    cs.setInt(1, rid);
                    cs.setString(2, productscolourDTO.getPcolour());
                    cs.registerOutParameter(3, Types.VARCHAR);
                    cs.execute();

                    return cs.getString(3);
                }
            });
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error handling response: " + e.getMessage();
        }
    }
    public  ProductsdetailsDTO getProductDetails(int id)
    {
        String query = "SELECT * FROM productdetai222 WHERE id = ?";

        try
        {
            return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(ProductsdetailsDTO.class), id);
            //creates a BeanPropertyRowMapper instance for the ProductsdetailsDTO class
            // It maps the columns from the query result to the properties of the DTO object
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public String deleteProductDetails(int id)
    {
        String deleteQuery = "DELETE FROM productdetai222 WHERE id = ?";

        try {
            int a = jdbcTemplate.update(deleteQuery, id);
            if (a > 0)
            {
                return "Successfully deleted details with ID: " + id;
            }
            else
            {
                return "No details found with ID: " + id;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Error deleting details: " + e.getMessage();
        }
    }
    public ProductscategoryDTO getProductCategoryByCid(int cid)
    {
        String query = "SELECT *  FROM productcategory33 WHERE cid = ?";
        try
        {
            return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(ProductscategoryDTO.class), cid);
        //creates a BeanPropertyRowMapper instance for the ProductscolourDTO class
            // It maps the columns from the query result to the properties of the DTO object
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public String deleteProductCategory(int cid)
    {
        String query = "DELETE FROM productcategory33 WHERE cid = ?";
        try {
            int b = jdbcTemplate.update(query, cid);
            if (b > 0)
            {
                return "Deleted";
            }
            else
            {
                return "Error";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Error deleting cattegory";
        }
    }
    public  ProductscolourDTO getProductColourByRid(int rid)
    {
        String query = "SELECT * FROM productcolour33 WHERE rid = ?";
        try
        {
            return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(ProductscolourDTO.class), rid);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public String deleteProductColour(int rid)
    {
        String query = "DELETE FROM productcolour33 WHERE rid = ?";
        try {
            int c = jdbcTemplate.update(query, rid);
            if (c > 0)
            {
                return "Deleted";
            } else
            {
                return "Error";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

}
