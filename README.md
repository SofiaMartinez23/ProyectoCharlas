<div align="center">
  <h1 align="center">Chat Estilo WhatsApp con Hilos Cliente-Servidor 👾</h1>
  <img src="https://www.jc-mouse.net/wp-content/uploads/2016/08/cliente_servidor_java.gif" alt="Logo" width="200">
</div>


---

# Descripción

Este proyecto implementa un **chat estilo WhatsApp** utilizando la arquitectura **cliente-servidor**. Utiliza **hilos** para gestionar múltiples clientes conectados simultáneamente y permite el envío y la recepción de mensajes en tiempo real. Los mensajes se distribuyen entre todos los usuarios conectados al servidor.

---

# Funcionalidades

## 🌐 **Arquitectura Cliente-Servidor**

Este chat sigue el modelo clásico **cliente-servidor**, donde:

- El **servidor** actúa como intermediario entre los clientes.
- Los **clientes** se conectan al servidor y pueden enviar y recibir mensajes.

## ⚙️ **Hilos para Gestionar Conexiones**

El servidor utiliza **hilos (threads)** para manejar múltiples conexiones de clientes simultáneamente, lo que permite a los usuarios comunicarse en tiempo real sin bloqueos.

- **Servidor**: Cada vez que un cliente se conecta, se crea un hilo independiente para manejar su comunicación.
- **Cliente**: Cada cliente se conecta al servidor y espera mensajes mientras puede enviar sus propios mensajes en paralelo.

## 💬 **Envío y Recepción de Mensajes**

- Los mensajes enviados por un cliente se reciben en el servidor y se distribuyen a todos los demás clientes conectados.
- Los mensajes se reciben en tiempo real, lo que permite una experiencia similar a la de WhatsApp.
  
## 🔌 **Desconexión de Usuarios**

- Los usuarios pueden desconectarse en cualquier momento. Cuando un cliente se desconecta, el servidor termina el hilo correspondiente y elimina la conexión.

---

# Componentes del Proyecto

### 🖥 **Servidor**
El servidor gestiona las conexiones de todos los clientes y distribuye los mensajes:

1. **Escucha de Conexiones**: Utiliza un socket para escuchar conexiones entrantes.
2. **Manejo de Hilos**: Crea un hilo para cada cliente conectado, gestionando la recepción y envío de mensajes sin bloquear.
3. **Distribución de Mensajes**: Al recibir un mensaje de un cliente, el servidor lo distribuye a todos los demás clientes conectados.
4. **Desconexión**: Cuando un cliente se desconecta, el servidor cierra la conexión y libera el hilo correspondiente.

### 📱 **Cliente**
El cliente tiene una interfaz simple donde los usuarios pueden enviar y recibir mensajes:

1. **Conexión al Servidor**: El cliente se conecta a la IP y puerto del servidor.
2. **Envío de Mensajes**: El usuario puede escribir un mensaje que será enviado al servidor.
3. **Recepción de Mensajes**: El cliente está constantemente escuchando por nuevos mensajes y los muestra en la interfaz.
4. **Desconexión**: El cliente puede desconectarse en cualquier momento, lo que cierra su hilo en el servidor.

---

# Instrucciones de Uso

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/chat-estilo-whatsapp.git
cd chat-estilo-whatsapp
