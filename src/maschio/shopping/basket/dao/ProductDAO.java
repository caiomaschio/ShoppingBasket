package maschio.shopping.basket.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import maschio.shopping.basket.bean.Product;

public class ProductDAO {

	private static class Helper {
		private static final ProductDAO INSTANCE = new ProductDAO();
	}

	private ProductDAO() {
	}

	public static ProductDAO getInstance() {
		return Helper.INSTANCE;
	}

	private enum ProductData {

		APPLE(1, "Apple", new BigDecimal("0.25")), ORANGE(2, "Orange", new BigDecimal("0.30")),
		BANANA(3, "Banana", new BigDecimal("0.15")), PAPAYA(4, "Papaya", new BigDecimal("0.50"));

		private long id;
		private String name;
		private BigDecimal price;

		private ProductData(long id, String name, BigDecimal price) {
			this.id = id;
			this.name = name;
			this.price = price;
		}

		private long getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		private Product toProduct() {
			return new Product(id, name, price);
		}
	}

	public Optional<Product> get(long productId) {
		return getBy(productData -> productData.getId() == productId);
	}

	public Optional<Product> getByName(String productName) {
		return getBy(productData -> productData.getName().equals(productName));
	}

	private Optional<Product> getBy(Predicate<ProductData> condition) {
		return Arrays.stream(ProductData.values()).parallel().filter(condition::test)
				.map(productData -> productData.toProduct()).findFirst();
	}
}
