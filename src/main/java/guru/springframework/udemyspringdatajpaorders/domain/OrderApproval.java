package guru.springframework.udemyspringdatajpaorders.domain;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class OrderApproval extends BaseEntity {

	private String approvedBy;

	public String getApprovedBy() {

		return approvedBy;
	}

	public void setApprovedBy(final String approvedBy) {

		this.approvedBy = approvedBy;
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o)
			return true;
		if (!(o instanceof final OrderApproval that))
			return false;
		if (!super.equals(o))
			return false;

		return Objects.equals(approvedBy, that.approvedBy);
	}

	@Override
	public int hashCode() {

		int result = super.hashCode();
		result = 31 * result + (approvedBy != null ? approvedBy.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {

		return "OrderApproval{" +
				"approvedBy='" + approvedBy + '\'' +
				'}';
	}
}
