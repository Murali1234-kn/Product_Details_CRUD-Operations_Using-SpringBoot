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
public class ProductsdetailsDTO
{
    @Id
    private int id;
    private String Pname;
    private int Pquantity;
    private int Punitcost;

    public int getid()
    {
        return id;
    }

    public String getPname() {
        return Pname;
    }

    public int getPquantity() {
        return Pquantity;
    }

    public int getPunitcost() {
        return Punitcost;
    }


}
