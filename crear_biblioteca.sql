DROP DATABASE IF EXISTS biblioteca1;
CREATE DATABASE biblioteca1
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
USE biblioteca1;

CREATE TABLE persona (
    cedula      INT(11)         NOT NULL,
    nombre      VARCHAR(30)     NOT NULL,
    email       VARCHAR(30)     NULL,
    PRIMARY KEY (cedula)
) ENGINE=InnoDB;

CREATE TABLE telefonos (
    cedulaPersona   INT(11)     NOT NULL,
    telefono        INT(11)     NOT NULL,
    PRIMARY KEY (cedulaPersona, telefono),
    CONSTRAINT fk_telefonos_persona
        FOREIGN KEY (cedulaPersona)
        REFERENCES persona(cedula)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE socios (
    cedula      INT(11)         NOT NULL,
    sancionado  TINYINT(1)      NOT NULL DEFAULT 0,
    PRIMARY KEY (cedula),
    CONSTRAINT fk_socios_persona
        FOREIGN KEY (cedula)
        REFERENCES persona(cedula)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE empleados (
    cedula      INT(11)         NOT NULL,
    sueldo      INT(11)         NOT NULL,
    clave       VARCHAR(30)     NOT NULL,
    PRIMARY KEY (cedula),
    CONSTRAINT fk_empleados_persona
        FOREIGN KEY (cedula)
        REFERENCES persona(cedula)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE autores (
    idAutor         INT(11)         NOT NULL AUTO_INCREMENT,
    nombre          VARCHAR(30)     NOT NULL,
    nacionalidad    VARCHAR(30)     NOT NULL,
    PRIMARY KEY (idAutor)
) ENGINE=InnoDB;

CREATE TABLE libros (
    ISBN        VARCHAR(20)     NOT NULL,
    titulo      VARCHAR(30)     NOT NULL,
    idAutor     INT(11)         NOT NULL,
    PRIMARY KEY (ISBN),
    CONSTRAINT fk_libros_autores
        FOREIGN KEY (idAutor)
        REFERENCES autores(idAutor)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE copias (
    idCopia     INT(11)         NOT NULL AUTO_INCREMENT,
    ISBN        VARCHAR(20)     NOT NULL,
    formato     VARCHAR(20)     NOT NULL,
    PRIMARY KEY (idCopia),
    CONSTRAINT fk_copias_libros
        FOREIGN KEY (ISBN)
        REFERENCES libros(ISBN)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE prestamos (
    idPrestamo      INT(11)     NOT NULL AUTO_INCREMENT,
    ciSocio         INT(11)     NOT NULL,
    ciEmpleado      INT(11)     NOT NULL,
    ISBN            VARCHAR(20) NOT NULL,
    idCopia         INT(11)     NOT NULL,
    fechaPrestamo   DATE        NOT NULL,
    fechaDevolucion DATE        NULL,
    PRIMARY KEY (idPrestamo),
    CONSTRAINT fk_prestamos_socio
        FOREIGN KEY (ciSocio)
        REFERENCES socios(cedula)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    CONSTRAINT fk_prestamos_empleado
        FOREIGN KEY (ciEmpleado)
        REFERENCES empleados(cedula)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    CONSTRAINT fk_prestamos_libro
        FOREIGN KEY (ISBN)
        REFERENCES libros(ISBN)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    CONSTRAINT fk_prestamos_copia
        FOREIGN KEY (idCopia)
        REFERENCES copias(idCopia)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE=InnoDB;

SELECT 'Base de datos biblioteca1 creada' AS resultado;
