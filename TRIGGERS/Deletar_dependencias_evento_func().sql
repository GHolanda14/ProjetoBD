
create OR REPLACE FUNCTION Deletar_dependencias_evento_func()
RETURNS TRIGGER AS $$
DECLARE
        
	participaevento CURSOR IS SELECT evento_id
				  FROM participaevento PE
				  WHERE PE.evento_id  = OLD.evento_id;
	evento int;
BEGIN   
	OPEN participaevento;
	FETCH participaevento into evento;
	LOOP
		EXIT WHEN NOT FOUND;
		DELETE FROM participaevento
		WHERE evento_id = evento;
		FETCH participaevento into evento;
	END LOOP;
	CLOSE participaevento;

	RETURN OLD;
END	
$$
LANGUAGE 'plpgsql';


CREATE TRIGGER Deletar_dependencias_evento
BEFORE DELETE
ON evento
FOR EACH ROW
EXECUTE PROCEDURE Deletar_dependencias_evento_func();