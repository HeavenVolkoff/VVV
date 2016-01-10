/**
 * This class is generated by jOOQ
 */
package Rest.Model.tables.daos;


import Rest.Model.tables.Reservation;
import Rest.Model.tables.records.ReservationRecord;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


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
public class ReservationDao extends DAOImpl<ReservationRecord, Rest.Model.tables.pojos.Reservation, Integer> {

	/**
	 * Create a new ReservationDao without any configuration
	 */
	public ReservationDao() {
		super(Reservation.RESERVATION, Rest.Model.tables.pojos.Reservation.class);
	}

	/**
	 * Create a new ReservationDao with an attached configuration
	 */
	public ReservationDao(Configuration configuration) {
		super(Reservation.RESERVATION, Rest.Model.tables.pojos.Reservation.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Integer getId(Rest.Model.tables.pojos.Reservation object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public List<Rest.Model.tables.pojos.Reservation> fetchById(Integer... values) {
		return fetch(Reservation.RESERVATION.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>id = value</code>
	 */
	public Rest.Model.tables.pojos.Reservation fetchOneById(Integer value) {
		return fetchOne(Reservation.RESERVATION.ID, value);
	}

	/**
	 * Fetch records that have <code>date IN (values)</code>
	 */
	public List<Rest.Model.tables.pojos.Reservation> fetchByDate(Timestamp... values) {
		return fetch(Reservation.RESERVATION.DATE, values);
	}

	/**
	 * Fetch records that have <code>status IN (values)</code>
	 */
	public List<Rest.Model.tables.pojos.Reservation> fetchByStatus(Byte... values) {
		return fetch(Reservation.RESERVATION.STATUS, values);
	}

	/**
	 * Fetch records that have <code>value IN (values)</code>
	 */
	public List<Rest.Model.tables.pojos.Reservation> fetchByValue(Double... values) {
		return fetch(Reservation.RESERVATION.VALUE, values);
	}

	/**
	 * Fetch records that have <code>passenger_user_id IN (values)</code>
	 */
	public List<Rest.Model.tables.pojos.Reservation> fetchByPassengerUserId(Integer... values) {
		return fetch(Reservation.RESERVATION.PASSENGER_USER_ID, values);
	}
}