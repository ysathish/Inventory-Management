pipeline {
    agent any
    environment {
        IMAGE_NAME = "inventory-management:latest"
        CONTAINER_NAME = "inventory_management_container"
        DB_CONTAINER_NAME = "inventory_postgres"
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
                docker run -d --name $CONTAINER_NAME -p 8081:8080 $IMAGE_NAME
                '''
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
                    -p 5433:5432 \
                    postgres:latest
                '''
            }
        }
    }
}
