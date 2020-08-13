package com.example.demo.controller;

import com.example.demo.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    int quantity = 1;
    int id = 1;
    Product product = new Product("desk",10,6);

    @RequestMapping("/")
    public String index(Model model){
        List<Product> products = searchRoom();
        model.addAttribute("products",products);
        return "index";
    }

    private List<Product> searchRoom(){
        String url= "http://localhost:8080/products";
        RestTemplate restTemplate = new RestTemplate();
        List<Product> lsRoom = new ArrayList();
        lsRoom = restTemplate.getForObject(url,ArrayList.class);
        return lsRoom;
    }

    @RequestMapping("/add")
    public String add(Model model){

        String url = "http://localhost:8080/create";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject( url, product, Product.class);

        List<Product> products = searchRoom();
        model.addAttribute("products",products);
        return "index";
    }

    @RequestMapping("/sell")
    public String sell(Model model){
        String url = "http://localhost:8080/sell?quantity="+quantity+"&id="+id;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url,ArrayList.class);

        List<Product> products = searchRoom();
        model.addAttribute("products",products);
        return "index";
    }
}
