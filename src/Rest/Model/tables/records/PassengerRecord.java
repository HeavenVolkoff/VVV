/**
 * This class is generated by jOOQ
 */
package Rest.Model.tables.records;


import Rest.Model.tables.Passenger;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
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
@Table(name = "passenger", schema = "vvv")
public class PassengerRecord extends UpdatableRecordImpl<PassengerRecord> implements Record5<Integer, String, String, String, Integer> {

	private static final long serialVersionUID = 643646447;

	/**
	 * Setter for <code>vvv.passenger.user_id</code>.
	 */
	public void setUserId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>vvv.passenger.user_id</code>.
	 */
	@Id
	@Column(name = "user_id", unique = true, nullable = false, precision = 10)
	public Integer getUserId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>vvv.passenger.cpf</code>.
	 */
	public void setCpf(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>vvv.passenger.cpf</code>.
	 */
	@Column(name = "cpf", unique = true, length = 11)
	public String getCpf() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>vvv.passenger.phone</code>.
	 */
	public void setPhone(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>vvv.passenger.phone</code>.
	 */
	@Column(name = "phone", nullable = false, length = 50)
	public String getPhone() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>vvv.passenger.occupation</code>.
	 */
	public void setOccupation(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>vvv.passenger.occupation</code>.
	 */
	@Column(name = "occupation", nullable = false, length = 255)
	public String getOccupation() {
		return (String) getValue(3);
	}

	/**
	 * Setter for <code>vvv.passenger.address_id</code>.
	 */
	public void setAddressId(Integer value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>vvv.passenger.address_id</code>.
	 */
	@Column(name = "address_id", unique = true, nullable = false, precision = 10)
	public Integer getAddressId() {
		return (Integer) getValue(4);
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
	// Record5 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row5<Integer, String, String, String, Integer> fieldsRow() {
		return (Row5) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row5<Integer, String, String, String, Integer> valuesRow() {
		return (Row5) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return Passenger.PASSENGER.USER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return Passenger.PASSENGER.CPF;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return Passenger.PASSENGER.PHONE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return Passenger.PASSENGER.OCCUPATION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field5() {
		return Passenger.PASSENGER.ADDRESS_ID;
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
	public String value4() {
		return getOccupation();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value5() {
		return getAddressId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PassengerRecord value1(Integer value) {
		setUserId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PassengerRecord value2(String value) {
		setCpf(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PassengerRecord value3(String value) {
		setPhone(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PassengerRecord value4(String value) {
		setOccupation(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PassengerRecord value5(Integer value) {
		setAddressId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PassengerRecord values(Integer value1, String value2, String value3, String value4, Integer value5) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached PassengerRecord
	 */
	public PassengerRecord() {
		super(Passenger.PASSENGER);
	}

	/**
	 * Create a detached, initialised PassengerRecord
	 */
	public PassengerRecord(Integer userId, String cpf, String phone, String occupation, Integer addressId) {
		super(Passenger.PASSENGER);

		setValue(0, userId);
		setValue(1, cpf);
		setValue(2, phone);
		setValue(3, occupation);
		setValue(4, addressId);
	}
}
