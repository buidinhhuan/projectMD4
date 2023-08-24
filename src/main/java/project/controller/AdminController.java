package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.dto.ProductDto;
import project.model.Catalog;
import project.model.Product;
import project.service.impl.CatalogService;
import project.service.impl.ProductService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PropertySource("classpath:update.properties")
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CatalogService  catalogService;
    @Value("${upload-path}")
    private String pathUpload;

    @GetMapping()
    public ModelAndView listProduct() {
        List<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("admin/listProduct", "products", products);
        return modelAndView;
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        // Xóa sản phẩm dựa trên ID
        productService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("admin/editProduct", "product", product);
        return modelAndView;
    }
    @PostMapping("/edit")
    public String update(@ModelAttribute("product") ProductDto productDto) {
        // Xóa sản phẩm dựa trên ID
        File file =new File(pathUpload);
        if(!file.exists()){
            // chưa tồn tại folder , khởi tạo 1 folder mới
            file.mkdirs();
        }
        String fileName = productDto.getImg_url().getOriginalFilename();
        try {
            FileCopyUtils.copy(productDto.getImg_url().getBytes(),new File(pathUpload+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // chuyen doi thanh doi tuong video
        Product newProduct= new Product();
        newProduct.setId(productDto.getId());
        newProduct.setImg_url(fileName);
        newProduct.setName(productDto.getName());
        newProduct.setCatalog_id(productDto.getCatalog_id());
        newProduct.setPrice(productDto.getPrice());
        newProduct.setStock(productDto.getStock());
        newProduct.setDescription(productDto.getDescription());
        newProduct.setStatus(productDto.isStatus());
        productService.save(newProduct);
        return "redirect:/admin";
    }

    @GetMapping("/add")
    public ModelAndView upload() {
        return new ModelAndView("admin/addProduct","product",new ProductDto());
    }
    @PostMapping("/add")
    public  String doUpload(@ModelAttribute("product") ProductDto productDto){
        // upload file
        File file =new File(pathUpload);
        if(!file.exists()){
            // chưa tồn tại folder , khởi tạo 1 folder mới
            file.mkdirs();
        }
        String fileName = productDto.getImg_url().getOriginalFilename();
        try {
            FileCopyUtils.copy(productDto.getImg_url().getBytes(),new File(pathUpload+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // chuyen doi thanh doi tuong video
        Product newProduct= new Product();
        newProduct.setImg_url(fileName);
        newProduct.setName(productDto.getName());
        newProduct.setCatalog_id(productDto.getCatalog_id());
        newProduct.setPrice(productDto.getPrice());
        newProduct.setStock(productDto.getStock());
        newProduct.setDescription(productDto.getDescription());
        newProduct.setStatus(productDto.isStatus());
        productService.save(newProduct);
        return "redirect:/admin";
    }



//    ********************  Phần quản lý danh mục  ******************

    @GetMapping("/catalog")
    public ModelAndView listCatalog() {
        List<Catalog> catalogs = catalogService.findAll();
        ModelAndView modelAndView = new ModelAndView("admin/ctl/catalog", "catalogs", catalogs);
        return modelAndView;
    }

    @GetMapping("/add_catalog")
    public ModelAndView add() {
        // Tạo ModelAndView để hiển thị form thêm mới trong view "/admin/ctl/add_catalog"
        ModelAndView modelAndView = new ModelAndView("/admin/ctl/add_catalog" ,"catalog" ,new Catalog());
        return modelAndView;
    }

    @PostMapping("/add_catalog")
    public String add(@ModelAttribute("catalog") Catalog catalog) {
        // Lưu thông tin công việc mới
        catalogService.save(catalog);
        // Chuyển hướng về trang danh sách công việc
        return "redirect:/admin/catalog";
    }
    @GetMapping("/edit_catalog/{id}")
    public ModelAndView edit_catalog(@PathVariable("id") int id) {
        // Lấy công việc cần chỉnh sửa dựa trên ID
        Catalog catalogEdit = catalogService.findById(id);
        // Tạo ModelAndView để hiển thị form chỉnh sửa trong view "/admin/ctl/add_catalog"
        ModelAndView modelAndView = new ModelAndView("/admin/ctl/edit_catalog", "catalog", catalogEdit);
        return modelAndView;
    }
    @PostMapping("/edit_catalog")
    public String update_catalog(@ModelAttribute("catalog") Catalog catalog) {
        // Lưu thông tin công việc đã cập nhật
        catalogService.save(catalog);
        // Chuyển hướng về trang danh sách công việc
        return "redirect:/admin/catalog";
    }

}