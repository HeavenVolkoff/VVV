/**
 * This class is generated by jOOQ
 */
package Rest.Model.tables.pojos;


import java.io.Serializable;
import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


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
@Table(name = "credit_card", schema = "vvv", uniqueConstraints = {
	@UniqueConstraint(columnNames = {"user_id", "address_id"})
})
public class CreditCard implements Serializable {

	private static final long serialVersionUID = -979675147;

	private Integer userId;
	private Integer number;
	private String  holderName;
	private Date    expire;
	private Integer addressId;

	public CreditCard() {}

	public CreditCard(CreditCard value) {
		this.userId = value.userId;
		this.number = value.number;
		this.holderName = value.holderName;
		this.expire = value.expire;
		this.addressId = value.addressId;
	}

	public CreditCard(
		Integer userId,
		Integer number,
		String  holderName,
		Date    expire,
		Integer addressId
	) {
		this.userId = userId;
		this.number = number;
		this.holderName = holderName;
		this.expire = expire;
		this.addressId = addressId;
	}

	@Column(name = "user_id", nullable = false, precision = 10)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "number", nullable = false, precision = 10)
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name = "holder_name", nullable = false, length = 255)
	public String getHolderName() {
		return this.holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	@Column(name = "expire", nullable = false)
	public Date getExpire() {
		return this.expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	@Column(name = "address_id", nullable = false, precision = 10)
	public Integer getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("CreditCard (");

		sb.append(userId);
		sb.append(", ").append(number);
		sb.append(", ").append(holderName);
		sb.append(", ").append(expire);
		sb.append(", ").append(addressId);

		sb.append(")");
		return sb.toString();
	}
}
