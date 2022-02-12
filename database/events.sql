#event on payment
DROP EVENT IF EXISTS payment_event;
CREATE EVENT payment_event
  ON SCHEDULE
	EVERY 2 MINUTE
    STARTS '2022-02-08 16:58:00'  
    ON COMPLETION PRESERVE
  DO
	UPDATE
		internetprovider.bankaccounts
	INNER JOIN(	
		SELECT tariffs.id as id, tariffs.price as price, ifnull(specialoffers.discount,0) as discount 
		FROM internetprovider.tariffs 
		LEFT JOIN internetprovider.specialoffers 
		ON tariffs.special_offers_id=specialoffers.id) as pr
	ON  bankaccounts.tariffs_id=pr.id
	SET	balance=balance-price*(100-discount)/100;