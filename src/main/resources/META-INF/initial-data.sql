
INSERT INTO USER_ (identificacion, tipo_identificacion, password, rol)
VALUES ('1101234567', 'CEDULA', 'admin123', 'SECRETARIA');

-- Insertar un Doctor de prueba
INSERT INTO usuarios (identificacion, tipo_identificacion, password, rol)
VALUES ('1107654321', 'CEDULA', 'doc123', 'DOCTOR');

-- Insertar un Paciente de prueba
INSERT INTO usuarios (identificacion, tipo_identificacion, password, rol)
VALUES ('1109998887', 'CEDULA', 'pac123', 'PACIENTE');

COMMIT;