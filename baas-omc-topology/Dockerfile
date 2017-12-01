
FROM 10.1.234.164:5000/storm:0.9.6
ADD ./build/libs/baas-omc-topology.jar /topology.jar
COPY  ./build/libs/baas-omc-topology.jar ./
COPY ./conf/yaml.yaml ./
COPY ./script/baas-omc-topology.sh ./
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
ENV PATH $PATH:$JAVA_HOME/bin
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

#RUN chmod 755 ./baas-omc-topology.sh

#RUN cd /etc && rm -f localtime && ln -s  /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

#CMD ["./baas-omc-topology.sh"]