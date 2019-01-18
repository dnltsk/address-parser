[![Build Status](https://travis-ci.org/dnltsk/address-parser.svg?branch=master)](https://travis-ci.org/dnltsk/address-parser)
[![codebeat](https://codebeat.co/badges/dc46c31b-9d2d-4b66-a0c1-052743a57d8d)](https://codebeat.co/projects/github-com-dnltsk-address-parser-master)
[![codecov](https://codecov.io/gh/dnltsk/address-parser/branch/master/graph/badge.svg)](https://codecov.io/gh/dnltsk/address-parser)

# Address Parser

## test

`./gradlew clean test`

## build

`./gradlew clean bootJar`

## execute

* simple address: <br>`java -jar build/libs/addressparser-*.jar "Winterallee 3"`
* more complex address: <br>`java -jar build/libs/addressparser-*.jar "Am Bächle 23"`
* french address: <br>`java -jar build/libs/addressparser-*.jar "4, rue de la revolution"`
* usa address: <br>`java -jar build/libs/addressparser-*.jar "200 Broadway Av"`
* spain address: <br>`java -jar build/libs/addressparser-*.jar "Calle Aduana, 29"`