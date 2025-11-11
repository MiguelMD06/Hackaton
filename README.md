Pasos para ejecutar backend
Abrir la aplicación de eclipse y dirigirnos hacia el proyecto, dar clic derecho para desplegar ventana donde encontraremos el “Run as” y elegimos “Spring Boot App”.
O podrías conectarte a la red de USCO Central Plus y colocar esta dirección en la url de tu navegador: 192.168.220.223:8080 y podrás usar el proyecto.
 
Pasos para ejecutar frontend
En nuestro explorador vamos a buscar “localhost:8080/” el cual nos va a desplegar el proyecto, a partir de ahí empezamos a utilizar la página.
Roles
•	Solicitante 
•	Oficina de mantenimiento
•	Jefe programador de mantenimiento
•	Operario
Vistas disponibles
•	actualizacionoperarios.html
•	actualizaciontickets.html
•	creartickets.html
•	indexoficina.html
•	indexoperarios.html
•	login.html
•	mantenimientoAbrir.html
•	mantenimientoValidar.html
•	operarios.html
•	operarios2.html
Flujo recomendado para probar app
1.	Desplegar el repositorio
2.	Ejecutar la el programa con el pgAdmin abierto para que se creen las tablas, seguido con los siguientes INSERTS vamos a ingresar la informacion.

INSERT INTO public.usuarios(user_contrasena, user_nombre)
    VALUES (123456,'Miguel');

    INSERT INTO public.usuarios(user_contrasena, user_nombre)
    VALUES (123456,'Jose');

INSERT INTO public.usuarios(user_contrasena, user_nombre)
    VALUES (123456,'Isabel');

    INSERT INTO public.usuarios(user_contrasena, user_nombre, user_habilidad, user_ubicacion)
    VALUES (123456,'Villalba','aires', 'neiva');

INSERT INTO rol (rol_nombre) 
    VALUES ('ROLE_SOLICITANTE'),('ROLE_ODM'),('ROLE_JODM'),('ROLE_OPERARIO')

INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES (1,1)
INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES (2,2)
INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES (3,3)
INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES (4,4)

INSERT INTO categories (cat_nombre) VALUES ('FALLO'),('CAMBIO'),('LIMPIEZA')
INSERT INTO estados (est_nombre) VALUES ('NUEVO'),('VALIDACION'),('ASIGNADO'),('EJECUCION'),('ESPERA'),('SOLUCIONADO'),('CERRADO')

SELECT * FROM tickets

3. 
