CREATE TABLE IF NOT EXISTS Departamento (
idDepartamento INT PRIMARY KEY,
nombreDepartamento VARCHAR (50)
);

CREATE TABLE IF NOT EXISTS Empleados (
idEmpleados INT PRIMARY KEY,
nombre VARCHAR(50),
apellido VARCHAR(50),
idDepartamento INT,
FOREIGN KEY (idDepartamento) REFERENCES Departamento(idDepartamento)
);

USE EmpresaDB;
INSERT INTO Departamento (idDepartamento, nombreDepartamento)
	VALUES(1,'Ventas'),
    (2, 'Marketing'),
    (3, 'Development'),
    (4, 'Gerencia'),
    (5, 'Finanzas'), 
    (6, 'Recursos Humanos');
    
USE EmpresaDB;
INSERT INTO Empleados (idEmpleado, nombre, apellido, idDepartamento)
	VALUES(1, 'Andrey', 'Salamanca', 5),
    (2, 'Estefani', 'Valverde', 4),
    (3, 'Niklas', 'Herrmann', 3), 
    (4, 'Danny', 'Jimenez', 3),
    (5, 'Andrew', 'Lopez', 3), 
    (6, 'Martin', 'Flores', 6),
    (7, 'Guillermo', 'Coto', 2);
    
INSERT INTO Empleados (idEmpleado, nombre, apellido, idDepartamento)
	VALUES(8, 'Mae', 'Tico', NULL),
    (9, 'Jorge', 'Garcia', NULL),
    (12, 'Yerlin', 'Quesada', NULL);

-- Punto A 
SELECT 
	E.idEmpleado,
    E.nombre,
    E.apellido,
    D.nombreDepartamento
FROM 
	Empleados AS E
    LEFT JOIN Departamento AS D ON D.idDepartamento = E.idDepartamento;
    
-- Punto B
SELECT
	D.idDepartamento,
    D.nombreDepartamento,
    COUNT(E.idEmpleado) AS conteoEmpleados
FROM
	Departamentos AS D
    LEFT JOIN Empleados AS E ON E.idDepartamento = D.idDepartamento
GROUP BY
	D.idDepartamento,
    D.nombreDepartamento; 

-- Punto C
SELECT 
	E.idEmpleado,
    E.nombre,
    E.apellido,
    D.nombreDepartamento
FROM 
	Empleados AS E
    LEFT JOIN Departamento AS D ON D.idDepartamento = E.idDepartamento
    
WHERE
	E.idDepartamento IS NULL;
    
    
-- Punto D
SELECT 
	E.idEmpleado,
    E.nombre,
    E.apellido,
    D.nombreDepartamento
FROM 
	Empleados AS E
    RIGHT JOIN Departamento AS D ON D.idDepartamento = E.idDepartamento;
    
-- Punto E - Cambiamos Ventas por Development porque nadie esta en Ventas
SELECT 
	E.idEmpleado,
    E.nombre,
    E.apellido,
    D.nombreDepartamento
FROM 
	Empleados AS E
    LEFT JOIN Departamento AS D ON D.idDepartamento = E.idDepartamento
WHERE
	D.nombreDepartamento = 'Development';
    
-- Punto F - Cambiamos Ventas por Development porque nadie esta en Ventas
SELECT 
	E.idEmpleado,
    E.nombre,
    E.apellido,
    D.nombreDepartamento
FROM 
	Empleados AS E
    LEFT JOIN Departamento AS D ON D.idDepartamento = E.idDepartamento
WHERE
	D.nombreDepartamento = 'Gerencia' OR E.idDepartamento IS NULL;

