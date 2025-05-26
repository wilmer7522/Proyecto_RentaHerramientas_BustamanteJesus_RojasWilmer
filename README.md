# Sistema de Alquiler de Herramientas

Este proyecto es una API REST construida con Spring Boot para gestionar el alquiler de herramientas. Permite registrar proveedores, clientes, herramientas, reservas, devoluciones, facturación e inventario.

## 🚀 Características

- Registro de proveedores con rol y contraseña
- Gestión de herramientas por proveedor
- Reservas y aprobaciones de alquiler
- Gestión de devoluciones (con estado y reporte de daños)
- Facturación con desglose detallado
- Inventario actualizado automáticamente
- Filtros para búsqueda de proveedores y herramientas

## 🛠️ Tecnologías utilizadas

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- Spring Web
- PostgreSQL (base de datos relacional)
- Lombok
- Maven

## 📁 Estructura del Proyecto

[estructura.txt](https://github.com/user-attachments/files/20441133/estructura.txt)./
├── HerramientaApplication.java
├── aplication/
│   └── service/
│       ├── AlquilerService.java
│       ├── CategoriaHerramientaService.java
│       ├── ClienteService.java
│       ├── DevolucionService.java
│       ├── FacturaPdfGenerator.java
│       ├── FacturacionService.java
│       ├── HerramientaService.java
│       ├── InventarioService.java
│       ├── PagoService.java
│       ├── ProveedorService.java
│       ├── ReservaService.java
│       └── UsuarioService.java
├── domain/
│   ├── dto/
│   │   ├── AlquilerDTO.java
│   │   ├── ClienteDTO.java
│   │   ├── DetalleAlquilerDTO.java
│   │   ├── DetalleFacturaDTO.java
│   │   ├── DevolucionDTO.java
│   │   ├── FacturacionDTO.java
│   │   ├── HerramientaCantidadDTO.java
│   │   ├── HerramientaDTO.java
│   │   ├── HerramientaInventarioDTO.java
│   │   ├── HerramientaInventarioResumenDTO.java
│   │   ├── HerramientaResponse.java
│   │   ├── InventarioDTO.java
│   │   ├── PagoDTO.java
│   │   ├── ProveedorDTO.java
│   │   ├── ReporteDanoDTO.java
│   │   └── ReservaDTO.java
│   ├── entities/
│   │   ├── Admin.java
│   │   ├── Alquiler.java
│   │   ├── Aprobacion.java
│   │   ├── CategoriaHerramienta.java
│   │   ├── Cliente.java
│   │   ├── DetalleAlquiler.java
│   │   ├── DetalleDevolucion.java
│   │   ├── DetalleDevolucionPorHerramienta.java
│   │   ├── DetalleReserva.java
│   │   ├── Devolucion.java
│   │   ├── EstadoAlquiler.java
│   │   ├── EstadoHerramienta.java
│   │   ├── EstadoPago.java
│   │   ├── EstadoReserva.java
│   │   ├── Facturacion.java
│   │   ├── Herramienta.java
│   │   ├── Inventario.java
│   │   ├── MetodoPago.java
│   │   ├── Notificacion.java
│   │   ├── Pago.java
│   │   ├── Proveedor.java
│   │   ├── ReporteDano.java
│   │   ├── Reserva.java
│   │   ├── Rol.java
│   │   ├── UnidadDevuelta.java
│   │   └── Usuario.java
│   ├── mapper/
│   │   ├── AlquilerMapper.java
│   │   ├── ClienteMapper.java
│   │   ├── DevolucionMapper.java
│   │   ├── FacturacionMapper.java
│   │   ├── HerramientaMapper.java
│   │   ├── PagoMapper.java
│   │   ├── ProveedorMapper.java
│   │   ├── ReporteDanoMapper.java
│   │   └── ReservaMapper.java
│   └── request/
│       ├── AlquilerRequest.java
│       ├── ClienteRequest.java
│       ├── ClienteUpdateRequest.java
│       ├── DevolucionRequest.java
│       ├── EstadoReservaRequest.java
│       ├── FacturacionRequest.java
│       ├── HerramientaInventarioRequest.java
│       ├── HerramientaRequest.java
│       ├── PagoRequest.java
│       ├── ProveedorRequest.java
│       ├── ProveedorUpdateRequest.java
│       ├── ReservaRequest.java
│       └── UsuarioRequest.java
├── estructura.txt
└── infraestructure/
    ├── controllers/
    │   ├── AlquilerController.java
    │   ├── CategoriaHerramientaController.java
    │   ├── ClienteController.java
    │   ├── DevolucionController.java
    │   ├── FacturacionController.java
    │   ├── HerramientaController.java
    │   ├── InventarioController.java
    │   ├── PagoController.java
    │   ├── ProveedorController.java
    │   ├── ReservaController.java
    │   └── UsuarioController.java
    ├── repositories/
    │   ├── alquiler/
    │   │   ├── AlquilerRepository.java
    │   │   └── AlquilerServiceImpl.java
    │   ├── cliente/
    │   │   ├── ClienteRepository.java
    │   │   └── ClienteServiceImpl.java
    │   ├── devolucion/
    │   │   ├── DevolucionRepository.java
    │   │   └── DevolucionServiceImpl.java
    │   ├── facturacion/
    │   │   ├── FacturacionRepository.java
    │   │   └── FacturacionServiceImpl.java
    │   ├── herramienta/
    │   │   ├── CategoriaHerramientaRepository.java
    │   │   └── HerramientaRepository.java
    │   ├── inventario/
    │   │   ├── InventarioRepository.java
    │   │   └── InventarioServiceImpl.java
    │   ├── pago/
    │   │   ├── PagoRepository.java
    │   │   └── PagoServiceImpl.java
    │   ├── proveedor/
    │   │   ├── ProveedorRepository.java
    │   │   └── ProveedorServiceImpl.java
    │   ├── reporteDano/
    │   │   └── ReporteDanoRepository.java
    │   ├── reserva/
    │   │   ├── ReservaRepository.java
    │   │   └── ReservaServiceImpl.java
    │   └── usuario/
    │       ├── LoginRequest.java
    │       ├── LoginResponse.java
    │       ├── RolRepository.java
    │       ├── UsuarioRepository.java
    │       └── UsuarioServiceImpl.java
    ├── security/
    │   └── SecurityConfig.java
    └── utils/
        └── exceptions/
            ├── GlobalExceptionHandler.java
            └── ResourceNotFoundException.java


# Crea una base de datos en PostgreSQL (por ejemplo alquiler_herramientas).


## 📦 Instalación y Ejecución

1. Clona el repositorio:
   
   # Clonar el repositorio
git clone https://github.com/wilmer7522/Proyecto_RentaHerramientas_BustamanteJesus_RojasWilmer.git

# Entrar al directorio del proyecto
cd Proyecto_RentaHerramientas_BustamanteJesus_RojasWilmer

Configura tu archivo application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/alquiler_herramientas
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html




![ER_RentaHerramita](https://github.com/user-attachments/assets/b0d187c3-b551-49f7-a338-5203182b18e5)
