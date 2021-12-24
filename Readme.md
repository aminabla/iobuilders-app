#IOBUILDERS APP

Se ha tratado de implementar los requerimientos solicitados:

API Rest para simular un pequeño banco:

- ~~Registro usuario~~. No se ha implementado. No quería emplear más tiempo y esto me parecía lo más prescindible. Los usuarios están cargados en memoria en la app.(Ver abajo)
- **Creación de cuenta (wallet)**
- **Realización de depósito de dinero**
- **Retirada de dinero**
- **Visualización de cuenta (wallet) --> Balance y movimientos**
- **Transferencia de una cuenta A a una cuenta B**

Puntos a destacar:

- **Arquitectura hexagonal y testing** (Obligatorio)
- Libertad en el stack usado en la prueba, aunque preferiblemente algún lenguaje de la JVM, **Java**, Groovy o  haciendo uso de **Spring**, Micronaut o Quarkus.
- **Conceptos DDD**. He intentado incorporarlos aunque no tenía conocimientos previos
- **Base de datos** (puedes usar una base de datos simulada en memoria, **H2 en memoria**, etc)

El fichero **wallet_openapi.yaml** contiene la definición del api para poder ser importada en Postman o una herramienta similar

El API está securizada utilizando autenticación 'basic'. Para acceder al API hay que utilizar los datos usuario/pwd
- user01/user01
- user02/user02

