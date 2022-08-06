package hu.wecan.vmr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.wecan.vmr.model.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

}
