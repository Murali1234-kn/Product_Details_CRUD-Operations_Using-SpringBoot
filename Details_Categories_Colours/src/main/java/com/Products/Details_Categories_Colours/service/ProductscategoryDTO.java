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
public class ProductscategoryDTO
{
    @Id
    private int cid;
    private String Pcategory;

    public int getcid()
    {
        return cid;
    }

    public String getPcategory()
    {
        return Pcategory;
    }

}
