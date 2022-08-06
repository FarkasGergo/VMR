package hu.wecan.vmr.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wecan.vmr.util.exception.BusinessException;
import hu.wecan.vmr.util.exception.TechnicalException;
import hu.wecan.vmr.util.exception.ResourceNotFoundException;
import hu.wecan.vmr.model.Voucher;
import hu.wecan.vmr.model.dto.VoucherDto;
import hu.wecan.vmr.repository.VoucherRepository;

@Service
@Transactional
public class VoucherService implements IVoucherService {

	@Autowired
	private VoucherRepository voucherRepository;

	@Override
	public List<VoucherDto> findAll() {
		return voucherRepository.findAll().stream().map(voucher -> VoucherDto.toDto(voucher))
				.collect(Collectors.toList());
	}

	@Override
	public VoucherDto getVoucherById(long id) throws ResourceNotFoundException {

		Voucher voucher = findVoucherById(id);

		return VoucherDto.toDto(voucher);
	}

	@Override
	public VoucherDto createVoucher(VoucherDto dto) {

		Voucher voucher = new Voucher(dto);

		voucherRepository.save(voucher);

		return VoucherDto.toDto(voucher);
	}

	@Override
	public void deleteVoucher(long id) throws ResourceNotFoundException {

		Voucher voucher = findVoucherById(id);

		voucherRepository.delete(voucher);
	}

	@Override
	public VoucherDto redeemVoucher(long id) throws ResourceNotFoundException, BusinessException, TechnicalException {

		Voucher voucher = findVoucherById(id);

		if (!voucher.isRedeemable()) {
			throw new BusinessException("Voucher is not redeemable. Id: " + voucher.getId());
		}
		
		voucher.redeem();
		
		return VoucherDto.toDto(voucher);
	}

	private Voucher findVoucherById(long id) throws ResourceNotFoundException {
		Voucher voucher = voucherRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Voucher not found for this id: " + id));

		return voucher;
	}

}
