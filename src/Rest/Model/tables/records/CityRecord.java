/**
 * This class is generated by jOOQ
 */
package Rest.Model.tables.records;


import Rest.Model.tables.City;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
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
@Table(name = "city", schema = "vvv")
public class CityRecord extends UpdatableRecordImpl<CityRecord> implements Record4<Integer, String, Double, Double> {

	private static final long serialVersionUID = -712070147;

	/**
	 * Setter for <code>vvv.city.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>vvv.city.id</code>.
	 */
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10)
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>vvv.city.name</code>.
	 */
	public void setName(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>vvv.city.name</code>.
	 */
	@Column(name = "name", length = 255)
	public String getName() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>vvv.city.latitute</code>.
	 */
	public void setLatitute(Double value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>vvv.city.latitute</code>.
	 */
	@Column(name = "latitute", precision = 12)
	public Double getLatitute() {
		return (Double) getValue(2);
	}

	/**
	 * Setter for <code>vvv.city.longitude</code>.
	 */
	public void setLongitude(Double value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>vvv.city.longitude</code>.
	 */
	@Column(name = "longitude", precision = 12)
	public Double getLongitude() {
		return (Double) getValue(3);
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
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<Integer, String, Double, Double> fieldsRow() {
		return (Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<Integer, String, Double, Double> valuesRow() {
		return (Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return City.CITY.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return City.CITY.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Double> field3() {
		return City.CITY.LATITUTE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Double> field4() {
		return City.CITY.LONGITUDE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double value3() {
		return getLatitute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double value4() {
		return getLongitude();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CityRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CityRecord value2(String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CityRecord value3(Double value) {
		setLatitute(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CityRecord value4(Double value) {
		setLongitude(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CityRecord values(Integer value1, String value2, Double value3, Double value4) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached CityRecord
	 */
	public CityRecord() {
		super(City.CITY);
	}

	/**
	 * Create a detached, initialised CityRecord
	 */
	public CityRecord(Integer id, String name, Double latitute, Double longitude) {
		super(City.CITY);

		setValue(0, id);
		setValue(1, name);
		setValue(2, latitute);
		setValue(3, longitude);
	}
}