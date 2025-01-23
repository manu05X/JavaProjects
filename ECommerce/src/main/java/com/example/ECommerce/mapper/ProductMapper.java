package com.example.ECommerce.mapper;

import com.example.ECommerce.dto.GenericProductDto;
import com.example.ECommerce.thirdPartyClient.FakeStoreDto.FakeStoreProductDTO;
import com.example.ECommerce.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    /**
     * Maps a FakeStoreProductDTO to a Product entity.
     *
     * @param dto the FakeStoreProductDTO object to map
     * @return a Product entity
     */
    public Product mapDTOToProduct(FakeStoreProductDTO dto) {
        if(dto == null){
            return null;
        }

        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setCategory(dto.getCategory());
        product.setImage(dto.getImage());

        // Map the rating fields
        Product.Rating rating = new Product.Rating();
        if (dto.getRating() != null) {
            rating.setRate(dto.getRating().getRate());
            rating.setCount(dto.getRating().getCount());
        }
        product.setRating(rating);

        return product;
    }

    /**
     * Maps a Product entity to a FakeStoreProductDTO.
     *
     * @param product the Product entity to map
     * @return a FakeStoreProductDTO object
     */
    public FakeStoreProductDTO mapProductToDTO(Product product) {
        FakeStoreProductDTO dto = new FakeStoreProductDTO();
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice()); // Convert Long to Double
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory());
        dto.setImage(product.getImage());

        // Map the rating fields
        FakeStoreProductDTO.RatingDTO ratingDTO = new FakeStoreProductDTO.RatingDTO();
        if (product.getRating() != null) {
            ratingDTO.setRate(product.getRating().getRate());
            ratingDTO.setCount(product.getRating().getCount());
        }
        dto.setRating(ratingDTO);

        return dto;
    }


    public GenericProductDto mapProductToGenericDTO(Product product) {
        GenericProductDto dto = new GenericProductDto();
        dto.setTitle(product.getTitle());
        //dto.setPrice(product.getPrice()); // Convert Long to Double
        dto.setDescription(product.getDescription());
        //dto.setCategory(product.getCategory());
        dto.setImage(product.getImage());

        // Map the rating fields
        FakeStoreProductDTO.RatingDTO ratingDTO = new FakeStoreProductDTO.RatingDTO();
        if (product.getRating() != null) {
            ratingDTO.setRate(product.getRating().getRate());
            ratingDTO.setCount(product.getRating().getCount());
        }
        dto.setRating(ratingDTO);

        return dto;
    }


}
