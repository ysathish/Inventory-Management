pipeline {
    agent any

    environment {
        IMAGE_NAME = 'inventory-management-system-image'
        CONTAINER_NAME = 'inventory-management-system-container'
    }

    stages {
        stage('Checkout') {
            steps {
<<<<<<< HEAD
                git 'your-git-repo-url'
=======
                git 'https://github.com/ysathish/Inventory-Management.git'
>>>>>>> 4562e8bc465a934ff2d3fa66fffc8ff276d6b4c3
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
                script {
                    sh 'docker rm -f $CONTAINER_NAME || true'
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                sh 'docker run -d -p 8080:8080 --name $CONTAINER_NAME $IMAGE_NAME'
            }
        }

        stage('PostgreSQL Setup') {
            steps {
                script {
                    sh 'docker start my-postgres || true'
                }
            }
        }
    }
}
