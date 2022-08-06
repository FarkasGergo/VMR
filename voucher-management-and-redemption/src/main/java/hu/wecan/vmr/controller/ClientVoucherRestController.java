package hu.wecan.vmr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.wecan.vmr.service.IVoucherService;
import hu.wecan.vmr.util.exception.BusinessException;
import hu.wecan.vmr.util.exception.ResourceNotFoundException;
import hu.wecan.vmr.util.exception.TechnicalException;
import hu.wecan.vmr.model.dto.VoucherDto;

@RestController
@RequestMapping("/vmr/client/v1")
public class ClientVoucherRestController {

	@Autowired
	private IVoucherService voucherService;

	@PostMapping("/vouchers/{id}")
	public ResponseEntity<VoucherDto> redeemVoucher(@PathVariable(value = "id") Long voucherId)
			throws ResourceNotFoundException, BusinessException, TechnicalException {

		VoucherDto dto = voucherService.redeemVoucher(voucherId);

		return ResponseEntity.ok().body(dto);
	}
}
