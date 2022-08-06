INSERT INTO voucher (name, limitation_type, remaining_redemption, redemption_limit, deadline)
VALUES ('test_voucher_limited', 'LIMITED', 3, 4, '2023-08-10');

INSERT INTO voucher (name, limitation_type, remaining_redemption, redemption_limit, deadline)
VALUES ('test_voucher_unlimited', 'UNLIMITED', 0, 0, '2023-08-01');

INSERT INTO voucher (name, limitation_type, remaining_redemption, redemption_limit, deadline)
VALUES ('test_voucher_limited_expired', 'LIMITED', 1, 1, '2022-01-01');

INSERT INTO voucher (name, limitation_type, remaining_redemption, redemption_limit, deadline)
VALUES ('test_voucher_unlimited_expired', 'UNLIMITED', 1, 1, '2022-01-01');