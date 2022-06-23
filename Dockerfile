#the first line is always from...defines base image
FROM tomcat:8.0-jre8
#Adding info about who crete this image

LABEL maintainer="Kenneth Burke"

#What did do with the WAR file with respect to tomcat on the ec2
ADD target/proj-01-team07.war /usr/local/tomcat/webapps

#The webapps directory contains the app that tomcat serves

#Expose port 8080 from the container
EXPOSE 8080

#CMD instruction specifies what to run when the container is run
#in this case...tomcat server is started by running a shell script
CMD ["catalina.sh","run"]


