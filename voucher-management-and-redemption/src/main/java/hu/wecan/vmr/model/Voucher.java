package hu.wecan.vmr.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import hu.wecan.vmr.model.dto.VoucherDto;
import hu.wecan.vmr.util.exception.TechnicalException;

@Entity
@Table(name = "voucher")
public class Voucher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "limitation_type")
	@Enumerated(EnumType.STRING)
	private LimitationType limitation;

	@Column(name = "redemption_limit")
	private Integer redemptionLimit;

	@Column(name = "remaining_redemption")
	private Integer remainingRedemption;

	@Column(name = "deadline")
	private Date deadline;

	public Voucher() {
		super();
	}

	public Voucher(String name, LimitationType limitation, Integer remainingRedemption, Date deadline) {
		super();
		this.name = name;
		this.limitation = limitation;
		this.remainingRedemption = remainingRedemption;
		this.deadline = deadline;
	}

	public Voucher(VoucherDto dto) {
		super();
		this.name = dto.getName();
		this.limitation = LimitationType.valueOf(dto.getLimitation());
		this.redemptionLimit = (LimitationType.UNLIMITED == this.limitation) ? 0 : dto.getRedemptionLimit().intValue();
		this.remainingRedemption = this.redemptionLimit.intValue();
		this.deadline = dto.getDeadline();
	}

	public void redeem() throws TechnicalException {
		if (this.isRedeemable() && LimitationType.LIMITED == this.limitation) {
			this.remainingRedemption = this.remainingRedemption.intValue() - 1;
		}
	}
	
	public boolean isRedeemable() throws TechnicalException {
		
		return !isDeadlineExpired() && !isRedemptionLimitExceeded();
	}

	public boolean isDeadlineExpired() throws TechnicalException {

		if (this.deadline == null) {
			throw new TechnicalException("Deadline is null in voucher. Id: " + this.id);
		}

		Date now = new Date();

		return this.deadline.before(now);
	}

	public boolean isRedemptionLimitExceeded() throws TechnicalException {

		if (this.remainingRedemption == null) {
			throw new TechnicalException("RemainingRedemption is null in voucher. Id: " + this.id);
		}

		return (LimitationType.LIMITED == this.limitation && this.remainingRedemption.intValue() < 1);
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LimitationType getLimitation() {
		return limitation;
	}

	public void setLimitation(LimitationType limitation) {
		this.limitation = limitation;
	}

	public Integer getRedemptionLimit() {
		return redemptionLimit;
	}

	public void setRedemptionLimit(Integer redemptionLimit) {
		this.redemptionLimit = redemptionLimit;
	}

	public Integer getRemainingRedemption() {
		return remainingRedemption;
	}

	public void setRemainingRedemption(Integer remainingRedemption) {
		this.remainingRedemption = remainingRedemption;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Override
	public int hashCode() {
		return Objects.hash(deadline, id, limitation, name, remainingRedemption);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voucher other = (Voucher) obj;
		return Objects.equals(deadline, other.deadline) && id == other.id && limitation == other.limitation
				&& Objects.equals(name, other.name) && Objects.equals(remainingRedemption, other.remainingRedemption);
	}

	@Override
	public String toString() {
		return "Voucher [id=" + id + ", name=" + name + ", limitation=" + limitation + ", remainingRedemption="
				+ remainingRedemption + ", deadline=" + deadline + "]";
	}

}