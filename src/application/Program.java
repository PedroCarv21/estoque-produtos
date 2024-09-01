package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

public class Program {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		List<Product> productList = new LinkedList<>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the number of products: ");
		Integer numberProducts = Integer.parseInt(sc.nextLine());
		
		for (int i = 0; i < numberProducts; i++) {
			System.out.printf("Product #%d data:%n", i+1);
			System.out.print("Common, used or imported (c/u/i)? ");
			char typeOfProduct = sc.nextLine().toLowerCase().charAt(0);
			System.out.print("Name: ");
			String name = sc.nextLine();
			System.out.print("Price: ");
			Double price = Double.parseDouble(sc.nextLine());
			if (typeOfProduct == 'c') {
				Product commonProduct = new Product(name, price);
				productList.add(commonProduct);
			}
			else if (typeOfProduct == 'i') {
				System.out.print("Customs fee: ");
				Double customsFee = Double.parseDouble(sc.nextLine());
				Product importProduct = new ImportedProduct(name, price, customsFee);
				productList.add(importProduct);
			}
			else if (typeOfProduct == 'u') {
				System.out.print("Manufacture date (DD/MM/YYYY): ");
				String dateStr = sc.nextLine();
				LocalDate date = LocalDate.parse(dateStr, dtf);
				Product UsedProduct = new UsedProduct(name, price, date);
				productList.add(UsedProduct);
			}
		}
		System.out.printf("%nPRICE TAGS:%n");
		for (Product p: productList) {
			System.out.println(p.priceTag());
		}
		
		sc.close();
	}
}
