mvn clean package
rm -r /usr/local/apache-tomcat-6.0.35/webapps/therapJavaFestWebApp
rm /usr/local/apache-tomcat-6.0.35/webapps/therapJavaFestWebApp.war
cp target/therapJavaFestWebApp.war /usr/local/apache-tomcat-6.0.35/webapps/

/usr/local/apache-tomcat-6.0.35/bin/startup.sh

