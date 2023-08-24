package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.dto.FormLoginDto;
import project.dto.FormRegisterDto;
import project.model.Product;
import project.model.User;
import project.service.impl.ProductService;
import project.service.impl.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
        @Autowired
    private UserService userService;
        @Autowired
        private ProductService productService;
    @GetMapping("/")
    public ModelAndView displayProduct() {
        List<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("home", "products", products);
        return modelAndView;
    }
    @GetMapping("/form-login")
    public ModelAndView login(){
        return new ModelAndView("login","login_form",new FormLoginDto());
    }
    @PostMapping("/handle-login")
    public String handleLogin(HttpSession session, @ModelAttribute("login_form") FormLoginDto formLoginDto){
        // checkk validate
         // tao mois user
        User user = userService.login(formLoginDto);
        if(user == null){
            return "redirect:/form-login";
        }
        session.setAttribute("userlogin",user);
        return "redirect:/";
    }
   @GetMapping("/logout")
   public String logout(HttpSession session){
       session.removeAttribute("userlogin");
       return "redirect:/";
   }
    @GetMapping("/form-register")
    public ModelAndView register(){
        return new ModelAndView("register","register_form",new FormRegisterDto());
    }
    @PostMapping("/handle-register")
    public String handleRegister(HttpSession session, @ModelAttribute("register_form") FormRegisterDto formRegisterDto){
        // checkk validate
        // tao mois user

        User user = userService.register(formRegisterDto);
        if(user==null){
            userService.save(formRegisterDto);
            System.out.println("Bạn đã tạo tài khoản thành công");
            return "redirect:/form-login";
        }
            System.err.println("tài khoản đã tồn tại");
            // lỗi
            return "redirect:/form-register";

    }

    @GetMapping({"productPage"})
    public ModelAndView productPage() {
        List<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("productPage", "products", products);
        return modelAndView;
    }

    @GetMapping({"productDetail"})
    public String productDetail() {
        return "productDetail";
    }

    @GetMapping({"contact"})
    public String contact() {
        return "contact";
    }
    @GetMapping({"reset"})
    public String reset() {
        return "reset";
    }

    @GetMapping({"forgot"})
    public String forgot() {
        return "forgot";
    }

    @GetMapping({"history"})
    public String history() {
        return "history";
    }

    @GetMapping({"cart"})
    public String cart() {
        return "cart";
    }
}