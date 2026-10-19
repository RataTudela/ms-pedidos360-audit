## 2- ms-audit (`ms-audit`)
# Microservicio de Auditoría (`ms-audit`)

Microservicio encargado de la suscripción pasiva, registro y persistencia histórica de todos los eventos de dominio generados en el sistema Pedidos360.

## REQUISITO CRÍTICO DE DESPLIEGUE

Para la correcta integración con el clúster de mensajería:

1. **Cambio de Rama Obligatorio:**
   Debes cambiarte a la rama `rabbitmq` antes de levantar el contenedor:
   git checkout rabbitmq
Orden de Encendido:
OBLIGATORIAMENTE se debe levantar primero el microservicio ms-orders antes de iniciar este microservicio, ms-orders despliega la red compartida y la infraestructura previa requerida.

Inicio del Servicio
Una vez verificado que ms-orders está en ejecución y que te encuentras en la rama rabbitmq:

docker compose up -d --build
Integración con RabbitMQ (Pub/Sub)
Este microservicio implementa el patrón Publicador/Suscriptor (Pub/Sub):

Exchange de Suscripción: pedidos360.events.topic (Tipo: Topic).

Cola Principal: audit.events.queue.

Patrones de Routing Key escuchados: order.*, catalog.*, #.

Funcionamiento: Escucha de forma desacoplada cualquier evento publicado por los demás microservicios (ej. creación de órdenes, actualización de stock), parsea la traza y la almacena en la base de datos de auditoría.

Manejo de Errores y DLQ:

Aplica 3 reintentos automáticos ante fallos de persistencia.

Ante fallos persistentes, envía un NACK sin requeue, desviando el evento a la cola de mensajes muertos audit.events.dlq (mediante audit.dlx.exchange).
