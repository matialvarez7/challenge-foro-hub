ALTER TABLE topicos
ADD COLUMN fecha_modificacion DATETIME NOT NULL DEFAULT NOW();

UPDATE topicos
SET fecha_modificacion = NOW();

