/**
 * This class is generated by jOOQ
 */
package Rest.Model.tables;


import Rest.Model.Keys;
import Rest.Model.Vvv;
import Rest.Model.tables.records.OauthRecord;

import java.sql.Timestamp;
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
public class Oauth extends TableImpl<OauthRecord> {

	private static final long serialVersionUID = 1093983297;

	/**
	 * The reference instance of <code>vvv.oAuth</code>
	 */
	public static final Oauth OAUTH = new Oauth();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<OauthRecord> getRecordType() {
		return OauthRecord.class;
	}

	/**
	 * The column <code>vvv.oAuth.token</code>.
	 */
	public final TableField<OauthRecord, byte[]> TOKEN = createField("token", org.jooq.impl.SQLDataType.BINARY.length(96).nullable(false), this, "");

	/**
	 * The column <code>vvv.oAuth.last_login_at</code>.
	 */
	public final TableField<OauthRecord, Timestamp> LAST_LOGIN_AT = createField("last_login_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

	/**
	 * The column <code>vvv.oAuth.user_id</code>.
	 */
	public final TableField<OauthRecord, Integer> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>vvv.oAuth</code> table reference
	 */
	public Oauth() {
		this("oAuth", null);
	}

	/**
	 * Create an aliased <code>vvv.oAuth</code> table reference
	 */
	public Oauth(String alias) {
		this(alias, OAUTH);
	}

	private Oauth(String alias, Table<OauthRecord> aliased) {
		this(alias, aliased, null);
	}

	private Oauth(String alias, Table<OauthRecord> aliased, Field<?>[] parameters) {
		super(alias, Vvv.VVV, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<OauthRecord> getPrimaryKey() {
		return Keys.KEY_OAUTH_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<OauthRecord>> getKeys() {
		return Arrays.<UniqueKey<OauthRecord>>asList(Keys.KEY_OAUTH_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<OauthRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<OauthRecord, ?>>asList(Keys.FK_OAUTH_USER1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Oauth as(String alias) {
		return new Oauth(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Oauth rename(String name) {
		return new Oauth(name, null);
	}
}
