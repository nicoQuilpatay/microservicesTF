🧱 Microservicios: Product y Orders
Este proyecto contiene dos microservicios desarrollados con Spring Boot: Product y Orders. Ambos servicios se comunican entre sí para gestionar productos y órdenes, asegurando que no se creen órdenes si no hay stock disponible.

📋 Requisitos
Java 17+

PostgreSQL (instalación local o Docker)

Docker (opcional, para base de datos, nossotros usamos docker)

🚀 Configuración
1. Clonar el repositorio
(https://github.com/nicoQuilpatay/microservicesTF.git)
2. Iniciar el Docker Compose (verificar que no se repita el nombre de las bases de datos con proyectos de otros alumnos)

3.Ejecutar los Microservicios

✅ Microservicio Product
Documentacion Swagger disponible en:
 (http://localhost:8080/swagger-ui/index.html#/)
 Usuario: user, contraseña: 1234

✅ Microservicio Orders
Documentacion Swagger disponible en:
 http://localhost:8084/swagger-ui/index.html#/
  Usuario: user, contraseña: 1234


📌 Uso
🛒 Product Service
Permite crear, consultar, modificar y eliminar productos.


📦 Orders Service
Permite crear órdenes solo si hay stock suficiente. Consulta el stock en Product Service antes de crear la orden.
Antes de crear una orden debe se debe crear previamente un producto y especificar su stock.
EJEMPLO PARA CREAR ORDEN:
<br>
[
  {
    "id": 3,
    "name": "Pedido de Julian",
    "productId": 3, (Aqui se especifica el Id de producto prewviamente creado para verificar si su stock es sufuciente)
    "stock": 6 (Esto se compara con el stock del producto antes de crear la orden)
  }
]


💫Comunicación entre microservicios
Para que el microservicio de Orders se comunique con el microservicio de Products, se utilizó RestTemplate, una herramienta que permite hacer peticiones HTTP desde Java.

Cuando se intenta crear una orden, el servicio de órdenes consulta al servicio de productos mediante una URL como:


http://localhost:8080/api/products/{id}
Esto sirve para:

Verificar si el producto existe.

Comprobar si tiene stock suficiente.

Si el producto no existe o no tiene stock, la orden no se crea y se devuelve un mensaje de error.

🔔 Ambos microservicios deben estar corriendo antes de crear órdenes.

Ambos microservicios implementan autenticación básica.

El proyecto sigue arquitectura MVC, incluye validaciones en DTOs, documentación Swagger externa, y separación en capas.

Echo por Nicolas Quilpatay y Martin Gonzalez

