package com.Products.Details_Categories_Colours;
import com.Products.Details_Categories_Colours.service.Productservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductDetailsCategoriesColoursApplication implements CommandLineRunner
{

	public static void main(String[] args)
	{
		SpringApplication.run(ProductDetailsCategoriesColoursApplication.class, args);
	}
    @Autowired
    public Productservice productservice;

	@Override
	public void run(String... args) throws Exception
	{



	}
}
