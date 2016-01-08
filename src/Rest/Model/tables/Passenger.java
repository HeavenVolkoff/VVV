/**
 * This class is generated by jOOQ
 */
package Rest.Model.tables;


import Rest.Model.Keys;
import Rest.Model.Vvv;
import Rest.Model.tables.records.PassengerRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class Passenger extends TableImpl<PassengerRecord> {

	private static final long serialVersionUID = -169830670;

	/**
	 * The reference instance of <code>vvv.passenger</code>
	 */
	public static final Passenger PASSENGER = new Passenger();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<PassengerRecord> getRecordType() {
		return PassengerRecord.class;
	}

	/**
	 * The column <code>vvv.passenger.user_id</code>.
	 */
	public final TableField<PassengerRecord, Integer> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>vvv.passenger.cpf</code>.
	 */
	public final TableField<PassengerRecord, String> CPF = createField("cpf", org.jooq.impl.SQLDataType.VARCHAR.length(11), this, "");

	/**
	 * The column <code>vvv.passenger.phone</code>.
	 */
	public final TableField<PassengerRecord, String> PHONE = createField("phone", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

	/**
	 * The column <code>vvv.passenger.occupation</code>.
	 */
	public final TableField<PassengerRecord, String> OCCUPATION = createField("occupation", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>vvv.passenger.address_id</code>.
	 */
	public final TableField<PassengerRecord, Integer> ADDRESS_ID = createField("address_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>vvv.passenger</code> table reference
	 */
	public Passenger() {
		this("passenger", null);
	}

	/**
	 * Create an aliased <code>vvv.passenger</code> table reference
	 */
	public Passenger(String alias) {
		this(alias, PASSENGER);
	}

	private Passenger(String alias, Table<PassengerRecord> aliased) {
		this(alias, aliased, null);
	}

	private Passenger(String alias, Table<PassengerRecord> aliased, Field<?>[] parameters) {
		super(alias, Vvv.VVV, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<PassengerRecord> getPrimaryKey() {
		return Keys.KEY_PASSENGER_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<PassengerRecord>> getKeys() {
		return Arrays.<UniqueKey<PassengerRecord>>asList(Keys.KEY_PASSENGER_PRIMARY, Keys.KEY_PASSENGER_CPF_UNIQUE, Keys.KEY_PASSENGER_ADDRESS_ID_UNIQUE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<PassengerRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<PassengerRecord, ?>>asList(Keys.FK_PASSENGER_USER1, Keys.FK_PASSENGER_ADDRESS1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Passenger as(String alias) {
		return new Passenger(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Passenger rename(String name) {
		return new Passenger(name, null);
	}
}
