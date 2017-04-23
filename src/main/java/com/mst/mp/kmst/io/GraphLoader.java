package com.mst.mp.kmst.io;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;
import com.mst.mp.kmst.Graph;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class GraphLoader {
	private static final int NODES_LINE = 0;
	private static final int EDGES_LINE = 1;

	public Graph loadInstanceKey(String instanceKey) throws IOException {
		return load(getPath(instanceKey));
	}


	private static String getPath(String key) {
		return "instances/" + key + ".dat";
	}

	public Graph load(String file) throws IOException {
		List<String> lines = Files.readLines(
				new File(getClass().getClassLoader().getResource(file).getFile()),
				Charsets.UTF_8);


		GraphJGraphTImpl.Builder builder = GraphJGraphTImpl.builder(
				Integer.parseInt(lines.get(NODES_LINE)),
				Integer.parseInt(lines.get(EDGES_LINE))
		);

		for (int i = 2; i < lines.size(); i++) {
			if (lines.get(i).isEmpty()) {
				continue;
			}
			Iterator<String> tokens = Splitter.on(" ").split(lines.get(i)).iterator();
			tokens.next();// omit index
			builder.addEdge(
					Integer.parseInt(tokens.next()),
					Integer.parseInt(tokens.next()),
					Double.parseDouble(tokens.next()));
		}
		return builder.build();
	}
}
