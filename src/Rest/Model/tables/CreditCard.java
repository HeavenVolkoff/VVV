/**
 * This class is generated by jOOQ
 */
package Rest.Model.tables;


import Rest.Model.Keys;
import Rest.Model.Vvv;
import Rest.Model.tables.records.CreditCardRecord;

import java.sql.Date;
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
 * User Credit Card
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.1"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CreditCard extends TableImpl<CreditCardRecord> {

	private static final long serialVersionUID = -1193655063;

	/**
	 * The reference instance of <code>vvv.credit_card</code>
	 */
	public static final CreditCard CREDIT_CARD = new CreditCard();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<CreditCardRecord> getRecordType() {
		return CreditCardRecord.class;
	}

	/**
	 * The column <code>vvv.credit_card.id</code>.
	 */
	public final TableField<CreditCardRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>vvv.credit_card.user_id</code>.
	 */
	public final TableField<CreditCardRecord, Integer> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>vvv.credit_card.number</code>.
	 */
	public final TableField<CreditCardRecord, Integer> NUMBER = createField("number", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>vvv.credit_card.holder_name</code>.
	 */
	public final TableField<CreditCardRecord, String> HOLDER_NAME = createField("holder_name", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>vvv.credit_card.expire</code>.
	 */
	public final TableField<CreditCardRecord, Date> EXPIRE = createField("expire", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

	/**
	 * The column <code>vvv.credit_card.facture_address_id</code>.
	 */
	public final TableField<CreditCardRecord, Integer> FACTURE_ADDRESS_ID = createField("facture_address_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>vvv.credit_card</code> table reference
	 */
	public CreditCard() {
		this("credit_card", null);
	}

	/**
	 * Create an aliased <code>vvv.credit_card</code> table reference
	 */
	public CreditCard(String alias) {
		this(alias, CREDIT_CARD);
	}

	private CreditCard(String alias, Table<CreditCardRecord> aliased) {
		this(alias, aliased, null);
	}

	private CreditCard(String alias, Table<CreditCardRecord> aliased, Field<?>[] parameters) {
		super(alias, Vvv.VVV, aliased, parameters, "User Credit Card");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<CreditCardRecord> getPrimaryKey() {
		return Keys.KEY_CREDIT_CARD_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<CreditCardRecord>> getKeys() {
		return Arrays.<UniqueKey<CreditCardRecord>>asList(Keys.KEY_CREDIT_CARD_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<CreditCardRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<CreditCardRecord, ?>>asList(Keys.CREDIT_CARD_IBFK_1, Keys.CREDIT_CARD_IBFK_2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CreditCard as(String alias) {
		return new CreditCard(alias, this);
	}

	/**
	 * Rename this table
	 */
	public CreditCard rename(String name) {
		return new CreditCard(name, null);
	}
}
