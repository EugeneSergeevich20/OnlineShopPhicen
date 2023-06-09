package com.example.onlineshopphicen.services.productService;

import com.example.onlineshopphicen.model.ImageProduct;
import com.example.onlineshopphicen.model.Product;
import com.example.onlineshopphicen.repositories.productRepository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Transactional
    public void save(List<MultipartFile> files, Product product) throws IOException {
        for (MultipartFile file : files) {
            ImageProduct image = ImageProduct
                    .builder()
                    .name(StringUtils.cleanPath(file.getOriginalFilename()))
                    .contentType(file.getContentType())
                    .data(file.getBytes())
                    .size(file.getSize())
                    .product(product)
                    .build();

            imageRepository.save(image);
        }
    }

    public List<ImageProduct> getImages(Long id){
        return imageRepository.findImageProductByProductId(id);
    }

}
