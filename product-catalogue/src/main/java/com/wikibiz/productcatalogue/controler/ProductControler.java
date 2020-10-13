package com.wikibiz.productcatalogue.controler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.wikibiz.productcatalogue.domain.Product;
import com.wikibiz.productcatalogue.domain.ProductDescription;
import com.wikibiz.productcatalogue.domain.ProductTag;
import com.wikibiz.productcatalogue.repo.ProductDescriptionRepo;
import com.wikibiz.productcatalogue.repo.ProductRepo;
import com.wikibiz.productcatalogue.repo.ProductTagRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/product")
public class ProductControler {

    @Autowired
    private ProductTagRepo tagRepo;

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductDescriptionRepo descRepo;

    @GetMapping("/all")
    public Slice<Product> getAllProducts(@RequestParam("page") int page, @RequestParam("size") int size) {

        return productRepo.findAll(PageRequest.of(page, size, Sort.by("name")));

    }

    @GetMapping("/id/{id}")
    public Product getById(@PathVariable("id") String id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent())
            return product.get();
        else
            return null;
    }

    @GetMapping("/supplier/all/{id}")
    public Slice<Product> findAllBySupplier(@PathVariable("id") String id, @RequestParam("page") int page,
            @RequestParam("size") int size) {

        return productRepo.findBySellerId(id, PageRequest.of(page, size, Sort.by("name")));

    }

    @GetMapping("/find/{category}/{subcategory}")
    public Slice<Product> findByCategories(@RequestParam("page") int page, @RequestParam("size") int size,
            @PathVariable("category") String category, @PathVariable("subcategory") String subcategory) {
        return productRepo.findAllByCategoryAndSubCategory(category, subcategory,
                PageRequest.of(page, size, Sort.by("name")));
    }

    @GetMapping("/desc/{id}")
    public List<ProductDescription> getProductDesc(@PathVariable("id") String id) {
        return descRepo.findAllByProductId(id);
    }

    @GetMapping("/tags/{id}")
    public List<String> findProductTag(@PathVariable("id") String id) {
        return tagRepo.findAllByProductId(id).stream().map(ProductTag::getTag).collect(Collectors.toList());
    }

    @GetMapping("/tag/{tag}")
    public List<Product> getAllWithTag(@PathVariable("tag") String tag) {
        return tagRepo.findAllByTag(tag).stream().map(ProductTag::getProductId).map(t -> productRepo.findById(t))
                .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

    }

    @GetMapping("/tag/search/{tag}")
    public List<Product> searchByTag(@PathVariable("tag") String tag) {
        return tagRepo.findAllByTagContainsIgnoreCase(tag).stream().map(ProductTag::getProductId)
                .map(t -> productRepo.findById(t)).filter(Optional::isPresent).map(Optional::get)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public String addProduct(@RequestBody Product product) {
        productRepo.save(product);
        return "success";

    }

    @PostMapping("/description")
    public String addDesc(@RequestBody ProductDescription description) {
        descRepo.save(description);

        return "success";

    }

    @PostMapping("/tag")
    public String addTag(@RequestBody ProductTag tag) {
        tagRepo.save(tag);
        return "success";
    }

    @DeleteMapping("/")
    public String deleteProduct(@RequestBody Product product) {
        if (productRepo.existsById(product.getId()))

            productRepo.delete(product);

        return "succeess";
    }

    @DeleteMapping("/tag")
    public String removeTag(@RequestBody ProductTag tag) {
        tagRepo.delete(tag);

        return "success";
    }

    @DeleteMapping("/description")
    public String removeDesc(@RequestBody ProductDescription desc) {
        descRepo.delete(desc);
        return "success";
    }

}
