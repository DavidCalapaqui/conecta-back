## INSTRUCCIONES DE INSTALACIÓN

1. Crear una base de datos en postgres llamada conecta-test
2. En el archivo /src/main/resources/application.properties ```spring.datasource.url=jdbc:postgresql://<IP_LOCAL>:5432/conecta-test```  cambiar por la direccion ip local de la máquina (no usar localhost ya que docker tratará de buscar en su propia red este alias)
3. En una terminal y en la raiz del proyecto ejecutar el comando
   ```docker build -t conecta-test .```
4. Una vez creada la imagen, debemos comprobar ejecutando el comando
   ```docker images```
   aquí podremos ver que la imagen ha sido creada y deberá aparecer en una lista así:
   ```
    REPOSITORY     TAG       IMAGE ID       CREATED          SIZE
    conecta-test   latest    27bacfce1142   16 minutes ago   379MB
   ```
5. Ejecutar contenedor usando la imagen con el comando: 
   ```docker run -d --name conecta-cont -p 8080:8080 conecta-test:latest```