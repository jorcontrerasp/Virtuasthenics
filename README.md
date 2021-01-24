# Virtuasthenics
Web de gimnasio en la que se podrá tener un seguimiento de entrenamientos y realizar acciones tales como consultar entrenamientos completos, ejercicios en particular, clases, y dietas, registrarse como usuario, añadir entrenamientos a favoritos...

Video explicativo: https://youtu.be/N6-PSzV88c8

La parte pública de la web será básicamente la pantalla en la que se podrán loguear o registrarse en caso de que no tengan cuenta en la web

En cuanto a la parte privada de la web, los usuarios podrán realizar las acciones comentadas con anterioridad (consulta de entrenamientos, ejercicios, etc.)

Tipos de usuarios:
  - Invitado: podrán acceder a la web para consultar entrenamientos pero no podrán almacenar entrenamientos, ejercicios, dietas, etc. en sus favoritos.
  - Cliente: accede a la parte pública y privada de la web y tiene la ventaja de almacenar los entrenamientos que considere.
  - Entrenador/Administrador: usuario que, además de la consulta de entrenamientos, dispone de botones para dar de alta nuevos entrenamientos/ejercicios a la aplicación.

Entidades principales:
  - Usuario.
  - Entrenador.
  - Entrenamiento.
  - Ejercicio.
  - Clase.
  - Dieta.
  - Alimento
    
Funcionalidades servicio interno:
  - Envío de mails.
  - Generar PDF de un determinado entrenamiento.
  
Modelo E/R:
<img src="Virtuasthenics/docs/modeloER.png" alt=“aaa” width="1000"/>

Pantallas:
  - Página principal.
  <img src="Virtuasthenics/docs/inicio.png" alt=“aaa” width="1000"/>
  - Clases.
  <img src="Virtuasthenics/docs/actividades.png" alt=“aaa” width="1000"/>
  - Búsqueda de clases.
  <img src="Virtuasthenics/docs/busquedaActividades.png" alt=“aaa” width="1000"/>
  - Entrenamientos.
  <img src="Virtuasthenics/docs/entrenamientos.png" alt=“aaa” width="1000"/>
  - Búsqueda de entrenamientos.
  <img src="Virtuasthenics/docs/busquedaEntrenamientos.png" alt=“aaa” width="1000"/>
  - Búsqueda de ejercicios.
  <img src="Virtuasthenics/docs/busquedaEjercicios.png" alt=“aaa” width="1000"/>
  - Dietas.
  <img src="Virtuasthenics/docs/dietas.png" alt=“aaa” width="1000"/>
  - Búsqueda de dietas.
  <img src="Virtuasthenics/docs/busquedaDietas.png" alt=“aaa” width="1000"/>
  - Búsqueda de alimentos.
  <img src="Virtuasthenics/docs/busquedaAlimentos.png" alt=“aaa” width="1000"/>
  
Diagrama de navegación:
<img src="Virtuasthenics/docs/diagramaNavegacion.png" alt=“aaa” width="1000"/>

<h1>PASOS PARA DESPLEGAR APLICACIÓN EN UNA MÁQUINA VIRTUAL:</h1>
<p>Crear carpeta vagrant.</p>
<p>Acceder a dicha carpeta vagrant mediante el comando cd vagrant.</p>
<p>Generar vagrantfile mediante: vagrant init ubuntu/trusty32.</p>

<p>Descomentar siguiente linea del vagrantfile:</p>
<p># config.vm.network “private_network”, ip: “192.168.33.10”</p>

<p>Levantar máquina virtual mediante: vagrant up.</p>

<p>Accedo a la VM mediante: vagrant ssh.</p>

<p>Instalar Java:</p>
<p>sudo apt-get update</p>
<p>sudo add-apt-repository ppa:openjdk-r/ppa</p>
<p>sudo apt-get update</p>
<p>sudo apt-get install openjdk-8-jdk</p>

<p>Instalar mysql:</p>
<p>sudo apt-get update</p>
<p>sudo apt-get install mysql-server</p>
<p>sudo apt-get update</p>
<p>sudo apt-get install mysql-workbench</p>

<p>Acceder al entorno de mysql:</p>
<p>mysql -h localhost -u root -p</p>
<p>Create database virtuasthenics</p>

<p>Generar .jar del proyecto: mvn package.</p>
<p>Colocarlo en la carpeta vagrant generada anteriormente.</p>
<p>En la máquina virtual acceder a la carpeta compartida /vagrant y navegar hasta la carpeta donde esté el fichero jar de nuestra aplicación</p>
<p>Ejecutar la instrucción Java - Jar virtuasthenics.jar</p>
<p>Abrir con navegador en http://192.168.33.10:8080</p>
<p>Abrir con navegador en https://192.168.33.10:8443 (Spring Security)</p>

<p>Ctrl + c para parar la aplicación</p>

<p>Exit para salir.</p>

<h1>DOCKERIZACIÓN DE LA APP:</h1>
<p>Mediante un terminal acceder a la carpeta donde tenemos el fichero docker-compose.yml junto con el fichero haproxy.cfg y las carpetas de las aplicaciones (app web y servicio interno)</p>

<p>Con Docker arrancado, ejecutar el comando docker-compose up --build</p>

<p>Comenzarán a arrancarse todos los contenedores configurados en el fichero docker-compose.yml</p>

<p>Una vez arrancados se podrá acceder a la aplicación mediante https://localhost o https://localhost:443 </p>

<p>Y se podrán consultar las STATS del balanceador con esta url: localhost:8404/admin?stats</p>

<h1>HAPROXY STATS:</h1>
<p>STATS de haproxy: los dos nodos de la app arrancados</p>
<img src="Virtuasthenics/docs/haproxy_ok.png" alt=“aaa” width="1000"/>

<p>STATS de haproxy: uno de los nodos de la app caído</p>
<img src="Virtuasthenics/docs/haproxy_caido.png" alt=“aaa” width="1000"/>
