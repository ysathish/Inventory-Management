pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'inventory-management-system'
        DOCKER_IMAGE_TAG = 'latest'
        CONTAINER_NAME = 'inventory-container'
        DB_CONTAINER_NAME = 'my-postgres'
        DB_PORT = '5432'
        DB_USER = 'postgres'
        DB_PASSWORD = 'password'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Start PostgreSQL Container') {
            steps {
                script {
                    // Check if DB container exists, if not start one
                    def dbExists = sh(script: "docker ps -a --filter name=$DB_CONTAINER_NAME | grep $DB_CONTAINER_NAME || true", returnStatus: true)
                    if (dbExists != 0) {
                        sh """
                        docker run -d \
                        --name $DB_CONTAINER_NAME \
                        -e POSTGRES_USER=$DB_USER \
                        -e POSTGRES_PASSWORD=$DB_PASSWORD \
                        -p $DB_PORT:5432 \
                        postgres:latest
                        """
                    } else {
                        echo "PostgreSQL container already running."
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG .'
            }
        }

        stage('Stop Existing App Container') {
            steps {
                script {
                    sh """
                    docker ps -q --filter "name=$CONTAINER_NAME" | grep -q . && docker stop $CONTAINER_NAME || echo 'No running container to stop'
                    docker ps -a -q --filter "name=$CONTAINER_NAME" | grep -q . && docker rm $CONTAINER_NAME || echo 'No existing container to remove'
                    """
                }
            }
        }

        stage('Run Docker Container with DB') {
            steps {
                sh """
                docker run -d -p 8081:8081 --name $CONTAINER_NAME \
                --link $DB_CONTAINER_NAME:postgres \
                -e SPRING_DATASOURCE_URL=jdbc:postgresql://$DB_CONTAINER_NAME:$DB_PORT/inventory \
                -e SPRING_DATASOURCE_USERNAME=$postgres \
                -e SPRING_DATASOURCE_PASSWORD=$postgres \
                $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG
                """
            }
        }
    }
}
