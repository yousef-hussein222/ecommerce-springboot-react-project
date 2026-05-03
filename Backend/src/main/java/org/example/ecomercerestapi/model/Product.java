package org.example.ecomercerestapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String  name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    //@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy") // to make json library handle returned object date in the following format
    private Date releaseDate;
    private Boolean productAvailable;
    private Integer stockQuantity;

    // to add image
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;

}
