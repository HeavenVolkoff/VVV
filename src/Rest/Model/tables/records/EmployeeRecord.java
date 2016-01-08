/**
 * This class is generated by jOOQ
 */
package Rest.Model.tables.records;


import Rest.Model.tables.Employee;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
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
@Table(name = "employee", schema = "vvv")
public class EmployeeRecord extends UpdatableRecordImpl<EmployeeRecord> implements Record6<Integer, String, String, Integer, Byte, byte[]> {

	private static final long serialVersionUID = 936038711;

	/**
	 * Setter for <code>vvv.employee.user_id</code>.
	 */
	public void setUserId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>vvv.employee.user_id</code>.
	 */
	@Id
	@Column(name = "user_id", unique = true, nullable = false, precision = 10)
	public Integer getUserId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>vvv.employee.cpf</code>.
	 */
	public void setCpf(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>vvv.employee.cpf</code>.
	 */
	@Column(name = "cpf", unique = true, nullable = false, length = 11)
	public String getCpf() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>vvv.employee.phone</code>.
	 */
	public void setPhone(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>vvv.employee.phone</code>.
	 */
	@Column(name = "phone", length = 50)
	public String getPhone() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>vvv.employee.address_id</code>.
	 */
	public void setAddressId(Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>vvv.employee.address_id</code>.
	 */
	@Column(name = "address_id", unique = true, nullable = false, precision = 10)
	public Integer getAddressId() {
		return (Integer) getValue(3);
	}

	/**
	 * Setter for <code>vvv.employee.post</code>.
	 */
	public void setPost(Byte value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>vvv.employee.post</code>.
	 */
	@Column(name = "post", nullable = false, precision = 3)
	public Byte getPost() {
		return (Byte) getValue(4);
	}

	/**
	 * Setter for <code>vvv.employee.work_days</code>.
	 */
	public void setWorkDays(byte[] value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>vvv.employee.work_days</code>.
	 */
	@Column(name = "work_days", length = 7)
	public byte[] getWorkDays() {
		return (byte[]) getValue(5);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record6 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row6<Integer, String, String, Integer, Byte, byte[]> fieldsRow() {
		return (Row6) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row6<Integer, String, String, Integer, Byte, byte[]> valuesRow() {
		return (Row6) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return Employee.EMPLOYEE.USER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return Employee.EMPLOYEE.CPF;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return Employee.EMPLOYEE.PHONE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field4() {
		return Employee.EMPLOYEE.ADDRESS_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Byte> field5() {
		return Employee.EMPLOYEE.POST;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<byte[]> field6() {
		return Employee.EMPLOYEE.WORK_DAYS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getUserId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getCpf();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getPhone();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value4() {
		return getAddressId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Byte value5() {
		return getPost();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public byte[] value6() {
		return getWorkDays();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeRecord value1(Integer value) {
		setUserId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeRecord value2(String value) {
		setCpf(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeRecord value3(String value) {
		setPhone(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeRecord value4(Integer value) {
		setAddressId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeRecord value5(Byte value) {
		setPost(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeRecord value6(byte[] value) {
		setWorkDays(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeRecord values(Integer value1, String value2, String value3, Integer value4, Byte value5, byte[] value6) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached EmployeeRecord
	 */
	public EmployeeRecord() {
		super(Employee.EMPLOYEE);
	}

	/**
	 * Create a detached, initialised EmployeeRecord
	 */
	public EmployeeRecord(Integer userId, String cpf, String phone, Integer addressId, Byte post, byte[] workDays) {
		super(Employee.EMPLOYEE);

		setValue(0, userId);
		setValue(1, cpf);
		setValue(2, phone);
		setValue(3, addressId);
		setValue(4, post);
		setValue(5, workDays);
	}
}
