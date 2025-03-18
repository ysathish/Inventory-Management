pipeline {
    agent any
    environment {
        IMAGE_NAME = "inventory-management:latest"
        CONTAINER_NAME = "inventory_management_container"
        DB_CONTAINER_NAME = "my-postgres"
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ysathish/Inventory-Management.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Create Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('PostgreSQL Setup') {
            steps {
                sh '''
                docker stop $DB_CONTAINER_NAME || true
                docker rm $DB_CONTAINER_NAME || true
                docker run -d --name $DB_CONTAINER_NAME \
                    -e POSTGRES_DB=inventory \
                    -e POSTGRES_USER=postgres \
                    -e POSTGRES_PASSWORD=postgres \
                    -p 5432:5432 \
                    postgres:latest
                '''
            }
        }

        stage('Stop and Remove Old Container') {
            steps {
                sh '''
                docker stop $CONTAINER_NAME || true
                docker rm $CONTAINER_NAME || true
                '''
            }
        }

        stage('Run Docker Container') {
            steps {
                sh '''
                docker run -d --name $CONTAINER_NAME \
                    --link $DB_CONTAINER_NAME:postgres \
                    -p 8081:8080 \
                    -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/inventory \
                    -e SPRING_DATASOURCE_USERNAME=postgres \
                    -e SPRING_DATASOURCE_PASSWORD=postgres \
                    $IMAGE_NAME
                '''
            }
        }
    }
}
