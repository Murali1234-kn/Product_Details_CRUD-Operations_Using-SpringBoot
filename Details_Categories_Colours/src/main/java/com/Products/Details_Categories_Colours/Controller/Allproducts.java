package com.Products.Details_Categories_Colours.Controller;

import com.Products.Details_Categories_Colours.service.ProductscategoryDTO;
import com.Products.Details_Categories_Colours.service.ProductscolourDTO;
import com.Products.Details_Categories_Colours.service.ProductsdetailsDTO;
import lombok.*;
import org.springframework.stereotype.Service;
@Getter
@Setter
@Data
public class Allproducts
{
    public ProductsdetailsDTO[] productsdetails;
    public ProductscategoryDTO[] productscategory;
    public ProductscolourDTO[] productscolour;

     public ProductsdetailsDTO[] getProductsdetails()
        {
            return productsdetails;
        }

        public ProductscategoryDTO[] getProductscategory()
        {
            return productscategory;
        }

        public ProductscolourDTO[] getProductscolour()
        {
            return productscolour;
        }
}
