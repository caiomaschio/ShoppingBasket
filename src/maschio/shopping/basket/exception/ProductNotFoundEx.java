package maschio.shopping.basket.exception;

public class ProductNotFoundEx extends RuntimeException {

	private static final long serialVersionUID = -4378368303876231665L;

	public ProductNotFoundEx(String msg) {
		super(msg);
	}

}
