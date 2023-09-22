package guru.springframework.udemyspringdatajpaorders.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.Objects;

@Entity
public class OrderApproval extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "order_header_id")
	OrderHeader orderHeader;

	private String approvedBy;

	public String getApprovedBy() {

		return approvedBy;
	}

	public void setApprovedBy(final String approvedBy) {

		this.approvedBy = approvedBy;
	}

	public OrderHeader getOrderHeader() {

		return orderHeader;
	}

	public void setOrderHeader(final OrderHeader orderHeader) {

		this.orderHeader = orderHeader;
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o)
			return true;
		if (!(o instanceof final OrderApproval that))
			return false;
		if (!super.equals(o))
			return false;

		if (!Objects.equals(orderHeader, that.orderHeader))
			return false;
		return Objects.equals(approvedBy, that.approvedBy);
	}

	@Override
	public int hashCode() {

		int result = super.hashCode();
		result = 31 * result + (orderHeader != null ? orderHeader.hashCode() : 0);
		result = 31 * result + (approvedBy != null ? approvedBy.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {

		return "OrderApproval{" +
				"orderHeader=" + orderHeader +
				", approvedBy='" + approvedBy + '\'' +
				'}';
	}
}
