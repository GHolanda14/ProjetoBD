
CREATE OR REPLACE FUNCTION Deletar_dependencias_participante_func()
RETURNS TRIGGER AS $$
DECLARE
        
	participaevento CURSOR IS SELECT participante_id
				  FROM participaevento PE
				  WHERE PE.participante_id  = OLD.participante_id;
	participante int;
BEGIN   
	OPEN participaevento;
	FETCH participaevento into participante;
	LOOP
		EXIT WHEN NOT FOUND;
		DELETE FROM participaevento
		WHERE participante_id = participante;
		FETCH participaevento into participante;
	END LOOP;
	CLOSE participaevento;

	RETURN OLD;
END	
$$
LANGUAGE 'plpgsql';


CREATE TRIGGER Deletar_dependencias_participante
BEFORE DELETE
ON participante
FOR EACH ROW
EXECUTE PROCEDURE Deletar_dependencias_participante_func();
