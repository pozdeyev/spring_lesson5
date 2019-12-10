package com.geekbrains.marketdemoa;

import com.geekbrains.marketdemoa.entites.Item;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketDemoAApplication {
	// Домашнее задание:
	// 1. Добавить сортировку //в процессе
	// 2. * Добавьте категории в качестве отдельной сущности
	// и опоробуйте сделать по ней фильтрацию

	public static void main(String[] args) {
		SpringApplication.run(MarketDemoAApplication.class, args);
	}
}
