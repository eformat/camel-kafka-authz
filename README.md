# camel-kafka-authz

## Requirements

- [Apache Maven 3.x](http://maven.apache.org)

## Preparing

Build the project source code

```
cd $PROJECT_ROOT
mvn clean install
```

## Running the example standalone

```
cd $PROJECT_ROOT
mvn spring-boot:run
```

## Running the example in OpenShift

```
oc new-project demo-fuse
oc create -f src/main/kube/alice.yml
oc create -f src/main/kube/bob.yml
oc create -f src/main/kube/serviceaccount.yml
oc create -f src/main/kube/configmap.yml
oc policy add-role-to-user view system:serviceaccount:strimzi:camel-kafka-authz-sa
mvn clean install fabric8:resource fabric8:build fabric8:deploy
```