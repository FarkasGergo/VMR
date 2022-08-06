package hu.wecan.vmr.model.dto;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import hu.wecan.vmr.model.LimitationType;
import hu.wecan.vmr.model.Voucher;
import hu.wecan.vmr.util.validation.ValueOfEnum;

public class VoucherDto {

	private long id;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@ValueOfEnum(enumClass = LimitationType.class, message = "Valid values: LIMITED, UNLIMITED")
	private String limitation;

	@NotNull(message = "Limit is mandatory")
	@Min(0)
	@Max(1000)
	private Integer redemptionLimit;

	private Integer remainingRedemption;

	@NotNull(message = "Deadline is mandatory")
	@FutureOrPresent
	private Date deadline;

	public static VoucherDto toDto(Voucher voucher) {

		VoucherDto dto = null;

		if (voucher != null) {
			dto = new VoucherDto();

			dto.id = voucher.getId();
			dto.name = voucher.getName();
			dto.limitation = voucher.getLimitation().name();
			dto.redemptionLimit = voucher.getRedemptionLimit().intValue();
			dto.remainingRedemption = voucher.getRemainingRedemption().intValue();
			dto.deadline = voucher.getDeadline();
		}

		return dto;
	}

	public VoucherDto() {
		super();
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

	public String getLimitation() {
		return limitation;
	}

	public void setLimitation(String limitation) {
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
		return Objects.hash(deadline, id, limitation, name, redemptionLimit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoucherDto other = (VoucherDto) obj;
		return Objects.equals(deadline, other.deadline) && id == other.id && limitation == other.limitation
				&& Objects.equals(name, other.name) && Objects.equals(redemptionLimit, other.redemptionLimit);
	}

	@Override
	public String toString() {
		return "VoucherDto [id=" + id + ", name=" + name + ", limitation=" + limitation + ", remainingRedemption="
				+ redemptionLimit + ", deadline=" + deadline + "]";
	}

}
