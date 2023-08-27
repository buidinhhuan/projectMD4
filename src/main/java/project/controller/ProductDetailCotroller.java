package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import project.model.Catalog;
import project.model.Product;
import project.service.impl.CatalogService;
import project.service.impl.ProductService;
import java.util.List;
@Controller
@RequestMapping("/productDetail")
public class ProductDetailCotroller {
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private ProductService productService;
    @GetMapping("/eye/{id}")
    public String eye(@PathVariable("id") int id, Model model) {
        Product product = productService.findById(id);
         model.addAttribute("product",product);
        return "productDetail";
    }
  }
