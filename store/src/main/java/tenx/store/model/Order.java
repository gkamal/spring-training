package tenx.store.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="order_id")
	private List<LineItem> items = new ArrayList<LineItem>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCost() {
		BigDecimal cost = new BigDecimal("0");
		for(LineItem lineItem:getItems()) {
			cost = cost.add(lineItem.getProduct().getPrice().multiply(new BigDecimal(lineItem.getQuantity())));
		}
		return cost;
	}

	public List<LineItem> getItems() {
		return items;
	}

	public void addItem(LineItem item) {
		items.add(item);
	}
	
	

}
