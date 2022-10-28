import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class TestPurchasePlanner {

	@Test
	void testItem() {
		Item coldStrawberry = new Item("strawberry", true);
		Item regularStrawberry = new Item("strawberry", false);
		Item blueberry = new Item("blueberry", false);

		assertFalse(coldStrawberry.equals(regularStrawberry));
		assertFalse(blueberry.equals(coldStrawberry));

		assertEquals("strawberry", coldStrawberry.getDescription());
		assertEquals(true, coldStrawberry.isRefrigerated());
	}

	@Test
	void testItemOrder() {
		Item coldStrawberry = new Item("strawberry", true);
		int qtty = 20;

		ItemOrder order = new ItemOrder(coldStrawberry, qtty);

		assertEquals(coldStrawberry, order.getItem());
		assertEquals(qtty, order.getQuantity());
	}

	@Test
	void testSupermarket() {
		int defaultPrice = 3;
		Supermarket market = new Supermarket("Aldee", defaultPrice);
		Item coldStrawberry = new Item("strawberry", true);

		// all items are the default price, so there is no cheapest item
		assertEquals(null, market.cheapestItem());
		assertEquals(defaultPrice, market.priceOf(coldStrawberry));

		int newPrice = 1;
		market.setPriceOf(coldStrawberry, newPrice);
		assertEquals(newPrice, market.priceOf(coldStrawberry));

		// now we have a cheapest item
		assertEquals(coldStrawberry, market.cheapestItem());
	}

	@Test
	void testPurchasePlanner() {
		Supermarket market1 = new Supermarket("Aldee", 1);
		Supermarket market2 = new Supermarket("LOLDL", 2);

		Item coldStrawberry = new Item("strawberry", true);
		int qtty = 20;

		ItemOrder order = new ItemOrder(coldStrawberry, qtty);

		PurchasePlanner plan = new PurchasePlanner();
		plan.addToOrder(order);

		assertEquals(qtty, plan.priceInMarket(market1));
		assertEquals(2 * qtty, plan.priceInMarket(market2));

		plan.removeFromOrder(coldStrawberry, 1);
		assertEquals(qtty - 1, plan.priceInMarket(market1));

		plan.removeFromOrder(order);
		assertEquals(0, plan.priceInMarket(market1));

	}

}