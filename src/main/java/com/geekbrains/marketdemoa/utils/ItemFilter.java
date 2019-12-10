package com.geekbrains.marketdemoa.utils;

import com.geekbrains.marketdemoa.entites.Item;
import com.geekbrains.marketdemoa.repositories.specifications.ItemSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class ItemFilter {
    private Specification<Item> spec;
    private StringBuilder filterDefinition;

    public ItemFilter(Map<String, String> map) {
        this.spec = Specification.where(null);
        this.filterDefinition = new StringBuilder();
        if (map.containsKey("min_price") && !map.get("min_price").isEmpty()) {
            int minPrice = Integer.parseInt(map.get("min_price"));
            spec = spec.and(ItemSpecifications.priceGEThan(minPrice));
            filterDefinition.append("&min_price=").append(minPrice);
        }
        if (map.containsKey("max_price") && !map.get("max_price").isEmpty()) {
            int maxPrice = Integer.parseInt(map.get("max_price"));
            spec = spec.and(ItemSpecifications.priceLEThan(maxPrice));
            filterDefinition.append("&max_price=").append(maxPrice);
        }

        //сортировка
        if (map.containsKey("sort_by") && !map.get("sort_by").isEmpty()) {
            filterDefinition.append("&sort_by=").append(map.get("sort_by"));
        }

    }
}
