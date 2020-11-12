package com.komponente.search;

/**
 * Enumeration that specifies if the search at the beginning of value, at the end of value, substring to value or<br>
 * equal to value.
 * @author ivan
 *
 */
public enum SearchEnum {
	/**
	 * Find value at the beginning.
	 */
	AT_THE_BEGINING,
	/**
	 * Find value at the end.
	 */
	AT_THE_END,
	/**
	 * Find substring.
	 */
	SUBSTRING,
	/**
	 * Find equal value
	 */
	EQUAL_AS
}
