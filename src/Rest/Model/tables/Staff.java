/**
 * This class is generated by jOOQ
 */
package Rest.Model.tables;


import Rest.Model.Keys;
import Rest.Model.Vvv;
import Rest.Model.tables.records.StaffRecord;

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
public class Staff extends TableImpl<StaffRecord> {

	private static final long serialVersionUID = -1891698473;

	/**
	 * The reference instance of <code>vvv.staff</code>
	 */
	public static final Staff STAFF = new Staff();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<StaffRecord> getRecordType() {
		return StaffRecord.class;
	}

	/**
	 * The column <code>vvv.staff.retail_outlet_id</code>.
	 */
	public final TableField<StaffRecord, Integer> RETAIL_OUTLET_ID = createField("retail_outlet_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>vvv.staff.employee_user_id</code>.
	 */
	public final TableField<StaffRecord, Integer> EMPLOYEE_USER_ID = createField("employee_user_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>vvv.staff</code> table reference
	 */
	public Staff() {
		this("staff", null);
	}

	/**
	 * Create an aliased <code>vvv.staff</code> table reference
	 */
	public Staff(String alias) {
		this(alias, STAFF);
	}

	private Staff(String alias, Table<StaffRecord> aliased) {
		this(alias, aliased, null);
	}

	private Staff(String alias, Table<StaffRecord> aliased, Field<?>[] parameters) {
		super(alias, Vvv.VVV, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<StaffRecord> getPrimaryKey() {
		return Keys.KEY_STAFF_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<StaffRecord>> getKeys() {
		return Arrays.<UniqueKey<StaffRecord>>asList(Keys.KEY_STAFF_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<StaffRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<StaffRecord, ?>>asList(Keys.FK_RETAIL_OUTLET_HAS_EMPLOYEE_RETAIL_OUTLET1, Keys.FK_RETAIL_OUTLET_HAS_EMPLOYEE_EMPLOYEE1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Staff as(String alias) {
		return new Staff(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Staff rename(String name) {
		return new Staff(name, null);
	}
}