# Technologie chmurowe

## Prerequisites:

* Maven
* Docker
* Buildx
* QEMU

### Zadanie 1

Kod do zadania znajduje sie w tym repozytorium :)

### Zadanie 2

Plik Dockerfile rowniez znajduje sie w tym repozytorium :))

### Zadanie 3

* W celu zbudowania obrazu nalezy:

``
mvn clean install
``

``
docker build -t technologie-chmurowe:1.0 .
``

* W celu uruchomienia kontenera na podstawie zbudowanego obrazu nalezy:

``
docker run -p 8080:8080 technologie-chmurowe:1.0 .
``

* W celu wydobycia logow ktore wrzucila aplikacja na podstawie cwiczenia 1a nalezy:

``
docker logs ${container_id} | grep TechnologieChmuroweAppInfo
``
, gdzie w miejscu ${container_id} nalezy podac id kontenera ktory zostal utworzony. W celu znalezienia id kontenera nalezy wykonac polecenie: ``docker ps -a`` i
znalezc odpowiedni kontener.

* W celu znalezienia informacji o liczbie warstw z jakich wykonany jest obraz nalezy:

``
docker history ${docker_image_id}
``
, gdzie tym razem ${docker_image_id} to id obrazu, ktory zbudowalismy. W celu znalezienia id obrazu nalezy wykonac polecenie: ``docker images`` i znalezc
odpowiedni obraz.

### Zadanie 4

Po ustawieniu na komputerze srodowiska buildkit wraz z jego wrapperem buildx, a takze instalacji emulatora qemu mozemy przejsc do budowania obrazu aplikacj na
podane architektury sprzetowe. W celu zbudowania takich obrazow i wypushowania ich do repozytorium nalezy:

``
mvn clean install
``

``
docker buildx build -t sgplb/zadanie1:1.0 --platform linux/arm/v7,linux/arm64/v8,linux/amd64 --push .
``

Link do repozytorium DockerHub: https://hub.docker.com/repository/docker/sgplb/zadanie1

# Dodatek 1

Utworzony plik do CI dla GithubActions znajduje sie w tym repozytorium w katalogu .github

Z powodu problemow z zainstalowaniem OpenJDK na architekturze arm v7 pipeline CI nie jest w stanie utworzyc obrazu na ta architekture.

Link do repozytorium Github container registry: https://github.com/users/s1gawron/packages/container/package/technologiechmurowe

# Dodatek 2

### 1.

W celu utworzenia lokalnego DOCKER REGISTRY nalezy:

``
docker run -e REGISTRY_HTTP_ADDR=0.0.0.0:6677 -p 6677:6677 --name local_registry registry:2
``

* W celu pobrania obrazu ubuntu nalezy wykonać polecenie: ``docker pull ubuntu``.
* Nastepnie w celu wrzucenia pobranego obrazu do lokalnego registry nalezy otagowac obraz wykorzystujac adres lokalnego
  registry: ``docker tag ubuntu localhost:6677/ubuntu``.
* Pushowanie obrazu wykonujemy poleceniem: ``docker push localhost:6677/ubuntu``

### 2.

W celu zabezpieczenia lokalnego rejestru obrazow musimy stworzyc uzytkownika (tworzymy katalog dla credentiali, a nastepnie tworzymy uzytkownika):

``
mkdir auth
``

``
docker run --entrypoint htpasswd httpd:2 -Bbn user password > auth/httpasswd
``

Po utworzeniu uzytkownika uruchamiamy zabezpieczony rejestr:

``
docker run -e REGISTRY_HTTP_ADDR=0.0.0.0:6677 -e REGISTRY_AUTH=htpasswd -e REGISTRY_AUTH_HTPASSWD_REALM="Registry Realm" -e REGISTRY_AUTH_HTPASSWD_PATH=/auth/httpasswd -p 6677:6677 -v "${PWD}"/auth:/auth --name local_registry_with_auth registry:2
``

Mozemy juz uzywac lokalnego rejestru. Proba wypushowania obrazu konczy sie bledem 401.

W celu zalogowania sie wykonujemy komende:

``
docker login localhost:6677
``
, gdzie wprowadzamy nazwe uzytkownika i haslo (user, password).

Po poprawnym zalogowaniu proba wypchniecia obrazu konczy sie sukcesem.