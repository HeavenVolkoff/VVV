/**
 * This class is generated by jOOQ
 */
package Rest.Model.tables;


import Rest.Model.Keys;
import Rest.Model.Vvv;
import Rest.Model.tables.records.AddressRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * User Address
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.1"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Address extends TableImpl<AddressRecord> {

	private static final long serialVersionUID = 291762418;

	/**
	 * The reference instance of <code>vvv.Address</code>
	 */
	public static final Address ADDRESS = new Address();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<AddressRecord> getRecordType() {
		return AddressRecord.class;
	}

	/**
	 * The column <code>vvv.Address.id</code>.
	 */
	public final TableField<AddressRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>vvv.Address.user_id</code>.
	 */
	public final TableField<AddressRecord, Integer> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>vvv.Address.street</code>.
	 */
	public final TableField<AddressRecord, String> STREET = createField("street", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>vvv.Address.number</code>.
	 */
	public final TableField<AddressRecord, Integer> NUMBER = createField("number", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>vvv.Address.complement</code>.
	 */
	public final TableField<AddressRecord, String> COMPLEMENT = createField("complement", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>vvv.Address.city</code>.
	 */
	public final TableField<AddressRecord, String> CITY = createField("city", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>vvv.Address.state</code>.
	 */
	public final TableField<AddressRecord, String> STATE = createField("state", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * Create a <code>vvv.Address</code> table reference
	 */
	public Address() {
		this("Address", null);
	}

	/**
	 * Create an aliased <code>vvv.Address</code> table reference
	 */
	public Address(String alias) {
		this(alias, ADDRESS);
	}

	private Address(String alias, Table<AddressRecord> aliased) {
		this(alias, aliased, null);
	}

	private Address(String alias, Table<AddressRecord> aliased, Field<?>[] parameters) {
		super(alias, Vvv.VVV, aliased, parameters, "User Address");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<AddressRecord, Integer> getIdentity() {
		return Keys.IDENTITY_ADDRESS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<AddressRecord> getPrimaryKey() {
		return Keys.KEY_ADDRESS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<AddressRecord>> getKeys() {
		return Arrays.<UniqueKey<AddressRecord>>asList(Keys.KEY_ADDRESS_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<AddressRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<AddressRecord, ?>>asList(Keys.ADDRESS_IBFK_1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Address as(String alias) {
		return new Address(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Address rename(String name) {
		return new Address(name, null);
	}
}
