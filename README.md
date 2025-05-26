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

![alt text](<Captura de pantalla 2025-05-26 080844.png>)
![alt text](<Captura de pantalla 2025-05-26 080906.png>)
![alt text](<Captura de pantalla 2025-05-26 080937.png>)
![alt text](<Captura de pantalla 2025-05-26 080955.png>)


# Crea una base de datos en PostgreSQL (por ejemplo alquiler_herramientas).


## 📦 Instalación y Ejecución

1. Clona el repositorio:
   
# Clonar el repositorio
git clone 
~~~
https://github.com/wilmer7522/Proyecto_RentaHerramientas_BustamanteJesus_RojasWilmer.git
~~~

# Entrar al directorio del proyecto
~~~
cd Proyecto_RentaHerramientas_BustamanteJesus_RojasWilmer
~~~

Configura tu archivo application.properties

~~~
spring.datasource.url=jdbc:postgresql://localhost:5432/alquiler_herramientas
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
~~~

- Ejecuta el proyecto

Desde tu IDE, ejecuta la clase **HerramientaApplication.java**

- O bien desde la terminal: 
~~~
./mvnw spring-boot:run
~~~

- Si usas PowerShell o CMD y tienes Maven instalado globalmente:
~~~
mvn spring-boot:run
~~~

- Accede a la API

Una vez ejecutado correctamente, abre tu navegador y visita:
~~~
http://localhost:8080/
~~~

Endpoints: 
| Módulo        | Endpoint base     | Descripción                       |
| ------------- | ----------------- | --------------------------------- |
| Proveedores   | /auth/supliers    | Registro y gestión de proveedores |
| Herramientas  | /service/herramientas   | CRUD herramientas por proveedor   |
| Clientes      | /auth/client       | Registro y consulta de clientes   |
| Reservas      | /service/reservas       | Crear y aprobar reservas          |
| Devoluciones  | /service/devolucion   | Registrar devoluciones            |
| Facturación   | /service/facturacion    | Generar y consultar facturas      |
| Inventario    | /service/inventario     | Consulta y gestión de stock       |

Ejemplos de uso de endpoints:

- http://localhost:8080/auth/client
~~~
POST Cliente {
    "correo": "alexfuentes@gmail.com",
    "password": "rojas123!",
    "nombre": "Alexander",
    "apellido": "Rojas",
    "telefono": "3118530131",
    "direccion": "Carrera 4 #3-4, Cúcuta",
    "cedula": "1232315468",
    "idRol":  3
}
~~~
- http://localhost:8080/auth/suppliers
~~~
POST Proveedor {
    "correo": "proveedor@ejemplo.com",
    "password": "ClaveSegura123",
    "nombre": "Proveedor S.A.",
    "telefono": "3001234567",
    "direccion": "Calle 123",
    "nit": "123456789-0"
}
~~~
- http://localhost:8080/service/reserve
~~~
POST Reserva {
  "idCliente": 15,
  "herramientas": [
    {
      "id": 4,
      "cantidad": 2
    },
    {
      "id": 5,
      "cantidad": 5
    }
  ],
  "fechaReserva": "",
  "fechaInicio": "2025-05-23",
  "fechaFin": "2025-05-25",
  "estado_reserva": "PENDIENTE"
}
~~~
- http://localhost:8080/service/reserves/{id}/estado
~~~
PATCH Reserva {

  "estado": "ACEPTADA"
}
~~~
- http://localhost:8080/service/alquiler
~~~
POST Alquiler {
  "idReserva": 11
}
~~~
- http://localhost:8080/service/devolucion
~~~
PATCH Devoluvion {
  "alquilerId": 21,
  "herramientas": [
    {
      "herramientaId": 4,
      "devoluciones": [
        { "danada": true, "descripcion": "Mango partido" },
        { "danada": false, "descripcion": null }
      ]
    },
    {
      "herramientaId": 5,
      "devoluciones": [
        { "danada": false, "descripcion": null },
        { "danada": false, "descripcion": null },
        { "danada": false, "descripcion": null },
        { "danada": false, "descripcion": null },
        { "danada": false, "descripcion": null }
      ]
    }
  ]
}
~~~
- http://localhost:8080/service/pago
~~~
POST Pago {
  "monto": 1,
  "fechaPago": "2025-05-25",
  "estadoPago": "",
  "metodoPago": "TARJETA",
  "alquilerId": 21
}
~~~
- http://localhost:8080/service/factura
~~~
POST Factura {
  "total": 1.0,
  "pagoId": 21,
  "proveedorId": 3
}
~~~
- http://localhost:8080/service/inventario/agregar
~~~
POST Inventario {
  "herramienta": {
    "nombre": "Taladro Eléctrico",
    "descripcion": "Taladro de 500W con velocidad variable",
    "costo_dia": 15000,
    "disponible": true,
    "estadoHerramienta": "BUENA",
    "categoriaId": 1
  },
  "id_proveedor": 3,
  "cantidad": 10
}
~~~


![ER_RentaHerramita](https://github.com/user-attachments/assets/b0d187c3-b551-49f7-a338-5203182b18e5)


### Desarrollado por Jesús Bustamante y Wilmer Rojas
