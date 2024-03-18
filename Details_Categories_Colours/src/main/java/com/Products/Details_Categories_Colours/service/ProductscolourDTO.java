package com.Products.Details_Categories_Colours.service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
public class ProductscolourDTO
{
    @Id
    private int rid;
    private String Pcolour;

    public int getrid()
    {
        return rid;
    }

    public String getPcolour()
    {
       return Pcolour;
    }

}
