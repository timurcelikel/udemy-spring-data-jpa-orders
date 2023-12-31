package guru.springframework.udemyspringdatajpaorders.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Objects;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp createdDate;

	@UpdateTimestamp
	private Timestamp lastModifiedDate;

	public Long getId() {

		return id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public Timestamp getCreatedDate() {

		return createdDate;
	}

	public void setCreatedDate(final Timestamp createdDate) {

		this.createdDate = createdDate;
	}

	public Timestamp getLastModifiedDate() {

		return lastModifiedDate;
	}

	public void setLastModifiedDate(final Timestamp lastModifiedDate) {

		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public boolean equals(final Object o) {

		if (this == o)
			return true;
		if (!(o instanceof final BaseEntity that))
			return false;

		if (!Objects.equals(id, that.id))
			return false;
		if (!Objects.equals(createdDate, that.createdDate))
			return false;
		return Objects.equals(lastModifiedDate, that.lastModifiedDate);
	}

	@Override
	public int hashCode() {

		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
		result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {

		return "BaseEntity{" +
			"id=" + id +
			", createdDate=" + createdDate +
			", lastModifiedDate=" + lastModifiedDate +
			'}';
	}
}
