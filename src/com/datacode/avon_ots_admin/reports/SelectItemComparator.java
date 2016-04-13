/**
 *
 *  @since 19/04/2012
 *
 */
package com.datacode.avon_ots_admin.reports;

import java.util.Comparator;

import javax.faces.model.SelectItem;

/**
 * @author jessica.leon
 *
 */
public class SelectItemComparator implements Comparator<Object> {

	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Object o1, Object o2) {
	   
		SelectItem selectItem1 = (SelectItem) o1;
		SelectItem selectItem2 = (SelectItem) o2;
		return selectItem1.getLabel().compareToIgnoreCase(selectItem2.getLabel());
	}
}
