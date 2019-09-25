CREATE OR REPLACE FUNCTION Maximo_um_show_por_dia_participante_func()
RETURNS TRIGGER AS $$
DECLARE
        qtd_show_atual int;
        data_atual DATE;
        aux int;
        
BEGIN   
	SELECT COUNT(*) INTO qtd_show_atual
	FROM participaevento PE
	WHERE PE.participante_id = NEW.participante_id;

	SELECT E.data_evento INTO data_atual
	FROM evento E, participaevento PE
	WHERE PE.participante_id = NEW.participante_id
	AND E.evento_id = PE.evento_id;

	SELECT COUNT(*) INTO aux
	FROM evento E, participaevento PE
	WHERE PE.participante_id = NEW.participante_id
	AND E.evento_id = PE.evento_id
	AND E.data_evento = data_atual;

	IF (qtd_show_atual < 1) AND (aux < 1) THEN
		RETURN NEW;
	ELSE
		RETURN NULL;
	END IF;

END	
$$
LANGUAGE 'plpgsql';


CREATE TRIGGER Maximo_um_show_por_dia_participante
BEFORE INSERT OR UPDATE
ON participaevento
FOR EACH ROW
EXECUTE PROCEDURE Maximo_um_show_por_dia_participante_func();
