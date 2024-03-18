package com.Products.Details_Categories_Colours.Repository;

import com.Products.Details_Categories_Colours.service.ProductsdetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsdetailsDTO,Integer>
{


}
