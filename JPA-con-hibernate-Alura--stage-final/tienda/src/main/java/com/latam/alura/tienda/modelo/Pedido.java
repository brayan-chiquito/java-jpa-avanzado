package com.latam.alura.tienda.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private LocalDate fecha = LocalDate.now();
	private BigDecimal valorTotal = new BigDecimal(0);
	
	@ManyToOne(fetch = FetchType.LAZY) //se cargan solo si son nesesarios ya que los manytoone son tipo iger osea que trae todo los elemntos los manytomany por defecto son lazy
	private Cliente cliente;
//	se hace la coneccion con la tabla items pedidos ya qye se creaba otra tabla
//	con la misma funcion pero sin los atributos
//	tambien en la relacion se indica que en todos lo casosa que se hagan alteraciones 
//	la actualizacion de la otra tabla relacionada 
//	tambien esto lo que hace es que se actulice en forma de cascada evitando haver un DAO
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemsPedido> items= new ArrayList<>();
	
	public Pedido() {
		
	}
	
	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void agregarItems(ItemsPedido item) {
		item.setPedido(this);
		this.items.add(item);
		this.valorTotal = this.valorTotal.add(item.getValor());
	}

	public List<ItemsPedido> getItems() {
		return items;
	}

	public void setItems(List<ItemsPedido> items) {
		this.items = items;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
}
