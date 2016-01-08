/**
 * This class is generated by jOOQ
 */
package Rest.Model.daoExtended;


import Rest.Model.tables.User;
import Rest.Model.tables.records.UserRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import javax.annotation.Generated;
import java.sql.Timestamp;
import java.util.List;


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
public class UserDao extends DAOImpl<UserRecord, Rest.Model.pojoExtended.User, Integer> {

	/**
	 * Create a new UserDao without any configuration
	 */
	public UserDao() {
		super(User.USER, Rest.Model.pojoExtended.User.class);
	}

	/**
	 * Create a new UserDao with an attached configuration
	 */
	public UserDao(Configuration configuration) {
		super(User.USER, Rest.Model.pojoExtended.User.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Integer getId(Rest.Model.pojoExtended.User object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public List<Rest.Model.pojoExtended.User> fetchById(Integer... values) {
		return fetch(User.USER.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>id = value</code>
	 */
	public Rest.Model.pojoExtended.User fetchOneById(Integer value) {
		return fetchOne(User.USER.ID, value);
	}

	/**
	 * Fetch records that have <code>first_name IN (values)</code>
	 */
	public List<Rest.Model.pojoExtended.User> fetchByFirstName(String... values) {
		return fetch(User.USER.FIRST_NAME, values);
	}

	/**
	 * Fetch records that have <code>last_name IN (values)</code>
	 */
	public List<Rest.Model.pojoExtended.User> fetchByLastName(String... values) {
		return fetch(User.USER.LAST_NAME, values);
	}

	/**
	 * Fetch records that have <code>email IN (values)</code>
	 */
	public List<Rest.Model.pojoExtended.User> fetchByEmail(String... values) {
		return fetch(User.USER.EMAIL, values);
	}

	/**
	 * Fetch a unique record that has <code>email = value</code>
	 */
	public Rest.Model.pojoExtended.User fetchOneByEmail(String value) {
		return fetchOne(User.USER.EMAIL, value);
	}

	/**
	 * Fetch records that have <code>password_hash IN (values)</code>
	 */
	public List<Rest.Model.pojoExtended.User> fetchByPasswordHash(byte[]... values) {
		return fetch(User.USER.PASSWORD_HASH, values);
	}

	/**
	 * Fetch records that have <code>created_at IN (values)</code>
	 */
	public List<Rest.Model.pojoExtended.User> fetchByCreatedAt(Timestamp... values) {
		return fetch(User.USER.CREATED_AT, values);
	}

	/**
	 * Fetch records that have <code>updated_at IN (values)</code>
	 */
	public List<Rest.Model.pojoExtended.User> fetchByUpdatedAt(Timestamp... values) {
		return fetch(User.USER.UPDATED_AT, values);
	}

	/**
	 * Fetch records that have <code>type IN (values)</code>
	 */
	public List<Rest.Model.pojoExtended.User> fetchByType(Byte... values) {
		return fetch(User.USER.TYPE, values);
	}
}
