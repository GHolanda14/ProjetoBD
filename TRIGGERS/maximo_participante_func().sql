
create or replace FUNCTION maximo_participante_func()
RETURNS TRIGGER AS $$
DECLARE
 qtd_atual integer;
 qtd_permitida integer;
BEGIN
  SELECT COUNT(*) INTO qtd_atual
  FROM participaevento PE
  WHERE PE.evento_id = new.evento_id;

  SELECT E.qtd_participante INTO qtd_permitida
  FROM evento E
  WHERE E.evento_id = new.evento_id;

  if (qtd_atual < qtd_permitida) THEN
       RETURN new;
  else
       RETURN null;
  end if;
END
$$
language'plpgsql';

CREATE TRIGGER maximo_participante
BEFORE INSERT
ON participaevento
FOR EACH ROW
EXECUTE PROCEDURE maximo_participante_func();
