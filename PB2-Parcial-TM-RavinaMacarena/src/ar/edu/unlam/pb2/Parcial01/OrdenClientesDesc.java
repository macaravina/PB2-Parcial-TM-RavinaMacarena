package ar.edu.unlam.pb2.Parcial01;

import java.util.Comparator;

public class OrdenClientesDesc implements Comparator<Cliente>{

	@Override
	public int compare(Cliente razonSocial1, Cliente razonSocial2) {
		return razonSocial2.getRazonSocial().compareTo(razonSocial1.getRazonSocial());
	}

	
}
