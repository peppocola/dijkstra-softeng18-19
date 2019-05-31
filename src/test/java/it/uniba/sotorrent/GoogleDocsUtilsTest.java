package it.uniba.sotorrent;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class GoogleDocsUtilsTest {
	final GoogleDocsUtils ut = new GoogleDocsUtils();
	@Test
	void testcreateSheet() {
		try {
			assertNotNull(ut.createSheet("Result"));
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}


}
