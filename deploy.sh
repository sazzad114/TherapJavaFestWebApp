mvn clean package
rm /usr/local/apache-tomcat-7.0.29/webapps/ROOT.war
rm -r /usr/local/apache-tomcat-7.0.29/webapps/ROOT
cp target/ROOT.war /usr/local/apache-tomcat-7.0.29/webapps/

/usr/local/apache-tomcat-7.0.29/bin/startup.sh

