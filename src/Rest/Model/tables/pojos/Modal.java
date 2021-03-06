/**
 * This class is generated by jOOQ
 */
package Rest.Model.tables.pojos;


import java.io.Serializable;
import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


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
@Table(name = "modal", schema = "vvv")
public class Modal implements Serializable {

	private static final long serialVersionUID = 1452840715;

	private Integer id;
	private Integer type;
	private Integer capacity;
	private String  model;
	private Date    manufacture;
	private Date    maintenanceStart;
	private Date    maintenanceEnd;
	private Integer carriersId;

	public Modal() {}

	public Modal(Modal value) {
		this.id = value.id;
		this.type = value.type;
		this.capacity = value.capacity;
		this.model = value.model;
		this.manufacture = value.manufacture;
		this.maintenanceStart = value.maintenanceStart;
		this.maintenanceEnd = value.maintenanceEnd;
		this.carriersId = value.carriersId;
	}

	public Modal(
		Integer id,
		Integer type,
		Integer capacity,
		String  model,
		Date    manufacture,
		Date    maintenanceStart,
		Date    maintenanceEnd,
		Integer carriersId
	) {
		this.id = id;
		this.type = type;
		this.capacity = capacity;
		this.model = model;
		this.manufacture = manufacture;
		this.maintenanceStart = maintenanceStart;
		this.maintenanceEnd = maintenanceEnd;
		this.carriersId = carriersId;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "type", nullable = false, precision = 10)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "capacity", nullable = false, precision = 10)
	public Integer getCapacity() {
		return this.capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@Column(name = "model", nullable = false, length = 255)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "manufacture", nullable = false)
	public Date getManufacture() {
		return this.manufacture;
	}

	public void setManufacture(Date manufacture) {
		this.manufacture = manufacture;
	}

	@Column(name = "maintenance_start", nullable = false)
	public Date getMaintenanceStart() {
		return this.maintenanceStart;
	}

	public void setMaintenanceStart(Date maintenanceStart) {
		this.maintenanceStart = maintenanceStart;
	}

	@Column(name = "maintenance_end", nullable = false)
	public Date getMaintenanceEnd() {
		return this.maintenanceEnd;
	}

	public void setMaintenanceEnd(Date maintenanceEnd) {
		this.maintenanceEnd = maintenanceEnd;
	}

	@Column(name = "carriers_id", nullable = false, precision = 10)
	public Integer getCarriersId() {
		return this.carriersId;
	}

	public void setCarriersId(Integer carriersId) {
		this.carriersId = carriersId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Modal (");

		sb.append(id);
		sb.append(", ").append(type);
		sb.append(", ").append(capacity);
		sb.append(", ").append(model);
		sb.append(", ").append(manufacture);
		sb.append(", ").append(maintenanceStart);
		sb.append(", ").append(maintenanceEnd);
		sb.append(", ").append(carriersId);

		sb.append(")");
		return sb.toString();
	}
}
