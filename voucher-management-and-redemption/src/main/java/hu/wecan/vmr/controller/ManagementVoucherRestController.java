package hu.wecan.vmr.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.wecan.vmr.service.IVoucherService;
import hu.wecan.vmr.util.exception.ResourceNotFoundException;
import hu.wecan.vmr.model.dto.VoucherDto;

@RestController
@RequestMapping("/vmr/management/v1")
public class ManagementVoucherRestController {

	@Autowired
	private IVoucherService voucherService;

	@GetMapping("/vouchers")
	public List<VoucherDto> getAllEmployees() {
		return voucherService.findAll();
	}

	@GetMapping("/vouchers/{id}")
	public ResponseEntity<VoucherDto> getVoucherById(@PathVariable(value = "id") Long voucherId)
			throws ResourceNotFoundException {

		VoucherDto dto = voucherService.getVoucherById(voucherId);

		return ResponseEntity.ok().body(dto);
	}

	@PostMapping("/vouchers")
	public ResponseEntity<VoucherDto> createVoucher(@Valid @RequestBody VoucherDto voucher) {

		VoucherDto dto = voucherService.createVoucher(voucher);

		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping("/vouchers/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteVoucher(@PathVariable(value = "id") Long voucherId)
			throws ResourceNotFoundException {

		voucherService.deleteVoucher(voucherId);

		return ResponseEntity.ok().body(Collections.singletonMap("The voucher has been deleted.", Boolean.TRUE));
	}
}
