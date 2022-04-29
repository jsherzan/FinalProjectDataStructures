import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Final2Test {

	@Test
	void isLinkedListHaveItems() {
		Final2 obj = new Final2();
		assertEquals(obj.bookList.isEmpty(), false); 
	}

	@Test
	void isReadModelEmpty() {
		Final2 obj = new Final2();
		assertEquals(obj.readmodel.getRowCount(), 0); 
	}

}
