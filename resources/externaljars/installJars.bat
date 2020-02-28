mvn install:install-file -Dfile=aaa-util.jar -DgroupId=com.aaa -DartifactId=aaa -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=aaa-restws-security-2.0.jar -DgroupId=com.aaa.restws.security -DartifactId=restws.security -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=aaa-util.jar -DgroupId=com.aaa.util -DartifactId=util -Dversion=1.0 -Dpackaging=jar
REM Adding the other dependencies just in case
mvn install:install-file -Dfile=bcprov-jdk15on-159.jar -DgroupId=org.bouncycastle -DartifactId=bcprov-jdk15on -Dversion=1.59 -Dpackaging=jar
mvn install:install-file -Dfile=commons-lang-2.6.jar -DgroupId=commons-lang -DartifactId=commons-lang -Dversion=2.6 -Dpackaging=jar
pause