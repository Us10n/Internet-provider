#trigger on update balance column
DROP TRIGGER balance_update_trigger;
DELIMITER |
CREATE TRIGGER balance_update_trigger
BEFORE UPDATE ON internetprovider.bankaccounts
FOR EACH ROW
BEGIN
	IF new.balance<0 then
		BEGIN
			SET new.tariffs_id=NULL;
            UPDATE internetprovider.users as us SET us.status = 'BLOCKED' WHERE us.id=new.users_id AND us.status='VERIFIED';
        END;
	ELSE 
		UPDATE internetprovider.users as us SET us.status = 'VERIFIED' WHERE us.id=new.users_id AND us.status='BLOCKED';
	END IF;
END;
|
DELIMITER ;