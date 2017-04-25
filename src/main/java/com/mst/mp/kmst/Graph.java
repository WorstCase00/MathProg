package com.mst.mp.kmst;

import java.util.Set;

public interface Graph {

	int getNodes();

	int getEdgeCount();

	Set<Integer> getNeighbors(int v);

	double getDistance(int v1, int v2);

	Set<Edge> getEdges();

	double getWeight(Edge e);

	double getWeightSum();
}
