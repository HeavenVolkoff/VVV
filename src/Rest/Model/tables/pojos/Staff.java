/**
 * This class is generated by jOOQ
 */
package Rest.Model.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.1"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "staff", schema = "vvv", uniqueConstraints = {
	@UniqueConstraint(columnNames = {"retail_outlet_id", "employee_user_id"})
})
public class Staff implements Serializable {

	private static final long serialVersionUID = 1129372137;

	private Integer retailOutletId;
	private Integer employeeUserId;

	public Staff() {}

	public Staff(Staff value) {
		this.retailOutletId = value.retailOutletId;
		this.employeeUserId = value.employeeUserId;
	}

	public Staff(
		Integer retailOutletId,
		Integer employeeUserId
	) {
		this.retailOutletId = retailOutletId;
		this.employeeUserId = employeeUserId;
	}

	@Column(name = "retail_outlet_id", nullable = false, precision = 10)
	public Integer getRetailOutletId() {
		return this.retailOutletId;
	}

	public void setRetailOutletId(Integer retailOutletId) {
		this.retailOutletId = retailOutletId;
	}

	@Column(name = "employee_user_id", nullable = false, precision = 10)
	public Integer getEmployeeUserId() {
		return this.employeeUserId;
	}

	public void setEmployeeUserId(Integer employeeUserId) {
		this.employeeUserId = employeeUserId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Staff (");

		sb.append(retailOutletId);
		sb.append(", ").append(employeeUserId);

		sb.append(")");
		return sb.toString();
	}
}
