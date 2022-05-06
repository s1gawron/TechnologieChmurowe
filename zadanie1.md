# Technologie chmurowe

### Prerequisites:

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
, gdzie w miejscu ${container_id} nalezy podac id kontenera ktory zostal utworzony. W celu znalezienia id kontenera nalezy wykonac polecenie: ``docker ps -a`` i znalezc odpowiedni kontener.

* W celu znalezienia informacji o liczbie warstw z jakich wykonany jest obraz nalezy:

``
docker history ${docker_image_id}
``, gdzie tym razem ${docker_image_id} to id obrazu, ktory zbudowalismy. W celu znalezienia id obrazu nalezy wykonac polecenie: ``docker images`` i znalezc odpowiedni obraz.


### Zadanie 4

Po ustawieniu na komputerze srodowiska buildkit wraz z jego wrapperem buildx, a takze instalacji emulatora qemu mozemy przejsc do budowania obrazu aplikacj na podane architektury sprzetowe.
W celu zbudowania takich obrazow i wypushowania ich do repozytorium nalezy:

``
mvn clean install
``

``
docker buildx build -t sgplb/zadanie1:1.0 --platform linux/arm/v7,linux/arm64/v8,linux/amd64 --push .
``

Link do repozytorium DockerHub: https://hub.docker.com/repository/docker/sgplb/zadanie1

# Dodatek 1

Utworzony plik do CI dla GithubActions znajduje sie w tym repozytorium w katalogu .github

Link do repozytorium Github container registry: https://github.com/users/s1gawron/packages/container/package/technologiechmurowe