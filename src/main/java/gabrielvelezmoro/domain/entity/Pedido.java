package gabrielvelezmoro.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @Column(name="DATA_PEDIDO")
    private LocalDate dataPedidio;

    @Column(name="TOTAL", scale = 2, precision = 20)
    private BigDecimal  total;


    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedidio() {
        return dataPedidio;
    }

    public void setDataPedidio(LocalDate dataPedidio) {
        this.dataPedidio = dataPedidio;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", dataPedidio=" + dataPedidio +
                ", total=" + total +
                '}';
    }
}
