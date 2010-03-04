package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.util.ArrayList;
import java.util.Collection;

public class RecordedList<E> extends ArrayList<E> implements UpdateRecordable {

	private static final long serialVersionUID = -8770443746190862735L;

	boolean updated = false;

	@Override
	public void add(int index, E element) {
		super.add(index, element);
		updated = true;
	}

	@Override
	public boolean add(E e) {
		boolean result = super.add(e);
		updated = true;
		return result;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean result = super.addAll(c);
		updated = true;
		return result;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean result = super.addAll(index, c);
		updated = true;
		return result;
	}

	@Override
	public void clear() {
		super.clear();
		updated = true;
	}

	@Override
	public E remove(int index) {
		E result = super.remove(index);
		updated = true;
		return result;
	}

	@Override
	public boolean remove(Object o) {
		boolean result = super.remove(o);
		updated = true;
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean removeAll(Collection c) {
		boolean result = super.removeAll(c);
		updated = true;
		return result;
	}

	@Override
	public E set(int index, E element) {
		E result = super.set(index, element);
		updated = true;
		return result;
	}

	public boolean isUpdated() {
		if (updated) {
			return true;
		}
		for (int i = 0; i < size(); i++) {
			E e = get(i);
			if (e instanceof UpdateRecordable) {
				if (((UpdateRecordable) e).isUpdated()) {
					return true;
				}
			}
		}
		return false;
	}

	public void resetUpdated() {
		for (int i = 0; i < size(); i++) {
			E e = get(i);
			if (e instanceof UpdateRecordable) {
				((UpdateRecordable) e).resetUpdated();
			}
		}
		updated = false;
	}

}
