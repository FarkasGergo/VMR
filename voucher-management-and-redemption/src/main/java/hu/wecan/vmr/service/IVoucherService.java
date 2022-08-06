package hu.wecan.vmr.service;

import java.util.List;

import hu.wecan.vmr.util.exception.BusinessException;
import hu.wecan.vmr.util.exception.ResourceNotFoundException;
import hu.wecan.vmr.util.exception.TechnicalException;
import hu.wecan.vmr.model.dto.VoucherDto;

public interface IVoucherService {
	
	List<VoucherDto> findAll();
	
	VoucherDto getVoucherById(long id) throws ResourceNotFoundException;
	
	VoucherDto createVoucher(VoucherDto dto);
	
	void deleteVoucher(long id) throws ResourceNotFoundException;
	
	VoucherDto redeemVoucher(long id) throws ResourceNotFoundException, BusinessException, TechnicalException;

}
