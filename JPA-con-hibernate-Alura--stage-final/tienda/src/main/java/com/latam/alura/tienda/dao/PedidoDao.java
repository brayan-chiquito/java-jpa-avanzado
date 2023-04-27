package com.latam.alura.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.modelo.Pedido;
import com.latam.alura.tienda.vo.RelatorioDeVenta;

public class PedidoDao {
	
	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	
	public void actualizar(Pedido pedido) {
		this.em.merge(pedido);
	}
	
	public void remover(Pedido pedido) {
		pedido=this.em.merge(pedido);
		this.em.remove(pedido);
	}
	
	public Pedido consultaPorId(Long id) {
		return em.find(Pedido.class, id);
	}
	
	public List<Pedido> consultarTodos(){
		String jqpl= "SELECT P FROM Pedido AS P";
		return em.createQuery(jqpl,Pedido.class).getResultList();
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql,BigDecimal.class).getSingleResult();
	}
	
	//este tipo de consultas es para cuandoa varios elementos de varias tablas deben esta unidas es una sola tabla con dichos elementos pedidos
	//estrategia 1
	public List<Object[]> relatorioDeVentas() {
		String jpql = "SELECT producto.nombre, "
				+ "SUM(item.cantidad), "
				+ "MAX(pedido.fecha) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.items item "
				+ "JOIN item.producto producto "
				+ "GROUP BY producto.nombre "
				+ "ORDER BY item.cantidad DESC";
		return em.createQuery(jpql, Object[].class).getResultList();
	}
	//estrategia 2 esta es mas especifica
	//contienen los atributos de esa consulta 
	public List<RelatorioDeVenta> relatorioDeVentasVO() {
		String jpql = "SELECT new com.latam.alura.tienda.vo.RelatorioDeVenta(producto.nombre, " //indico donde esta la clase y el contructor
				+ "SUM(item.cantidad), "
				+ "MAX(pedido.fecha)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.items item "
				+ "JOIN item.producto producto "
				+ "GROUP BY producto.nombre "
				+ "ORDER BY item.cantidad DESC";
		return em.createQuery(jpql, RelatorioDeVenta.class).getResultList();
	}
	
	//join fetch ese usa para cuando se planee usar una consulta con el entity manager cerrado
	public Pedido consultarPedidoConCliente(Long id) {
		String jpql= "SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id=:id";
		return em.createQuery(jpql, Pedido.class).setParameter("id", id).getSingleResult();
	}
	
	
}
