package maschio.shopping.basket;

import maschio.shopping.basket.controller.BasketController;
import maschio.shopping.basket.exception.ReceiptGenerationEx;

public class Shopping {

	public static void main(String[] items) {
		try {
			System.out.print(BasketController.getInstance().generateReceipt(items));
		} catch (ReceiptGenerationEx e) {
			System.err.print(e.getMessage());
		}
	}

}
