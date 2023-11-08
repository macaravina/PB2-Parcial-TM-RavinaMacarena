package ar.edu.unlam.pb2.Parcial01;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class TiendaTest {

	/**
	 * Resolver los siguientes tests
	 */
	

	@Test (expected = VendedorDeLicenciaException.class)
	public void queAlIntentarAgregarUnaVentaParaUnVendedorDeLicenciaSeLanceUnaVendedorDeLicenciaException() throws VendedorDeLicenciaException {
		Tienda tienda = new Tienda ("302233445566", "TiendaUNLAM");
		Vendedor vendedorSergio = new Vendedor("41213141", "Sergio");
		Cliente cliente = new Cliente("2745454545", "UP");
		
		vendedorSergio.setDeLicencia(true);
		Venta ventaNueva = new Venta("12", cliente, vendedorSergio);
		tienda.agregarVenta(ventaNueva);
		
	}

	@Test (expected = VendibleInexistenteException.class)
	public void queAlIntentarAgregarUnVendibleInexistenteAUnaVentaSeLanceUnaVendibleInexistenteException() throws VendibleInexistenteException, VendedorDeLicenciaException {
		Tienda tienda = new Tienda ("302233445566", "TiendaUNLAM");
		Vendedor vendedorSergio = new Vendedor("41213141", "Sergio");
		Cliente cliente = new Cliente("2745454545", "UP");
		Venta nuevaVenta = new Venta("15", cliente, vendedorSergio);
		
		tienda.agregarVenta(nuevaVenta);
		
		Vendible productoB = new Producto(2, "teclado", 500.0, 10);
		
		tienda.agregarProductoAVenta("01", (Producto)productoB);

	}

	@Test
	public void queSePuedaObtenerUnaListaDeProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion() {
		Tienda tienda = new Tienda ("302233445566", "TiendaUNLAM");
		
		Vendible productoA = new Producto(1, "productoA", 500.0, 5);
		tienda.agregarProducto((Producto)productoA, 5);
		
		Vendible productoB = new Producto (2, "productoB", 100.0, 3);
		tienda.agregarProducto((Producto)productoB, 10);
		
		List<Producto> productosConPocoStock = tienda.obtenerProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion();
		assertTrue(productosConPocoStock.size() == 1);
	}

	@Test
	public void queSePuedaObtenerUnSetDeClientesOrdenadosPorRazonSocialDescendente() {
		Tienda tienda = new Tienda ("302233445566", "TiendaUNLAM");
		Cliente cliente = new Cliente("2745454545", "AP");
		Cliente cliente2 = new Cliente("2745454545", "ZP");

		
		tienda.agregarCliente(cliente);
		tienda.agregarCliente(cliente2);
		
		TreeSet<Cliente> clientesOrdenadosPorRazonSocial = tienda.obtenerClientesOrdenadosPorRazonSocialDescendente();
		assertTrue(clientesOrdenadosPorRazonSocial.size() == 2);
		assertTrue(clientesOrdenadosPorRazonSocial.last().equals(cliente));
		assertTrue(clientesOrdenadosPorRazonSocial.first().equals(cliente2));


	}

	@Test
	public void queSePuedaObtenerUnMapaDeVentasRealizadasPorCadaVendedor() throws VendedorDeLicenciaException {
		// TODO: usar como key el vendedor y Set<Venta> para las ventas
		//Fallo en la logica. No me alcanzo el tiempo para verificar si el vendedor ya existe como key en map, para agregarle al set la otra venta, o en tal caso tambien el vendedor
		Tienda tienda = new Tienda ("302233445566", "TiendaUNLAM");
		
		Cliente cliente = new Cliente("2745454545", "AP");
		
		Vendedor vendedorSergio = new Vendedor("41213141", "Sergio");
		tienda.agregarVendedor(vendedorSergio);
		
		Vendedor vendedorCarlos = new Vendedor("43333141", "Carlos");
		tienda.agregarVendedor(vendedorCarlos);
		
		Venta venta1 = new Venta("1", cliente, vendedorSergio);
		tienda.agregarVenta(venta1);

		Venta venta2 = new Venta("2", cliente, vendedorSergio);
		tienda.agregarVenta(venta2);
		
		Venta venta3 = new Venta("3", cliente, vendedorCarlos);
		tienda.agregarVenta(venta3);
		
		Map<Vendedor, Set<Venta>> ventasPorVendedor = tienda.obtenerVentasPorVendedor();

		assertTrue(ventasPorVendedor.get(vendedorSergio).size() == 2);
		assertTrue(ventasPorVendedor.get(vendedorCarlos).size() == 1);


	}

	/*@Test
	public void queSePuedaObtenerElTotalDeVentasDeServicios() {
	}

	@Test
	public void queAlRealizarLaVentaDeUnProductoElStockSeActualiceCorrectamente() {
	}*/
}
