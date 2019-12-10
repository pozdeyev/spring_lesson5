package com.geekbrains.marketdemoa.controllers;

import com.geekbrains.marketdemoa.entites.Item;
import com.geekbrains.marketdemoa.repositories.specifications.ItemSpecifications;
import com.geekbrains.marketdemoa.services.ItemService;
import com.geekbrains.marketdemoa.utils.ItemFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MarketController {
    private ItemService itemService;

    @Autowired
    public MarketController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        int pageIndex = 0;
        String sort = "id"; //сортировка по умолчанию

        if (params.containsKey("sort_by") && !params.get("sort_by").isEmpty()) {
            sort = params.get("sort_by");
        }


        if (params.containsKey("p")) {
            pageIndex = Integer.parseInt(params.get("p")) - 1;
        }

        Pageable pageRequest = PageRequest.of(pageIndex, 5, Sort.Direction.ASC, sort);
        ItemFilter itemFilter = new ItemFilter(params);
        Page<Item> page = itemService.findAll(itemFilter.getSpec(), pageRequest);


        model.addAttribute("filtersDef", itemFilter.getFilterDefinition());
        model.addAttribute("page", page);
        return "index";
    }
}