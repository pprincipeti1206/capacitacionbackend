DROP FUNCTION GET_DATE_LOCALX;
CREATE FUNCTION GET_DATE_LOCALX(numMinutos INTEGER)
RETURNS ex_result TIMESTAMP
AS BEGIN
    declare second_aux integer;
    second_aux = (60*numMinutos) - 18000;

    SELECT ADD_SECONDS (TO_TIMESTAMP (CURRENT_UTCTIMESTAMP), second_aux) "add seconds" into ex_result FROM DUMMY;
    ex_result = ex_result;
END;

DROP FUNCTION GET_DATE_LOCAL_SECONDS;
CREATE FUNCTION GET_DATE_LOCAL_SECONDS(numSegundos INTEGER)
RETURNS ex_result TIMESTAMP
AS BEGIN
    declare second_aux integer;
    second_aux = (numSegundos) - 18000;

    SELECT ADD_SECONDS (TO_TIMESTAMP (CURRENT_UTCTIMESTAMP), second_aux) "add seconds" into ex_result FROM DUMMY;
    ex_result = ex_result;
END;