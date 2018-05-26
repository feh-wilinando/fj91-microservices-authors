post:
	@ curl -X POST -H "Content-Type: application/json" -d '{"name":"$(author)"}' http://localhost:8080/authors -i

get:
ifdef id
	@ curl http://localhost:8080/authors/$(id)
else
	@ curl http://localhost:8080/authors
endif

package:
	@ docker rmi microservice-authors
	@ echo "Packaging authors project"
	@ mvn clean package && rm -f authors.out

run: package
	@ echo "Start authors project"
	@ nohup java -jar target/*.jar &> authors.out&

stop:
	@ echo "Stop authors project"
	@ jps | grep "authors-.*\.jar" | cut -f 1 -d ' ' | xargs kill -9


up: package
	@ docker-compose up -d
	@ sleep 10

down:
	@ docker-compose stop && docker-compose rm -vf