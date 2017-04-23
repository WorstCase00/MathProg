package com.mst.mp.kmst;

public class EdgeImpl implements Edge {

	private final int source;
	private final int target;

	public EdgeImpl(int source, int target) {
		this.source = source;
		this.target = target;
	}

	@Override
	public int getSource() {
		return source;
	}

	@Override
	public int getTarget() {
		return target;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		EdgeImpl edge = (EdgeImpl) o;

		if (source != edge.source) return false;
		return target == edge.target;
	}

	@Override
	public int hashCode() {
		int result = source;
		result = 31 * result + target;
		return result;
	}
}
