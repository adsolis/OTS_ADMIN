/**
 *
 *  @since 19/01/2012
 *
 */
package com.datacode.avon_ots_admin.cu003;

import java.util.Comparator;

import com.datacode.avon_ots_admin.model.Representante;

/**
 * @author jessica.leon
 *
 */
public class OrdenarSecuencia implements Comparator<Representante> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Representante representante1, Representante representante2) {
		Integer noSecuencia1 = Integer.parseInt(representante1.getRepresentantePorRuta().getSeqEntregaReciente());
		Integer noSecuencia2 = Integer.parseInt(representante2.getRepresentantePorRuta().getSeqEntregaReciente());
		return noSecuencia1.compareTo(noSecuencia2);      
	}

}
