# S-Money API client

[![Join the chat at https://gitter.im/payintech/smoney-java-client](https://badges.gitter.im/payintech/smoney-java-client.svg)](https://gitter.im/payintech/smoney-java-client?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

[![Latest release](https://img.shields.io/badge/latest_release-16.02-orange.svg)](https://github.com/payintech/smoney-java-client/releases)
[![Build](https://img.shields.io/travis-ci/payintech/smoney-java-client.svg?branch=master&style=flat)](https://travis-ci.org/payintech/smoney-java-client)
[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/payintech/smoney-java-client/master/LICENSE)

s-money-client is a Java based library compatible with Android facilitating
the usage of the REST API provided by S-Money ([s-money.fr](http://www.s-money.fr)).
*****



## Questions and issues
The GitHub issue tracker is only for bug reports and feature requests. Anything
else, such as questions for help in using the library, should be posted in
[StackOverflow](http://stackoverflow.com/questions/tagged/s-money-client?sort=active)
under tags `s-money-client`, `java` and `android` (if applicable).



## Build the library
To compile s-money-client, you must ensure that Java 7 and Maven are correctly
installed and are functional.

    #> mvn compile
    #> mvn package -DskipTests=true



## How to run unit tests?
The API provided by S-Money requires the use of an authentication token.
Therefore, before you can run the unit tests, it is necessary to provide this
token by creating a configuration file.

You will find below the model of `smoney.properties` configuration file that
is to be created in the `src/test/resources` directory.

    ##
    ## S-Money properties file
    ## ~~~~~
    ## Filename: smoney.properties
    ##
    smoney.api.endpoint      = https://rest-pp.s-money.fr/api/sandbox/
    smoney.api.token         = <YOUR_SMONEY_ACCESS_TOKEN>
    smoney.transport.timeout = 15

After you are sure that the configuration is valid, you can run the unit tests
with the following command:

    #> mvn test

Due to S-Money sandbox servers performance and depending of your network speed,
is it possible that some tests timeout. To avoid this issue, you can rise up
the timeout value from 15 to a greater value.



## Add s-money-client to your project

### Gradle

    dependencies {
        compile 'com.payintech:s-money-client:16.02'
    }


### Maven

    <dependency>
        <groupId>com.payintech</groupId>
        <artifactId>s-money-client</artifactId>
        <version>16.02</version>
    </dependency>


### SBT

    libraryDependencies += "com.payintech" % "s-money-client" % "16.02"



## Usage

### Synchronous call

```java
final SMoneyService service = SMoneyServiceFactory.createService();
final Call<List<UserEntity>> listCall = service.listUsers();
final Response<List<UserEntity>> response = listCall.execute();

if (response.code() == 200) {
    for (final UserEntity user : response.body()) {
        System.out.println(user.AppUserId);
    }
}
```


### Asynchronous call

```java
final SMoneyService service = SMoneyServiceFactory.createService();
final Call<List<UserEntity>> listCall = service.listUsers();

listCall.enqueue(new Callback<List<UserEntity>>() {

    @Override
    public void onResponse(final Response<List<UserEntity>> response, final Retrofit retrofit) {
        if (response.code() == 200) {
            for (final UserEntity user : response.body()) {
                System.out.println(user.AppUserId);
            }
        }
    }

    @Override
    public void onFailure(final Throwable t) {
        t.printStackTrace();
    }
});
```



## License
This project is released under terms of the [MIT license](https://raw.githubusercontent.com/payintech/smoney-java-client/master/LICENSE).
