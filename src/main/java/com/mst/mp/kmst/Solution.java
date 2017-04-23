package com.mst.mp.kmst;

import java.util.Set;

public interface Solution {

	double getCost();

	Problem getProblem();

	Set<Edge> getEdges();

	Set<Integer> getNodes();
}
