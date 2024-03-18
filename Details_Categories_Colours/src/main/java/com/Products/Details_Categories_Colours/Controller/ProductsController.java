package com.Products.Details_Categories_Colours.Controller;

import com.Products.Details_Categories_Colours.service.ProductscategoryDTO;
import com.Products.Details_Categories_Colours.service.ProductscolourDTO;
import com.Products.Details_Categories_Colours.service.ProductsdetailsDTO;
import com.Products.Details_Categories_Colours.service.Productservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
public class ProductsController
{

    @Autowired
    public  Productservice productservice;

    @PostMapping("/products")
    public ResponseEntity<String> insertData(@RequestBody Allproducts allproducts)
    {
        try
        {
            String response = productservice.insertdetails(allproducts.getProductsdetails());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.ok("Error inserting data.");
        }
    }
    @PostMapping("/category")
    public ResponseEntity<String> insertdata1(@RequestBody Allproducts allproducts)
    {
        try
        {
            String response  = productservice.insertcategory(allproducts.getProductscategory());
            return ResponseEntity.ok(response);
        }
        catch (Exception e)
        {
            return ResponseEntity.ok("error inserting data");
        }
    }
    @PostMapping("/colour")
    public ResponseEntity<String> insertdata2(@RequestBody Allproducts allproducts)
    {
        try
        {
            String response = productservice.insertcolour(allproducts.getProductscolour());
            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            return ResponseEntity.ok("error inserting data");
        }
    }

        @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProductDetails(@PathVariable int id, @RequestBody ProductsdetailsDTO productDetailsDTO)
    {
        try
        {
            String resultMessage = productservice.updateProductDetails(id, productDetailsDTO);
            return ResponseEntity.ok(resultMessage);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating details.");
        }

    }

    @PutMapping("/category/{cid}")
    public ResponseEntity<String> updateProductCategory(@PathVariable int cid, @RequestBody ProductscategoryDTO productscategoryDTO) {
        try {
            String resultMessage = productservice.updateProductCategory(cid, productscategoryDTO);
            return ResponseEntity.ok(resultMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating category.");
        }
    }

    @PutMapping("/colour/{rid}")
    public ResponseEntity<String> updateProductColour(@PathVariable int rid, @RequestBody ProductscolourDTO productscolourDTO) {
        try {
            String resultMessage = productservice.updateProductColour(rid, productscolourDTO);
            return ResponseEntity.ok(resultMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating colour.");
        }
    }
/////////////////////////////////////;
@GetMapping("/products/{id}")
public ResponseEntity<?> getProductDetails(@PathVariable int id) {
    try {
        ProductsdetailsDTO productsdetailsDTO = productservice.getProductDetails(id);

        if (productsdetailsDTO != null)
        {
            return ResponseEntity.ok(productsdetailsDTO);
        }
        else {
            Responseerror errorResponse = new Responseerror("Product details with the given " +id+ "ID not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    } catch (Exception e) {
        e.printStackTrace();
        Responseerror errorResponse = new Responseerror("Error retrieving product details.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProductDetails(@PathVariable int id) {
        try {
            String resultMessage = productservice.deleteProductDetails(id);
            return ResponseEntity.ok(resultMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting product details.");
        }
    }

//////////////////////////////////////////////////////////////////////;

    @GetMapping("/category/{cid}")
    public ResponseEntity<?> getProductCategory(@PathVariable int cid) {
        try {
            ProductscategoryDTO productscategoryDTO = productservice.getProductCategoryByCid(cid);
            if (productscategoryDTO != null) {
                return ResponseEntity.ok(productscategoryDTO);
            }
            else {
                Responseerror errorResponse = new Responseerror("Product category with the given " +cid+ "ID not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            Responseerror errorResponse = new Responseerror("Error retrieving product category.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

        @DeleteMapping("/category/{cid}")
        public ResponseEntity<String> deleteProductCategory(@PathVariable int cid) {
            try {
                String resultMessage = productservice.deleteProductCategory(cid);
                if (resultMessage.equals("Deleted")) {
                    return ResponseEntity.ok("Product category deleted successfully.");
                } else {
                    return ResponseEntity.status(HttpStatus.FOUND).body("deleting "+cid+ "Not in the product category.");
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error");
            }
        }
///////////////////////////////////////////////////////////;
     @GetMapping("/colour/{rid}")
      public ResponseEntity<?> getProductColour(@PathVariable int rid) {
    try {
        ProductscolourDTO productscolourDTO = productservice.getProductColourByRid(rid);
        if (productscolourDTO != null) {
            return ResponseEntity.ok(productscolourDTO);
        }
        else
        {
            return ResponseEntity.ok("id not found in table");
              }
        }
            catch (Exception e)
         {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          }
       }

        @DeleteMapping("/colour/{rid}")
        public ResponseEntity<String> deleteProductColour(@PathVariable int rid) {
            try {
                String resultMessage = productservice.deleteProductColour(rid);
                if (resultMessage.equals("Deleted")) {
                    return ResponseEntity.ok("Product colour deleted successfully.");
                } else {
                    return ResponseEntity.status(HttpStatus.FOUND).body("Deleting "+rid+" not in the  product colour.");
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error deleting product colour.");
            }
        }
    }



