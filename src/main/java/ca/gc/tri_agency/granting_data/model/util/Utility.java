package ca.gc.tri_agency.granting_data.model.util;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Utility {

	/*
	 * This method is used to extract distinct projection fields from JOIN queries. E.g. when fetching
	 * the BUs and FOs that are associated, this method can be used in a stream to filter out the unique
	 * BUs since many FOs can map to one BU.
	 */
	public static <P> Predicate<P> isSubProjectionIdUnique(Function<? super P, ?> subProjectionIdGetter) {
		Set<Object> distinctProjections = ConcurrentHashMap.newKeySet();
		return projection -> distinctProjections.add(subProjectionIdGetter.apply(projection));

	}
	
	public static String returnNotFoundMsg(String entityType, Long id) {
		return entityType + " id=" + id + " does not exist";
	}

}
