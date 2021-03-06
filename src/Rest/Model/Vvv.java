/**
 * This class is generated by jOOQ
 */
package Rest.Model;


import Rest.Model.tables.Address;
import Rest.Model.tables.Carrier;
import Rest.Model.tables.City;
import Rest.Model.tables.Employee;
import Rest.Model.tables.Itinerary;
import Rest.Model.tables.Modal;
import Rest.Model.tables.Oauth;
import Rest.Model.tables.Passenger;
import Rest.Model.tables.Reservation;
import Rest.Model.tables.RetailOutlet;
import Rest.Model.tables.Route;
import Rest.Model.tables.Staff;
import Rest.Model.tables.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class Vvv extends SchemaImpl {

	private static final long serialVersionUID = -755228859;

	/**
	 * The reference instance of <code>vvv</code>
	 */
	public static final Vvv VVV = new Vvv();

	/**
	 * No further instances allowed
	 */
	private Vvv() {
		super("vvv");
	}

	@Override
	public final List<Table<?>> getTables() {
		List result = new ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final List<Table<?>> getTables0() {
		return Arrays.<Table<?>>asList(
			Address.ADDRESS,
			Carrier.CARRIER,
			City.CITY,
			Employee.EMPLOYEE,
			Itinerary.ITINERARY,
			Modal.MODAL,
			Oauth.OAUTH,
			Passenger.PASSENGER,
			Reservation.RESERVATION,
			RetailOutlet.RETAIL_OUTLET,
			Route.ROUTE,
			Staff.STAFF,
			User.USER);
	}
}
