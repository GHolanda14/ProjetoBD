CREATE OR REPLACE FUNCTION Deletar_dependencias_local_func()
RETURNS TRIGGER AS $$
DECLARE
	participaevento CURSOR IS SELECT* 
				  FROM participaevento PE, (SELECT evento_id
							    FROM evento 
							    WHERE id_local = OLD.local_id
							    )
				  E WHERE PE.evento_id  = E.evento_id;
	p int;
BEGIN   
	OPEN participaevento;
	FETCH participaevento into p;
	LOOP
		EXIT WHEN NOT FOUND;
		DELETE FROM participaevento
		WHERE evento_id = p;
		FETCH participaevento into p;
	END LOOP;
	CLOSE participaevento;
	DELETE FROM evento WHERE id_local = OLD.local_id;

	RETURN OLD;
END	
$$
LANGUAGE 'plpgsql';



CREATE TRIGGER Deletar_dependencias_local
BEFORE DELETE
ON local
FOR EACH ROW
EXECUTE PROCEDURE Deletar_dependencias_local_func();

	
