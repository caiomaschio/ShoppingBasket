package maschio.shopping.basket.controller;

import java.math.BigDecimal;

import maschio.shopping.basket.bean.Product;
import maschio.shopping.basket.dao.ProductDAO;
import maschio.shopping.basket.exception.ProductNotFoundEx;
import maschio.shopping.basket.exception.ReceiptGenerationEx;

public class BasketController {

	private static class Helper {
		private static final BasketController INSTANCE = new BasketController();
	}

	private BasketController() {
	}

	public static BasketController getInstance() {
		return Helper.INSTANCE;
	}

	public String generateReceipt(String[] items) throws ReceiptGenerationEx {
		try {
			StringBuilder receiptText = new StringBuilder();
			BigDecimal total = new BigDecimal("0");

			int amountPapayas = 0;
			Product PAPAYA = ProductDAO.getInstance().getByName("Papaya").get();

			for (String itemName : items) {
				Product currentProduct = ProductDAO.getInstance().getByName(itemName)
						.orElseThrow(() -> new ProductNotFoundEx("Product: '" + itemName
								+ "' is not registered in the database! Please contact the sales assistant."));

				addProductToReceipt(currentProduct, receiptText);
				total = total.add(currentProduct.getPrice());

				if (currentProduct.getId() == PAPAYA.getId()) {
					amountPapayas++;
					if (amountPapayas == 3) {
						addDiscountToReceipt("Discount 3 for 2", PAPAYA.getPrice(), receiptText);
						total = total.subtract(PAPAYA.getPrice());
						amountPapayas = 0;
					}
				}
			}

			receiptText.append("\nTotal").append("\t\t\t").append(total);
			return receiptText.toString();
		} catch (ProductNotFoundEx e) {
			throw new ReceiptGenerationEx(e.getMessage());
		}
	}

	private void addDiscountToReceipt(String discountName, BigDecimal price, StringBuilder receiptText) {
		receiptText.append(discountName).append("\t-").append(price).append("\n");
	}

	private void addProductToReceipt(Product currentProduct, StringBuilder receiptText) {
		receiptText.append(currentProduct.getName()).append("\t\t\t").append(currentProduct.getPrice()).append("\n");
	}

}
