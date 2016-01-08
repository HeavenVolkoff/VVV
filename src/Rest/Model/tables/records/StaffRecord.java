/**
 * This class is generated by jOOQ
 */
package Rest.Model.tables.records;


import Rest.Model.tables.Staff;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


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
public class StaffRecord extends UpdatableRecordImpl<StaffRecord> implements Record2<Integer, Integer> {

	private static final long serialVersionUID = 1068132596;

	/**
	 * Setter for <code>vvv.staff.retail_outlet_id</code>.
	 */
	public void setRetailOutletId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>vvv.staff.retail_outlet_id</code>.
	 */
	@Column(name = "retail_outlet_id", nullable = false, precision = 10)
	public Integer getRetailOutletId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>vvv.staff.employee_user_id</code>.
	 */
	public void setEmployeeUserId(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>vvv.staff.employee_user_id</code>.
	 */
	@Column(name = "employee_user_id", nullable = false, precision = 10)
	public Integer getEmployeeUserId() {
		return (Integer) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record2<Integer, Integer> key() {
		return (Record2) super.key();
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<Integer, Integer> fieldsRow() {
		return (Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<Integer, Integer> valuesRow() {
		return (Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return Staff.STAFF.RETAIL_OUTLET_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return Staff.STAFF.EMPLOYEE_USER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getRetailOutletId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value2() {
		return getEmployeeUserId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StaffRecord value1(Integer value) {
		setRetailOutletId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StaffRecord value2(Integer value) {
		setEmployeeUserId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StaffRecord values(Integer value1, Integer value2) {
		value1(value1);
		value2(value2);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached StaffRecord
	 */
	public StaffRecord() {
		super(Staff.STAFF);
	}

	/**
	 * Create a detached, initialised StaffRecord
	 */
	public StaffRecord(Integer retailOutletId, Integer employeeUserId) {
		super(Staff.STAFF);

		setValue(0, retailOutletId);
		setValue(1, employeeUserId);
	}
}
