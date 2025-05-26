-- Insertar roles
INSERT INTO rol (id, nombre) VALUES 
(1, 'ADMINISTRADOR'),
(2, 'PROVEEDOR'),
(3, 'CLIENTE');

-- Insertar usuarios administradores (nombres reales)
INSERT INTO usuario (correo, password, id_rol) VALUES
('juan.gomez@maquinalquiler.com', 'Jg2024*Secure', 1),
('marta.rodriguez@maquinalquiler.com', 'Mr2024*Secure', 1);

-- Insertar usuarios proveedores (empresas realistas)
INSERT INTO usuario (correo, password, id_rol) VALUES
('contacto@herramientasnorte.com', 'Hnorte2024!', 2),
('ventas@alquitools.com.co', 'Alqui2024$', 2),
('info@proequipos.com', 'ProEq2024#', 2),
('soporte@rentamaq.co', 'Renta2024&', 2),
('admin@maquinarialigera.com', 'MaLig2024*', 2),
('servicio@ferreteriaindustrial.com', 'FerInd2024!', 2),
('contacto@andamiosbogota.com', 'AndBog2024$', 2),
('ventas@construherramientas.com', 'ConHer2024#', 2),
('info@alquilerdeequipos.co', 'AlqEq2024&', 2),
('soporte@maquinariapesada.com', 'MaqPes2024*', 2);

-- Insertar usuarios clientes (personas realistas)
INSERT INTO usuario (correo, password, id_rol) VALUES
('carlos.martinez@constructoralba.com', 'Cm2024!', 3),
('maria.lopez@ingecivil.com', 'Ml2024$', 3),
('jorge.ramirez@obrasurbanas.com', 'Jr2024#', 3),
('ana.gonzalez@arquitecturaydiseno.com', 'Ag2024&', 3),
('luis.fernandez@construccionesnova.com', 'Lf2024*', 3),
('sofia.torres@ingenieriaintegral.com', 'St2024!', 3),
('david.gomez@proyectosedificios.com', 'Dg2024$', 3),
('laura.sanchez@disenoyconstruccion.com', 'Ls2024#', 3),
('pedro.diaz@obrasciviles.com', 'Pd2024&', 3),
('isabel.castro@construccionesverdes.com', 'Ic2024*', 3);

-- Insertar datos de proveedores (empresas reales de Colombia)
INSERT INTO proveedor (id, nombre, telefono, direccion, nit) VALUES
(3, 'Herramientas Norte SAS', '6012345678', 'Calle 100 #15-20, Bogotá', '830012345-1'),
(4, 'AlquiTools Colombia', '6045678901', 'Cra 45 #80-10, Medellín', '830078901-2'),
(5, 'ProEquipos Ltda.', '6023456789', 'Av. 6N #15-25, Cali', '830045678-3'),
(6, 'Rentamaq S.A.', '6056789012', 'Calle 72 #10-15, Barranquilla', '830056789-4'),
(7, 'Maquinaria Ligera de Colombia', '6019876543', 'Cra 50 #22-30, Bogotá', '830098765-5'),
(8, 'Ferretería Industrial del Valle', '6028765432', 'Av. 4 #15-60, Cali', '830087654-6'),
(9, 'Andamios Bogotá SAS', '6017654321', 'Calle 85 #11-20, Bogotá', '830076543-7'),
(10, 'ConstruHerramientas S.A.S.', '6043210987', 'Cra 30 #45-10, Medellín', '830032109-8'),
(11, 'Alquiler de Equipos Ltda.', '6054321098', 'Calle 30 #8-40, Cartagena', '830043210-9'),
(12, 'Maquinaria Pesada Colombia', '6012109876', 'Autopista Norte #120-45, Bogotá', '830021098-0');

-- Insertar datos de clientes (personas realistas colombianas)
INSERT INTO cliente (id, cedula, nombre, apellido, telefono, direccion) VALUES
(13, '1023456789', 'Carlos', 'Martínez', '3101234567', 'Calle 145 #12-30, Bogotá'),
(14, '1034567890', 'María', 'López', '3202345678', 'Cra 80 #45-20, Medellín'),
(15, '1045678901', 'Jorge', 'Ramírez', '3003456789', 'Av. 5N #20-35, Cali'),
(16, '1056789012', 'Ana', 'González', '3154567890', 'Calle 84 #10-15, Barranquilla'),
(17, '1067890123', 'Luis', 'Fernández', '3015678901', 'Cra 1 #10-25, Cartagena'),
(18, '1078901234', 'Sofía', 'Torres', '3176789012', 'Calle 10 #5-40, Bucaramanga'),
(19, '1089012345', 'David', 'Gómez', '3187890123', 'Av. Circunvalar #45-10, Manizales'),
(20, '1090123456', 'Laura', 'Sánchez', '3198901234', 'Cra 15 #20-30, Pereira'),
(21, '1012345678', 'Pedro', 'Díaz', '3129012345', 'Calle 50 #12-45, Ibagué'),
(22, '1023456701', 'Isabel', 'Castro', '3140123456', 'Cra 7 #15-20, Neiva');

INSERT INTO categoria_herramienta (id, nombre, descripcion) VALUES
(1, 'Taladros', 'Herramientas para perforar materiales diversos'),
(2, 'Sierras', 'Herramientas para cortar madera, metal u otros materiales'),
(3, 'Lijadoras y Pulidoras', 'Herramientas para lijado y pulido de superficies'),
(4, 'Compresores', 'Equipos para comprimir aire y alimentar herramientas neumáticas'),
(5, 'Generadores', 'Equipos generadores de electricidad para uso portátil'),
(6, 'Mezcladoras', 'Herramientas para mezclar cemento u otros materiales de construcción'),
(7, 'Cortadoras de Cerámica', 'Herramientas especializadas en cortar azulejos y cerámicas'),
(8, 'Elevadores', 'Equipos hidráulicos para elevación de objetos pesados o personas'),
(9, 'Soldadoras', 'Herramientas para procesos de soldadura eléctrica');

INSERT INTO herramienta (nombre, descripcion, costo_dia, disponible, estado_Herramienta, fotourl, id_categoria) VALUES
('Taladro Inalámbrico', 'Taladro inalámbrico para uso general', 15000, true, 'BUENA', 'https://i.postimg.cc/L5cWjXR7/taladro-inalambrico.jpg', 1),
('Sierra Circular', 'Sierra para cortes precisos en madera', 20000, false, 'DAÑADA', 'https://i.postimg.cc/rs0bdW6x/sierra-circular.jpg', 2),
('Lijadora Orbital', 'Lijadora para acabado fino', 12000, false, 'REPARACION', 'https://i.postimg.cc/y84qZf8d/lijadora-orbital.jpg', 3),
('Martillo Perforador', 'Martillo perforador para trabajos pesados', 18000, true, 'BUENA', 'https://i.postimg.cc/SQPbyQ0c/martillo-perforador.jpg', 1),
('Compresor de Aire', 'Compresor portátil para herramientas neumáticas', 25000, true, 'BUENA', 'https://i.postimg.cc/hGcHCfxR/compresor-aire.jpg', 4),
('Generador Portátil', 'Generador eléctrico para uso en campo', 30000, false, 'DAÑADA', 'https://i.postimg.cc/RVn5qr9y/generador-portatil.jpg', 5),
('Mezcladora de Cemento', 'Mezcladora para obra civil', 22000, true, 'BUENA', 'https://i.postimg.cc/wvBnpZ0W/mezcladora-cemento.jpg', 6),
('Taladro de Banco', 'Taladro de banco para taller', 17000, false, 'REPARACION', 'https://i.postimg.cc/Qtnwxmzj/taladro-banco.jpg', 1),
('Sierra de Cinta', 'Sierra para cortes curvos', 21000, true, 'BUENA', 'https://i.postimg.cc/0NnhhPdM/sierra-cinta.jpg', 2),
('Pulidora', 'Pulidora para superficies metálicas', 16000, false, 'DAÑADA', 'https://i.postimg.cc/FHjMgjYx/pulidora.jpg', 3),
('Lijadora de Banda', 'Lijadora para trabajos pesados', 13000, true, 'BUENA', 'https://i.postimg.cc/G2454YGr/lijadora-banda.jpg', 3),
('Atornillador Eléctrico', 'Atornillador para ensamblajes rápidos', 14000, true, 'BUENA', 'https://i.postimg.cc/QCQwDTRb/atornillador-electrico.jpg', 1),
('Cortadora de Azulejos', 'Herramienta para corte preciso de azulejos', 15000, false, 'REPARACION', 'https://i.postimg.cc/qv5m9wNr/cortadora-azulejos.png', 7),
('Taladro de Impacto', 'Taladro para trabajos difíciles', 19000, true, 'BUENA', 'https://i.postimg.cc/TYhgZ6gy/taladro-impacto.jpg', 1),
('Sierra Recíproca', 'Sierra para cortes de demolición', 20000, false, 'DAÑADA', 'https://i.postimg.cc/jjhDDgy0/sierra-reciproca.jpg', 2),
('Amoladora Angular', 'Herramienta para desbaste y corte', 18000, true, 'BUENA', 'https://i.postimg.cc/Y2YJY5hW/amoladora-angular.jpg', 3),
('Elevador Hidráulico', 'Elevador para trabajos en altura', 30000, true, 'BUENA', 'https://i.postimg.cc/7YTb3YSN/elevador-hidraulico.jpg', 8),
('Soldadora Inverter', 'Soldadora portátil de última generación', 25000, false, 'REPARACION', 'https://i.postimg.cc/bYS2TThb/soldadora-inverter.jpg', 9),
('Martillo Demoledor', 'Herramienta para demolición pesada', 35000, false, 'DAÑADA', 'https://i.postimg.cc/mgLD030m/martillo-demoledor.jpg', 1),
('Compresor Industrial', 'Compresor para grandes trabajos industriales', 40000, true, 'BUENA', 'https://i.postimg.cc/4x0gBzZ7/compresor-industrial.jpg', 4);

--select * from proveedor;


INSERT INTO reserva (id_cliente, fecha_reserva, fecha_inicio, fecha_fin, estado_reserva) VALUES
(13, '2025-01-02 09:00:00', '2025-01-05 10:00:00', '2025-01-10 18:00:00', 'PENDIENTE'),
(14, '2025-01-10 12:30:00', '2025-01-15 09:00:00', '2025-01-20 17:00:00', 'ACEPTADA'),
(15, '2025-01-15 14:00:00', '2025-01-18 08:00:00', '2025-01-22 16:00:00', 'RECHAZADA'),
(16, '2025-01-20 08:00:00', '2025-01-25 09:00:00', '2025-01-30 17:00:00', 'CANCELADA'),
(17, '2025-02-01 10:15:00', '2025-02-05 09:00:00', '2025-02-12 18:00:00', 'PENDIENTE'),
(18, '2025-02-10 11:45:00', '2025-02-15 08:00:00', '2025-02-20 16:00:00', 'ACEPTADA'),
(19, '2025-02-18 09:30:00', '2025-02-22 09:00:00', '2025-02-27 17:00:00', 'RECHAZADA'),
(20, '2025-03-01 10:00:00', '2025-03-05 08:00:00', '2025-03-10 16:00:00', 'CANCELADA'),
(21, '2025-03-10 09:00:00', '2025-03-12 09:00:00', '2025-03-18 18:00:00', 'PENDIENTE'),
(22, '2025-03-15 11:00:00', '2025-03-20 08:00:00', '2025-03-25 17:00:00', 'ACEPTADA');


-- Insertar aprobaciones (solo para reservas ACEPTADAS)
INSERT INTO aprobacion (id, id_reserva, id_proveedor, fecha_aprobacion, estado) VALUES
(1, 1, 3, '2024-03-01', true),
(2, 2, 5, '2024-03-03', true),
(3, 3, 7, '2024-03-05', true),
(4, 4, 9, '2024-03-07', true),
(5, 5, 11, '2024-03-09', true),
(6, 6, 12, '2024-03-12', true),
(7, 7, 10, '2024-03-15', true),
(8, 8, 6, '2024-03-17', true),
(9, 9, 4, '2024-03-20', true),
(10, 10, 8, '2024-03-20', true);

-- Insertar 20 alquileres con sus respectivos detalles
INSERT INTO alquiler (fecha_alquiler, fecha_devolucion, estado_alquiler, id_cliente, id_reserva) VALUES
('2025-01-05 08:00:00', '2025-01-10 18:00:00', 'ACTIVO', 13, 1),
('2025-01-15 09:00:00', '2025-01-20 17:00:00', 'ACTIVO', 14, 2),
('2025-02-05 07:30:00', '2025-02-12 17:30:00', 'ACTIVO', 15, 5),
('2025-02-15 08:00:00', '2025-02-20 16:00:00', 'ACTIVO', 16, 6),
('2025-03-12 09:00:00', '2025-03-18 17:00:00', 'ACTIVO', 17, 9),
('2025-03-20 08:00:00', '2025-03-25 17:00:00', 'ACTIVO', 18, 10),
('2025-04-01 07:00:00', '2025-04-08 18:00:00', 'CANCELADO', 19, 3),
('2025-04-10 08:30:00', '2025-04-15 17:30:00', 'ACTIVO', 20, 4),
('2025-05-05 09:00:00', '2025-05-12 18:00:00', 'ACTIVO', 21, 7),
('2025-05-15 08:00:00', '2025-05-22 17:00:00', 'ACTIVO', 22, 8),
('2025-01-25 08:00:00', '2025-01-30 17:00:00', 'FINALIZADO', 21, 10),
('2025-02-22 09:00:00', '2025-02-27 17:00:00', 'FINALIZADO', 20, 8),
('2025-03-05 08:00:00', '2025-03-10 16:00:00', 'FINALIZADO', 19, 6),
('2025-04-18 07:30:00', '2025-04-25 17:30:00', 'FINALIZADO', 18, 4),
('2025-04-22 08:00:00', '2025-04-29 16:00:00', 'CANCELADO', 17, 2),
('2025-05-19 09:00:00', '2025-05-26 18:00:00', 'FINALIZADO', 15, 1),
('2025-06-01 08:00:00', '2025-06-08 17:00:00', 'FINALIZADO', 14, 3),
('2025-06-10 07:30:00', '2025-06-17 16:30:00', 'FINALIZADO', 13, 5),
('2025-06-15 09:00:00', '2025-06-22 18:00:00', 'FINALIZADO', 22, 7),
('2025-06-20 08:00:00', '2025-06-27 17:00:00', 'FINALIZADO', 21, 9);

-- Insertar 20 pagos asociados a alquileres existentes
INSERT INTO pago (monto, fecha_pago, estado_pago, metodo_pago, id_alquiler) VALUES
-- Pagos completados con tarjeta (6)
(57500, '2025-01-05 08:30:00', 'COMPLETADO', 'TARJETA', 1),
(55000, '2025-01-15 09:15:00', 'COMPLETADO', 'TARJETA', 2),
(74000, '2025-02-05 08:00:00', 'COMPLETADO', 'TARJETA', 3),
(38000, '2025-02-15 08:45:00', 'COMPLETADO', 'TARJETA', 4),
(63000, '2025-03-12 09:30:00', 'COMPLETADO', 'TARJETA', 5),
(54000, '2025-03-20 08:15:00', 'COMPLETADO', 'TARJETA', 6),
-- Pagos completados en efectivo (5)
(60000, '2025-01-25 09:00:00', 'COMPLETADO', 'EFECTIVO', 11),
(58000, '2025-02-22 09:45:00', 'COMPLETADO', 'EFECTIVO', 12),
(45000, '2025-03-05 08:30:00', 'COMPLETADO', 'EFECTIVO', 13),
(82000, '2025-04-18 08:15:00', 'COMPLETADO', 'EFECTIVO', 14),
(78000, '2025-04-22 09:00:00', 'COMPLETADO', 'EFECTIVO', 15),
-- Pagos completados por transferencia (5)
(67000, '2025-04-01 08:45:00', 'COMPLETADO', 'TRANSFERENCIA', 7),
(59000, '2025-04-10 09:30:00', 'COMPLETADO', 'TRANSFERENCIA', 8),
(115000, '2025-05-05 08:15:00', 'COMPLETADO', 'TRANSFERENCIA', 9),
(95000, '2025-05-15 09:00:00', 'COMPLETADO', 'TRANSFERENCIA', 10),
(55000, '2025-05-19 08:45:00', 'COMPLETADO', 'TRANSFERENCIA', 16),
-- Pagos pendientes (4)
(48000, '2025-06-01 09:30:00', 'PENDIENTE', 'TARJETA', 17),
(62000, '2025-06-10 08:15:00', 'PENDIENTE', 'EFECTIVO', 18),
(87000, '2025-06-15 09:00:00', 'PENDIENTE', 'TRANSFERENCIA', 19),
(72000, '2025-06-20 08:45:00', 'PENDIENTE', 'TARJETA', 20);

-- Insertar 20 facturas asociadas a pagos y proveedores existentes
INSERT INTO facturacion (fecha_emision, total, id_pago, id_proveedor, numero_factura) VALUES
-- Facturas para pagos con tarjeta (6)
('2025-01-05 09:00:00', 57500, 1, 3, 'FACT-2025-0001'),
('2025-01-15 09:30:00', 55000, 2, 4, 'FACT-2025-0002'),
('2025-02-05 08:15:00', 74000, 3, 5, 'FACT-2025-0003'),
('2025-02-15 09:00:00', 38000, 4, 6, 'FACT-2025-0004'),
('2025-03-12 10:00:00', 63000, 5, 7, 'FACT-2025-0005'),
('2025-03-20 08:30:00', 54000, 6, 8, 'FACT-2025-0006'),
-- Facturas para pagos en efectivo (5)
('2025-01-25 09:15:00', 60000, 7, 9, 'FACT-2025-0007'),
('2025-02-22 10:00:00', 58000, 8, 10, 'FACT-2025-0008'),
('2025-03-05 08:45:00', 45000, 9, 11, 'FACT-2025-0009'),
('2025-04-18 08:30:00', 82000, 10, 12, 'FACT-2025-0010'),
('2025-04-22 09:15:00', 78000, 11, 3, 'FACT-2025-0011'),
-- Facturas para pagos por transferencia (5)
('2025-04-01 09:00:00', 67000, 12, 4, 'FACT-2025-0012'),
('2025-04-10 09:45:00', 59000, 13, 5, 'FACT-2025-0013'),
('2025-05-05 08:30:00', 115000, 14, 6, 'FACT-2025-0014'),
('2025-05-15 09:15:00', 95000, 15, 7, 'FACT-2025-0015'),
('2025-05-19 09:00:00', 55000, 16, 8, 'FACT-2025-0016'),
-- Facturas para pagos pendientes (4)
('2025-06-01 10:00:00', 48000, 17, 9, 'FACT-2025-0017'),
('2025-06-10 08:30:00', 62000, 18, 10, 'FACT-2025-0018'),
('2025-06-15 09:15:00', 87000, 19, 11, 'FACT-2025-0019'),
('2025-06-20 09:00:00', 72000, 20, 12, 'FACT-2025-0020');

-- Insertar 20 notificaciones para clientes y proveedores
INSERT INTO notificacion (mensaje, fecha, leido, id_cliente, id_proveedor) VALUES
-- Notificaciones no leídas (10)
('Su reserva #1 ha sido aprobada', '2025-01-05 09:15:00', false, 13, 3),
('Su herramienta Martillo Demoledor está disponible para retiro', '2025-01-15 10:30:00', false, 14, 4),
('Recordatorio: su alquiler vence mañana', '2025-02-05 08:45:00', false, 17, 5),
('Nuevas herramientas disponibles para alquiler', '2025-02-15 11:00:00', false, 18, 6),
('Promoción especial: 10% de descuento en su próximo alquiler', '2025-03-12 10:15:00', false, 21, 7),
('Su factura #FACT-2025-0006 ha sido emitida', '2025-03-20 09:30:00', false, 22, 8),
('Actualización: su pago está en proceso', '2025-04-01 14:00:00', false, 13, 9),
('Confirmación de devolución de herramientas', '2025-04-10 16:45:00', false, 14, 10),
('Encuesta de satisfacción: califique su experiencia', '2025-05-05 12:30:00', false, 17, 11),
('Alerta: cambios en nuestros términos de servicio', '2025-05-15 15:15:00', false, 18, 12),
-- Notificaciones leídas (10)
('Bienvenido a nuestro sistema de alquiler', '2025-01-02 08:00:00', true, 13, 3),
('Su reserva #2 ha sido completada', '2025-01-20 17:30:00', true, 14, 4),
('Gracias por su pago #0003', '2025-02-10 13:45:00', true, 15, 5),
('Oferta especial: andamios con 15% de descuento', '2025-02-18 10:20:00', true, 16, 6),
('Recordatorio: devolución de herramientas pendiente', '2025-03-05 09:10:00', true, 19, 7),
('Su reclamo ha sido procesado', '2025-03-22 11:45:00', true, 20, 8),
('Confirmación de renovación de alquiler', '2025-04-05 14:30:00', true, 21, 9),
('Nuevo proveedor agregado a su zona', '2025-04-15 16:20:00', true, 22, 10),
('Actualización de inventario completada', '2025-05-10 12:15:00', true, 13, 11),
('Su cuenta ha sido verificada exitosamente', '2025-05-20 09:45:00', true, 14, 12);

-- Insertar 20 detalles de alquiler con relaciones completas (ahora incluye 'cantidad')
INSERT INTO detalle_alquiler (id_herramienta, id_alquiler, cantidad_dias, cantidad, precio_unitario, subtotal) VALUES
-- Detalles para alquileres activos (10)
(1, 1, 5, 1, 15000, 75000),    -- Taladro Inalámbrico
(3, 1, 5, 1, 12000, 60000),    -- Lijadora Orbital
(5, 2, 5, 1, 25000, 125000),   -- Compresor de Aire
(7, 2, 5, 1, 22000, 110000),   -- Mezcladora de Cemento
(9, 3, 7, 1, 21000, 147000),   -- Sierra de Cinta
(11, 3, 7, 1, 13000, 91000),   -- Lijadora de Banda
(13, 4, 5, 1, 15000, 75000),   -- Cortadora de Azulejos
(15, 4, 5, 1, 20000, 100000),  -- Sierra Recíproca
(17, 5, 6, 1, 30000, 180000),  -- Elevador Hidráulico
(19, 5, 6, 1, 35000, 210000),  -- Martillo Demoledor

-- Detalles para alquileres completados (10)
(2, 6, 5, 1, 20000, 100000),   -- Sierra Circular
(4, 6, 5, 1, 18000, 90000),    -- Martillo Perforador
(6, 7, 7, 1, 30000, 210000),   -- Generador Portátil
(8, 7, 7, 1, 17000, 119000),   -- Taladro de Banco
(10, 8, 5, 1, 16000, 80000),   -- Pulidora
(12, 8, 5, 1, 14000, 70000),   -- Atornillador Eléctrico
(14, 9, 7, 1, 19000, 133000),  -- Taladro de Impacto
(16, 9, 7, 1, 18000, 126000),  -- Amoladora Angular
(18, 10, 5, 1, 25000, 125000), -- Soldadora Inverter
(20, 10, 5, 1, 40000, 200000); -- Compresor Industrial



INSERT INTO devolucion (fecha_devolucion, observaciones, confirmacion_proveedor, id_alquiler) VALUES
('2025-01-15', 'Devolución sin problemas, todo correcto.', true, 1),
('2025-01-18', 'Pequeña abolladura en la carcasa.', false, 2),
('2025-01-20', 'Herramienta entregada en óptimas condiciones.', true, 3),
('2025-02-02', 'Mango roto, necesita reemplazo.', false, 4),
('2025-02-10', 'Sin observaciones, devolución rápida.', true, 5),
('2025-02-15', 'Cable desgastado, requiere mantenimiento.', false, 6),
('2025-03-01', 'Herramienta limpia y funcional.', true, 7),
('2025-03-05', 'Todo en buen estado.', true, 8),
('2025-03-10', 'Falta tornillo, se reporta.', false, 9),
('2025-03-15', 'Devolución sin daños.', true, 10),
('2025-04-01', 'Carcasa con rayones profundos.', false, 11),
('2025-04-05', 'Perfecto estado tras uso.', true, 12),
('2025-04-10', 'Problemas con el sistema de cierre.', false, 13),
('2025-04-15', 'Entrega sin inconvenientes.', true, 14),
('2025-04-20', 'Desgaste en partes móviles.', false, 15),
('2025-04-25', 'Herramienta en buen estado.', true, 16),
('2025-05-01', 'Falla leve en el motor.', false, 17),
('2025-05-05', 'Entrega sin problemas.', true, 18),
('2025-05-10', 'Se detectó daño en el cable.', false, 19),
('2025-05-14', 'Sin observaciones.', true, 20);


INSERT INTO inventario (cantidad_total, cantidad_disponible, id_herramienta, id_proveedor) VALUES
(10, 8, 1, 3),
(15, 15, 2, 4),
(12, 10, 3, 5),
(8, 5, 4, 6),
(20, 19, 5, 7),
(6, 3, 6, 8),
(10, 10, 7, 9),
(9, 6, 8, 10),
(14, 14, 9, 11),
(11, 8, 10, 12),
(5, 2, 11, 10),
(7, 7, 12, 10),
(10, 9, 13, 12),
(13, 13, 14, 7),
(9, 4, 15, 12),
(16, 15, 16, 9),
(18, 17, 17, 5),
(8, 5, 18, 3),
(14, 13, 19, 11),
(10, 9, 20, 12);

INSERT INTO reporte_dano (descripcion, fecha_reporte, id_devolucion, id_herramienta) VALUES
('Abolladura en la carcasa lateral tras caída accidental.', '2025-01-18 15:00:00', 2, 2),
('Mango roto durante el uso intensivo.', '2025-02-02 17:10:00', 4, 6),
('Cable desgastado, presenta chispazos.', '2025-02-15 14:00:00', 6, 10),
('Tornillo faltante en mecanismo de ajuste.', '2025-03-10 08:40:00', 9, 15),
('Rayones profundos por mal uso.', '2025-04-01 13:00:00', 11, 19),
('Problemas con cierre, no mantiene firme.', '2025-04-10 16:00:00', 13, 2),
('Partes móviles desgastadas por sobreuso.', '2025-04-20 17:00:00', 15, 6),
('Motor presenta fallas al encender.', '2025-05-01 15:00:00', 17, 10),
('Cable dañado cerca del conector.', '2025-05-10 14:00:00', 19, 15),
('Ruido extraño al funcionar, posible daño interno.', '2025-03-15 15:00:00', 20, 19);