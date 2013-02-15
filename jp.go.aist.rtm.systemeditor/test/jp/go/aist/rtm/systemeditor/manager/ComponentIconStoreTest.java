package jp.go.aist.rtm.systemeditor.manager;

import java.io.File;

import junit.framework.TestCase;

public class ComponentIconStoreTest extends TestCase {

	static String ICON_PATH;

	ComponentIconStore store;

	void setUpClass() {
		File f = new File("icons").getAbsoluteFile();
		ICON_PATH = f.getAbsolutePath();
	}

	@Override
	protected void setUp() throws Exception {
		if (ICON_PATH == null) {
			setUpClass();
		}
		store = new ComponentIconStore();
	}

	public void testParsePreference() throws Exception {
		String fon = new File(ICON_PATH + "/on.png").getAbsolutePath();
		String foff = new File(ICON_PATH + "/off.png").getAbsolutePath();

		store.parsePreference("type;T1;" + fon + "|category;C1;" + foff
				+ "|category;C2;" + foff);

		assertEquals(fon, store.type2PathMap.get("T1"));
		assertEquals(foff, store.category2PathMap.get("C1"));
		assertEquals(foff, store.category2PathMap.get("C2"));
	}

	public void testToPreference() throws Exception {
		String fon = new File(ICON_PATH + "/on.png").getAbsolutePath();
		String foff = new File(ICON_PATH + "/off.png").getAbsolutePath();

		store.type2PathMap.put("T1", fon);
		store.category2PathMap.put("C1", foff);
		store.category2PathMap.put("C2", foff);

		String act = store.toPreference();
		assertEquals("type;T1;" + fon + "|category;C1;" + foff
				+ "|category;C2;" + foff, act);
	}

}
