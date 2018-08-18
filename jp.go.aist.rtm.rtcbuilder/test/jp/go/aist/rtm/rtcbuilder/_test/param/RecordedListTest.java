package jp.go.aist.rtm.rtcbuilder._test.param;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.generator.param.RecordedList;
import jp.go.aist.rtm.rtcbuilder.generator.param.UpdateRecordable;
import junit.framework.TestCase;

public class RecordedListTest extends TestCase {

	public void testAdd() throws Exception {
		RecordedList<String> list = new RecordedList<String>();
		assertFalse(list.isUpdated());

		list.add("one");
		assertTrue(list.isUpdated());
		list.resetUpdated();
		assertFalse(list.isUpdated());

		list.add(0, "one");
		assertTrue(list.isUpdated());
		list.resetUpdated();
		assertFalse(list.isUpdated());
	}

	public void testAddAll() throws Exception {
		RecordedList<String> list = new RecordedList<String>();
		assertFalse(list.isUpdated());

		List<String> added = new ArrayList<String>();
		added.add("ichi");
		added.add("ni");
		list.addAll(added);
		assertTrue(list.isUpdated());
		assertEquals(2, list.size());
		list.resetUpdated();
		assertFalse(list.isUpdated());

		list.addAll(0, added);
		assertTrue(list.isUpdated());
		assertEquals(4, list.size());
		list.resetUpdated();
		assertFalse(list.isUpdated());
	}

	public void testRemove() throws Exception {
		RecordedList<String> list = new RecordedList<String>();
		assertFalse(list.isUpdated());

		list.add("one");
		list.add("two");
		list.resetUpdated();
		assertFalse(list.isUpdated());

		list.remove(0);
		assertTrue(list.isUpdated());
		assertEquals(1, list.size());

		list.resetUpdated();
		assertFalse(list.isUpdated());

		list.remove("two");
		assertTrue(list.isUpdated());
		assertEquals(0, list.size());
	}

	public void testSet() throws Exception {
		RecordedList<String> list = new RecordedList<String>();
		assertFalse(list.isUpdated());

		list.add("two");
		list.resetUpdated();
		assertFalse(list.isUpdated());

		list.set(0, "one");
		assertTrue(list.isUpdated());
		list.resetUpdated();
		assertFalse(list.isUpdated());
		assertEquals(1, list.size());

		list.clear();
		assertTrue(list.isUpdated());
		assertEquals(0, list.size());
	}

	class TestRecord implements UpdateRecordable {
		boolean updated = false;

		public boolean isUpdated() {
			return updated;
		}

		public void resetUpdated() {
			updated = false;
		}
	}

	public void testRecordedElement() throws Exception {
		RecordedList<TestRecord> list = new RecordedList<TestRecord>();
		assertFalse(list.isUpdated());

		TestRecord r1 = new TestRecord();
		list.add(r1);
		list.resetUpdated();
		assertFalse(list.isUpdated());

		r1.updated = true;
		assertTrue(r1.isUpdated());
		assertTrue(list.isUpdated());

		list.resetUpdated();
		assertFalse(r1.isUpdated());
		assertFalse(list.isUpdated());
	}

}
