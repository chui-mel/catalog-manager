package com.bunnings.catalog.service;

import java.util.ArrayList;
import java.util.List;

public class ListsMerger {

	public <T extends Comparable> List<T> mergeTwoSorted(List<T> primary, List<T> secondary) {
		if (secondary.size() == 0) {
			return primary;
		}

		if (primary.size() == 0) {
			return secondary;
		}

		List<T> merged = new ArrayList<>();

		int i = 0, j = 0;
		while (i < primary.size() && j < secondary.size()) {
			int compare = primary.get(i).compareTo(secondary.get(j));
			if (compare <= 0) {
				merged.add(primary.get(i));
				i++;
				if (compare == 0) {
					j++;
				}
			} else {
				merged.add(secondary.get(j));
				j++;
			}
		}

		if (i < primary.size()) {
			merged.addAll(primary.subList(i, primary.size()));
		}

		if (j < secondary.size()) {
			merged.addAll(secondary.subList(j, secondary.size()));
		}

		return merged;
	}
}
